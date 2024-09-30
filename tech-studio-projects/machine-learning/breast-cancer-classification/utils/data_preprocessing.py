import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler

def preprocess_data(df, selected_features):
    X = df[selected_features]
    y = df['target']  # Assuming 'target' is the dependent variable
    
    # Clean and scale data
    scaler = StandardScaler()
    X_scaled = scaler.fit_transform(X)
    
    return X_scaled, y
