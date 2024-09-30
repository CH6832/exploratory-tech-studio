import pandas as pd
import os

def ingest_data():
    raw_data_path = './data/raw/titanic.csv'
    processed_data_path = './data/processed/processed_titanic.csv'

    # Load raw data
    df = pd.read_csv(raw_data_path)

    # Simple preprocessing
    df['Age'].fillna(df['Age'].mean(), inplace=True)
    df['Embarked'].fillna(df['Embarked'].mode()[0], inplace=True)

    # Save processed data
    df.to_csv(processed_data_path, index=False)

if __name__ == '__main__':
    ingest_data()
