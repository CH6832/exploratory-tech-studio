# Fashion MNIST Experiment with TensorFlow Java

This project demonstrates how to train and evaluate machine learning models on the Fashion MNIST dataset using the TensorFlow Java API. It includes experiments with various neural network architectures, such as increasing the number of neurons, adding additional layers, and implementing early stopping.

## Table of Contents

- [Fashion MNIST Experiment with TensorFlow Java](#fashion-mnist-experiment-with-tensorflow-java)
  - [Table of Contents](#table-of-contents)
  - [Overview](#overview)
  - [Project Structure](#project-structure)
  - [Requirements](#requirements)
  - [Installation](#installation)
  - [Usage](#usage)
  - [Experiments](#experiments)
    - [1. **Initial Model**:](#1-initial-model)
    - [2. **Increased Neurons**:](#2-increased-neurons)
    - [3. **Additional Layer**:](#3-additional-layer)
    - [4. **Non-Normalized Data**:](#4-non-normalized-data)
    - [5. **Early Stopping**:](#5-early-stopping)
  - [References](#references)

## Overview

This project uses the TensorFlow Java API to train neural networks on the Fashion MNIST dataset, which consists of grayscale images of fashion items like shoes, shirts, and pants. The key functionalities include:

- **Loading the Fashion MNIST dataset** (you will need to implement your own loader in Java).
- **Building a Sequential neural network model** with options to add customizable layers and neurons.
- **Training the model** using different configurations such as:
  - Initial basic model.
  - Increased number of neurons.
  - Adding additional hidden layers.
  - Training with non-normalized data.
  - Early stopping based on accuracy.

## Project Structure

```plaintext
.
├── src
│   └── FashionMNISTExperiment.java   # Main Java file implementing model training and experiments
├── README.md                         # Project documentation
└── .gitignore                        # Ignore unnecessary files
```

## Requirements

- **Java 8** or higher
- **TensorFlow Java API** (v0.4.0 or higher)
- **Gradle** or **Maven** for dependency management
- **Fashion MNIST Dataset** (You need to load it manually in Java as it's not provided out-of-the-box like in Python)

## Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/fashion-mnist-tensorflow-java.git
   cd fashion-mnist-tensorflow-java
   ```

2. **Set up TensorFlow Java dependencies**:
   If you're using **Maven**, include the following dependency in your `pom.xml`:

   ```xml
   <dependency>
     <groupId>org.tensorflow</groupId>
     <artifactId>tensorflow-core-platform</artifactId>
     <version>0.4.0</version>
   </dependency>
   ```

   If you're using **Gradle**, add this to your `build.gradle`:

   ```gradle
   implementation 'org.tensorflow:tensorflow-core-platform:0.4.0'
   ```

3. **Download the Fashion MNIST dataset**:
   You need to implement or use external utilities to load the Fashion MNIST dataset into Java. This project does not include an automatic dataset loader like TensorFlow Python.

## Usage

1. **Compile the Java files**:
   After setting up the dependencies, compile the Java file using:

   ```bash
   javac -cp "path/to/tensorflow-jars/*" src/FashionMNISTExperiment.java
   ```

2. **Run the experiment**:
   Run the main experiment code using:

   ```bash
   java -cp "path/to/tensorflow-jars/*:." FashionMNISTExperiment
   ```

   The program will run the following experiments on the Fashion MNIST dataset:
   - Initial model training.
   - Model with more neurons.
   - Model with an additional hidden layer.
   - Model trained on non-normalized data.
   - Model with early stopping based on accuracy.

## Experiments

### 1. **Initial Model**:
   A simple model with a single dense layer of 512 neurons followed by a softmax layer for classification.

### 2. **Increased Neurons**:
   This experiment increases the number of neurons in the dense layer to 1024 and observes how the model performs with a larger number of parameters.

### 3. **Additional Layer**:
   Adds an additional hidden layer of 256 neurons to see if more layers improve performance.

### 4. **Non-Normalized Data**:
   Train the model on non-normalized data to see how normalization impacts model accuracy and performance.

### 5. **Early Stopping**:
   Implements early stopping to terminate training if the model reaches 95% accuracy before completing the maximum number of epochs.

## References

- [TensorFlow Java API](https://www.tensorflow.org/java)
- [Fashion MNIST Dataset](https://github.com/zalandoresearch/fashion-mnist)
- [DeepLearning4J](https://deeplearning4j.org/)
