### **Trade Service Documentation**

#### **Table of Contents**
- Overview
- API Endpoints
- Business Logic
- Real-time Notifications
- Error Handling

---

#### **Overview**
The Trade Service is responsible for handling all trade execution requests within the Crypto Trading Simulator. It validates transactions, ensures that users have sufficient balance and meet trading limits, and maintains a record of each trade for future reference. This service is crucial for enabling users to perform trades in real time, monitor their trade history, and receive instant notifications about the status of their trades.

The service also integrates with **WebSockets** to deliver real-time notifications regarding trade execution and price alerts, ensuring that users are always informed about the status of their transactions.

---

#### **API Endpoints**
The Trade Service exposes several key API endpoints to facilitate trade execution, history retrieval, and real-time updates. These endpoints enable the frontend to interact with the service, allowing users to perform trades, track their past trades, and subscribe to live updates.

| **Method** | **Endpoint**            | **Description**                                                       |
|------------|-------------------------|-----------------------------------------------------------------------|
| POST       | /api/trades/execute      | Initiates a new trade, executing a transaction based on user inputs and available balance. |
| GET        | /api/trades/history      | Fetches a user's trade history, providing details of past transactions, including the assets traded and their respective values. |
| GET        | /api/trades/live-feed    | Subscribes to live updates, pushing notifications on the status of ongoing trades and any relevant price changes. |

These endpoints are designed to be efficient and provide quick access to vital trading information for users.

---

#### **Business Logic**
The Trade Service ensures the validity and integrity of trade transactions through several layers of business logic:

- **Transaction Validation**: Before executing a trade, the service checks whether the user has sufficient funds or assets to complete the transaction. If the user attempts to trade beyond their available balance or exceeds preset trading limits, the trade request will be rejected.
  
- **Balance and Limits Check**: The service ensures that the trade does not exceed the user's available balance, considering both cash and cryptocurrency holdings. It also checks if the trade falls within predefined limits for certain assets (e.g., daily trading limits).

- **Recording Trades**: Once a trade is validated and executed, it is recorded in the database to maintain an accurate history of user transactions. This historical data can be accessed via the `/api/trades/history` endpoint for future reference or reporting.

The business logic layer ensures that the trade execution process is robust, secure, and adheres to the platform's rules and limits.

---

#### **Real-time Notifications**
The Trade Service integrates with **WebSockets** to provide real-time notifications, ensuring users are immediately informed about their trades. 

- **WebSocket Connection**: Once a user subscribes to the live feed, a WebSocket connection is established between the frontend and the Trade Service.
- **Trade Execution Updates**: As trades are executed, the service pushes real-time updates to the frontend, notifying users of the trade's status, including success, failure, or pending state.
- **Price Alerts**: In addition to trade updates, the WebSocket connection may also push notifications regarding price fluctuations or significant changes in the market that could impact the user's trade.

The integration with WebSockets ensures that users have up-to-the-minute information about their trades, making the experience dynamic and interactive.

**Diagram**:
```plaintext
Client
   |
WebSocket Connection
   |
Trade Service
   |
Database
```

This communication flow ensures that users can stay updated in real time without needing to manually refresh or recheck the status of their trades.

---

#### **Error Handling**
The Trade Service employs comprehensive error handling to ensure that users are provided with clear feedback when issues arise. The system is designed to catch various types of errors and respond with appropriate messages.

- **403 Forbidden**:
   - **Cause**: This error is triggered when a user attempts to perform a trade that exceeds their available balance or violates trading limits (e.g., trading more than the allowed amount of a particular asset).
   - **Response**: A message will be sent to the client indicating that the trade request was denied due to insufficient balance or exceeded limits. The response includes the specific reason for rejection, allowing the user to correct their input.

- **500 Internal Server Error**:
   - **Cause**: This error occurs when an unexpected server-side issue arises during the trade execution process. It may be triggered by database failures, service disruptions, or internal logic errors.
   - **Response**: A general message indicating that an internal error occurred, suggesting the user try again later. The issue would be logged for debugging and resolution by the development team.
