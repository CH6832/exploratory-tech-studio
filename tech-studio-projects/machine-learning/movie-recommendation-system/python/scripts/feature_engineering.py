#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# ----------------------------------------------------------------------------------------
# Title:        feature_engineering.py
# Author:       Christoph Hartleb
# Date:         2024-10-02
# Description:  Feature engineering for movie ratings prediction pipeline.
# Version:      1.1
#
# You are free to:
# - Share — copy and redistribute the material in any medium or format.
#
# Under the following terms:
# - Attribution — You must give appropriate credit, provide a link to the license, and 
#   indicate if changes were made. You may do so in any reasonable manner, but not in any 
#   way that suggests the licensor endorses you or your use.
# - NonCommercial — You may not use the material for commercial purposes.
# - NoDerivatives — If you remix, transform, or build upon the material, you may not 
#   distribute the modified material.
#
# No additional restrictions — You may not apply legal terms or technological measures 
# that legally restrict others from doing anything the license permits.
# ----------------------------------------------------------------------------------------

import os
import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split


def main() -> None:
    """Main function to execute feature engineering pipeline."""
    # Paths to input files
    movies_path = "../data/preprocessed/movies_cleaned.csv"
    ratings_path = "../data/preprocessed/ratings_cleaned.csv"
    processed_dir = "../data/processed"

    # Load datasets
    movies, ratings = load_datasets(movies_path, ratings_path)

    # Merge datasets
    movie_ratings = merge_datasets(movies, ratings)

    # Add year and time features
    movie_ratings = add_year_features(movie_ratings)
    movie_ratings = add_time_features(movie_ratings)

    # Count missing values
    count_missing_values(movie_ratings)

    # Split data into training and test sets
    train_data, test_data = split_data(movie_ratings)

    # Convert timestamps to numeric format for machine learning models
    train_data["Timestamp"] = train_data["Timestamp"].astype(np.int64) // 10**9
    test_data["Timestamp"] = test_data["Timestamp"].astype(np.int64) // 10**9

    # Save processed datasets
    save_data(train_data, test_data, processed_dir)

    return None


def load_datasets(movies_path: str, ratings_path: str) -> tuple[pd.DataFrame, pd.DataFrame]:
    """Loads preprocessed movie and ratings datasets."""
    movies = pd.read_csv(movies_path)
    ratings = pd.read_csv(ratings_path)

    return movies, ratings


def merge_datasets(movies: pd.DataFrame, ratings: pd.DataFrame) -> pd.DataFrame:
    """Merges movies and ratings datasets on MovieID column."""
    movie_ratings = pd.merge(ratings, movies, on="MovieID", how="inner")
    print("- Merged `movies_cleaned.csv` and `ratings_cleaned.csv` based on `MovieID`.")

    return movie_ratings


def add_year_features(df: pd.DataFrame) -> pd.DataFrame:
    """Checks and adds the 'Year' column from the movies data."""
    if "Year" in df.columns:
        df["Year"] = df["Year"]
        print("- `Year` directly taken from the movies dataset (new feature).")
    else:
        raise ValueError("Year column is missing in the movies dataset.")

    return df


def add_time_features(df: pd.DataFrame) -> pd.DataFrame:
    """Converts timestamp to human-readable date and extracts year and month as new features."""
    # Check if the 'Timestamp' column is already in datetime format (str or object)
    if df["Timestamp"].dtype == "object":  # string or object type (e.g., date-time strings)
        df["Timestamp"] = pd.to_datetime(df["Timestamp"], errors='coerce')  # Convert to datetime
    elif df["Timestamp"].dtype == "int64" or df["Timestamp"].dtype == "float64":
        # If the 'Timestamp' is numeric (Unix timestamp), convert it to datetime
        df["Timestamp"] = pd.to_datetime(df["Timestamp"], unit="s")
    else:
        raise ValueError("Timestamp column has an unrecognized format")

    # Extract features from the 'Timestamp' column
    df["Year"] = df["Timestamp"].dt.year
    df["Month"] = df["Timestamp"].dt.month
    df["Day"] = df["Timestamp"].dt.day
    df["Hour"] = df["Timestamp"].dt.hour
    df["Minute"] = df["Timestamp"].dt.minute
    df["Second"] = df["Timestamp"].dt.second

    return df


def count_missing_values(df: pd.DataFrame) -> int:
    """Counts missing values in the dataframe."""
    missing_values = df.isna().sum().sum()
    print(f"- Number of missing values after feature engineering: {missing_values}")

    return missing_values


def split_data(df: pd.DataFrame, test_size: float = 0.2, random_state: int = 123) -> tuple[pd.DataFrame, pd.DataFrame]:
    """Splits data into training and testing sets."""
    train_data, test_data = train_test_split(df, test_size=test_size, random_state=random_state)
    print("\n## Train-Test Split Overview")
    print(f"- Training data size: {train_data.shape[0]} rows")
    print(f"- Test data size: {test_data.shape[0]} rows")

    return train_data, test_data


def save_data(train_data: pd.DataFrame, test_data: pd.DataFrame, processed_dir: str) -> None:
    """Saves the training and testing datasets in processed directory."""
    if not os.path.exists(processed_dir):
        os.makedirs(processed_dir)
    train_data.to_csv(f"{processed_dir}/train_data.csv", index=False)
    test_data.to_csv(f"{processed_dir}/test_data.csv", index=False)
    print("Feature engineering completed. Processed data saved.")

    return None


if __name__ == "__main__":
    main()
