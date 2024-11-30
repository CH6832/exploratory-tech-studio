# System Architecture Document for Portfolio Management Simulation System  

---

## Table of Contents  

- [System Architecture Document for Portfolio Management Simulation System](#system-architecture-document-for-portfolio-management-simulation-system)  
  - [Introduction](#introduction)  
  - [High-Level Architecture Overview](#high-level-architecture-overview)  
  - [Component Architecture](#component-architecture)  
    - [Market Data Ingestion Service](#market-data-ingestion-service)  
    - [Strategy Engine](#strategy-engine)  
    - [Order Management System (OMS)](#order-management-system-oms)  
    - [Execution Management System (EMS)](#execution-management-system-ems)  
    - [Risk Management Module](#risk-management-module)  
    - [Backtesting Engine](#backtesting-engine)  
    - [Data Storage and Analytics](#data-storage-and-analytics)  
    - [User Interface (UI) and Monitoring](#user-interface-ui-and-monitoring)  
  - [Non-Functional Architectural Considerations](#non-functional-architectural-considerations)  
    - [Performance](#performance)  
    - [Scalability](#scalability)  
    - [Reliability and High Availability](#reliability-and-high-availability)  
    - [Security](#security)  
  - [Technology Choices](#technology-choices)  
  - [Deployment Architecture](#deployment-architecture)  
  - [Sequence Diagrams](#sequence-diagrams)  
  - [Conclusion](#conclusion)  

---

## Introduction  

This document outlines the **system architecture** for the **Portfolio Management Simulation System**, a scalable, high-performance, and secure solution built with **C#** and **ASP.NET Core**. The system is designed to support real-time data ingestion, strategy simulation, order and execution management, risk compliance, and analytics through a modern and responsive web interface.  

---

## High-Level Architecture Overview  

### Key Architecture Style:  
- **Microservices-based Architecture** with independent, scalable components deployed via **Docker** and orchestrated using **Kubernetes**.  
- **Event-driven Communication** leveraging **RabbitMQ** or **Kafka** for decoupled services.  
- **Real-time Features** enabled by **SignalR**.  

### Architectural Layers:  
1. **Presentation Layer**: Angular frontend interacting with backend services via RESTful APIs.  
2. **Application Layer**: Services responsible for business logic, data processing, and orchestration.  
3. **Data Layer**: Responsible for persistent storage, caching, and analytics.  

```

                                  +----------------------------+
                                  |    User Interface (UI)     |
                                  |  [Angular + SignalR Frontend] |
                                  +----------------------------+
                                             |
                                             |
                        +------------------------------------------+
                        |           API Gateway (ASP.NET Core)     |
                        | Handles routing, auth, and validation    |
                        +------------------------------------------+
                                             |
                                             |
-------------------------------------------------------------------------------------------
|                       |                        |                     |                 |
|                       |                        |                     |                 |
|                       |                        |                     |                 |
|      +----------------------------+      +----------------+     +------------------+   |
|      |  Market Data Ingestion     |      |  OMS           |     |   Backtesting    |   |
|      | [Kafka or RabbitMQ]        |      |  (Order Mgmt)  |     |   Engine         |   |
|      | WebSocket/REST Feeds       |      |                |     | Historical Data  |   |
|      +----------------------------+      +----------------+     +------------------+   |
|                 |                                     |                  |             |
|                 v                                     |                  |             |
|       +-------------------------+                    v                  |             |
|       |     Normalizer          |         +-------------------+         |             |
|       |  (Standardizes Data)    |         |  Execution Mgmt    |         |             |
|       +-------------------------+         | [Exchange APIs]    |         |             |
|                 |                         | Order Execution    |         |             |
|                 |                         +-------------------+         |             |
|                 v                                                          v             |
|       +-----------------------+                                   +-----------------+   |
|       | Event Publisher       |                                   |  Performance    |   |
|       | Publishes Data to     |                                   |  Evaluator      |   |
|       | Kafka/RabbitMQ Queue  |                                   |  (Metrics Gen.) |   |
|       +-----------------------+                                   +-----------------+   |
|                 |                                                          ^             |
|                 v                                                          |             |
|       +-----------------------+         +-------------------+              |             |
|       | Consumers: Strategy   |-------->|  Risk Mgmt Module |              |             |
|       | Engine + UI Updates   |         | [Compliance]      |              |             |
|       +-----------------------+         +-------------------+              |             |
|                      |                                        +------------+-------------+
|                      v                                        |
|        +----------------------------+                        v
|        |       Strategy Engine      |         +----------------------------+
|        |     (Executes Strategies)  |         |    Analytics/Storage       |
|        +----------------------------+         |   [PostgreSQL + Timescale] |
|                                               +----------------------------+
|                                                          ^
|                                                          |
-------------------------------------------------------------------------------------------

```

### Explanation of the Diagram:
1. **User Interface (UI)**:
   - Angular frontend for interacting with the system.
   - SignalR handles real-time updates.
2. **API Gateway**:
   - Mediates between the frontend and backend services.
   - Validates requests and routes them appropriately.
3. **Core Backend Services**:
   - **Market Data Ingestion**:
     - Fetches, normalizes, and publishes market data.
     - Uses Kafka or RabbitMQ for event streaming.
   - **Order Management System (OMS)**:
     - Handles order lifecycle and forwards to execution management.
   - **Backtesting Engine**:
     - Simulates strategies with historical data.
   - **Strategy Engine**:
     - Processes strategies and consumes market data.
4. **Execution Management**:
   - Interfaces with exchanges and executes orders.
5. **Risk Management Module**:
   - Enforces compliance rules and limits.
6. **Data Storage and Analytics**:
   - Stores all persistent and time-series data.
   - Supports analytics and performance metrics.

---

## Component Architecture  

### 1. Market Data Ingestion Service  

#### Responsibilities:  
- Connect to data sources (exchanges, brokers).  
- Normalize data to a consistent format.  
- Push updates to downstream services using **event-driven communication**.  

#### Components:  
- **Market Data Fetcher**: Fetches data via WebSocket or REST.  
- **Data Normalizer**: Standardizes raw data.  
- **Event Publisher**: Sends processed data to Kafka or RabbitMQ for consumption.  

---

### 2. Strategy Engine  

#### Responsibilities:  
- Enable users to design, upload, and execute strategies.  
- Execute multiple strategies concurrently with efficient resource management.  

#### Components:  
- **Compiler/Parser**: Validate and compile uploaded strategy scripts (using Roslyn).  
- **Execution Manager**: Manages runtime execution and isolation of strategies.  
- **Result Logger**: Logs outcomes for analysis and debugging.  

---

### 3. Order Management System (OMS)  

#### Responsibilities:  
- Manage the lifecycle of orders.  
- Interface with the **Execution Management System (EMS)** for routing and execution.  

#### Components:  
- **Order Router**: Routes orders to EMS or backtesting modules.  
- **Order State Tracker**: Tracks the status of all active orders.  
- **Portfolio Manager**: Maintains portfolio states and valuations.  

---

### 4. Execution Management System (EMS)  

#### Responsibilities:  
- Execute trades in live or simulated environments.  
- Interface with external exchanges and brokers.  

#### Components:  
- **Exchange Connector**: Provides APIs for various exchanges.  
- **Order Matching Module**: Simulates order matching for backtesting.  
- **Execution Monitor**: Tracks execution time and latency.  

---

### 5. Risk Management Module  

#### Responsibilities:  
- Monitor and enforce compliance with trading rules.  
- Prevent violations of configured risk limits.  

#### Components:  
- **Risk Checker**: Evaluates risk for each trade (pre-trade checks).  
- **Alert System**: Generates alerts for limit breaches.  
- **Compliance Tracker**: Logs risk compliance events.  

---

### 6. Backtesting Engine  

#### Responsibilities:  
- Replay historical data for strategy validation.  
- Simulate realistic market conditions.  

#### Components:  
- **Historical Data Loader**: Fetches historical market data.  
- **Simulation Manager**: Simulates order book and trade execution.  
- **Performance Evaluator**: Analyzes strategy outcomes.  

---

### 7. Data Storage and Analytics  

#### Responsibilities:  
- Persist trading data, logs, and performance metrics.  
- Provide historical and real-time analytics.  

#### Components:  
- **PostgreSQL**: For transactional data.  
- **TimescaleDB**: For time-series market data.  
- **Redis**: For caching frequently accessed data.  
- **Analytics Engine**: Powers dashboard metrics and insights.  

---

### 8. User Interface (UI) and Monitoring  

#### Responsibilities:  
- Provide a modern, responsive dashboard for monitoring and control.  
- Display real-time metrics, risk alerts, and portfolio updates.  

#### Components:  
- **Angular Frontend**: Interactive user interface.  
- **SignalR Hub**: Real-time updates to the frontend.  
- **Notification Service**: Alerts users via email or in-app messages.  

---

## Non-Functional Architectural Considerations  

### Performance  
- **Latency**: Sub-10 ms order processing latency.  
- **Throughput**: Minimum of 10,000 orders per second.  

### Scalability  
- **Horizontal Scaling**: Kubernetes ensures scalability across all services.  

### Reliability and High Availability  
- **Redundancy**: Multiple instances of critical components.  
- **Failover**: Automatic failover mechanisms for data ingestion and order processing.  

### Security  
- **Authentication**: JWT-based user authentication.  
- **Encryption**: All communication encrypted via TLS.  
- **Role-Based Access Control (RBAC)**: Fine-grained access control.  

---

## Technology Choices  

| Layer             | Technology                          | Justification                          |  
|-------------------|-------------------------------------|----------------------------------------|  
| Frontend          | Angular, SignalR                   | Real-time updates, modern UI framework |  
| Backend Framework | ASP.NET Core                       | High performance, cross-platform       |  
| Messaging         | RabbitMQ/Kafka                     | Reliable event-driven communication    |  
| Caching           | Redis                              | Low-latency data access                |  
| Database          | PostgreSQL, TimescaleDB            | ACID compliance, time-series analysis  |  
| Containerization  | Docker                             | Consistent environments                |  
| Orchestration     | Kubernetes                         | Scaling and failover                   |  

---

## Deployment Architecture  

### Deployment Components:  
1. **Frontend Deployment**: Angular served via **NGINX**.  
2. **Backend Services**: Deployed as microservices in **Kubernetes pods**.  
3. **Message Broker**: Kafka cluster or RabbitMQ for event streaming.  
4. **Database Cluster**: Managed PostgreSQL and TimescaleDB instances.  
5. **Monitoring Tools**: Prometheus and Grafana for metrics and alerts.  

---

## Sequence Diagrams  

### 1. Market Data Flow  
1. Market Data Ingestion Service fetches data from an exchange.  
2. Data is normalized and published to Kafka.  
3. Downstream services consume the data for strategy execution and UI updates.  

### 2. Order Lifecycle  
1. User places an order via the UI.  
2. OMS routes the order to EMS for execution.  
3. EMS interacts with an exchange API and returns execution results.  
4. Results are logged and updated in the portfolio.  

---
