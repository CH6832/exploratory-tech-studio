import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
import joblib

# Load scaled features and labels
def load_scaled_data(file_path):
    return pd.read_csv(file_path)

if __name__ == "__main__":
    scaled_file_path = "data/processed/scaled_features.csv"
    data = load_scaled_data(scaled_file_path)

    # Prepare features and labels
    X = data.values  # Assuming this is the scaled feature set
    y = pd.read_csv("data/processed/processed_data.csv")['label']  # Load labels separately
    
    # Split the dataset
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

    # Train a Random Forest model
    model = RandomForestClassifier(n_estimators=100, random_state=42)
    model.fit(X_train, y_train)

    # Save the model
    joblib.dump(model, 'models/intrusion_detection_model.pkl')
    print("Model training complete.")
