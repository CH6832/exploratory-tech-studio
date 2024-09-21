import pandas as pd
from sklearn.preprocessing import StandardScaler

def preprocess_data(train_file, weather_file, output_dir):
    # Load the dataset
    data = pd.read_csv(train_file)
    weather_data = pd.read_csv(weather_file)

    # Merge data
    data = data.merge(weather_data, on=['site_id', 'timestamp'], how='left')

    # Feature engineering
    data['timestamp'] = pd.to_datetime(data['timestamp'])
    data['hour'] = data['timestamp'].dt.hour
    data['day'] = data['timestamp'].dt.day
    data['month'] = data['timestamp'].dt.month

    # Select relevant features
    features = ['hour', 'day', 'month', 'air_temperature', 'dew_temperature', 'sea_level_pressure', 'wind_speed']
    target = 'meter_reading'

    # Handle missing values
    data = data.dropna(subset=features)

    # Normalize features
    scaler = StandardScaler()
    data[features] = scaler.fit_transform(data[features])

    # Prepare data for training
    X = data[features]
    y = data[target]

    # Save processed data
    X.to_csv(f'{output_dir}/features.csv', index=False)
    y.to_csv(f'{output_dir}/target.csv', index=False)

if __name__ == "__main__":
    preprocess_data('data/train.csv', 'data/weather_train.csv', 'data/processed')
