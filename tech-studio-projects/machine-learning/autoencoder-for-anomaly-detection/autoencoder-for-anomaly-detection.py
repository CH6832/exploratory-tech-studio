#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""
Anomaly Detection using Autoencoders on ECG Data

This script trains an autoencoder to detect anomalies in ECG data. It includes:
1. Data loading and preprocessing.
2. Model definition and training.
3. Evaluation of reconstruction and ROC metrics.
4. Threshold selection for anomaly detection.

Dependencies:
    - Pandas
    - NumPy
    - Matplotlib
    - TensorFlow
    - scikit-learn
"""

import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
import tensorflow as tf
from sklearn.metrics import accuracy_score, precision_score, recall_score, roc_curve, auc
from sklearn.model_selection import train_test_split
from tensorflow.keras import layers, Model

# Load and preprocess ECG data
url = 'http://storage.googleapis.com/download.tensorflow.org/data/ecg.csv'
dataframe = pd.read_csv(url, header=None)
raw_data = dataframe.values

# Extract features and labels
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

# Mix some anomalies into the training data
portion_of_anomaly_in_training = 0.1
end_size = int(len(normal_train_data) / (10 - portion_of_anomaly_in_training * 10))
combined_train_data = np.append(normal_train_data, anomalous_test_data[:end_size], axis=0)

# Plot normal and anomalous ECGs
plt.grid()
plt.plot(np.arange(140), normal_train_data[0])
plt.title("A Normal ECG")
plt.show()

plt.grid()
plt.plot(np.arange(140), anomalous_train_data[0])
plt.title("An Anomalous ECG")
plt.show()

# Define the autoencoder model
EMBEDDING_SIZE = 16  # Choose an embedding size

class AnomalyDetector(Model):
    def __init__(self):
        super(AnomalyDetector, self).__init__()
        self.encoder = tf.keras.Sequential([
            layers.Dense(8, activation="relu"),
            layers.Dense(EMBEDDING_SIZE, activation="relu")  # Smallest layer
        ])
        self.decoder = tf.keras.Sequential([
            layers.Dense(8, activation="relu"),
            layers.Dense(140, activation="sigmoid")
        ])
    
    def call(self, x):
        encoded = self.encoder(x)
        decoded = self.decoder(encoded)
        return decoded

autoencoder = AnomalyDetector()
print("Chosen Embedding Size: ", EMBEDDING_SIZE)

optimizer = tf.keras.optimizers.Adam(learning_rate=0.01)
autoencoder.compile(optimizer=optimizer, loss='mae')

# Train the model
history = autoencoder.fit(
    combined_train_data, combined_train_data,
    epochs=50, batch_size=512,
    validation_data=(test_data, test_data),
    shuffle=True
)

# Plot training and validation loss
plt.plot(history.history["loss"], label="Training Loss")
plt.plot(history.history["val_loss"], label="Validation Loss")
plt.legend()
plt.show()

# Evaluate reconstruction
def plot_reconstruction(model, data, title):
    encoded_imgs = model.encoder(data).numpy()
    decoded_imgs = model.decoder(encoded_imgs).numpy()
    
    plt.plot(data[0], 'b')
    plt.plot(decoded_imgs[0], 'r')
    plt.fill_between(np.arange(140), decoded_imgs[0], data[0], color='lightcoral')
    plt.legend(["Input", "Reconstruction", "Error"])
    plt.title(title)
    plt.show()

plot_reconstruction(autoencoder, normal_test_data, "Normal ECG Reconstruction")
plot_reconstruction(autoencoder, anomalous_test_data, "Anomalous ECG Reconstruction")

# ROC and AUC metrics
reconstructions = autoencoder(test_data)
loss = tf.keras.losses.mae(reconstructions, test_data)
flipped_labels = 1 - test_labels
fpr, tpr, thresholds = roc_curve(flipped_labels, loss)

plt.figure()
plt.plot(fpr, tpr, color='darkorange', lw=2, label='ROC curve')
plt.plot([0, 1], [0, 1], color='navy', lw=2, linestyle='--')
plt.xlim([0.0, 1.0])
plt.ylim([0.0, 1.05])
plt.xlabel('False Positive Rate')
plt.ylabel('True Positive Rate')
plt.title('Receiver Operating Characteristic')
plt.legend(loc="lower right")

# Plot thresholds
thresholds_every = 20
for i in range(0, len(thresholds), thresholds_every):
    threshold_value_with_max_four_decimals = str(thresholds[i])[:5]
    plt.scatter(fpr[i], tpr[i], c='black')
    plt.text(fpr[i] - 0.03, tpr[i] + 0.005, threshold_value_with_max_four_decimals, fontsize=15)

plt.show()

# Calculate and print AUC
roc_auc = auc(fpr, tpr)
print("AUC:", roc_auc)

# Select and apply threshold for anomaly detection
threshold = 0.05  # Choose a threshold based on ROC curve or other criteria
print("Chosen Threshold: ", threshold)

def predict(model, data, threshold):
    reconstructions = model(data)
    loss = tf.keras.losses.mae(reconstructions, data)
    return tf.math.less(loss, threshold), loss

def print_stats(predictions, labels):
    print("Accuracy = {}".format(accuracy_score(labels, predictions)))
    print("Precision = {}".format(precision_score(labels, predictions)))
    print("Recall = {}".format(recall_score(labels, predictions)))

predictions, scores = predict(autoencoder, test_data, threshold)
print_stats(predictions, test_labels)
