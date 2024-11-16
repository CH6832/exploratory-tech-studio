# High-Frequency Trading (HFT) Simulation System

## Overview
This project is a **high-frequency trading (HFT) simulation system** designed to demonstrate trading strategies in a low-latency environment using **Java RTS (Real-Time System)**. The system is architected with a **hybrid microservice-monolithic** design. The **core trading engine** is implemented, which is capable of processing high-frequency trades with optimized performance. However, the **microservices** and **frontend** components are currently work in progress.

### Core System Implemented
The core system includes the main trading engine that handles:
- **Order Matching**: Efficiently matches buy and sell orders based on price and time priority.
- **Risk Management**: Implements margin calculations, order validation, and real-time risk checks.
- **Market Data Ingestion**: Ingests and processes real-time market data for decision-making.
- **Order Book Management**: Manages and updates the order book with buy and sell orders in real-time.

The remaining components such as **microservices** for auxiliary tasks (data processing, event handling, etc.) and the **frontend UI** are still under development.

---

## Features
- **Core Trading Engine**: High-frequency trading engine with low-latency order matching, risk management, and real-time market data ingestion.
- **Microservices**: Will support tasks such as data processing, order matching, risk management, and event handling (currently work in progress).
- **Real-Time Data Integration**: Ingest real-time data from APIs like **Yahoo Finance**, **Alpha Vantage**, and **Binance**.
- **Optimized Java RTS Configuration**: Configured for low-latency, high-performance trading operations.
- **Scalable and Containerized**: The system uses **Docker** for local development and **Kubernetes** for production deployment.

---

## Directory Structure
- **`core-system/`** - Contains the main trading engine and core logic.
- **`microservices/`** - A collection of services for auxiliary tasks (data processing, messaging, etc.) - **Work in Progress**.
- **`frontend/`** - A web-based UI for monitoring and managing trading activities - **Work in Progress**.
- **`kubernetes/`** - Deployment configurations for Kubernetes.
- **`scripts/`** - Build and deployment scripts for automation.

---

## Requirements
To run and build the project, ensure you have the following tools installed:

- **Java RTS (Real-Time System)** for low-latency processing.
- **Docker** and **Docker Compose** for containerization and local development.
- **Kubernetes** for deployment in a production environment.
- **Maven** for building and managing dependencies in Java projects.

---

## Setup

### 1. Clone the repository

Clone the repository to your local machine:

```bash
git clone <repo_url>
cd hft-simulation
```

### 2. Build and run with Docker Compose

Use Docker Compose to build and run the entire project, including the core system and any services:

```bash
docker-compose up --build
```

This will build the Docker containers and start the system with all the necessary services.

### 3. Run the Core System Locally

To run the **core trading engine** locally, navigate to the `core-system` directory and use Maven:

```bash
cd core-system
mvn clean install
mvn spring-boot:run
```

The system will start and will listen for incoming data, orders, and will execute basic trading operations.

### 4. Kubernetes Deployment (For Production)

If you're deploying to Kubernetes, use the provided configurations in the `kubernetes/` folder to manage deployment, scaling, and orchestration.

To deploy the system in Kubernetes, run:

```bash
kubectl apply -f kubernetes/
```

This will set up the necessary resources and deploy the application to your Kubernetes cluster.

---

## Current Development Status

### Core System
The **core system** is fully implemented and includes the order matching engine, risk management, order book management, and market data ingestion. The core logic is optimized for low-latency and high-throughput operations, designed for high-frequency trading environments.

### Microservices
The **microservices** architecture is being developed to handle various auxiliary tasks such as:
- Data processing (e.g., aggregating market data from multiple sources).
- Event handling (e.g., notifications for order status updates).
- Integration with external APIs and services (e.g., risk checks, margin calculations).

These microservices are still under development and will be added in future updates.

### Frontend UI
A **web-based frontend** is planned to allow users to monitor trading activities, risk metrics, and system health. It will be built using **Vue.js** for a lightweight and responsive experience. The frontend is still under development and will be added in later stages.

---

## Future Plans

- **Finish implementing microservices** for data processing, event handling, and risk management.
- **Develop the frontend UI** using Vue.js for visualizing system metrics, order statuses, and risk exposure.
- **Backtesting Engine**: Implement historical data replay and strategy testing capabilities.
- **Execution Management System (EMS)**: Develop logic for routing orders to exchanges with advanced execution strategies.

---

## Contributing

Contributions to this project are welcome! To contribute:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to your branch (`git push origin feature-branch`).
5. Create a pull request with a detailed description of your changes.

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## Contact

For any questions, comments, or support, feel free to reach out to the project maintainer:

- **Name**: [Your Name]
- **Email**: [Your Email]
- **LinkedIn/GitHub**: [Your LinkedIn/GitHub URL]

---

## Acknowledgements

- **Vue.js** for the frontend UI framework.
- **Java RTS (Real-Time System)** for low-latency processing and performance optimization.
- **Docker** for containerization and local development.
- **Kubernetes** for container orchestration and production deployment.
- **Maven** for dependency management and build automation.
