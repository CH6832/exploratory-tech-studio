#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""model_conversion_test.py"""

# https://docs.pylint.org/
# pylint: disable=line-too-long, trailing-whitespace, multiple-statements, fixme, locally-disabled

import pytest
import tensorflow as tf

def test_model_conversion():
    # Load the trained model
    model = tf.keras.models.load_model('../../models/version_model.h5')
    
    # Convert the model to TFLite
    converter = tf.lite.TFLiteConverter.from_keras_model(model)
    tflite_model = converter.convert()
    
    # Ensure the TFLite model is not empty
    assert tflite_model, "TFLite model conversion failed."
    
    # Optional: Save the model for manual inspection if needed
    with open('../../models/test_version_model.tflite', 'wb') as f:
        f.write(tflite_model)
    
    # Optionally, test a round-trip of input/output
    interpreter = tf.lite.Interpreter(model_content=tflite_model)
    interpreter.allocate_tensors()
    input_details = interpreter.get_input_details()
    output_details = interpreter.get_output_details()
    
    # Prepare a dummy input and ensure the interpreter runs
    input_data = tf.constant([[0.025, 0.0]], dtype=tf.float32)
    interpreter.set_tensor(input_details[0]['index'], input_data)
    interpreter.invoke()
    output_data = interpreter.get_tensor(output_details[0]['index'])
    
    assert output_data is not None, "Interpreter output is None."

if __name__ == "__main__":
    pytest.main()
