### **Portfolio Service Documentation**

#### **Table of Contents**
- Overview
- API Endpoints
- Portfolio Calculation
- Performance Analytics
- Error Handling

---

#### **Overview**
The Portfolio Service is responsible for providing detailed insights into the user's holdings, including the performance metrics of the assets they hold and the overall portfolio. This service interacts with the **PostgreSQL database** to store and retrieve portfolio data, ensuring efficient management of the user's assets and enabling accurate reporting on portfolio performance. The service aims to provide users with an easy-to-understand view of their investments, growth trends, and asset diversification.

This service is an essential component of the Crypto Trading Simulator, helping users track their investments, calculate returns, and make informed decisions about future trades.

---

#### **API Endpoints**
The Portfolio Service exposes several API endpoints to interact with users and provide them with portfolio data, asset performance, and analytics. These endpoints allow the frontend to retrieve necessary data to display on the user interface.

| **Method** | **Endpoint**           | **Description**                           |
|------------|------------------------|-------------------------------------------|
| GET        | /api/portfolio          | Fetches the current portfolio holdings, including the list of assets the user owns, their quantities, and the current value of each asset. |
| GET        | /api/portfolio/stats    | Provides a detailed analytics summary, including total portfolio value, individual asset performance, and trends over time. |

These endpoints allow users to interact with their portfolios, view the current status of their holdings, and gain insights into the performance of their assets.

---

#### **Portfolio Calculation**
The Portfolio Service calculates the overall value of the user's portfolio by performing several key operations:

- **Total Portfolio Value**: 
   The service fetches the current market prices for each asset in the user's portfolio (e.g., Bitcoin, Ethereum) and multiplies them by the respective quantities held by the user. The sum of all these values gives the total portfolio value.

- **Individual Asset Performance**:
   Each asset's performance is tracked over time by comparing its historical price data to the current market price. The Portfolio Service can calculate the **percentage change** in value for each asset to provide users with a clear understanding of their asset’s growth or decline since purchase.

This portfolio calculation process ensures that users always have an up-to-date view of the worth of their assets and the overall health of their portfolio.

---

#### **Performance Analytics**
The Portfolio Service provides performance analytics to help users visualize how their portfolio is performing over time. These analytics include:

- **Portfolio Diversification**:
   The service provides insights into the distribution of assets in the user's portfolio. This allows users to see how diversified their investments are across different cryptocurrencies. Users can make more informed decisions regarding rebalancing their portfolio based on diversification metrics.

- **Historical Growth Trends**:
   The service generates visual summaries of portfolio growth, including:
   - **Year-over-Year (YoY) Growth**: Provides a snapshot of the portfolio’s performance on an annual basis.
   - **Growth Over Time**: Tracks how the portfolio has performed since its inception or during a specific time range, helping users to identify profitable trends or areas of improvement.

These analytics provide users with an understanding of how well their portfolio has performed and where they might want to adjust their investment strategies.

---

#### **Error Handling**
The Portfolio Service includes robust error handling to ensure that users receive accurate and helpful feedback in case of issues or failures. Here are some common error responses:

- **404 Not Found**:
   - **Cause**: This error is returned if the requested portfolio data cannot be found in the database. This could happen if the user does not have an active portfolio or if the data is missing due to a database issue.
   - **Response**: A message indicating that the requested portfolio data is not available, along with a suitable HTTP status code.

- **503 Service Unavailable**:
   - **Cause**: This error is returned if external dependencies (such as APIs for fetching market data) are unavailable. If the Portfolio Service is unable to retrieve current market prices from third-party APIs, this error will be triggered.
   - **Response**: A message indicating that the service is currently unavailable, with a request to try again later. This may also include suggestions for the user to check their internet connection or wait for service recovery.

By providing clear error messages, users can understand the issue and take appropriate actions. The Portfolio Service aims to minimize downtime and ensure that users are always able to access their portfolio information as accurately and quickly as possible.
