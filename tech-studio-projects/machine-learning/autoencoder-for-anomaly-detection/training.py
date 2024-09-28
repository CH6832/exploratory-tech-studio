#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""
This script defines a function to train an autoencoder model using TensorFlow/Keras.

The `train_autoencoder` function takes an autoencoder model and trains it on the provided 
training data while monitoring its performance on validation data. It plots the training and 
validation loss over epochs to visualize the training process.

Dependencies:
    - TensorFlow
    - Matplotlib

Functions:
    - train_autoencoder: Train the autoencoder model and plot training/validation loss.
"""

import matplotlib.pyplot as plt
import tensorflow as tf


def train_autoencoder(model, train_data, test_data, learning_rate=0.01, epochs=50, batch_size=512):
    """Train an autoencoder model and plot training and validation loss.

    Args:
        model: The autoencoder model to be trained (a TensorFlow/Keras model).
        train_data: Data used for training the model (features).
        test_data: Data used for validation (features).
        learning_rate (float): The learning rate for the optimizer. Default is 0.01.
        epochs (int): The number of epochs for training. Default is 50.
        batch_size (int): The batch size used for training. Default is 512.

    Returns:
        model: The trained autoencoder model.

    This function compiles the model with the Adam optimizer and Mean Absolute Error (MAE) loss.
    It then trains the model and plots the training and validation loss curves.
    """
    # Compile the model with Adam optimizer and MAE loss
    optimizer = tf.keras.optimizers.Adam(learning_rate=learning_rate)
    model.compile(optimizer=optimizer, loss='mae')
    
    # Train the model
    history = model.fit(train_data, train_data, epochs=epochs, 
                        batch_size=batch_size, validation_data=(test_data, test_data), shuffle=True)
    
    # Plot training and validation loss
    plt.figure(figsize=(10, 6))
    plt.plot(history.history["loss"], label="Training Loss")
    plt.plot(history.history["val_loss"], label="Validation Loss")
    plt.xlabel("Epoch")
    plt.ylabel("Loss")
    plt.title("Training and Validation Loss")
    plt.legend()
    plt.grid(True)
    plt.show()
    
    return model
