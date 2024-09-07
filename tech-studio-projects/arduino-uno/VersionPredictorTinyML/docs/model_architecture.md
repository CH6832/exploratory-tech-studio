# Model Architecture

## Overview

The model is a neural network designed to predict the next version number in a software versioning sequence. It utilizes a simple yet effective architecture to handle the task of forecasting the next major and minor version numbers based on the current version information. This document provides an in-depth look at the model's architecture, including its layers, activation functions, and how the network processes the input to produce predictions.

## Architecture Details

### 1. **Input Layer**

**Description:**
- The input layer receives the current version numbers and serves as the starting point for the neural network. It directly takes the major and minor version numbers as input features.

**Configuration:**
- **Neurons:** 2
- **Features:**
  - **Major Version Number:** Represents the major version of the software (e.g., `1` in `1.0`).
  - **Minor Version Number:** Represents the minor version of the software (e.g., `0` in `1.0`).

**Purpose:**
- To provide the raw versioning data to the network for further processing.

**Example Input:**
- `[1, 0]` (for version `1.0`)

### 2. **Dense Layer**

**Description:**
- The dense layer is a fully connected layer that performs a weighted sum of the inputs and applies an activation function. This layer helps the model learn complex patterns and relationships between the input features and the output predictions.

**Configuration:**
- **Neurons:** 8
- **Activation Function:** ReLU (Rectified Linear Unit)

**ReLU Activation Function:**
- **Formula:** \( f(x) = \max(0, x) \)
- **Purpose:** Introduces non-linearity into the model, allowing it to learn more complex patterns by transforming the output of each neuron to a non-negative value.

**Properties:**
- **Weights and Biases:** Each neuron in this layer has associated weights and biases that are adjusted during training.
- **Learning:** The dense layer’s weights are learned through backpropagation, helping the model to fit the training data.

### 3. **Output Layer**

**Description:**
- The output layer generates the final predictions of the next version number based on the processed information from the dense layer. It consists of two neurons corresponding to the predicted major and minor version numbers.

**Configuration:**
- **Neurons:** 2
- **Activation Function:** Linear (no activation function)

**Purpose:**
- To produce the final output values (predicted major and minor version numbers) based on the learned patterns from the previous layers.

**Output Format:**
- **Major Version Number:** Predicted major version as a continuous value.
- **Minor Version Number:** Predicted minor version as a continuous value.

**Example Output:**
- `[2, 1]` (for predicting the next version to be `2.1`)

## Training Process

### Data Preparation

- **Input Data:** The input data consists of pairs of current version numbers (major and minor).
- **Target Data:** The target data includes the next version numbers (major and minor) that the model aims to predict.

### Training Procedure

- **Loss Function:** Mean Squared Error (MSE) is typically used to measure the difference between predicted and actual version numbers. The model is trained to minimize this error.
- **Optimizer:** An optimization algorithm like Adam or SGD (Stochastic Gradient Descent) adjusts the weights and biases during training to minimize the loss function.
- **Epochs:** The number of training iterations over the entire dataset, typically determined based on validation performance.

### Evaluation

- **Validation Set:** A portion of the data used to validate the model's performance and prevent overfitting.
- **Metrics:** Metrics such as Mean Absolute Error (MAE) or Root Mean Squared Error (RMSE) may be used to evaluate the model’s prediction accuracy on the validation set.

## Model Summary

- **Input Layer:** 2 neurons (captures current major and minor version numbers).
- **Dense Layer:** 8 neurons with ReLU activation (learns complex patterns).
- **Output Layer:** 2 neurons with linear activation (predicts next major and minor version numbers).
