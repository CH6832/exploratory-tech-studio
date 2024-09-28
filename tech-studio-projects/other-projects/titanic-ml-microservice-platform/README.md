# RealTime-ML-Microservice-Platform

## Overview
This project is a real-time machine learning microservice platform that predicts Titanic survival using a microservices architecture. It is designed to demonstrate the entire workflow of data ingestion, model training, serving, and monitoring in a scalable environment.

## Features
- **Data Ingestion Service**: Collects and processes data from various sources.
- **Model Training and Evaluation**: Trains machine learning models and evaluates their performance.
- **Model Serving with Flask**: Deploys trained models for serving predictions via REST API.
- **Real-time Predictions via WebSocket**: Provides real-time predictions and interactions using WebSocket.
- **Monitoring Service for Model Performance**: Tracks model performance and alerts on deviations.

## Getting Started

### Prerequisites
- [Docker](https://www.docker.com/get-started) and [Docker Compose](https://docs.docker.com/compose/) installed on your machine.
- Basic knowledge of Python and machine learning concepts.

### Installation Steps
1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/Realtime-ML-Microservice-Platform.git
   cd Realtime-ML-Microservice-Platform
   ```
   
2. **Build the Docker containers using Docker Compose**:
   ```bash
   docker-compose up --build
   ```

3. **Access the Application**:
   - **Model Serving**: Open your browser and navigate to `http://localhost:5000` (or the specified port in your Docker configuration).
   - **WebSocket**: Connect to the WebSocket server at `ws://localhost:5001` (or the specified port).

---

### **GitHub Repository Structure**

Below is the directory structure for the **"Realtime-ML-Microservice-Platform"** project. It follows best practices for structuring a Data Science project with microservices, scalability, and real-time model inference in mind.

```
Realtime-ML-Microservice-Platform/
│
├── .gitignore
├── README.md
├── docker-compose.yml
├── requirements.txt
├── LICENSE
│
├── data/
│   ├── raw/
│   ├── processed/
│   └── external/
│
├── notebooks/
│   ├── EDA.ipynb
│   └── report_generation.ipynb
│
├── reports/                     # New directory for reports
│   ├── final_report.pdf         # Generated PDF report
│
├── model/
│   ├── training/
│   │   ├── model_training.py
│   │   ├── preprocessing.py
│   │   ├── feature_engineering.py
│   │   └── model_evaluation.py
│   ├── trained_models/
│   └── serving/
│       ├── model_inference.py
│       └── Dockerfile
│
├── services/
│   ├── data-ingestion/
│   │   ├── Dockerfile
│   │   ├── ingest_data.py
│   │   └── config.yaml
│   ├── model-serving/
│   │   ├── Dockerfile
│   │   ├── app.py
│   │   ├── requirements.txt
│   │   └── config.yaml
│   ├── monitoring/
│   │   ├── Dockerfile
│   │   ├── monitoring_service.py
│   │   └── alerting.py
│   └── websocket-server/
│       ├── Dockerfile
│       ├── server.py
│       └── websocket_client.py
│
├── deployment/
│   ├── kubernetes/
│   │   ├── deployment.yaml
│   │   ├── service.yaml
│   │   └── configmap.yaml
│   └── cloud/
│       ├── aws/
│       │   ├── ecs_deployment.yaml
│       │   ├── lambda_deploy.py
│       │   └── cloudformation_template.yaml
│       └── gcp/
│           ├── gcp_deployment.yaml
│           └── cloud_function_deploy.py
│
└── tests/
    ├── data_ingestion_tests.py
    ├── model_serving_tests.py
    └── websocket_tests.py
```

### **Repository Explanation**

---

#### 1. **Top-Level Files**

- **`.gitignore`**: Specifies files and directories that Git should ignore (e.g., `*.pyc`, virtual environments, or large datasets).
- **`README.md`**: Describes the project, provides setup instructions, usage examples, and relevant links.
- **`docker-compose.yml`**: Defines and orchestrates multiple Docker containers, enabling easier management of the services.
- **`requirements.txt`**: Python dependencies used for the project.
- **`LICENSE`**: Defines the open-source license for the project.

---

#### 2. **Data Directory (`data/`)**

- **`raw/`**: Raw, unprocessed data collected from external sources.
- **`processed/`**: Cleaned and transformed data, ready for model training and evaluation.
- **`external/`**: Any external datasets or APIs pulled during the data collection process.

---

#### 3. **Notebooks Directory (`notebooks/`)**

- **`EDA.ipynb`**: A Jupyter notebook used for Exploratory Data Analysis (EDA), which includes visualizations, basic statistics, and insights into data.
- **`report_generation.ipynb`**: A Jupyter notebook to generate the final project report and visualize results.

---

#### 4. **Reports Directory (`reports/`)**

- **`final_report.pdf`**: The generated PDF report summarizing the entire project, including methodologies, visualizations, and interpretations of the results.

---

#### 5. **Model Directory (`model/`)**

- **`training/`**: Contains scripts for training the machine learning model.
  - **`model_training.py`**: Script to train the model using the processed data.
  - **`preprocessing.py`**: Handles data preprocessing (e.g., cleaning, feature engineering).
  - **`feature_engineering.py`**: Scripts for feature extraction and transformation.
  - **`model_evaluation.py`**: Evaluates model performance with metrics like accuracy, precision, and recall.
  
- **`trained_models/`**: Stores trained model artifacts (e.g., saved models as `.pkl` files).

- **`serving/`**: Scripts related to model inference and deployment.
  - **`model_inference.py`**: Serves predictions using the trained model in production.
  - **`Dockerfile`**: Defines how to containerize the model-serving service.

---

#### 6. **Services Directory (`services/`)**

The **microservices** architecture is reflected here, with different services independently developed and containerized.

- **`data-ingestion/`**: Service responsible for collecting and preprocessing the data.
  - **`Dockerfile`**: Builds the Docker image for the data ingestion service.
  - **`ingest_data.py`**: Script to collect data from APIs, databases, or files and insert it into NoSQL databases.
  - **`config.yaml`**: Configuration file for the data ingestion service (e.g., API keys, DB connection info).

- **`model-serving/`**: Service responsible for real-time model inference.
  - **`Dockerfile`**: Builds the Docker image for the model serving service.
  - **`app.py`**: The main FastAPI or Flask application that serves model predictions via REST or WebSocket.
  - **`requirements.txt`**: Dependencies for the model-serving service (e.g., `FastAPI`, `scikit-learn`, etc.).
  - **`config.yaml`**: Configuration file for model serving (e.g., model path, API routes).

- **`monitoring/`**: Service for monitoring model performance and alerting.
  - **`Dockerfile`**: Containerizes the monitoring service.
  - **`monitoring_service.py`**: Tracks model performance, detects model drift, and sends alerts.
  - **`alerting.py`**: Implements alerting (e.g., sends emails or triggers webhooks when performance degrades).

- **`websocket-server/`**: WebSocket server for real-time model prediction and communication with clients.
  - **`Dockerfile`**: Builds the WebSocket server image.
  - **`server.py`**: WebSocket server for handling real-time communication with clients.
  - **`websocket_client.py`**: Client-side example to interact with the WebSocket server (e.g., send requests for predictions and receive responses).

---

#### 7. **Deployment Directory (`deployment/`)**

- **`kubernetes/`**: Kubernetes manifests for deploying microservices.
  - **`deployment.yaml`**: Manages the deployment of microservices.
  - **`service.yaml`**: Exposes microservices to the external network via LoadBalancer or NodePort.
  - **`configmap.yaml`**: Stores configuration details (e.g., DB credentials, API keys) as environment variables.

- **`cloud/`**: Cloud-specific deployment files for AWS, GCP, and Azure

.
  - **`aws/`**: Scripts to deploy on AWS ECS, Lambda, or CloudFormation.
  - **`gcp/`**: Deployment files for Google Cloud Platform, such as Cloud Function deployments.

---

#### 8. **Tests Directory (`tests/`)**

- **`data_ingestion_tests.py`**: Unit and integration tests for the data ingestion service.
- **`model_serving_tests.py`**: Tests to verify that the model is serving predictions correctly.
- **`websocket_tests.py`**: Unit tests for WebSocket server communication.

---

### **Key Components**

1. **Microservices**: Each microservice (data ingestion, model serving, monitoring, WebSocket) has its own directory with independent Dockerfiles and configuration files.
2. **Docker & Kubernetes**: Containers and Kubernetes manifests enable deployment and scalability of the services. Kubernetes config files allow for easy orchestration of microservices.
3. **Real-time**: WebSocket integration provides real-time data exchange between clients and the server.
4. **Monitoring**: The monitoring service tracks model performance and detects data/model drift over time.
5. **Scalability**: Cloud deployment files for AWS and GCP ensure that the project is scalable and production-ready on major cloud platforms.

---

### **Next Steps**

1. **Write the `README.md` file**: Add detailed documentation explaining how to set up and run each service, how to train and deploy the model, and how to run the project both locally (using Docker) and in a cloud environment (using Kubernetes or ECS).
  
2. **Add CI/CD pipelines**: Implement a continuous integration and continuous deployment (CI/CD) pipeline (e.g., GitHub Actions or Jenkins) to automate testing and deployment across environments.

3. **Set up monitoring**: Add integration with services like **Prometheus** and **Grafana** to monitor model performance, container health, and traffic.
