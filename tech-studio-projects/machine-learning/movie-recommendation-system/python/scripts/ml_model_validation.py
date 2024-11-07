#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""ml_model_validation.py

Machine learning pipeline for validating movie rating predictions.
"""

import os
import pandas as pd
import numpy as np
import pickle
import matplotlib.pyplot as plt
import seaborn as sns
from datetime import datetime


def main() -> None:
    """Main function for validating movie rating predictions."""
    model_path = "../models/random_forest_model.pkl"
    movies_path = "../data/final_holdout_test/ml-10M100K/movies.dat"
    ratings_path = "../data/final_holdout_test/ml-10M100K/ratings.dat"
    cleaned_ratings_path = "../data/preprocessed/ratings_cleaned.csv"
    
    # Load model
    model = load_model(model_path)
    
    # Prepare test data
    test_data = prepare_test_data(movies_path, ratings_path, cleaned_ratings_path)
    
    # Predict ratings
    predictions = model.predict(test_data)
    
    # Load actual ratings from cleaned data
    cleaned_ratings = pd.read_csv(cleaned_ratings_path)
    actual_ratings = test_data.merge(cleaned_ratings[["UserID", "MovieID", "Rating"]], on=["UserID", "MovieID"], how="left")["Rating"]
    actual_ratings.fillna(actual_ratings.mean(), inplace=True)
    
    # Calculate RMSE
    rmse = get_rmse(actual_ratings, predictions)
    print("Root Mean Squared Error on test data (Random Forest):", rmse)
    
    # Plot predictions vs actuals
    # plot = plot_pr

    return None


def load_model(model_path: str):
    """Loads a saved model using pickle."""
    with open(model_path, 'rb') as file:
        model = pickle.load(file)

    return model


def load_movies_data(file_path: str) -> pd.DataFrame:
    """Loads and processes movie data from a text file."""
    with open(file_path, 'r') as file:
        lines = file.readlines()
    movies = [line.strip().split("::") for line in lines]
    df = pd.DataFrame(movies, columns=["MovieID", "Title", "Genres"])
    df["MovieID"] = pd.to_numeric(df["MovieID"])
    df["Year"] = df["Title"].str.extract(r"\((\d{4})\)").astype(float)
    
    return df[["MovieID", "Year"]]


def load_ratings_data(file_path: str) -> pd.DataFrame:
    """Loads and processes rating data from a text file."""
    with open(file_path, 'r') as file:
        lines = file.readlines()
    ratings = [line.strip().split("::") for line in lines]
    df = pd.DataFrame(ratings, columns=["UserID", "MovieID", "Rating", "Timestamp"])
    df[["UserID", "MovieID", "Rating"]] = df[["UserID", "MovieID", "Rating"]].apply(pd.to_numeric)

    return df


def validate_columns(df: pd.DataFrame, required_columns: list) -> None:
    """Validates that required columns are present in a DataFrame."""
    missing_cols = [col for col in required_columns if col not in df.columns]
    if missing_cols:
        raise ValueError(f"Data is missing columns: {missing_cols}")

    return None


def prepare_test_data(movies_path: str, ratings_path: str, cleaned_ratings_path: str) -> pd.DataFrame:
    """Prepares and merges test data from movies, ratings, and cleaned ratings data."""
    test_movies = load_movies_data(movies_path)
    test_ratings = load_ratings_data(ratings_path)
    cleaned_ratings = pd.read_csv(cleaned_ratings_path)

    validate_columns(cleaned_ratings, ["UserID", "MovieID", "Rating"])

    test_data = test_ratings.merge(test_movies, on="MovieID", how="left")
    test_data["Timestamp"] = pd.to_datetime(test_data["Timestamp"], unit='s')
    test_data["RatingYear"] = test_data["Timestamp"].dt.year
    test_data["RatingMonth"] = test_data["Timestamp"].dt.month
    current_year = datetime.now().year
    test_data["YearsSinceRelease"] = current_year - test_data["Year"]

    user_avg_ratings = cleaned_ratings.groupby("UserID")["Rating"].mean().reset_index().rename(columns={"Rating": "UserAvgRating"})
    test_data = test_data.merge(user_avg_ratings, on="UserID", how="left")
    
    test_data["UserRatingCount"] = test_data.groupby("UserID")["Rating"].transform('count')
    test_data["DayOfWeek"] = test_data["Timestamp"].dt.day_name()
    
    if "MovieAvgRating" not in test_data.columns:
        movie_avg_ratings = cleaned_ratings.groupby("MovieID")["Rating"].mean().reset_index().rename(columns={"Rating": "MovieAvgRating"})
        test_data = test_data.merge(movie_avg_ratings, on="MovieID", how="left")

    test_data.fillna({"MovieAvgRating": cleaned_ratings["Rating"].mean(), "UserAvgRating": cleaned_ratings["Rating"].mean()}, inplace=True)

    return test_data[["UserID", "MovieID", "Year", "RatingYear", "RatingMonth", "YearsSinceRelease", 
                      "UserAvgRating", "UserRatingCount", "MovieAvgRating", "DayOfWeek"]]


def get_mse(actual: pd.Series, predicted: pd.Series) -> float:
    """Calculates Mean Squared Error (MSE) between actual and predicted ratings."""

    return np.mean((actual - predicted) ** 2)


def get_rmse(actual: pd.Series, predicted: pd.Series) -> float:
    """Calculates Root Mean Squared Error (RMSE) between actual and predicted ratings."""

    return np.sqrt(get_mse(actual, predicted))


def plot_predicted_vs_actual(actual: pd.Series, predicted: pd.Series, plot_title: str = "Predicted vs Actual Ratings") -> None:
    """Plots a scatter plot comparing actual and predicted ratings."""
    # Create a figure
    plt.figure(figsize=(10, 7))
    # Scatter plot
    sns.scatterplot(x=actual, y=predicted, alpha=0.5)
    # Plot the line for perfect predictions (y = x)
    plt.plot([actual.min(), actual.max()], [actual.min(), actual.max()], 'r--')
    # Add title and labels
    plt.title(plot_title)
    plt.xlabel("Actual Ratings")
    plt.ylabel("Predicted Ratings")
    # Save the plot in the '../plots' directory
    if not os.path.exists('../plots'):
        os.makedirs('../plots')  # Create the directory if it doesn't exist
    # Save the plot as an image (you can change the filename as needed)
    plt.savefig('../plots/predicted_vs_actual_ratings.png')

    return None


if __name__ == "__main__":
    main()
