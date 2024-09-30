
"""
    Load Data: The function load_data loads the processed NumPy arrays for the test features and labels.

    Evaluate Model:
        The evaluate_model function takes a trained model, test features, and true labels as input.
        It makes predictions using the model and calculates various evaluation metrics:
            Accuracy: Proportion of correct predictions.
            Precision: Proportion of true positive predictions among all positive predictions.
            Recall: Proportion of true positive predictions among all actual positives.
            F1 Score: Harmonic mean of precision and recall.
            ROC AUC: Area under the Receiver Operating Characteristic curve.
            Confusion Matrix: A summary of prediction results.
            Classification Report: A comprehensive report of precision, recall, and F1 score for each class.

    Main Execution Block:
        Loads the trained model using joblib.
        Loads the test data.
        Evaluates the model and prints the metrics.

Usage

    Place this script in the model/training/ directory.
    Ensure you have the trained model saved as titanic_model.pkl in the model/trained_models/ directory.
    Ensure you have the processed test data (X_test.npy and y_test.npy) in the data/processed/ directory.
    Run the script to evaluate the model and display the evaluation metrics.

This script will provide insights into how well your trained model is performing on the unseen test data. 
"""

import numpy as np
import pandas as pd
from sklearn.metrics import (
    accuracy_score,
    precision_score,
    recall_score,
    f1_score,
    roc_auc_score,
    confusion_matrix,
    classification_report
)
import joblib

def load_data(filepath):
    """Load the dataset from a given file path."""
    return np.load(filepath, allow_pickle=True)

def evaluate_model(model, X_test, y_test):
    """Evaluate the model using various metrics.

    Args:
        model: The trained model to evaluate.
        X_test (np.ndarray): The test features.
        y_test (np.ndarray): The true labels for the test set.

    Returns:
        metrics (dict): A dictionary of evaluation metrics.
    """
    # Make predictions
    y_pred = model.predict(X_test)

    # Calculate evaluation metrics
    metrics = {
        'accuracy': accuracy_score(y_test, y_pred),
        'precision': precision_score(y_test, y_pred),
        'recall': recall_score(y_test, y_pred),
        'f1_score': f1_score(y_test, y_pred),
        'roc_auc': roc_auc_score(y_test, model.predict_proba(X_test)[:, 1]),
        'confusion_matrix': confusion_matrix(y_test, y_pred),
        'classification_report': classification_report(y_test, y_pred)
    }

    return metrics

def main():
    # Load the trained model
    model_path = '../model/trained_models/titanic_model.pkl'  # Adjust path as needed
    model = joblib.load(model_path)

    # Load test data
    X_test = load_data('../data/processed/X_test.npy')
    y_test = load_data('../data/processed/y_test.npy')

    # Evaluate the model
    metrics = evaluate_model(model, X_test, y_test)

    # Print metrics
    print("Model Evaluation Metrics:")
    for key, value in metrics.items():
        if key == 'confusion_matrix':
            print(f"{key}:\n{value}")
        elif key == 'classification_report':
            print(f"{key}:\n{value}")
        else:
            print(f"{key}: {value:.4f}")

if __name__ == "__main__":
    main()
