#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""train_model.py"""

# https://docs.pylint.org/
# pylint: disable=line-too-long, trailing-whitespace, multiple-statements, fixme, locally-disabled

import pandas as pd
import tensorflow as tf
import tensorflow_model_optimization as tfmot
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, Dropout, BatchNormalization
from tensorflow.keras.optimizers import Adam
from tensorflow.keras.callbacks import LearningRateScheduler
from sklearn.preprocessing import StandardScaler
from sklearn.model_selection import train_test_split
import numpy as np

# Define parameters
EPOCHS = 100  # Number of epochs for the training.
BATCH_SIZE = 32  # Size of batches used in training.
LEARNING_RATE = 0.001  # Learning rate for the Adam optimizer.
HIDDEN_UNITS_LIST = [8]  # Number of units in each hidden layer.
DROPOUT_RATE = 0.5  # Dropout rate for regularization (0 for no dropout).
L1_REGULARIZATION = 0.0  # L1 regularization strength
L2_REGULARIZATION = 0.01  # L2 regularization strength
INITIAL_SPARSITY = 0.2  # Initial sparsity for pruning
FINAL_SPARSITY = 0.8  # Final sparsity for pruning
BEGIN_STEP = 0  # Beginning step for pruning
END_STEP = np.ceil(5000 / BATCH_SIZE).astype(np.int32) * EPOCHS  # Adjust the value 5000 to your dataset size
OPTIMIZATION_TYPE = 'post_training'  # Options: 'qat' or 'post_training'
ACTIVATION_FUNCTIONS = ['relu']  # Activation functions for hidden layers
USE_BATCH_NORM = True  # Whether to use batch normalization
LEARNING_RATE_SCHEDULER = None  # Options: 'ExponentialDecay', 'PiecewiseConstantDecay', etc.

def build_model():
    """Builds and compiles the model based on the given parameters."""
    model = Sequential()
    input_shape = (2,)

    # Input layer
    for idx, units in enumerate(HIDDEN_UNITS_LIST):
        if idx == 0:
            model.add(Dense(units, activation=ACTIVATION_FUNCTIONS[0], input_shape=input_shape))
        else:
            model.add(Dense(units, activation=ACTIVATION_FUNCTIONS[0]))
        
        if USE_BATCH_NORM:
            model.add(BatchNormalization())
        
        if DROPOUT_RATE > 0:
            model.add(Dropout(DROPOUT_RATE))
    
    model.add(Dense(2))  # Output layer
    
    # Apply L1/L2 regularization if specified
    if L1_REGULARIZATION > 0 or L2_REGULARIZATION > 0:
        for layer in model.layers:
            if isinstance(layer, Dense):
                layer.kernel_regularizer = tf.keras.regularizers.L1L2(l1=L1_REGULARIZATION, l2=L2_REGULARIZATION)
    
    return model

def apply_learning_rate_scheduler(optimizer):
    """Applies learning rate scheduling if specified."""
    if LEARNING_RATE_SCHEDULER == 'ExponentialDecay':
        lr_schedule = tf.keras.optimizers.schedules.ExponentialDecay(
            initial_learning_rate=LEARNING_RATE,
            decay_steps=1000,
            decay_rate=0.9
        )
        optimizer.learning_rate = lr_schedule
    elif LEARNING_RATE_SCHEDULER == 'PiecewiseConstantDecay':
        boundaries = [1000, 2000]
        values = [LEARNING_RATE, LEARNING_RATE * 0.1, LEARNING_RATE * 0.01]
        lr_schedule = tf.keras.optimizers.schedules.PiecewiseConstantDecay(
            boundaries=boundaries,
            values=values
        )
        optimizer.learning_rate = lr_schedule

def main():
    """Driving code"""

    # Load the training data
    train_data = pd.read_csv('../../data/processed/train_versions.csv')
    val_data = pd.read_csv('../../data/processed/val_versions.csv')

    # Split into input and output
    X_train = train_data[['major', 'minor']].values
    y_train = train_data[['major', 'minor']].values
    X_val = val_data[['major', 'minor']].values
    y_val = val_data[['major', 'minor']].values

    # Optional: Standardize the data
    scaler = StandardScaler()
    X_train = scaler.fit_transform(X_train)
    X_val = scaler.transform(X_val)

    # Build the model with pruning
    pruning_schedule = tfmot.sparsity.keras.PolynomialDecay(
        initial_sparsity=INITIAL_SPARSITY,
        final_sparsity=FINAL_SPARSITY,
        begin_step=BEGIN_STEP,
        end_step=END_STEP
    )

    model = build_model()
    pruned_model = tfmot.sparsity.keras.prune_low_magnitude(model, pruning_schedule=pruning_schedule)

    # Apply quantization aware training if specified
    if OPTIMIZATION_TYPE == 'qat':
        qat_model = tfmot.quantization.keras.quantize_model(pruned_model)
        model_to_train = qat_model
    else:
        model_to_train = pruned_model

    # Compile the model
    optimizer = Adam(learning_rate=LEARNING_RATE)
    apply_learning_rate_scheduler(optimizer)
    model_to_train.compile(optimizer=optimizer, loss='mse')

    # Train the model
    callbacks = [tfmot.sparsity.keras.UpdatePruningStep()]
    history = model_to_train.fit(
        X_train, y_train,
        epochs=EPOCHS,
        batch_size=BATCH_SIZE,
        validation_data=(X_val, y_val),
        callbacks=callbacks
    )

    # Save the trained model
    model_to_train.save('../../models/version_model.h5')

    # Optional: Save training history
    history_df = pd.DataFrame(history.history)
    history_df.to_csv('../../processed/training_history.csv', index=False)

    # Post-training quantization if not using QAT
    if OPTIMIZATION_TYPE == 'post_training':
        def apply_post_training_quantization(model):
            converter = tf.lite.TFLiteConverter.from_keras_model(model)
            converter.optimizations = [tf.lite.Optimize.DEFAULT]
            tflite_model = converter.convert()
            return tflite_model

        tflite_model = apply_post_training_quantization(model_to_train)

        # Save the quantized TFLite model
        with open('../../models/version_model_quantized.tflite', 'wb') as f:
            f.write(tflite_model)

if __name__ == "__main__":
    main()
