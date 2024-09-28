#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""
This script provides a function to plot the Receiver Operating Characteristic (ROC) curve 
and calculate the Area Under the Curve (AUC) for a given model.

The function `plot_roc_auc` takes a model, test data, and test labels as input. It computes
the reconstruction loss using Mean Absolute Error (MAE), then calculates and plots the ROC curve
for the model's performance on the test data. The plot includes annotations for selected thresholds
and displays the AUC value.

Dependencies:
    - TensorFlow
    - Matplotlib
    - scikit-learn

Usage:
    Call the `plot_roc_auc` function with a trained model, test data, and test labels.
"""

import matplotlib.pyplot as plt
from sklearn.metrics import roc_curve, auc
import tensorflow as tf

def plot_roc_auc(model, test_data, test_labels):
    """Plot the ROC curve and compute the AUC for a given model on test data.

    This function computes the reconstruction loss, calculates the ROC curve,
    and plots it with annotations for selected thresholds. The AUC is also printed.
    """
    # Compute reconstructions and loss
    reconstructions = model(test_data)
    loss = tf.keras.losses.mean_absolute_error(test_data, reconstructions)

    # Compute ROC curve
    flipped_labels = 1 - test_labels
    fpr, tpr, thresholds = roc_curve(flipped_labels, loss)

    # Plot ROC curve
    plt.figure()
    lw = 2
    plt.plot(fpr, tpr, color='darkorange', lw=lw, label='ROC curve')
    plt.plot([0, 1], [0, 1], color='navy', lw=lw, linestyle='--')
    plt.xlim([0.0, 1.0])
    plt.ylim([0.0, 1.05])
    plt.xlabel('False Positive Rate')
    plt.ylabel('True Positive Rate')
    plt.title('Receiver Operating Characteristic')
    plt.legend(loc="lower right")

    # Annotate selected thresholds
    thresholds_every = 20
    thresholds_length = len(thresholds)
    for i in range(0, thresholds_length, thresholds_every):
        threshold_value_with_max_four_decimals = f"{thresholds[i]:.4f}"
        plt.scatter(fpr[i], tpr[i], c='black')
        plt.text(fpr[i] - 0.03, tpr[i] + 0.005, threshold_value_with_max_four_decimals, 
                 fontdict={'size': 15})

    plt.show()

    # Compute and print AUC
    roc_auc = auc(fpr, tpr)
    print("AUC:", roc_auc)
