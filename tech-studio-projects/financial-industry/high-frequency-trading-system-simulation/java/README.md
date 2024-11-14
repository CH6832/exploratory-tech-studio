# High-Frequency Trading (HFT) Simulation System

## Overview
This project is a high-frequency trading (HFT) simulation system designed to demonstrate trading strategies in a low-latency environment using Java RTS and a hybrid microservice-monolithic architecture.

## Features
- Core trading engine with high-frequency capabilities
- Microservices for data processing, order matching, and risk management
- Real-time data integration from APIs (Yahoo Finance, Alpha Vantage, Binance)
- Low-latency processing and optimized Java RTS configuration
- Scalable, containerized with Docker and Kubernetes

## Directory Structure
- `core-system/` - Main trading engine
- `microservices/` - Collection of services for auxiliary tasks
- `kubernetes/` - Deployment configurations for Kubernetes
- `scripts/` - Scripts for building and deploying

## Requirements
- **Java RTS (Real-Time System)**
- **Docker** and **Docker Compose**
- **Kubernetes** (for production deployment)
- **Maven** (for building projects)

## Setup
1. Clone the repository.
   ```bash
   git clone <repo_url>
   ```
2. Build and run with Docker Compose.
   ```bash
   docker-compose up --build
   ```
