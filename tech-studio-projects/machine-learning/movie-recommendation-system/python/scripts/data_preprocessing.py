#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""data_preprocessing.py

This script performs essential data preprocessing tasks on raw
datasets to clean and structure the data for further analysis.
"""

import pandas as pd


def main() -> None:
    """Driving code."""
    raw_movies_dataset = pd.read_csv("../data/raw/ml-latest-small/movies.csv")
    raw_ratings_dataset = pd.read_csv("../data/raw/ml-latest-small/ratings.csv")
    raw_links_dataset = pd.read_csv("../data/raw/ml-latest-small/links.csv")
    raw_tags_dataset = pd.read_csv("../data/raw/ml-latest-small/tags.csv")
    
    # Process Movies dataset
    movies_cleaned = preprocess_movies(raw_movies_dataset)
    movies_cleaned = check_missing_values(movies_cleaned, "Movies Dataset")
    movies_cleaned = remove_duplicates(movies_cleaned, "Movies Dataset")
    movies_cleaned['Genres'] = movies_cleaned['Genres'].apply(lambda x: '|'.join(x))
    
    # Process Ratings dataset
    ratings_cleaned = preprocess_ratings(raw_ratings_dataset)
    ratings_cleaned = check_missing_values(ratings_cleaned, "Ratings Dataset")
    ratings_cleaned = remove_duplicates(ratings_cleaned, "Ratings Dataset")
    ratings_cleaned = calculate_aggregates(ratings_cleaned)
    
    # Process Tags dataset
    tags_cleaned = preprocess_tags(raw_tags_dataset)
    tags_cleaned = check_missing_values(tags_cleaned, "Tags Dataset")
    tags_cleaned = remove_duplicates(tags_cleaned, "Tags Dataset")
    
    # Process Links dataset
    links_cleaned = preprocess_links(raw_links_dataset)
    links_cleaned = check_missing_values(links_cleaned, "Links Dataset")
    links_cleaned = remove_duplicates(links_cleaned, "Links Dataset")
    
    # Save cleaned datasets
    movies_cleaned.to_csv("../data/preprocessed/movies_cleaned.csv", index=False)
    ratings_cleaned.to_csv("../data/preprocessed/ratings_cleaned.csv", index=False)
    tags_cleaned.to_csv("../data/preprocessed/tags_cleaned.csv", index=False)
    links_cleaned.to_csv("../data/preprocessed/links_cleaned.csv", index=False)

    return None


def check_missing_values(data: pd.DataFrame, dataset_name: str) -> pd.DataFrame:
    """This function checks for missing values in the provided dataset and handles them
    by removing rows that contain any missing data. It prints the count of missing
    values for each column in the dataset.
    """
    missing_info = data.isna().sum()
    print(f"\nMissing values in {dataset_name}:\n{missing_info}")
    if missing_info.any():
        print(f"\nHandling missing values by removing rows with missing data in {dataset_name}.\n")
        data = data.dropna()
    
    return data


def remove_duplicates(data: pd.DataFrame, dataset_name: str) -> pd.DataFrame:
    """This function identifies and removes duplicate rows from the provided dataset.
    Logs the number of duplicate rows that were removed.
    """
    for column in data.columns:
        # Check if the column contains lists (or other unhashable types)
        if data[column].apply(lambda x: isinstance(x, list)).any():
            # Convert lists to tuples
            data[column] = data[column].apply(lambda x: tuple(x) if isinstance(x, list) else x)
    
    initial_rows = len(data)
    
    data = data.drop_duplicates()
    
    removed_rows = initial_rows - len(data)
    print(f"\nRemoved {removed_rows} duplicate rows from {dataset_name}.\n")
    
    return data


def preprocess_movies(data: pd.DataFrame) -> pd.DataFrame:
    """This function preprocesses the movies dataset by cleaning the title, extracting
    the year from the title, and splitting genres into a list format.
    """
    print("\nPreprocessing Movies Data...\n")
    data['Title'] = data['title'].str.strip()
    data['Year'] = data['title'].str.extract(r'(\d{4})').astype(float)
    data['Genres'] = data['genres'].str.split('|')
    data = data[['movieId', 'Title', 'Year', 'Genres']].rename(columns={'movieId': 'MovieID'})
    data.dropna(subset=['Title', 'Year'], inplace=True)
    print("\nFinished preprocessing Movies data.\n")

    return data


def preprocess_ratings(data: pd.DataFrame) -> pd.DataFrame:
    """Converts timestamps into a date-time format and renames columns."""
    print("\nPreprocessing Ratings Data...\n")
    data['Timestamp'] = pd.to_datetime(data['timestamp'], unit='s')
    data = data[['userId', 'movieId', 'rating', 'Timestamp']].rename(columns={'userId': 'UserID', 'movieId': 'MovieID', 'rating': 'Rating'})
    print("\nFinished preprocessing Ratings data.\n")

    return data


def preprocess_tags(data: pd.DataFrame) -> pd.DataFrame:
    """Converts timestamps into a date-time format and renames columns."""
    print("\nPreprocessing Tags Data...\n")
    data['Timestamp'] = pd.to_datetime(data['timestamp'], unit='s')
    data = data[['userId', 'movieId', 'tag', 'Timestamp']].rename(columns={'userId': 'UserID', 'movieId': 'MovieID', 'tag': 'Tag'})
    print("\nFinished preprocessing Tags data.\n")
    
    return data


def preprocess_links(data: pd.DataFrame) -> pd.DataFrame:
    """Renames columns for clarity."""
    print("\nPreprocessing Links Data...\n")
    data = data.rename(columns={'movieId': 'MovieID', 'imdbId': 'IMDbID', 'tmdbId': 'TMDBID'})
    print("\nFinished preprocessing Links data.\n")
    
    return data


def calculate_aggregates(ratings_data: pd.DataFrame) -> pd.DataFrame:
    """This function calculates average ratings for each movie and user from ratings data."""
    print("\nCalculating MovieAvgRating and UserAvgRating...\n")
    movie_avg_ratings = ratings_data.groupby('MovieID')['Rating'].mean().rename('MovieAvgRating')
    user_avg_ratings = ratings_data.groupby('UserID')['Rating'].mean().rename('UserAvgRating')
    ratings_data = ratings_data.join(movie_avg_ratings, on='MovieID').join(user_avg_ratings, on='UserID')
    print("\nFinished calculating aggregate ratings.\n")
    
    return ratings_data


if __name__ == "__main__":
    main()
