# Renewable Energy Price Forecasting

This project is designed to predict the price of renewable energy based on historical data. The goal is to train a machine learning model using a simple linear regression algorithm and make predictions for future energy prices based on features (e.g., energy demand, time, etc.). The project uses Scala and simple file-based data to keep it lightweight and easy to use.

## Table of Contents

- [Renewable Energy Price Forecasting](#renewable-energy-price-forecasting)
  - [Table of Contents](#table-of-contents)
  - [Project Overview](#project-overview)
    - [Key Features:](#key-features)
  - [Features](#features)
  - [Technologies Used](#technologies-used)
  - [Setup Instructions](#setup-instructions)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
  - [Usage](#usage)
    - [Training the Model](#training-the-model)
    - [Making Predictions](#making-predictions)
    - [Model Details](#model-details)
  - [File Structure](#file-structure)
  - [Model Details](#model-details-1)
  - [License](#license)

## Project Overview

This project aims to forecast the price of renewable energy based on historical data using a **linear regression model**. It utilizes historical data from CSV files, trains a model using simple machine learning, and saves the trained model parameters (slope and intercept). You can then use the model to predict energy prices for a given input feature.

### Key Features:
- **Data Parsing**: Load and process historical data from CSV files.
- **Model Training**: Train a linear regression model using historical energy prices.
- **Model Prediction**: Use the trained model to predict energy prices for a given input feature.
- **Error Handling**: Robust error handling for malformed CSV data (e.g., non-numeric values).

## Features

- **Model Training**: Trains a simple linear regression model using the least squares method to predict energy prices.
- **Prediction**: The model can predict energy prices based on a given input feature.
- **Data Parsing**: Uses CSV format for loading historical data, where the features are numeric and prices are numeric.
- **Model Persistence**: Saves the model parameters (slope and intercept) to a file so that they can be re-used for future predictions.
- **Error Handling**: Skips any rows with malformed or invalid data in the CSV file.

## Technologies Used

- **Scala 3**: The main programming language.
- **SBT (Scala Build Tool)**: Used to manage dependencies and build the project.
- **Apache Commons CSV**: For reading and parsing CSV files.
- **Simple Linear Regression**: Used to model the energy price predictions.

## Setup Instructions

### Prerequisites
To run this project, you must have the following installed on your local machine:

1. **Java 8 or newer** (required for Scala and Spark).
2. **Scala 3.3.4** (for running the Scala code).
3. **SBT 1.10.6** (to manage project dependencies and build).
4. **Apache Commons CSV library** for CSV parsing.

### Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/renewable-energy-price-forecasting.git
   cd renewable-energy-price-forecasting
   ```

2. **Set up the project**:
   - Install SBT (Scala Build Tool). Follow the [official installation guide](https://www.scala-sbt.org/1.x/docs/).
   - Run the following command to install dependencies:
     ```bash
     sbt update
     ```

3. **Make sure you have the data**: Create a folder `data` inside the project directory and place your `historical_energy_prices.csv` there. Example data format:
   ```csv
   timestamp,feature,price
   2023-01-01 00:00:00,1.0,50.0
   2023-01-01 01:00:00,2.0,55.0
   2023-01-01 02:00:00,3.0,60.0
   ```

4. **Build the project**: In the terminal, run:
   ```bash
   sbt compile
   ```

## Usage

### Training the Model

To train the linear regression model, run the following command:
```bash
sbt run
```
This will:
1. Load historical energy data from `data/historical_energy_prices.csv`.
2. Train the linear regression model using the data.
3. Save the model parameters (slope and intercept) to `model/energy-price-model.txt`.

### Making Predictions

To make a prediction using the trained model, provide an input feature (e.g., an energy demand value) as follows:

```scala
val predictedPrice = forecaster.predict(10.5)  // Replace 10.5 with your input feature
println(s"Predicted energy price: $$${predictedPrice}")
```

### Model Details
The model is a simple linear regression that predicts the price of renewable energy based on a single feature. The model parameters are stored as a slope and intercept.

- **Model Training**: Uses the least squares solution for linear regression to find the best-fitting line.
- **Prediction**: Uses the formula `y = slope * x + intercept` to calculate the predicted price based on the input feature.

## File Structure

Here's a brief overview of the project directory structure:

```plaintext
renewable-energy-price-forecasting/
│
├── data/
│   └── historical_energy_prices.csv   # Your historical data
│
├── model/
│   └── energy-price-model.txt         # The saved model (slope and intercept)
│
├── src/
│   └── main/
│       └── scala/
│           ├── EnergyPriceForecaster.scala  # Main logic for forecasting and training
│           └── Main.scala                  # Main entry point for running the project
│
├── build.sbt                         # SBT build file with dependencies
└── README.md                         # Project documentation
```

- **`data/historical_energy_prices.csv`**: Contains the historical data for training and prediction.
- **`model/energy-price-model.txt`**: Stores the trained model (slope and intercept) after training.

## Model Details

- **Linear Regression**: The model uses a linear regression algorithm to predict energy prices. It assumes a linear relationship between a feature (like energy demand) and the price of energy.
  
- **Model Formula**: `price = slope * feature + intercept`

The `slope` and `intercept` values are saved after training, and they can be loaded for future predictions.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
