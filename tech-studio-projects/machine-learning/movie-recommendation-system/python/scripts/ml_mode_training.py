#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""ml_model_training.py

Machine learning pipeline for predicting movie ratings.
"""

import os
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.ensemble import RandomForestRegressor
from sklearn.metrics import mean_squared_error
import joblib

def main() -> None:
    """Driving code."""
    # Load preprocessed datasets
    train_data = pd.read_csv("../data/processed/train_data.csv")
    test_data = pd.read_csv("../data/processed/test_data.csv")

    print("Columns in train_data:", train_data.columns.tolist())

    # Check for required columns
    required_columns = ["UserID", "MovieID", "Rating", "Timestamp", "UserAvgRating", "MovieAvgRating", "Title", "Year", "Genres", "Month", "Day", "Hour", "Minute", "Second"]
    if not all(col in train_data.columns for col in required_columns):
        raise ValueError("Missing required feature columns in train_data")

    # Group by MovieID and calculate average rating
    movie_avg_ratings = train_data.groupby("MovieID")["Rating"].mean().reset_index().rename(columns={"Rating": "MovieAvgRating"})

    # Merge average ratings back into train_data and test_data if necessary
    train_data = pd.merge(train_data, movie_avg_ratings, on="MovieID", how="left")
    if "MovieAvgRating" not in test_data.columns:
        test_data = pd.merge(test_data, movie_avg_ratings, on="MovieID", how="left")

    # Clean up duplicate columns with suffixes from merges
    train_data = train_data.loc[:, ~train_data.columns.str.endswith((".x", ".y"))]
    test_data = test_data.loc[:, ~test_data.columns.str.endswith((".x", ".y"))]

    print("Cleaned Training Data Columns:", train_data.columns.tolist())
    print("Cleaned Test Data Columns:", test_data.columns.tolist())

    # Exclude unnecessary columns
    train_data_rf = train_data.drop(columns=["Title", "Genres", "MovieID", "UserID", "Timestamp"])
    test_data_rf = test_data.drop(columns=["Title", "Genres", "MovieID", "UserID", "Timestamp"])

    # Ensure consistent features in both datasets
    common_columns = train_data_rf.columns.intersection(test_data_rf.columns)
    train_data_rf = train_data_rf[common_columns]
    test_data_rf = test_data_rf[common_columns]

    # Check for missing values
    if train_data_rf.isna().any().any() or test_data_rf.isna().any().any():
        raise ValueError("There are missing values in the training or test data.")

    print("Final Training Data Columns:", train_data_rf.columns.tolist())
    print("Final Test Data Columns:", test_data_rf.columns.tolist())

    # Train the Random Forest model
    rf_model = RandomForestRegressor(n_estimators=100, random_state=123)
    X_train = train_data_rf.drop(columns=["Rating"])
    y_train = train_data_rf["Rating"]
    rf_model.fit(X_train, y_train)

    # Generate predictions on the test data
    X_test = test_data_rf.drop(columns=["Rating"])
    y_test = test_data_rf["Rating"]
    rf_predictions = rf_model.predict(X_test)

    # Calculate Mean Squared Error (MSE) and Root Mean Squared Error (RMSE)
    rf_mse = mean_squared_error(y_test, rf_predictions)
    rf_rmse = np.sqrt(rf_mse)
    print(f"Mean Squared Error on test data (Random Forest): {rf_mse}")
    print(f"Root Mean Squared Error on test data (Random Forest): {rf_rmse}")

    # Create the scatter plot
    plt.scatter(y_test, rf_predictions, alpha=0.5)
    # Plot the line for perfect predictions (y = x)
    plt.plot([y_test.min(), y_test.max()], [y_test.min(), y_test.max()], 'r')
    # Add title and labels
    plt.title("Random Forest: Predicted vs Actual Ratings")
    plt.xlabel("Actual Ratings")
    plt.ylabel("Predicted Ratings")
    # Save the plot as an image in the 'plots/' folder
    plt.savefig("../plots/actual_vs_predicted_ratings.png")

    # Save the trained model
    if not os.path.exists("../models"):
        os.makedirs("../models")
    joblib.dump(rf_model, "../models/random_forest_model.pkl")
    print("Model saved successfully.")

    return None


if __name__ == "__main__":
    main()
