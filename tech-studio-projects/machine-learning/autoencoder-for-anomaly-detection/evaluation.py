#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""
Module for visualizing the reconstruction performance of a model.

The `plot_reconstruction` function visualizes the model's reconstruction of input data.
It compares the original data with its reconstruction and highlights the reconstruction error.

Dependencies:
    - Matplotlib
"""

import matplotlib.pyplot as plt

def plot_reconstruction(model, data, title):
    """
    Plot the reconstruction of input data by the model.

    Args:
        model: The trained model with `encoder` and `decoder` methods (a TensorFlow/Keras model).
        data: Input data to be reconstructed (numpy array or tensor).
        title (str): Title of the plot.

    This function generates a plot showing:
        - Original input data (in blue).
        - Reconstructed data (in red).
        - The area between the original and reconstructed data (in light coral) to highlight the reconstruction error.
    """
    # Ensure data is in numpy format for plotting
    data = data.numpy() if hasattr(data, 'numpy') else data
    
    # Encode and decode the data
    encoded_imgs = model.encoder(data).numpy()
    decoded_imgs = model.decoder(encoded_imgs).numpy()
    
    # Plot original and reconstructed data
    plt.figure(figsize=(10, 6))
    plt.plot(data[0], 'b', label="Input")
    plt.plot(decoded_imgs[0], 'r', label="Reconstruction")
    plt.fill_between(range(len(data[0])), data[0], decoded_imgs[0], color='lightcoral', alpha=0.5, label="Error")
    
    # Add labels and title
    plt.xlabel('Sample Index')
    plt.ylabel('Value')
    plt.legend()
    plt.title(title)
    
    # Display the plot
    plt.show()
