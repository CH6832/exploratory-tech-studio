#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""main.py

Program entry point. Contains the driving code
"""

import subprocess
import time
from model_training import train_model


def main():
    """Driving code."""
    # Train the model and save it
    train_model()

    # Start the consumer in a separate process to predict the fraud
    try:
        consumer_process = subprocess.Popen(['python', 'kafka_consumer.py'])
    except subprocess.CalledProcessError as e:
        print(f"An error occurred while running the consumer script: {e}")
    
    # Give the consumer a moment to start
    time.sleep(2)
    
    # Start the producer
    try:
        subprocess.run(['python', 'kafka_producer.py'], check=True)
    except subprocess.CalledProcessError as e:
        print(f"An error occurred while running the producer script: {e}")
    
    # Optional: Terminate the consumer after the producer finishes
    consumer_process.terminate()

if __name__ == "__main__":
    main()
