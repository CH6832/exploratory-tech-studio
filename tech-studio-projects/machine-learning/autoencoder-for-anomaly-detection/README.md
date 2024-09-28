# Autoencoders for Anomaly Detection

This project demonstrates the use of autoencoders for anomaly detection on the ECG5000 dataset. The autoencoder is trained to detect anomalies in ECG signals by reconstructing the input data and calculating the reconstruction error.

## Table of Contents

- [Autoencoders for Anomaly Detection](#autoencoders-for-anomaly-detection)
  - [Table of Contents](#table-of-contents)
  - [Introduction](#introduction)
  - [Setup](#setup)
  - [Data Preparation](#data-preparation)
  - [Model Architecture](#model-architecture)
  - [Training](#training)
  - [Evaluation](#evaluation)
  - [ROC and AUC Metrics](#roc-and-auc-metrics)
  - [Threshold Selection](#threshold-selection)
  - [Running the Code](#running-the-code)

## Introduction

Autoencoders are a type of neural network used for unsupervised learning. In this project, we use an autoencoder to detect anomalies in ECG data. The model is trained on normal ECG signals, and it learns to reconstruct these signals. During evaluation, if the reconstruction error is high, the signal is classified as anomalous.

## Setup

To run this project, you need to have Python and the following libraries installed:

- TensorFlow
- NumPy
- Pandas
- Matplotlib
- scikit-learn

## Data Preparation

The data preparation step involves loading the ECG dataset, normalizing the data, and splitting it into training and test sets. The dataset is then prepared with a mixture of normal and anomalous data.

## Model Architecture

The autoencoder model consists of an encoder and a decoder. The encoder compresses the input data into a lower-dimensional representation (embedding), and the decoder reconstructs the original data from this embedding.

## Training

The autoencoder is trained using the Mean Absolute Error (MAE) loss function. The training process involves minimizing the reconstruction error on the training data.

## Evaluation

After training, the model is evaluated by comparing the reconstructed signals with the original signals. The reconstruction error is plotted for both normal and anomalous ECG signals.

## ROC and AUC Metrics

The Receiver Operating Characteristic (ROC) curve and the Area Under the Curve (AUC) are used to evaluate the model's performance in distinguishing between normal and anomalous signals.

## Threshold Selection

A threshold is selected based on the reconstruction error to classify signals as normal or anomalous. The accuracy, precision, and recall metrics are calculated to evaluate the classification performance.

## Running the Code

To run the project, execute the `main.py` script:

```sh
python main.py
