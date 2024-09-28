

import pandas as pd
from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split
from sklearn.metrics import classification_report
import joblib


def load_data(filepath):
    return pd.read_csv(filepath)


def train_model(data):
    X = data.drop('Survived', axis=1)
    y = data['Survived']
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

    model = RandomForestClassifier()
    model.fit(X_train, y_train)

    y_pred = model.predict(X_test)
    print(classification_report(y_test, y_pred))

    joblib.dump(model, 'trained_models/titanic_model.pkl')

if __name__ == '__main__':
    data = load_data('../data/processed/processed_titanic.csv')
    train_model(data)
