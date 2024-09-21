# Bean Disease Classifier

## Project Overview

This project aims to build a neural network classifier to distinguish between healthy bean leaves and two types of common bean diseases: bean rust and angular leaf spots. The model is trained using color images of bean leaves taken in Uganda, with the goal of accurately classifying each leaf as healthy or diseased.

The project involves:
- Setting up the environment and dependencies
- Downloading and preparing the dataset
- Defining and training a neural network model
- Evaluating and visualizing model performance

## Features

- Image classification using a convolutional neural network (CNN)
- Data augmentation to improve model robustness
- Visualization of training and validation accuracy

## Installation

### Prerequisites

Ensure you have Python 3.6 or higher installed on your system. You will also need TensorFlow 2.x and Matplotlib.

### Setup

1. **Clone the repository:**

    ```bash
    git clone https://github.com/yourusername/bean-disease-classifier.git
    cd bean-disease-classifier
    ```

2. **Install dependencies:**

    You can install the required packages using `pip`. It's recommended to use a virtual environment.

    ```bash
    pip install -r requirements.txt
    ```

    **`requirements.txt` content:**

    ```
    tensorflow==2.x
    matplotlib
    gdown
    ```

3. **Download the dataset:**

    The dataset is automatically downloaded and extracted in the script. If you need to download it manually, use the following commands:

    ```bash
    pip install gdown
    gdown "https://storage.googleapis.com/learning-datasets/beans/train.zip" -O /tmp/train.zip
    gdown "https://storage.googleapis.com/learning-datasets/beans/validation.zip" -O /tmp/validation.zip
    gdown "https://storage.googleapis.com/learning-datasets/beans/test.zip" -O /tmp/test.zip
    ```

## Usage

1. **Run the training script:**

    Execute the `bean_disease_classifier.py` script to train the model.

    ```bash
    python bean_disease_classifier.py
    ```

    This script will:
    - Load and preprocess the dataset
    - Define and compile a CNN model
    - Train the model on the training data
    - Evaluate the model on the validation data
    - Plot the training and validation accuracy

2. **Script Details:**

    The script includes the following main sections:
    - **Setup and Dependencies**: Ensures TensorFlow version and installs required packages.
    - **Data Preparation**: Downloads and extracts the dataset, prepares data generators.
    - **Model Definition**: Defines a CNN model suitable for image classification tasks.
    - **Training and Evaluation**: Compiles and trains the model, visualizes performance.

## Model Architecture

The neural network used in this project is a Convolutional Neural Network (CNN) with the following layers:
- Convolutional layers to extract features from images
- MaxPooling layers to reduce dimensionality
- Dense layers for classification

## Training Details

- **Batch Size**: 128
- **Number of Epochs**: 20 (modifiable)
- **Loss Function**: Categorical Crossentropy
- **Optimizer**: Adam

## Evaluation

The model's performance is evaluated based on accuracy, and the training and validation accuracy are plotted for visual inspection.
