#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""model_trainig.py

The kaggle dataset (data/creditcard.csv) is used to train a ML model
for detecting the fraud. A Random Forest Classifier is used here to, because
it is robust and easy to implement.
"""

import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.preprocessing import StandardScaler
from sklearn.metrics import accuracy_score
import joblib


def train_model() -> None:
    """Train and save the model."""
    # Load the dataset
    df = pd.read_csv('data/creditcard.csv')

    # Split features and labels
    X = df.drop(['Class'], axis=1)
    y = df['Class']

    # Split into training and test sets
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

    # Standardize the feature values
    scaler = StandardScaler()
    X_train_scaled = scaler.fit_transform(X_train)
    X_test_scaled = scaler.transform(X_test)

    # Train a Random Forest Classifier
    model = RandomForestClassifier(n_estimators=100, random_state=42)
    model.fit(X_train_scaled, y_train)

    # Evaluate the model
    y_pred = model.predict(X_test_scaled)
    accuracy = accuracy_score(y_test, y_pred)
    print(f'Model Accuracy: {accuracy}')

    # Save the model and scaler for use in the consumer
    joblib.dump(model, 'models/fraud_detection_model.pkl')
    joblib.dump(scaler, 'models/scaler.pkl')

    return None
