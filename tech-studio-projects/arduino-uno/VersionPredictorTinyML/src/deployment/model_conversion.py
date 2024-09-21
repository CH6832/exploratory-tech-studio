#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""model_conversion.py"""

# https://docs.pylint.org/
# pylint: disable=line-too-long, trailing-whitespace, multiple-statements, fixme, locally-disabled

import tensorflow as tf
import numpy as np

# Load the trained model
model = tf.keras.models.load_model('../../models/version_model_qat.h5')  # Adjust path if needed

# Define optimization settings
def optimize_model(converter, optimization_type='DEFAULT', representative_dataset=None):
    """Apply optimizations to the TFLite converter."""
    if optimization_type == 'DEFAULT':
        converter.optimizations = [tf.lite.Optimize.DEFAULT]
    elif optimization_type == 'INT8':
        # For int8 quantization, a representative dataset is needed
        converter.optimizations = [tf.lite.Optimize.OPTIMIZE_FOR_SIZE]
        if representative_dataset is not None:
            converter.representative_dataset = representative_dataset
    elif optimization_type == 'FLOAT16':
        converter.optimizations = [tf.lite.Optimize.OPTIMIZE_FOR_LATENCY]
    else:
        raise ValueError(f"Unsupported optimization type: {optimization_type}")

    # Further optimizations can be added here
    return converter

# Create a representative dataset function for int8 quantization
def representative_dataset_gen():
    """Generate a representative dataset for int8 quantization."""
    for _ in range(100):
        yield [np.random.rand(1, 2).astype(np.float32)]  # Adjust shape and dtype as needed

# Convert to TensorFlow Lite model with optimizations
converter = tf.lite.TFLiteConverter.from_keras_model(model)

# Apply optimizations
optimization_type = 'DEFAULT'  # Choose from 'DEFAULT', 'INT8', or 'FLOAT16'
converter = optimize_model(converter, optimization_type, representative_dataset=representative_dataset_gen)

# Convert the model
tflite_model = converter.convert()

# Save the TensorFlow Lite model
with open('../../models/version_model_optimized.tflite', 'wb') as f:
    f.write(tflite_model)

print(f"Model converted and saved to '../../models/version_model_optimized.tflite' with optimization type '{optimization_type}'")
