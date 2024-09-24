# Credit Card Fraud Detection Simulation with Apache Kafka

This project simulates a real-world credit card fraud detection system, using Apache Kafka for real-time data streaming and a machine learning model for detecting fraudulent transactions. The application produces simulated credit card transactions, streams them through Kafka, and processes them using a consumer that applies a pre-trained fraud detection model.

## Project Overview

The project simulates real-time credit card transactions using a Kafka producer, sends these transactions to a Kafka topic, and consumes the data using a Kafka consumer. The consumer processes each transaction and predicts whether it's fraudulent using a machine learning model trained on historical credit card transaction data.

### Features

- **Real-Time Simulation**: The producer generates random credit card transactions and streams them to the Kafka broker.
- **Fraud Detection**: The consumer applies a machine learning model (Random Forest) trained on real credit card transaction data to classify transactions as legitimate or fraudulent.
- **Scalable Architecture**: Built using Apache Kafka, the system can scale to handle millions of transactions in real-time.
- **Multithreading**: The Kafka producer uses multithreading to send multiple transactions concurrently, simulating a real-world environment.

---

## Table of Contents

- [Credit Card Fraud Detection Simulation with Apache Kafka](#credit-card-fraud-detection-simulation-with-apache-kafka)
  - [Project Overview](#project-overview)
    - [Features](#features)
  - [Table of Contents](#table-of-contents)
  - [Project Architecture](#project-architecture)
    - [Workflow](#workflow)
  - [Technologies Used](#technologies-used)
  - [Project Structure](#project-structure)
  - [Installation and Setup](#installation-and-setup)
    - [Prerequisites](#prerequisites)
    - [Setup Kafka and Zookeeper](#setup-kafka-and-zookeeper)
  - [Running the Project](#running-the-project)
    - [Step 1: Train the Fraud Detection Model](#step-1-train-the-fraud-detection-model)
    - [Step 2: Start the Kafka Producer](#step-2-start-the-kafka-producer)
    - [Step 3: Start the Kafka Consumer](#step-3-start-the-kafka-consumer)
  - [Resources](#resources)
  - [License](#license)
  - [Future Improvements](#future-improvements)

---

## Project Architecture

1. **Kafka Producer**: Simulates real-time credit card transactions and sends them to a Kafka topic (`credit-card-transactions`).
2. **Kafka Consumer**: Consumes the transactions, preprocesses them, and applies a pre-trained Random Forest model to detect fraud.
3. **Machine Learning**: The fraud detection model is trained on the `creditcard.csv` dataset using scikit-learn. The model predicts whether each transaction is legitimate or fraudulent.

### Workflow

1. The producer simulates credit card transactions and sends them to Kafka.
2. Kafka brokers store and stream the data to consumers.
3. The consumer fetches transactions from the Kafka topic and applies the fraud detection model.
4. The results are displayed (e.g., `Transaction ID X is fraudulent` or `Transaction ID Y is legitimate`).

---

## Technologies Used

- **Apache Kafka**: For real-time data streaming and message brokering.
- **Zookeeper**: Manages Kafka broker metadata and helps coordinate distributed processes.
- **Python**: Main language for simulating transactions, handling Kafka, and applying machine learning models.
  - `confluent-kafka`: Python client for Kafka.
  - `pandas`, `scikit-learn`: For dataset manipulation and model training.
  - `joblib`: For saving and loading the trained model and preprocessor.
- **Multithreading**: Simulates multiple transactions being processed concurrently.
- **Windows/Linux Compatibility**: The project works on both operating systems and checks for the environment at runtime.

---

## Project Structure

```
.
├── kafka_producer.py              # Kafka Producer that generates and sends simulated transactions.
├── kafka_consumer.py              # Kafka Consumer that processes and evaluates transactions.
├── fraud_detection_model.pkl      # Pre-trained Random Forest model (saved model).
├── scaler.pkl                     # Pre-trained Standard Scaler for feature scaling.
├── creditcard.csv                 # Dataset for training the fraud detection model.
├── train_model.py                 # Script to train the fraud detection model.
├── README.md                      # Project documentation (this file).
└── requirements.txt               # Python dependencies.
```

---

## Installation and Setup

### Prerequisites

1. **Apache Kafka and Zookeeper**:
   - Download and install [Apache Kafka](https://kafka.apache.org/downloads) and [Zookeeper](https://zookeeper.apache.org/releases.html).
   - Make sure Kafka and Zookeeper are properly configured. Ensure `server.properties` and `zookeeper.properties` are correctly set up.
   
2. **Python**:
   - Python 3.8 or higher is required.
   - Install dependencies with `pip`:
   
     ```bash
     pip install -r requirements.txt
     ```

### Setup Kafka and Zookeeper

1. **Start Zookeeper**:
   
   ```bash
   zookeeper-server-start.bat config/zookeeper.properties
   ```

2. **Start Kafka Broker**:
   
   ```bash
   kafka-server-start.bat config/server.properties
   ```

3. **Create Kafka Topic**:
   
   Run this command to create a Kafka topic for transactions:
   
   ```bash
   kafka-topics.bat --create --topic credit-card-transactions --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
   ```

---

## Running the Project

### Step 1: Train the Fraud Detection Model

You need to first train the machine learning model. Run the `train_model.py` script:

```bash
python train_model.py
```

This script will generate the `fraud_detection_model.pkl` and `scaler.pkl` files.

### Step 2: Start the Kafka Producer

The producer simulates transactions and streams them to the Kafka topic. Start it using:

```bash
python kafka_producer.py
```

This will generate 1000 credit card transactions and stream them to Kafka.

### Step 3: Start the Kafka Consumer

The consumer listens to the Kafka topic, processes the transactions, and applies the fraud detection model to classify them:

```bash
python kafka_consumer.py
```

You should see output indicating whether each transaction is fraudulent or legitimate.

---

## Resources

- [Apache Kafka Documentation](https://kafka.apache.org/documentation/)
- [Zookeeper Documentation](https://zookeeper.apache.org/doc/current/)
- [Scikit-Learn Documentation](https://scikit-learn.org/stable/documentation.html)
- [Credit Card Fraud Detection Dataset](https://www.kaggle.com/datasets/mlg-ulb/creditcardfraud)

---

## Future Improvements

- **Scalability**: Use distributed computing or containerization (e.g., Kubernetes, Docker) to scale the project further.
- **Real-Time Dashboard**: Integrate with a front-end or dashboard (e.g., Grafana) to visualize transaction streams.
- **Model Update**: Periodically retrain the model with updated data to maintain high accuracy in fraud detection.
