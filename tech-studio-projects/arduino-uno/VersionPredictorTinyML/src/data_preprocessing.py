#!\usr\bin\env python3
# -*- coding: utf-8 -*-

"""data_preprocessing.py"""

# https://docs.pylint.org/
# pylint: disable=line-too-long, trailing-whitespace, multiple-statements, fixme, locally-disabled

import pandas as pd
from sklearn.preprocessing import StandardScaler
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import OneHotEncoder

# Load the data
data = pd.read_csv('../../data/raw/versions.csv')

# Display the first few rows of the dataframe
print(data.head())

# Check for missing values
print(data.isnull().sum())

# Handle missing values (e.g., fill with median, mean, or drop rows/columns)
data = data.dropna()  # Example: Drop rows with missing values

# Extract features and target
X = data[['major', 'minor']].values
y = data[['target']].values  # Assuming there is a target column

# Standardize the features
scaler = StandardScaler()
X = scaler.fit_transform(X)

# Convert categorical features to one-hot encoding
encoder = OneHotEncoder()
encoded_features = encoder.fit_transform(data[['major', 'minor']]).toarray()

# Create a DataFrame from the encoded features
encoded_df = pd.DataFrame(encoded_features, columns=encoder.get_feature_names_out())

# Combine with other features or target
data_preprocessed = pd.concat([encoded_df, data[['target']]], axis=1)

# Split the data
X_train, X_val, y_train, y_val = train_test_split(X, y, test_size=0.2, random_state=42)

# Save preprocessed data
train_data = pd.DataFrame(X_train, columns=['feature1', 'feature2', ...])
train_data['target'] = y_train

val_data = pd.DataFrame(X_val, columns=['feature1', 'feature2', ...])
val_data['target'] = y_val

train_data.to_csv('path/to/save/train_data.csv', index=False)
val_data.to_csv('path/to/save/val_data.csv', index=False)
