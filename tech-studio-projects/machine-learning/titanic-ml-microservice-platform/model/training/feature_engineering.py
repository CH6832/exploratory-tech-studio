"""
    Function Definition:
        The feature_engineering function takes a pandas DataFrame (df) as input and returns a modified DataFrame with new features.

    Feature Engineering Steps:
        Family Size: The FamilySize feature is created by summing the number of siblings/spouses (SibSp) and parents/children (Parch) and adding 1 for the individual.
        Is Alone: The IsAlone feature indicates whether the passenger is traveling alone.
        Encoding Categorical Variables: The Embarked and Sex columns are mapped to numerical values for machine learning compatibility.
        Age Grouping: A new categorical feature AgeGroup is created based on age bins, which is then converted to numerical codes.
        Dropping Unnecessary Features: Columns that are not needed for modeling (like Name, Ticket, Cabin, SibSp, and Parch) are dropped.

    Main Execution Block:
        Loads the raw Titanic dataset.
        Calls the feature_engineering function to create new features.
        Saves the resulting DataFrame with engineered features to a CSV file in the data/processed/ directory.

Usage

    Place this script in the model/training/ directory.
    Ensure the Titanic dataset (titanic.csv) is available in the data/raw/ directory.
    Run the script to perform feature engineering and save the modified dataset to engineered_titanic_data.csv.

This feature engineering script aims to enhance the dataset's predictive power by adding new informative features.
"""

import pandas as pd

def feature_engineering(df):
    """Perform feature engineering on the dataset.

    Args:
        df (pd.DataFrame): The input DataFrame containing the original features.

    Returns:
        pd.DataFrame: The DataFrame with engineered features.
    """
    # Example feature engineering steps

    # 1. Create a new feature 'FamilySize' from 'SibSp' and 'Parch'
    df['FamilySize'] = df['SibSp'] + df['Parch'] + 1  # +1 for the individual

    # 2. Create a new feature 'IsAlone' based on 'FamilySize'
    df['IsAlone'] = (df['FamilySize'] == 1).astype(int)

    # 3. Convert 'Embarked' to numerical values
    df['Embarked'] = df['Embarked'].map({'C': 0, 'Q': 1, 'S': 2})

    # 4. Convert 'Sex' to numerical values
    df['Sex'] = df['Sex'].map({'male': 0, 'female': 1})

    # 5. Create new feature 'AgeGroup' based on the 'Age' column
    bins = [0, 12, 20, 40, 60, 80, 100]
    labels = ['Child', 'Teen', 'Adult', 'Middle-Aged', 'Senior', 'Elderly']
    df['AgeGroup'] = pd.cut(df['Age'], bins=bins, labels=labels)

    # 6. Convert 'AgeGroup' to numerical values
    df['AgeGroup'] = df['AgeGroup'].cat.codes

    # 7. Drop features that are not needed
    df.drop(['Name', 'Ticket', 'Cabin', 'SibSp', 'Parch'], axis=1, inplace=True)

    return df

def main():
    # Load the dataset
    df = pd.read_csv('../data/raw/titanic.csv')  # Adjust path as needed

    # Perform feature engineering
    engineered_df = feature_engineering(df)

    # Save the engineered features to a new file
    engineered_df.to_csv('../data/processed/engineered_titanic_data.csv', index=False)
    print("Feature engineering complete. Data saved to 'engineered_titanic_data.csv'.")

if __name__ == "__main__":
    main()
