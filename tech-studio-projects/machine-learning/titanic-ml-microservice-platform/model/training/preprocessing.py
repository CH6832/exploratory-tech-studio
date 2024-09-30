
"""
    Load Data: The function load_data reads the Titanic dataset from a CSV file.

    Preprocess Data:
        Target and Features: The preprocess_data function separates the target variable (Survived) from the features.
        Column Identification: It identifies numerical and categorical columns.
        Transformers: It defines transformers for numerical features (mean imputation and scaling) and categorical features (constant imputation and one-hot encoding).
        Column Transformer: Combines the transformers to preprocess the features.

    Split Data: The split_data function splits the processed data into training and testing sets.

    Main Execution Block: Loads the data, preprocesses it, splits it into training and testing sets, and saves the processed datasets as NumPy arrays.

Usage

    Place this script in the model/training/ directory.
    Ensure you have a dataset named titanic.csv in the data/raw/ directory.
    Run the script to preprocess the data and save the results.

You can now integrate this preprocessing step into your machine learning pipeline for training models based on the Titanic dataset. If you have any further questions or need modifications, feel free to ask!

"""

import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler, OneHotEncoder
from sklearn.compose import ColumnTransformer
from sklearn.pipeline import Pipeline
from sklearn.impute import SimpleImputer
import numpy as np

def load_data(filepath):
    """Load the dataset from a given file path."""
    return pd.read_csv(filepath)

def preprocess_data(df):
    """Preprocess the Titanic dataset.

    Args:
        df (pd.DataFrame): The input DataFrame containing raw Titanic data.

    Returns:
        X (pd.DataFrame): Preprocessed features.
        y (pd.Series): Target variable.
    """
    # Define target variable and features
    target = 'Survived'
    features = df.drop(columns=[target])
    
    # Identify numerical and categorical columns
    num_cols = features.select_dtypes(include=['int64', 'float64']).columns.tolist()
    cat_cols = features.select_dtypes(include=['object']).columns.tolist()
    
    # Define preprocessing for numerical features
    num_transformer = Pipeline(steps=[
        ('imputer', SimpleImputer(strategy='mean')),  # Fill missing values with the mean
        ('scaler', StandardScaler())                   # Scale features
    ])
    
    # Define preprocessing for categorical features
    cat_transformer = Pipeline(steps=[
        ('imputer', SimpleImputer(strategy='constant', fill_value='missing')),  # Fill missing with 'missing'
        ('onehot', OneHotEncoder(handle_unknown='ignore'))                       # One-hot encoding
    ])
    
    # Combine preprocessing steps
    preprocessor = ColumnTransformer(
        transformers=[
            ('num', num_transformer, num_cols),
            ('cat', cat_transformer, cat_cols)
        ])
    
    # Apply transformations
    X = preprocessor.fit_transform(features)
    y = df[target]

    return X, y

def split_data(X, y, test_size=0.2, random_state=42):
    """Split the data into training and testing sets."""
    return train_test_split(X, y, test_size=test_size, random_state=random_state)

if __name__ == "__main__":
    # Load the dataset
    filepath = '../data/raw/titanic.csv'  # Adjust path as needed
    df = load_data(filepath)
    
    # Preprocess the data
    X, y = preprocess_data(df)

    # Split the data into training and testing sets
    X_train, X_test, y_train, y_test = split_data(X, y)

    # Save preprocessed data for later use
    np.save('../data/processed/X_train.npy', X_train)
    np.save('../data/processed/X_test.npy', X_test)
    np.save('../data/processed/y_train.npy', y_train)
    np.save('../data/processed/y_test.npy', y_test)

    print("Data preprocessing completed. Train and test sets saved.")
