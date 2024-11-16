### **4. API Specifications**
**Title**: API Specifications for Algorithmic Trading Platform

---

#### **4.1 Overview**

This document provides comprehensive API specifications for all core and microservices within the algorithmic trading platform. It includes details for each endpoint, such as its purpose, request format, and expected response format. The goal of these APIs is to enable seamless integration and communication between various system components (frontend, backend, and external services).

The API architecture follows REST principles, utilizing JSON as the format for both requests and responses. Additionally, we employ JWT (JSON Web Tokens) for secure, token-based authentication and authorization.

---

#### **4.2 Endpoint Definitions**

The following sections outline each API endpoint in detail. Each endpoint includes the HTTP method, URL, a description of its functionality, input data (if applicable), and a sample response.

---

##### **4.2.1 Place Order**
- **Endpoint**: `/api/v1/trade/place-order`
- **Method**: `POST`
- **Description**: Submits a trade order to the platform for execution. The order details, including the type of order (market, limit), asset symbol, quantity, and price, are required to execute the trade.
  
- **Request** (JSON):
  ```json
  {
    "orderType": "market",  // Type of order: market, limit, etc.
    "symbol": "AAPL",       // Trading symbol (e.g., AAPL for Apple)
    "quantity": 100,        // Number of shares/contracts
    "price": null           // Only required for limit orders (market orders use null)
  }
  ```

- **Response** (JSON):
  ```json
  {
    "status": "success",      // The status of the trade submission (success or failure)
    "tradeID": "123456",      // Unique identifier for the trade
    "executionTime": "2024-11-13T15:00:00Z",  // Time of order execution
    "message": "Order placed successfully"
  }
  ```

- **Status Codes**:
  - `200 OK`: Successfully placed the order.
  - `400 Bad Request`: Invalid request format or missing parameters.
  - `401 Unauthorized`: The user is not authorized to perform this action.
  - `500 Internal Server Error`: Unexpected error in the system.

---

##### **4.2.2 Cancel Order**
- **Endpoint**: `/api/v1/trade/cancel-order`
- **Method**: `POST`
- **Description**: Cancels a previously placed order by its unique `tradeID`.

- **Request** (JSON):
  ```json
  {
    "tradeID": "123456"  // Unique identifier for the trade to be cancelled
  }
  ```

- **Response** (JSON):
  ```json
  {
    "status": "success",
    "message": "Order cancelled successfully"
  }
  ```

- **Status Codes**:
  - `200 OK`: Successfully cancelled the order.
  - `400 Bad Request`: Missing or invalid tradeID.
  - `404 Not Found`: The tradeID does not exist or has already been executed.
  - `500 Internal Server Error`: An unexpected error occurred while processing the cancellation.

---

##### **4.2.3 Get Order Status**
- **Endpoint**: `/api/v1/trade/order-status/{tradeID}`
- **Method**: `GET`
- **Description**: Retrieves the status of a specific trade order, providing details such as its execution status and any associated errors.

- **Request**: None (tradeID passed as a URL parameter).

- **Response** (JSON):
  ```json
  {
    "status": "completed",        // Possible values: pending, completed, cancelled
    "tradeID": "123456",          // Unique identifier for the trade
    "symbol": "AAPL",             // Trading symbol
    "quantity": 100,              // Number of shares/contracts
    "price": 150.25,              // Price at which the order was executed
    "executionTime": "2024-11-13T15:10:00Z",  // Time of execution
    "message": "Order completed successfully"
  }
  ```

- **Status Codes**:
  - `200 OK`: Successfully fetched the order status.
  - `400 Bad Request`: Invalid tradeID format.
  - `404 Not Found`: The specified tradeID does not exist.
  - `500 Internal Server Error`: An error occurred while retrieving the order status.

---

##### **4.2.4 Fetch Market Data**
- **Endpoint**: `/api/v1/market/data`
- **Method**: `GET`
- **Description**: Retrieves real-time market data for a specific trading symbol. Provides the latest market price, volume, and other key statistics.

- **Request** (Query parameters):
  - `symbol`: The asset symbol for which market data is requested (e.g., "AAPL", "BTC-USD").
  - `interval`: The time interval for the data (e.g., "1m" for one-minute, "5m" for five-minute candles).

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

- **Status Codes**:
  - `200 OK`: Successfully retrieved market data.
  - `400 Bad Request`: Missing or invalid query parameters.
  - `404 Not Found`: The specified symbol does not exist.
  - `500 Internal Server Error`: An error occurred while retrieving market data.

---

#### **4.3 Authentication**

All protected API endpoints (such as placing orders or accessing sensitive account data) require authentication via **JWT (JSON Web Tokens)**. These tokens must be included in the `Authorization` header for each request.

##### **4.3.1 Authentication Workflow**
1. **Login**: 
   - Use the `/api/v1/auth/login` endpoint to authenticate a user. 
   - After successful login, the server responds with a JWT, which must be included in the header for subsequent requests.

   **Example Request** (POST to `/api/v1/auth/login`):
   ```json
   {
     "username": "trader1",
     "password": "securepassword123"
   }
   ```

   **Example Response** (JSON):
   ```json
   {
     "status": "success",
     "token": "jwt-token-here"
   }
   ```

2. **Making Authenticated Requests**: 
   - Add the received JWT to the `Authorization` header of each request. Use the format: `Bearer <token>`.
   
   **Example**:
   ```plaintext
   Authorization: Bearer jwt-token-here
   ```

##### **4.3.2 JWT Token Expiration**

JWT tokens have an expiration time (`exp` field) encoded within them. If the token has expired, the server will respond with a `401 Unauthorized` status, indicating the need for a new token.

**Example Error Response**:
```json
{
  "status": "error",
  "message": "Token expired. Please re-authenticate."
}
```

---

#### **4.4 Rate Limiting**

To ensure fair use of resources, the API enforces rate limits. Each client can make a maximum of **1000 requests per hour**. If the rate limit is exceeded, the API will return a `429 Too Many Requests` error.

**Example Error Response**:
```json
{
  "status": "error",
  "message": "Rate limit exceeded. Please try again later."
}
```

---

### **4.5 Error Handling**

The API returns standardized error responses. Below is an overview of the most common HTTP status codes and their meanings.

- **200 OK**: The request was successful.
- **201 Created**: A resource was successfully created (e.g., a new trade).
- **400 Bad Request**: The request was malformed or missing required parameters.
- **401 Unauthorized**: The request did not include a valid authentication token.
- **403 Forbidden**: The client is authenticated, but does not have permission to perform this action.
- **404 Not Found**: The requested resource does not exist.
- **429 Too Many Requests**: Rate limit exceeded.
- **500 Internal Server Error**: A server error occurred, please try again later.
