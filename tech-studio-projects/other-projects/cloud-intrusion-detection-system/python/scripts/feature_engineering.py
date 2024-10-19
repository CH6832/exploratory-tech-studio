import pandas as pd
from sklearn.preprocessing import StandardScaler

# Load processed data
def load_processed_data(file_path):
    return pd.read_csv(file_path)

# Scale features
def scale_features(X):
    scaler = StandardScaler()
    return scaler.fit_transform(X), scaler

if __name__ == "__main__":
    processed_file_path = "data/processed/processed_data.csv"
    data = load_processed_data(processed_file_path)
    
    X = data.drop('label', axis=1)
    y = data['label']
    
    X_scaled, scaler = scale_features(X)
    
    # Save the scaled features
    pd.DataFrame(X_scaled).to_csv("data/processed/scaled_features.csv", index=False)
    print("Feature scaling complete.")
