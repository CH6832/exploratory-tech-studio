import pandas as pd
import numpy as np
from sklearn.preprocessing import StandardScaler, OneHotEncoder
from sklearn.impute import SimpleImputer
from sklearn.compose import ColumnTransformer
from sklearn.pipeline import Pipeline

def create_features(df):
    """
    Create new features based on existing ones.
    
    :param df: Original DataFrame
    :return: DataFrame with new features
    """
    # Example: Create a new feature that is the ratio of two existing features
    if 'feature1' in df.columns and 'feature2' in df.columns:
        df['feature_ratio'] = df['feature1'] / (df['feature2'] + 1e-5)  # Avoid division by zero

    # Additional feature engineering steps can be added here
    return df