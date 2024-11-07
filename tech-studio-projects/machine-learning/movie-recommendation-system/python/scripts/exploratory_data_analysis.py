#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""exploratory_data_analysis.py

The script performs exploratory data analysis (EDA) on a movie dataset,
focusing on tasks like data cleaning, transformation, and visualization. 
"""

import os
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
from typing import List


def main() -> None:
    """Main function that loads the datasets, cleans them, and performs
    exploratory data analysis tasks.
    """
    # Load raw datasets
    raw_movies_dataset = load_dataset("../data/raw/ml-latest-small/movies.csv")
    raw_ratings_dataset = load_dataset("../data/raw/ml-latest-small/ratings.csv")
    raw_tags_dataset = load_dataset("../data/raw/ml-latest-small/tags.csv")
    raw_links_dataset = load_dataset("../data/raw/ml-latest-small/links.csv")

    # Clean datasets
    data_movies = clean_movies_dataset(raw_movies_dataset)
    data_ratings = clean_ratings_dataset(raw_ratings_dataset)
    data_links = clean_links_dataset(raw_links_dataset)
    data_tags = clean_tags_dataset(raw_tags_dataset)

    # Initial Data Exploration
    first_data_exploration(data_movies, data_ratings, data_links, data_tags)

    # Movie Summary Statistics
    movie_stats = calc_movie_stats(data_movies)
    print("\nMovie Summary Statistics:\n", movie_stats)

    # Check for missing values
    missing_values_movies = count_missing_values(data_movies)
    missing_values_ratings = count_missing_values(data_ratings)
    print("\nMissing Values in Movies Dataset:\n", missing_values_movies)
    print("\nMissing Values in Ratings Dataset:\n", missing_values_ratings)

    # Plot distribution of movies by year
    plot_movies_year_distribution(data_movies)

    # Plot distribution of ratings
    plot_ratings_distribution(data_ratings)

    # Plot correlation matrix for selected columns
    numeric_columns = ['MovieID', 'Year']
    plot_correlation_matrix(data_movies, numeric_columns)

    return None


def load_dataset(file_path: str) -> pd.DataFrame:
    """Utility function to load a dataset from CSV."""

    return pd.read_csv(file_path)


def clean_movies_dataset(df: pd.DataFrame) -> pd.DataFrame:
    """Clean and format the movies dataset by extracting the year from the title,
    cleaning the title, and expanding genres.
    """
    df['Year'] = df['title'].str.extract(r'\((\d{4})\)').astype(float)
    df['Title'] = df['title'].str.replace(r'\s*\(\d{4}\)', '', regex=True)
    df['Genres'] = df['genres'].str.split('|')
    df = df.explode('Genres').reset_index(drop=True)
    df = df[['movieId', 'Title', 'Year', 'Genres']].rename(columns={'movieId': 'MovieID'})

    return df


def clean_ratings_dataset(df: pd.DataFrame) -> pd.DataFrame:
    """Clean and format the ratings dataset by renaming columns
    and converting timestamps to datetime.
    """
    df = df.rename(columns={'userId': 'UserID', 'movieId': 'MovieID', 'rating': 'Rating', 'timestamp': 'Timestamp'})
    df['Timestamp'] = pd.to_datetime(df['Timestamp'], unit='s')

    return df


def clean_links_dataset(df: pd.DataFrame) -> pd.DataFrame:
    """Clean and format the links dataset by renaming columns for clarity."""
    df = df.rename(columns={'movieId': 'MovieID', 'imdbId': 'IMDbID', 'tmdbId': 'TMDBID'})

    return df[['MovieID', 'IMDbID', 'TMDBID']]


def clean_tags_dataset(df: pd.DataFrame) -> pd.DataFrame:
    """Clean and format the tags dataset by renaming columns and
    converting timestamps to datetime.
    """
    df = df.rename(columns={'userId': 'UserID', 'movieId': 'MovieID', 'tag': 'Tag', 'timestamp': 'Timestamp'})
    df['Timestamp'] = pd.to_datetime(df['Timestamp'], unit='s')

    return df


def first_data_exploration(*datasets: pd.DataFrame) -> None:
    """Print basic structure and head of each dataset provided."""
    for i, dataset in enumerate(datasets, 1):
        print(f"\nDataset {i} Info:")
        print(dataset.info())
        print(dataset.head())

    return None


def calc_movie_stats(df: pd.DataFrame) -> pd.DataFrame:
    """Calculate summary statistics for the movies dataset, including
    mean, median, and variance for 'MovieID' and 'Year'.
    """
    stats = {
        'mean_movieID': df['MovieID'].mean(),
        'median_movieID': df['MovieID'].median(),
        'var_movieID': df['MovieID'].var(),
        'mean_year': df['Year'].mean(),
        'median_year': df['Year'].median(),
        'var_year': df['Year'].var()
    }

    return pd.DataFrame([stats])


def count_missing_values(df: pd.DataFrame) -> pd.Series:
    """Count the number of missing values in each column of the dataframe."""

    return df.isna().sum()


def plot_movies_year_distribution(df: pd.DataFrame) -> None:
    """Plot a histogram of movies by release year."""
    plt.figure(figsize=(10, 6))
    sns.histplot(df['Year'].dropna(), bins=20, color="lightblue", kde=False)
    plt.title("Distribution of Movies by Year")
    plt.xlabel("Year")
    plt.ylabel("Count")
    plt.show()

    return None


def plot_ratings_distribution(df: pd.DataFrame) -> None:
    """Plot a histogram of ratings distribution."""
    # Ensure the 'plots' directory exists
    if not os.path.exists("../plots"):
        os.makedirs("../plots")

    # Create the plot
    plt.figure(figsize=(10, 6))
    sns.histplot(df['Rating'], bins=10, color="orange", kde=False)
    plt.title("Distribution of Ratings")
    plt.xlabel("Rating")
    plt.ylabel("Count")

    # Save the plot as an image in the 'plots' directory
    plt.savefig("../plots/ratings_distribution.png", bbox_inches="tight")

    # Close the plot to free up memory
    plt.close()

    return None


def plot_correlation_matrix(df: pd.DataFrame, columns: List[str]) -> None:
    """Calculate and plot the correlation matrix for selected numeric columns."""
    # Ensure the 'plots' directory exists
    if not os.path.exists("../plots"):
        os.makedirs("../plots")

    # Calculate the correlation matrix for the selected columns
    corr_matrix = df[columns].corr()

    # Create the plot
    plt.figure(figsize=(8, 6))
    sns.heatmap(corr_matrix, annot=True, cmap="coolwarm", center=0)
    plt.title("Correlation Matrix")

    # Save the plot as an image in the 'plots' directory
    plt.savefig("../plots/correlation_matrix.png", bbox_inches="tight")

    # Close the plot to free up memory
    plt.close()

    return None


if __name__ == "__main__":
    main()
