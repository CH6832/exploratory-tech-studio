### **API Specifications for Python Integration**

This document provides comprehensive API specifications for integrating the algorithmic trading platform with Python-based applications. It includes details for each endpoint, expected request and response formats, and code examples for seamless integration. These APIs enable efficient communication between the backend trading system and Python-based services, ensuring smooth operation of trading strategies.

The API architecture follows REST principles, with JSON for both requests and responses. JWT (JSON Web Tokens) is used for secure, token-based authentication and authorization.

---

### **4.1 Overview**

These specifications allow Python developers to interact with core trading services (order execution, market data retrieval, etc.) via a simple REST API.

- **Base URL**: All endpoints start with `https://api.tradingplatform.com/api/v1`.
- **Authentication**: API calls require JWT authentication via an `Authorization` header.

---

### **Endpoint Definitions**

#### **Place Order**
- **Endpoint**: `/api/v1/trade/place-order`
- **Method**: `POST`
- **Description**: Submits a trade order for execution.

- **Request** (JSON):
  ```json
  {
    "orderType": "market",  // Type of order: market, limit, etc.
    "symbol": "AAPL",       // Trading symbol (e.g., AAPL for Apple)
    "quantity": 100,        // Number of shares/contracts
    "price": null           // Required only for limit orders, null for market orders
  }
  ```

- **Response** (JSON):
  ```json
  {
    "status": "success",
    "tradeID": "123456",
    "executionTime": "2024-11-13T15:00:00Z",
    "message": "Order placed successfully"
  }
  ```

- **Python Code Example**:
  ```python
  import requests
  import json

  url = "https://api.tradingplatform.com/api/v1/trade/place-order"
  headers = {"Authorization": "Bearer <jwt_token>", "Content-Type": "application/json"}
  payload = {
    "orderType": "market",
    "symbol": "AAPL",
    "quantity": 100,
    "price": None
  }

  response = requests.post(url, headers=headers, data=json.dumps(payload))
  print(response.json())
  ```

- **Status Codes**:
  - `200 OK`: Success
  - `400 Bad Request`: Invalid request format or missing parameters
  - `401 Unauthorized`: Authentication failure
  - `500 Internal Server Error`: Internal error

---

#### **Cancel Order**
- **Endpoint**: `/api/v1/trade/cancel-order`
- **Method**: `POST`
- **Description**: Cancels an existing order by `tradeID`.

- **Request** (JSON):
  ```json
  {
    "tradeID": "123456"  // The unique identifier for the trade to be cancelled
  }
  ```

- **Response** (JSON):
  ```json
  {
    "status": "success",
    "message": "Order cancelled successfully"
  }
  ```

- **Python Code Example**:
  ```python
  url = "https://api.tradingplatform.com/api/v1/trade/cancel-order"
  headers = {"Authorization": "Bearer <jwt_token>", "Content-Type": "application/json"}
  payload = {"tradeID": "123456"}

  response = requests.post(url, headers=headers, data=json.dumps(payload))
  print(response.json())
  ```

- **Status Codes**:
  - `200 OK`: Success
  - `400 Bad Request`: Invalid or missing `tradeID`
  - `404 Not Found`: Trade does not exist
  - `500 Internal Server Error`: Internal error

---

#### **Get Order Status**
- **Endpoint**: `/api/v1/trade/order-status/{tradeID}`
- **Method**: `GET`
- **Description**: Retrieves the status of a trade order.

- **Request**: None (URL parameter: `tradeID`).

- **Response** (JSON):
  ```json
  {
    "status": "completed",   // Possible values: pending, completed, cancelled
    "tradeID": "123456",
    "symbol": "AAPL",
    "quantity": 100,
    "price": 150.25,
    "executionTime": "2024-11-13T15:10:00Z",
    "message": "Order completed successfully"
  }
  ```

- **Python Code Example**:
  ```python
  tradeID = "123456"
  url = f"https://api.tradingplatform.com/api/v1/trade/order-status/{tradeID}"
  headers = {"Authorization": "Bearer <jwt_token>"}

  response = requests.get(url, headers=headers)
  print(response.json())
  ```

- **Status Codes**:
  - `200 OK`: Successfully retrieved order status
  - `400 Bad Request`: Invalid `tradeID`
  - `404 Not Found`: Order not found
  - `500 Internal Server Error`: Internal error

---

#### **Fetch Market Data**
- **Endpoint**: `/api/v1/market/data`
- **Method**: `GET`
- **Description**: Fetches real-time market data for a given symbol.

- **Request** (Query parameters):
  - `symbol`: Trading symbol (e.g., "AAPL", "BTC-USD").
  - `interval`: Time interval (e.g., "1m", "5m").

  Example URL:
  ```
  /api/v1/market/data?symbol=AAPL&interval=1m
  ```

- **Response** (JSON):
  ```json
  {
    "symbol": "AAPL",
    "interval": "1m",
    "data": [
      {
        "timestamp": "2024-11-13T15:00:00Z",
        "open": 150.00,
        "high": 151.00,
        "low": 149.50,
        "close": 150.50,
        "volume": 5000
      },
      {
        "timestamp": "2024-11-13T15:01:00Z",
        "open": 150.60,
        "high": 151.20,
        "low": 150.30,
        "close": 150.90,
        "volume": 4500
      }
    ]
  }
  ```

- **Python Code Example**:
  ```python
  symbol = "AAPL"
  interval = "1m"
  url = f"https://api.tradingplatform.com/api/v1/market/data?symbol={symbol}&interval={interval}"
  headers = {"Authorization": "Bearer <jwt_token>"}

  response = requests.get(url, headers=headers)
  print(response.json())
  ```

- **Status Codes**:
  - `200 OK`: Success
  - `400 Bad Request`: Missing or invalid query parameters
  - `404 Not Found`: Symbol does not exist
  - `500 Internal Server Error`: Internal error

---

### **Authentication**

All protected endpoints require a **JWT** (JSON Web Token) for authorization.

#### **Authentication Workflow**

1. **Login**: Use `/api/v1/auth/login` to authenticate a user and receive a JWT.

   Example Request (POST to `/api/v1/auth/login`):
   ```json
   {
     "username": "trader1",
     "password": "securepassword123"
   }
   ```

   Example Response (JSON):
   ```json
   {
     "status": "success",
     "token": "jwt-token-here"
   }
   ```

2. **Authenticated Requests**: Include the JWT in the `Authorization` header for subsequent API requests.

   Example:
   ```python
   headers = {"Authorization": "Bearer jwt-token-here"}
   ```

#### **JWT Token Expiration**

JWT tokens have an expiration (`exp`) field. If expired, the server responds with `401 Unauthorized`, requiring re-authentication.

Error Response Example:
```json
{
  "status": "error",
  "message": "Token expired. Please re-authenticate."
}
```

---

### **Rate Limiting**

API calls are rate-limited to **1000 requests per hour**.

If exceeded, the API responds with `429 Too Many Requests`.

Error Response Example:
```json
{
  "status": "error",
  "message": "Rate limit exceeded. Please try again later."
}
```

---

### **Error Handling**

Standardized error responses:

- **200 OK**: Request was successful
- **201 Created**: Resource created
- **400 Bad Request**: Malformed request or missing parameters
- **401 Unauthorized**: Missing or invalid token
- **403 Forbidden**: Insufficient permissions
- **404 Not Found**: Resource not found
- **429 Too Many Requests**: Rate limit exceeded
- **500 Internal Server Error**: Server error
