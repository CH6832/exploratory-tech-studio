# API Specifications and Usage

---

## Table of Contents

1. [Introduction](#introduction)  
2. [Authentication and Security](#authentication-and-security)  
3. [Endpoints Overview](#endpoints-overview)  
4. [Endpoint Specifications](#endpoint-specifications)  
   - [Market Data API](#market-data-api)  
   - [Portfolio Management API](#portfolio-management-api)  
   - [Strategy Management API](#strategy-management-api)  
   - [Risk Management API](#risk-management-api)  
   - [Backtesting API](#backtesting-api)  
5. [Error Handling](#error-handling)  
6. [Rate Limiting](#rate-limiting)  
7. [Versioning and Deprecation Policy](#versioning-and-deprecation-policy)  
8. [Glossary](#glossary)  

---

## 1. Introduction

This document defines the API specifications for the Portfolio Management Simulation System. These APIs support modularity and scalability, allowing users to interact with various system components, including market data ingestion, portfolio management, strategy execution, risk monitoring, and backtesting.

All endpoints follow RESTful principles and use JSON for request and response payloads. The system supports HTTP and HTTPS protocols, with HTTPS strongly recommended in production environments.

---

## 2. Authentication and Security

### Authentication
- **Protocol**: OAuth 2.0  
- **Token Type**: Bearer Tokens  
- **Endpoint**: `/api/v1/auth/token`  
- **Scopes**:  
  - `market_data:read`  
  - `portfolio:read/write`  
  - `strategy:execute`  
  - `risk:monitor`  
  - `backtesting:run`

### Security Measures
- **TLS Encryption**: Enforces HTTPS for all API traffic.  
- **IP Whitelisting**: Optional configuration for enterprise users.  
- **Rate Limiting**: Default rate limit of 1000 requests per minute.  

---

## 3. Endpoints Overview

| API Group            | Endpoint                     | Description                                      |
|----------------------|-----------------------------|--------------------------------------------------|
| Market Data API      | `/api/v1/market-data`       | Fetch live or historical market data.           |
| Portfolio API        | `/api/v1/portfolio`         | Manage portfolios, positions, and allocations.  |
| Strategy API         | `/api/v1/strategy`          | Deploy, execute, and manage trading strategies. |
| Risk API             | `/api/v1/risk`             | Monitor and enforce risk parameters.            |
| Backtesting API      | `/api/v1/backtesting`       | Run historical simulations and retrieve results.|

---

## 4. Endpoint Specifications

### Market Data API

#### Fetch Live Market Data
- **Endpoint**: `/api/v1/market-data/live`  
- **Method**: `GET`  
- **Description**: Retrieves live market data for specified instruments.  
- **Parameters**:  
  - `symbol` (required): The ticker symbol (e.g., `AAPL`).  
  - `exchange` (optional): Exchange name (e.g., `NASDAQ`).  
- **Response**:
  ```json
  {
    "symbol": "AAPL",
    "price": 174.56,
    "volume": 12345678,
    "timestamp": "2024-11-27T14:25:36Z"
  }
  ```
- **Errors**:
  - `400`: Invalid parameters.
  - `500`: Internal server error.

#### Fetch Historical Market Data
- **Endpoint**: `/api/v1/market-data/history`  
- **Method**: `POST`  
- **Description**: Retrieves historical data for backtesting or analytics.  
- **Request Body**:
  ```json
  {
    "symbol": "AAPL",
    "start_date": "2024-01-01",
    "end_date": "2024-11-27"
  }
  ```
- **Response**:
  ```json
  [
    {
      "date": "2024-01-01",
      "open": 150.00,
      "close": 152.00,
      "high": 153.00,
      "low": 149.00,
      "volume": 1000000
    },
    ...
  ]
  ```

---

### Portfolio Management API

#### Retrieve Portfolio
- **Endpoint**: `/api/v1/portfolio/{id}`  
- **Method**: `GET`  
- **Description**: Retrieves portfolio details by portfolio ID.  
- **Response**:
  ```json
  {
    "id": "123",
    "name": "Growth Portfolio",
    "positions": [
      {
        "symbol": "AAPL",
        "quantity": 100,
        "average_price": 150.00
      },
      ...
    ]
  }
  ```

#### Update Portfolio
- **Endpoint**: `/api/v1/portfolio/{id}`  
- **Method**: `PUT`  
- **Description**: Updates portfolio details.  
- **Request Body**:
  ```json
  {
    "name": "Updated Portfolio Name"
  }
  ```
- **Response**:
  ```json
  {
    "status": "success",
    "message": "Portfolio updated."
  }
  ```

---

### Strategy Management API

#### Deploy Strategy
- **Endpoint**: `/api/v1/strategy`  
- **Method**: `POST`  
- **Description**: Deploys a new trading strategy.  
- **Request Body**:
  ```json
  {
    "name": "Mean Reversion",
    "parameters": {
      "lookback_period": 20,
      "threshold": 0.05
    }
  }
  ```
- **Response**:
  ```json
  {
    "status": "success",
    "strategy_id": "456"
  }
  ```

#### Execute Strategy
- **Endpoint**: `/api/v1/strategy/{id}/execute`  
- **Method**: `POST`  
- **Description**: Executes a deployed strategy.  
- **Response**:
  ```json
  {
    "status": "success",
    "message": "Strategy execution started."
  }
  ```

---

### Risk Management API

#### Retrieve Risk Limits
- **Endpoint**: `/api/v1/risk/limits`  
- **Method**: `GET`  
- **Description**: Retrieves defined risk limits.  
- **Response**:
  ```json
  {
    "limits": {
      "max_exposure": 1000000,
      "max_position_size": 50000
    }
  }
  ```

---

### Backtesting API

#### Run Backtest
- **Endpoint**: `/api/v1/backtesting`  
- **Method**: `POST`  
- **Description**: Runs a backtest with specified parameters.  
- **Request Body**:
  ```json
  {
    "strategy_id": "456",
    "start_date": "2024-01-01",
    "end_date": "2024-11-27"
  }
  ```
- **Response**:
  ```json
  {
    "status": "success",
    "results": {
      "total_return": 12.5,
      "max_drawdown": -5.2,
      "sharpe_ratio": 1.3
    }
  }
  ```

---

## 5. Error Handling

| Status Code | Description                           |
|-------------|---------------------------------------|
| `400`       | Bad Request - Invalid input.          |
| `401`       | Unauthorized - Invalid token.         |
| `403`       | Forbidden - Insufficient permissions. |
| `404`       | Not Found - Resource does not exist.  |
| `500`       | Internal Server Error.                |

---

## 6. Rate Limiting

Default rate limit: **1000 requests per minute**. Exceeding this limit will return a `429 Too Many Requests` error.

---

## 7. Versioning and Deprecation Policy

- **Current Version**: `v1`  
- **Deprecation Policy**: Endpoints marked as deprecated will be supported for 6 months post-notification.  

---

## 8. Glossary

- **API Gateway**: A centralized entry point for API requests.
- **Endpoint**: A specific function exposed via an API.
- **Strategy**: A set of rules defining a trading decision-making process.
- **Backtest**: Simulating strategy performance on historical data.
- **Portfolio**: A collection of financial assets managed as a group.
