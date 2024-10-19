#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""
Bean Disease Classifier

This script builds a neural network classifier to distinguish between healthy bean leaves and two types of bean diseases: bean rust and angular leaf spots.
It includes environment setup, data preparation, model definition, training, and visualization of the results.

Requirements:
- TensorFlow 2.x
- Matplotlib
- gdown (for downloading files)
"""

import os
import zipfile
import gdown
import tensorflow as tf
# from tensorflow.keras.preprocessing.image import ImageDataGenerator
from keras.preprocessing.image import ImageDataGenerator
import matplotlib.pyplot as plt

# Function to download and extract dataset
def download_and_extract(url: str, extract_to: str) -> None:
    """
    Download a zip file from the given URL and extract it to the specified directory.
    
    Args:
    - url: URL to the zip file
    - extract_to: Directory where the zip file should be extracted
    """
    # Download the file
    gdown.download(url, '/tmp/temp.zip', quiet=False)
    # Extract the file
    with zipfile.ZipFile('/tmp/temp.zip', 'r') as zip_ref:
        zip_ref.extractall(extract_to)

# URLs for dataset
train_url = "https://storage.googleapis.com/learning-datasets/beans/train.zip"
validation_url = "https://storage.googleapis.com/learning-datasets/beans/validation.zip"
test_url = "https://storage.googleapis.com/learning-datasets/beans/test.zip"

# Directories to extract the datasets
if not os.path.exists('/tmp/train'):
    download_and_extract(train_url, '/tmp/train')
if not os.path.exists('/tmp/validation'):
    download_and_extract(validation_url, '/tmp/validation')
if not os.path.exists('/tmp/test'):
    download_and_extract(test_url, '/tmp/test')

# Define paths and parameters
TRAIN_DIRECTORY_LOCATION = '/tmp/train'
VAL_DIRECTORY_LOCATION = '/tmp/validation'
TARGET_SIZE = (224, 224)  # Size of the images
CLASS_MODE = 'categorical'  # Classification problem

# Prepare data generators
train_datagen = ImageDataGenerator(
    rescale=1.0/255,  # Normalize pixel values
    rotation_range=40,
    width_shift_range=0.2,
    height_shift_range=0.2,
    shear_range=0.2,
    zoom_range=0.2,
    horizontal_flip=True,
    fill_mode='nearest'
)

validation_datagen = ImageDataGenerator(
    rescale=1.0/255  # Normalize pixel values
)

train_generator = train_datagen.flow_from_directory(
    TRAIN_DIRECTORY_LOCATION,
    target_size=TARGET_SIZE,
    batch_size=128,
    class_mode=CLASS_MODE
)

validation_generator = validation_datagen.flow_from_directory(
    VAL_DIRECTORY_LOCATION,
    target_size=TARGET_SIZE,
    batch_size=128,
    class_mode=CLASS_MODE
)

# Define the model
model = tf.keras.models.Sequential([
    tf.keras.layers.Conv2D(32, (3, 3), activation='relu', input_shape=(TARGET_SIZE[0], TARGET_SIZE[1], 3)),
    tf.keras.layers.MaxPooling2D((2, 2)),
    tf.keras.layers.Conv2D(64, (3, 3), activation='relu'),
    tf.keras.layers.MaxPooling2D((2, 2)),
    tf.keras.layers.Conv2D(128, (3, 3), activation='relu'),
    tf.keras.layers.MaxPooling2D((2, 2)),
    tf.keras.layers.Conv2D(128, (3, 3), activation='relu'),
    tf.keras.layers.Flatten(),
    tf.keras.layers.Dense(512, activation='relu'),
    tf.keras.layers.Dense(train_generator.num_classes, activation='softmax')
])

# Model summary
print("Model Summary:")
model.summary()

# Compile the model
LOSS_FUNCTION = 'categorical_crossentropy'
OPTIMIZER = 'adam'

model.compile(
    loss=LOSS_FUNCTION,
    optimizer=OPTIMIZER,
    metrics=['accuracy']
)

# Train the model
NUM_EPOCHS = 20

history = model.fit(
    train_generator,
    epochs=NUM_EPOCHS,
    verbose=1,
    validation_data=validation_generator
)

# Plot training history
plt.plot(history.history['accuracy'], label='Train Accuracy')
plt.plot(history.history['val_accuracy'], label='Validation Accuracy')
plt.title('Model Accuracy')
plt.xlabel('Epoch')
plt.ylabel('Accuracy')
plt.legend(loc='upper left')
plt.xlim([0, NUM_EPOCHS])
plt.ylim([0.4, 1.0])
plt.show()
