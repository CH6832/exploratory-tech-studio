from flask import Flask, request, jsonify
import pickle
import numpy as np

# Initialize the Flask application
app = Flask(__name__)

# Load the trained model
MODEL_PATH = 'trained_models/titanic_model.pkl'

try:
    with open(MODEL_PATH, 'rb') as model_file:
        model = pickle.load(model_file)
except Exception as e:
    print(f"Error loading model: {e}")
    model = None

@app.route('/')
def home():
    return "Welcome to the Titanic Survival Prediction API!"

@app.route('/predict', methods=['POST'])
def predict():
    if model is None:
        return jsonify({"error": "Model not loaded!"}), 500

    # Get JSON data from the request
    data = request.json

    # Example of expected input data format
    # {
    #     "Pclass": 3,
    #     "Sex": "male",
    #     "Age": 22,
    #     "SibSp": 1,
    #     "Parch": 0,
    #     "Fare": 7.25,
    #     "Embarked": "S"
    # }
    
    try:
        # Preprocess input data here
        input_data = preprocess_input(data)

        # Make a prediction
        prediction = model.predict([input_data])

        # Return the prediction
        return jsonify({"survived": prediction[0]})
    
    except Exception as e:
        return jsonify({"error": str(e)}), 400

def preprocess_input(data):
    """
    Preprocess the input data for model prediction.
    This function should include necessary transformations.
    Modify according to your model's input requirements.
    """
    # Example: Convert categorical variables, normalize/standardize, etc.
    # Here, we're assuming the input will have already been formatted correctly.

    # Example preprocessing steps:
    pclass = data.get('Pclass', 3)
    sex = 1 if data.get('Sex', 'male') == 'male' else 0  # Encoding sex
    age = data.get('Age', 30)
    sibsp = data.get('SibSp', 0)
    parch = data.get('Parch', 0)
    fare = data.get('Fare', 0)
    embarked = 1 if data.get('Embarked', 'S') == 'C' else 0  # Example encoding

    return [pclass, sex, age, sibsp, parch, fare, embarked]

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
