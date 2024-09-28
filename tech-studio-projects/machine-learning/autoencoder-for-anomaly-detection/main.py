#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""
Main script for training, evaluating, and analyzing an anomaly detection model.

This script performs the following steps:
1. Loads and prepares data.
2. Builds and trains an autoencoder model.
3. Evaluates the model by plotting reconstructions.
4. Calculates and plots ROC AUC.
5. Selects a threshold, predicts anomalies, and prints performance statistics.

Dependencies:
    - data_preparation (custom module)
    - model (custom module)
    - training (custom module)
    - evaluation (custom module)
    - roc_auc_metrics (custom module)
    - threshold_selection (custom module)
"""

from data_preparation import load_and_prepare_data
from model import AnomalyDetector
from training import train_autoencoder
from evaluation import plot_reconstruction
from roc_auc_metrics import plot_roc_auc
from threshold_selection import predict, print_stats

def main():
    """
    Main function to execute the end-to-end workflow for anomaly detection.
    """
    # Load and prepare data
    url = 'http://storage.googleapis.com/download.tensorflow.org/data/ecg.csv'
    combined_train_data, test_data, train_labels, test_labels, normal_train_data, anomalous_train_data = load_and_prepare_data(url)

    # Build the model
    embedding_size = 16
    autoencoder = AnomalyDetector(embedding_size)

    # Train the model
    autoencoder = train_autoencoder(autoencoder, combined_train_data, test_data)

    # Evaluate the model
    plot_reconstruction(autoencoder, normal_train_data, "Normal ECG Reconstruction")
    plot_reconstruction(autoencoder, anomalous_train_data, "Anomalous ECG Reconstruction")

    # Calculate ROC and AUC
    plot_roc_auc(autoencoder, test_data, test_labels)

    # Select threshold and predict anomalies
    threshold = 0.05
    predictions, scores = predict(autoencoder, test_data, threshold)
    print_stats(predictions, test_labels)

if __name__ == "__main__":
    main()
