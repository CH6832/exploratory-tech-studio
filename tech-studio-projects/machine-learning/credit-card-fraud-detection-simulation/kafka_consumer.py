#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""kafka_consumer.py

The consumer will receive transactions from Kafka, apply
the machine learning model, and detect fraud.
"""

import json
from sqlite3 import DataError
import joblib
import numpy as np
# For kafka-python, please install:
# pip install git+https://github.com/dpkp/kafka-python.git
# because of error:ModuleNotFoundError: No module named 'kafka.vendor.six.moves'
from confluent_kafka import Consumer, KafkaError


def create_consumer() -> Consumer:
    """Creates and returns a Kafka Consumer for the 'credit-card-transactions' topic."""
    config = {
        'bootstrap.servers': 'localhost:9092',
        'group.id': 'credit-card-fraud-detector',
        'auto.offset.reset': 'earliest',
        'enable.auto.commit': False
    }
    
    consumer = Consumer(config)
    consumer.subscribe(['credit-card-transactions'])

    return consumer


def start_consumer():
    """Start the consumer."""
    # Load the trained model and scaler
    model = joblib.load('models/fraud_detection_model.pkl')
    scaler = joblib.load('models/scaler.pkl')

    consumer = create_consumer()
    
    try:
        while True:
            msg = consumer.poll(1.0)  # Timeout of 1 second
            if msg is None:
                continue
            if msg.error():
                if msg.error().code() == KafkaError._PARTITION_EOF:
                    # End of partition event
                    continue
                else:
                    print(f"Error: {msg.error()}")
                    continue

            # Deserialize the message value
            transaction = json.loads(msg.value().decode('utf-8'))
            print(f"Received transaction: {transaction}")

    except KeyboardInterrupt:
        pass
    finally:
        consumer.close()

    print("Listening for transactions...")

    for message in consumer:
        transaction = message.value
        print(f'Received transaction: {transaction}')

        # Prepare the features for prediction
        features = np.array([
            transaction['amount'],
            transaction['transaction_frequency'],
            # Add other features as needed, possibly from the generated transaction
        ]).reshape(1, -1)

        # Scale the features
        features_scaled = scaler.transform(features)

        # Predict fraud (0 = normal, 1 = fraud)
        fraud_prediction = model.predict(features_scaled)

        if fraud_prediction[0] == 1:
            print(f'ALERT: Fraud detected in transaction: {transaction}')
        else:
            print(f'Transaction is normal: {transaction}')

if __name__ == "__main__":
    start_consumer()
