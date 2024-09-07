#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""
Module for loading and preparing data for anomaly detection.

This script provides a function to load data from a CSV file, preprocess it, and split it into
training and testing datasets. It also separates the data into normal and anomalous classes
and prepares combined training data for anomaly detection.

Dependencies:
    - Pandas
    - TensorFlow
    - scikit-learn
"""

import pandas as pd
import tensorflow as tf
from sklearn.model_selection import train_test_split

def load_and_prepare_data(url):
    """
    Load and preprocess data from a CSV file, and split it into training and testing sets.

    Args:
        url (str): URL or path to the CSV file containing the dataset.

    Returns:
        tuple: A tuple containing:
            - combined_train_data (tf.Tensor): Combined training data including normal and 
              a portion of anomalous data.
            - test_data (tf.Tensor): Normalized test data.
            - train_labels (numpy.ndarray): Labels for the training data.
            - test_labels (numpy.ndarray): Labels for the test data.
            - normal_train_data (tf.Tensor): Training data samples labeled as normal.
            - anomalous_train_data (tf.Tensor): Training data samples labeled as anomalous.
    """
    # Load data from CSV
    dataframe = pd.read_csv(url, header=None)
    raw_data = dataframe.values
    
    # Split into features and labels
    labels = raw_data[:, -1]
    data = raw_data[:, :-1]
    
    # Split data into training and test sets
    train_data, test_data, train_labels, test_labels = train_test_split(
        data, labels, test_size=0.2, random_state=21
    )
    
    # Normalize data
    min_val = tf.reduce_min(train_data)
    max_val = tf.reduce_max(train_data)
    train_data = (train_data - min_val) / (max_val - min_val)
    test_data = (test_data - min_val) / (max_val - min_val)
    
    # Convert to TensorFlow tensors
    train_data = tf.cast(train_data, tf.float32)
    test_data = tf.cast(test_data, tf.float32)
    
    # Convert labels to boolean
    train_labels = train_labels.astype(bool)
    test_labels = test_labels.astype(bool)
    
    # Separate normal and anomalous data
    normal_train_data = train_data[train_labels]
    anomalous_train_data = train_data[~train_labels]
    normal_test_data = test_data[test_labels]
    anomalous_test_data = test_data[~test_labels]
    
    # Prepare combined training data
    portion_of_anomaly_in_training = 0.1
    end_size = int(len(normal_train_data) / (10 - portion_of_anomaly_in_training * 10))
    combined_train_data = tf.concat([normal_train_data, anomalous_test_data[:end_size]], axis=0)
    
    return combined_train_data, test_data, train_labels, test_labels, normal_train_data, anomalous_train_data
