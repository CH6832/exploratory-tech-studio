#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""script.py

This module demonstrates the use of TensorFlow 2 for training and evaluating models on the Fashion MNIST dataset.

It includes:
- Data loading and normalization for training and test datasets.
- Definition of a base model with customizable neurons and additional layers.
- Functions for compiling, training, and evaluating models.
- An experiment function to:
  - Train and evaluate a basic model.
  - Train with increased neurons.
  - Add an additional layer and evaluate.
  - Experiment with non-normalized data.
  - Utilize early stopping to terminate training based on accuracy criteria.

The script ensures compatibility with Python 3 and TensorFlow 2.
"""

# https://docs.pylint.org/
# pylint: disable=line-too-long, trailing-whitespace, multiple-statements, fixme, locally-disabled

import sys
import tensorflow as tf
import keras


# Ensure Python 3 and TensorFlow 2
if sys.version_info.major < 3:
    raise Exception("The script requires Python 3. Current version: %s", str(sys.version_info.major))

if tf.__version__.split('.', maxsplit=1)[0] != '2':
    raise Exception("The script requires TensorFlow 2. Current version: %s", str(tf.__version__))

# Load Fashion MNIST Dataset
mnist = keras.datasets.fashion_mnist
(training_images, training_labels), (test_images, test_labels) = mnist.load_data()

# Normalize the Images
training_images = training_images / 255.0
test_images = test_images / 255.0


def create_model(number_of_neurons=512, additional_layer=None):
    """Create a model."""
    model_layers = [
        keras.layers.Flatten(input_shape=(28, 28)),
        keras.layers.Dense(number_of_neurons, activation=tf.nn.relu)
    ]
    if additional_layer:
        model_layers.append(additional_layer)
    model_layers.append(keras.layers.Dense(10, activation=tf.nn.softmax))

    model = keras.models.Sequential(model_layers)
    
    return model


def compile_and_train_model(model, train_images, train_labels, epochs=5, callbacks=None):
    """Compile and train the model."""
    model.compile(optimizer=keras.optimizers.Adam(),
                  loss='sparse_categorical_crossentropy',
                  metrics=['accuracy'])
    model.fit(train_images, train_labels, epochs=epochs, callbacks=callbacks)
    
    return model


def evaluate_model(model, test_images, test_labels):
    """Evaluate the model."""
    test_loss, test_acc = model.evaluate(test_images, test_labels)
    print(f"Test Loss: {test_loss:.4f}")
    print(f"Test Accuracy: {test_acc:.4f}")
    
    return test_loss, test_acc


def make_predictions(model, test_images):
    """make predictions."""
    predictions = model.predict(test_images)
    
    return predictions


def experiment() -> None:
    """Run experiment."""
    # Initial Model
    print("Training Initial Model...")
    model = create_model()
    model = compile_and_train_model(model, training_images, training_labels)
    evaluate_model(model, test_images, test_labels)

    # Increase Neurons
    print("Training Model with More Neurons...")
    model = create_model(number_of_neurons=1024)
    model = compile_and_train_model(model, training_images, training_labels)
    evaluate_model(model, test_images, test_labels)

    # Add Additional Layer
    print("Training Model with Additional Layer...")
    additional_layer = keras.layers.Dense(256, activation=tf.nn.relu)
    model = create_model(number_of_neurons=512, additional_layer=additional_layer)
    model = compile_and_train_model(model, training_images, training_labels)
    evaluate_model(model, test_images, test_labels)

    # Non-normalized Data
    print("Training Model with Non-normalized Data...")
    (training_images_non, training_labels), (test_images_non, test_labels) = mnist.load_data()
    training_images_non = training_images_non / 255.0
    test_images_non = test_images_non / 255.0
    model = create_model(number_of_neurons=512, additional_layer=additional_layer)
    model = compile_and_train_model(model, training_images_non, training_labels)
    evaluate_model(model, test_images_non, test_labels)

    # Early Stopping Callback
    class EarlyStoppingCallback(keras.callbacks.Callback):
        """
        Class EarlyStoppingCallback
        """

        def on_epoch_end(self, epoch, logs=None) -> None:
            """Check if epoch ends or not here."""
            logs = logs or {}
            if logs.get('accuracy') > 0.95 and epoch >= 2:
                print("Stopping training early as accuracy has reached 95%")
                self.model.stop_training = True

            return None

    callbacks = [EarlyStoppingCallback()]
    print("Training Model with Early Stopping Callback...")
    model = create_model(number_of_neurons=512, additional_layer=additional_layer)
    model = compile_and_train_model(model, training_images, training_labels, callbacks=callbacks)
    evaluate_model(model, test_images, test_labels)

    return None


if __name__ == "__main__":
    experiment()
