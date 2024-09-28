from flask import Flask, request, render_template, redirect, url_for, flash
import joblib
import pandas as pd
from utils.data_preprocessing import preprocess_data
from utils.model_training import train_model
from werkzeug.utils import secure_filename
import os

app = Flask(__name__)
app.secret_key = 'supersecretkey'
app.config['UPLOAD_FOLDER'] = 'datasets/'

# Route for the homepage
@app.route('/')
def index():
    return render_template('base.html')

# Route for uploading data
@app.route('/upload', methods=['GET', 'POST'])
def upload_file():
    if request.method == 'POST':
        if 'file' not in request.files:
            flash('No file part')
            return redirect(request.url)
        file = request.files['file']
        if file.filename == '':
            flash('No selected file')
            return redirect(request.url)
        if file:
            filename = secure_filename(file.filename)
            file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))
            flash('File successfully uploaded!')
            return redirect(url_for('eda', filename=filename))
    return render_template('upload.html')

# Route for Exploratory Data Analysis (EDA)
@app.route('/eda/<filename>', methods=['GET'])
def eda(filename):
    data_path = os.path.join(app.config['UPLOAD_FOLDER'], filename)
    df = pd.read_csv(data_path)
    
    # Perform EDA (Summary statistics, etc.)
    summary = df.describe().to_html()
    return render_template('eda.html', tables=[summary], filename=filename)

# Route for feature selection
@app.route('/features/<filename>', methods=['GET', 'POST'])
def feature_selection(filename):
    if request.method == 'POST':
        selected_features = request.form.getlist('features')
        return redirect(url_for('train_model', filename=filename, features=','.join(selected_features)))
    
    # Show column names for selection
    data_path = os.path.join(app.config['UPLOAD_FOLDER'], filename)
    df = pd.read_csv(data_path)
    columns = df.columns
    return render_template('feature_selection.html', columns=columns, filename=filename)

# Route for model training
@app.route('/train/<filename>/<features>', methods=['GET'])
def train_model_route(filename, features):
    data_path = os.path.join(app.config['UPLOAD_FOLDER'], filename)
    df = pd.read_csv(data_path)
    selected_features = features.split(',')
    
    # Preprocess data and train model
    X, y = preprocess_data(df, selected_features)
    model, accuracy = train_model(X, y)
    
    # Save model
    model_filename = os.path.join('models', 'model.pkl')
    joblib.dump(model, model_filename)
    
    return render_template('results.html', accuracy=accuracy)

# Route for model evaluation and results
@app.route('/results')
def results():
    # Load evaluation metrics and render results
    return render_template('results.html')


if __name__ == '__main__':
    app.run(debug=True)
