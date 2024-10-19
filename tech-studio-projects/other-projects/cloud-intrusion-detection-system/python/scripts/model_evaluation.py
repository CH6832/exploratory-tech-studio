import pandas as pd
from sklearn.metrics import classification_report, confusion_matrix
import joblib

# Load model and data
def load_model(model_path):
    return joblib.load(model_path)

if __name__ == "__main__":
    model = load_model('models/intrusion_detection_model.pkl')
    scaled_file_path = "data/processed/scaled_features.csv"
    data = pd.read_csv(scaled_file_path)
    
    X = data.values
    y = pd.read_csv("data/processed/processed_data.csv")['label']  # Load labels separately
    
    # Make predictions
    y_pred = model.predict(X)

    # Evaluation
    print(confusion_matrix(y, y_pred))
    print(classification_report(y, y_pred))
