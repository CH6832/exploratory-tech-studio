from flask import Flask, request, jsonify
import joblib
import numpy as np
import pandas as pd

app = Flask(__name__)

# Load the model and scaler
model = joblib.load('models/intrusion_detection_model.pkl')
scaler = joblib.load('models/scaler.pkl')  # Ensure to save scaler in model_training.py

@app.route('/predict', methods=['POST'])
def predict():
    data = request.get_json(force=True)
    data_df = pd.DataFrame(data)
    
    # Scale the input data
    scaled_data = scaler.transform(data_df)
    prediction = model.predict(scaled_data)
    
    return jsonify({'prediction': int(prediction[0])})

if __name__ == '__main__':
    app.run(debug=True)
