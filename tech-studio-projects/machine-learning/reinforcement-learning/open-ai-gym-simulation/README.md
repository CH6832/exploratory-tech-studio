# HVAC Optimization with Reinforcement Learning

## Overview

This project demonstrates how to use Reinforcement Learning (RL), specifically the Proximal Policy Optimization (PPO) algorithm, to optimize the operation of a Heating, Ventilation, and Air Conditioning (HVAC) system. The objective is to minimize energy consumption while maintaining occupant comfort levels in a building. The project leverages real-world data from the ASHRAE Great Energy Predictor III competition dataset.

## Project Structure

The repository is organized as follows:

- **`data/`**: Contains the raw and processed data files used for training and evaluation.
  - **`train.csv`**: Raw training data.
  - **`weather_train.csv`**: Weather data.
  - **`processed/`**: Preprocessed feature and target CSV files.
    - **`features.csv`**: Processed feature data used for training.
    - **`target.csv`**: Processed target data (energy consumption).

- **`notebooks/`**: Contains Jupyter notebooks for different stages of the project.
  - **`data_preprocessing.ipynb`**: Data preprocessing steps including merging datasets, feature engineering, and normalization.
  - **`environment_creation.ipynb`**: Definition of the custom Gym environment for the HVAC system.
  - **`training.ipynb`**: Training of the PPO model using the custom Gym environment.
  - **`evaluation.ipynb`**: Evaluation of the trained PPO model.

- **`models/`**: Directory to store the trained models.
  - **`ppo_hvac`**: Saved PPO model file.

- **`README.md`**: This file.

## Requirements

To run the project, you need the following Python libraries:
- `gym`
- `pandas`
- `numpy`
- `tensorflow`
- `keras`
- `stable-baselines3`

You can install the required libraries using pip:

```bash
pip install gym pandas numpy tensorflow keras stable-baselines3
```

## Dataset

The project uses a subset of the ASHRAE Great Energy Predictor III dataset. Example files for `train.csv` and `weather_train.csv` are included in the `data/` directory. For the complete dataset, refer to Kaggle or the ASHRAE competition page.

## How to Run the Project

### 1. Data Preprocessing

Preprocess the raw dataset to extract relevant features and normalize them. This step is handled in the `data_preprocessing.ipynb` notebook.

To run the notebook:

1. Open `notebooks/data_preprocessing.ipynb`.
2. Execute the cells to preprocess the data and save the processed `features.csv` and `target.csv` files in the `data/processed/` directory.

### 2. Environment Creation

Define the custom Gym environment that simulates the HVAC system operation using the processed data. This step is handled in the `environment_creation.ipynb` notebook.

To run the notebook:

1. Open `notebooks/environment_creation.ipynb`.
2. Execute the cells to create the Gym environment.

### 3. Model Training

Train the PPO agent using the defined Gym environment. This step is handled in the `training.ipynb` notebook.

To run the notebook:

1. Open `notebooks/training.ipynb`.
2. Execute the cells to train the PPO model and save it in the `models/` directory.

### 4. Model Evaluation

Evaluate the performance of the trained PPO model. This step is handled in the `evaluation.ipynb` notebook.

To run the notebook:

1. Open `notebooks/evaluation.ipynb`.
2. Execute the cells to evaluate the model and print the total reward.

## File Descriptions

- **`data/train.csv`**: Contains raw training data with building energy usage.
- **`data/weather_train.csv`**: Contains weather data with weather conditions at the building site.
- **`data/processed/features.csv`**: Processed feature data used for model training.
- **`data/processed/target.csv`**: Processed target data (energy consumption) used for model training.
- **`notebooks/data_preprocessing.ipynb`**: Notebook for data preprocessing.
- **`notebooks/environment_creation.ipynb`**: Notebook for creating the Gym environment.
- **`notebooks/training.ipynb`**: Notebook for training the PPO model.
- **`notebooks/evaluation.ipynb`**: Notebook for evaluating the PPO model.
- **`models/ppo_hvac`**: Directory for saving the trained PPO model.

## Example Data Files

Example data files are provided in the `data/processed/` directory:

- **`features.csv`**: Example preprocessed features data.
- **`target.csv`**: Example preprocessed target data (energy consumption).

### Sample Content

**`features.csv`:**

```csv
hour,day,month,air_temperature,dew_temperature,sea_level_pressure,wind_speed
0,1,1,0.0,-0.5,0.1,-0.7
1,1,1,-0.2,0.0,-0.5,-0.9
...
```

**`target.csv`:**

```csv
meter_reading
1.1
1.2
...
```
