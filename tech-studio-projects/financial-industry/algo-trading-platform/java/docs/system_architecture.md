# System Architecture

- [System Architecture](#system-architecture)
  - [Introduction](#introduction)
    - [Core Monolithic](#core-monolithic)
    - [Microservices](#microservices)
    - [Communication and Integration](#communication-and-integration)
  - [High-Level System](#high-level-system)
    - [Explanation](#explanation)
    - [Summary of Flow](#summary-of-flow)
  - [High-Level Component Diagram](#high-level-component-diagram)
    - [Detailed Component Descriptions](#detailed-component-descriptions)
      - [Frontend UI](#frontend-ui)
      - [API Gateway](#api-gateway)
      - [Trading Engine](#trading-engine)
      - [Supporting Services](#supporting-services)
      - [Core Components \& Flow](#core-components--flow)
  - [Trade Execution Flow](#trade-execution-flow)
    - [Component explanation](#component-explanation)

## Introduction

The architecture of the algorithmic trading platform leverages a hybrid architecture combining both a Core Monolithic Application and Microservices. This combination allows for the optimization of low-latency, tightly coupled core trading operations while providing the flexibility, scalability, and modularity required for additional services through microservices.

- Core Monolithic Application: The core application provides a centralized, high-performance system that handles key aspects of trading and market data processing, while ensuring fast execution times.

- Microservices: These modular services are designed to scale independently, handle auxiliary tasks like user management and notifications, and facilitate more agile and fault-tolerant operations.

### Core Monolithic

The Core Monolithic Application contains the main logic of the trading platform, handling critical operations such as strategy execution, order management, risk monitoring, and market data processing.

- Trading Engine: This is the heart of the platform, responsible for processing strategies, executing orders, managing positions, and assessing risks associated with trades.
  - Key Functions: Strategy execution, order placement, stop-loss logic, and position updates.
  
- Market Data Processing: Handles the ingestion of real-time market data streams and the storage of historical data for backtesting and real-time analysis.
  - Key Functions: Market data collection, storage (e.g., time-series data), and feeding live data to the trading engine.
  
- Portfolio Management: Manages user portfolios, tracks assets, positions, and account balances, and ensures that funds are properly allocated across different assets.
  - Key Functions: Balance management, portfolio updates, margin tracking.

### Microservices

Microservices are used to handle auxiliary services that are non-critical but require flexibility, scalability, and fault tolerance. The microservice architecture ensures that these services can evolve independently from the core system and scale according to demand.

- User Management: This service handles user authentication, authorization, and management of user-specific data.
  - Key Functions: User registration, login, token-based authentication (JWT), profile management.
  
- Data Retrieval: Retrieves market data from external sources, caches frequently accessed data for efficiency, and ensures seamless data flow into the trading engine.
  - Key Functions: External API data fetch, caching layer, market data storage, and management.

- Reporting: Generates and serves performance reports, trading history, and other key metrics to the users.
  - Key Functions: Trade logs, performance analysis, transaction reports, tax reports.

- Notification Service: Handles real-time notifications, such as order execution updates, trade alerts, and risk threshold warnings.
  - Key Functions: Push notifications, email alerts, SMS, and in-app messaging.

### Communication and Integration

- The communication between the Core Monolithic Application and the Microservices is achieved through a message broker (e.g., RabbitMQ, Apache Kafka) to ensure high availability, fault tolerance, and asynchronous communication. The use of REST APIs enables synchronous communication for request-response based interactions.

- Frontend Communication: The Frontend UI communicates with the backend through REST APIs for standard requests such as placing orders, fetching reports, or checking user balances. For real-time updates (e.g., live price data or order status), the frontend uses WebSockets to subscribe to events and receive continuous updates without polling.

## High-Level System

```

    +---------------------------------------------------------+
    |                        Frontend UI                      |
    |       (Vue.js - Trading View, React Dashboard)         |
    +---------------------------------------------------------+
    |   Users can view market data, configure strategies,     |
    |   monitor active trades, and receive real-time updates. |
    +---------------------------------------------------------+
                            |
                            |       REST / WebSocket
                            |
    +---------------------------------------------------------+
    |                 API Gateway (REST API)                  |
    |       (Manages Requests, Authentication, Logging)       |
    +---------------------------------------------------------+
    | - Routes requests to backend services                  |
    | - Supports REST for configuration and WebSocket        |
    |   for real-time trade updates                          |
    +---------------------------------------------------------+
                            |
                            |
                            |
                    +--------+--------+
                    |                 |
                    |                 |
    +---------------v-----------------v---------------+  +---------------------------------+
    |          Core Monolithic Application           |   |           Microservices         |
    |   (Handles Trading Logic, Market Data,         |   | (Additional Services)           |
    |   Strategy Execution, Order Management)        |   +---------------------------------+
    +---------------------+--------------------------+            |
    | - Trading Engine    |                          |            |
    | - Strategy Manager  |                          |            |
    | - Risk Management   |                          |            |
    | - Order Execution   |                          |            |
    | - Historical Data   |                          |            |
    +---------------------+--------------------------+    +-------v--------+   +-----------v----------+
                            |                             |                |   |                      |
                            |                             |                |   |                      |
    +------------------------+---------------------+   +---v----+     +-----v----+   +-----------------+
    |    Trading Engine (Core Execution Unit)      |   | User   |     | Reporting |   | Notifications   |
    | - Coordinates trading strategies, trade      |   | Service|     | Service   |   | Service         |
    |   signals, and order execution               |   |        |     |           |   |                 |
    | - Manages cycle of strategy eval and         |   | - User |     | - Generates|   | - Sends alerts |
    |   risk checks                                |   |   data |     | reports    |   |   & real-time   |
    +------------------------+---------------------+   |   mgmt |     | on trades,|   |   notifications |
                            |                         +--------+     | strategies|   +-----------------+
                            |                         +--------+     |   market  |
                            |                         |  Strategy  +-------------------------+-----------+
    +------------------------+---------------------+     Registry    +                         +-------+   
    | Market Data Processor                         |      (AI-based strategy         Real-Time   |
    | - Ingests, cleans, and processes live         |       recommendations)        Monitoring  |
    |   market data feeds                           |                                        & System |
    | - Calculates indicators for strategy inputs   |                                              |
    +------------------------+----------------------+
                            |
                            |
                            |
    +------------------------+----------------------+
    | Order Execution & Routing                     |
    | - Connects with brokerage APIs                |
    | - Sends buy/sell signals to market            |
    | - Ensures orders are filled optimally         |
    +------------------------+----------------------+

    +--------------------------------+          +--------------------------------+
    |            Databases           |          |            Analytics           |
    | - Market Data (Historical)     |          | - ML-driven strategies         |
    | - User Profiles & Portfolios   |          | - Strategy optimization        |
    | - Order Histories              |          | - Performance analysis         |
    | - Risk Profiles                |          | - Anomaly detection            |
    +--------------------------------+          +--------------------------------+

```

### Explanation

1. **Frontend UI**:
   - **Vue.js (Trading View)** or **React**: Provides an interactive web interface for users to view real-time data, configure trading strategies, and receive alerts.
   - Users can monitor active trades and manage configurations from their dashboard.

2. **API Gateway (REST API)**:
   - Routes requests between the frontend and backend components.
   - **REST API**: Handles configuration requests and trade history.
   - **WebSocket**: Supports real-time trade updates, notifications, and market feed updates.

3. **Core Monolithic Application**:
   - **Trading Engine**: Manages the central trading logic. Runs trading strategies, evaluates them against live data, performs risk checks, and decides when to execute trades.
   - **Strategy Manager**: Selects and applies strategies based on market data.
   - **Risk Management**: Assesses risks for each trade decision according to the user’s risk profile.
   - **Order Execution**: Routes orders to the brokerage API, ensuring optimal order placement and handling.
   - **Historical Data Management**: Provides historical market data and trade history, supporting backtesting and strategy refinement.

4. **Market Data Processor**:
   - Ingests and processes market data in real-time.
   - Calculates indicators and metrics (like moving averages, volatility) necessary for strategy evaluation.

5. **Order Execution & Routing**:
   - Connects to external brokerage APIs or exchanges, sending buy/sell orders and monitoring fill status.

6. **Microservices**:
   - **User Service**: Manages user profiles, portfolios, and preferences.
   - **Reporting Service**: Generates reports on trading performance, strategy outcomes, and market trends.
   - **Notification Service**: Sends alerts to users about key events, trades, or market changes.
   - **Strategy Registry**: (Optional) Allows access to a pool of predefined and AI-driven strategies, suggesting them based on performance or market conditions.

7. **Databases**:
   - **Market Data (Historical)**: Stores historical market data, allowing for backtesting and strategy optimization.
   - **User Profiles & Portfolios**: Manages user accounts, portfolio data, and trade histories.
   - **Order Histories**: Logs all order transactions, statuses, and outcomes.
   - **Risk Profiles**: Stores individual risk tolerance levels and preferences, integrated with risk management.

8. **Analytics**:
   - **Machine Learning**: Enables advanced algorithmic strategies, optimizes existing ones, and provides anomaly detection.
   - **Strategy Optimization**: Continually improves strategy parameters based on backtesting and performance analytics.
   - **Anomaly Detection**: Monitors for unusual market behavior or potential trading anomalies.

### Summary of Flow

1. **User Interactions** with the **Frontend UI** go through the **API Gateway**.
2. The **Core Monolithic Application** manages key trading logic:
   - **Market Feed Handler** receives live data.
   - **Market Data Processor** prepares data for strategies.
   - **Trading Engine** evaluates strategies, considers risk, and initiates order execution.
3. The **Microservices** handle user accounts, notifications, and reporting.
4. Data storage is maintained in **Databases**, with advanced analysis managed through **Analytics**.

---

## High-Level Component Diagram

- Frontend UI: User interface for real-time monitoring, configuration, and reporting.

- API Gateway: Entry point for requests, managing and routing client API calls.

- Trading Engine: Core trading logic that evaluates strategies and makes trade decisions.

- Supporting Services: Modules that provide data, risk analysis, user management, and notifications.

```

    +-------------------------------+
    |          Frontend UI          |
    |    (e.g., Web App, Mobile)    |
    +-------------------------------+
                |
                |
    REST API / WebSocket Connection
                |
                |
    +-----------------------------+
    |         API Gateway         |
    | (Handles client requests,   |
    |  authenticates, throttles)  |
    +--------------+--------------+
                    |
                    |
          +---------+---------+
          |                   |
          |                   |
    +-----v-----+     +-------v----------+                 +--------------------+
    |           |     |                  |                 |                    |
    | Trading   |     | User Service     |                 | Notification       |
    | Engine    |     | (Manages user    |                 | Service            |
    | (Core)    |     |  profiles,       |                 | (Alerts, Emails,   |
    |           |     |  authentication) |                 |  Messages)         |
    +-----------+     +------------------+                 +--------------------+
          |
          |
      +----v-----+
      | Market   |    +----------------+    +----------------+     +---------------+
      | Data     |----> Market Feed    |----> Market Data    |<----|  Risk         |
      | Processor|    |  Handler       |    |  Processor     |     |  Management   |
      +----------+    +----------------+    +----------------+     +---------------+
          |
          | Uses processed data to
          | evaluate trading strategies
          |
    +------v-------+         +-----------+----------+
    |              |         |                      |
    | Trading      |         | Strategy Selector    |
    | Strategy     |         | (Chooses strategy    |
    | Algorithms   |         |  per market state)   |
    +--------------+         +----------------------+
          |
          |
    +------v-------+
    |              |
    | Order        |
    | Execution    |
    | (Sends orders|
    |  to market)  |
    +--------------+

```

### Detailed Component Descriptions

#### Frontend UI
- Description: This is the user-facing interface that allows users to view real-time trading activity, configure strategies, and monitor performance.
- Components: 
  - User dashboard (Vue.js or React)
  - Real-time data visualization
  - Trading settings interface
  
#### API Gateway
- Description: The API Gateway serves as the entry point, handling REST or WebSocket connections, authenticating requests, and routing them to the appropriate microservices or core components.
  
#### Trading Engine
- Description: The Trading Engine is the heart of the application, executing trading strategies based on incoming market data.
- Subcomponents:
  - Market Feed Handler: Handles the reception of real-time market data, either simulated or live.
  - Market Data Processor: Processes incoming market data and prepares it for use by trading strategies.
  - Strategy Selector: Dynamically selects the optimal trading strategy based on market conditions.
  - Order Execution: Sends buy/sell orders to the market based on trade decisions.

#### Supporting Services

1. User Service:
   - Manages user profiles, permissions, and authentication, ensuring that only authorized users can make certain requests.

2. Notification Service:
   - Manages alerts and notifications, sending real-time updates or emails for critical events such as trade executions or risk breaches.

3. Risk Management:
   - Monitors trade decisions against predefined risk thresholds and validates whether trades should proceed based on current risk profiles.

#### Core Components & Flow

1. Market Data Processor: Processes raw market data to derive metrics and indicators.
2. Trading Strategy Algorithms: Various algorithmic strategies (e.g., Mean Reversion, Trend Following, Arbitrage) evaluate the processed data.
3. Order Execution: Executes trades as per the strategy decision and reports back on the status of each trade.

---

## Trade Execution Flow

```

                          +-----------------------+
                          |     Start Trading     |
                          |      Application      |
                          +-----------+-----------+
                                      |
                                      |
                                      |
                         +------------v------------+
                         |                         |
                         |      Market Feed        |
                         |        Handler          |
                         |                         |
                         +------------+------------+
                                      |
                       Receives Market Data (simulated or real)
                                      |
                                      |
                         +------------v------------+
                         |                         |
                         |  Market Data Processor  |
                         |                         |
                         +------------+------------+
                                      |
                    Processes and prepares market data
                                      |
                                      |
                         +------------v------------+
                         |                         |
                         |   Trading Strategy      |
                         |        Factory          |
                         |                         |
                         +------------+------------+
                                      |
                    Chooses appropriate strategy 
           (e.g., Mean Reversion, Trend Following, Arbitrage)
                                      |
                   +------------------+------------------+
                   |                  |                  |
                   |                  |                  |
                   |                  |                  |
        +----------v--------+ +-------v--------+ +------v----------+
        |                   | |                | |                 |
        | Mean Reversion    | | Trend Following| | Arbitrage       |
        | Strategy          | | Strategy       | | Strategy        |
        |                   | |                | |                 |
        +---------+---------+ +-------+--------+ +-------+---------+
                  |                   |                  |
     Evaluates trade decision   Evaluates trend    Looks for price
     based on price mean        and momentum       discrepancies
     (e.g., Z-Score)            (e.g., SMA/EMA)   (e.g., between markets)
                  |                   |                  |
                  +-------------------+------------------+
                                      |
                                      |
                         +------------v------------+
                         |                         |
                         |     Risk Management     |
                         |                         |
                         +------------+------------+
                                      |
                  Checks if trade passes risk thresholds
                                      |
                                      |
                         +------------v------------+
                         |                         |
                         |     Order Execution     |
                         |                         |
                         +------------+------------+
                                      |
                      Executes trade decision (e.g., Buy, Sell)
                                      |
                                      |
                         +------------v------------+
                         |                         |
                         |    Trading Engine       |
                         |                         |
                         +------------+------------+
                                      |
                 Coordinates order execution, monitors
                 system state, and initiates next cycle
                                      |
                                      |
                         +------------v------------+
                         |                         |
                         |   End of Trading Cycle  |
                         |                         |
                         +-------------------------+

```

### Component explanation

1. Start Trading Application:
   - Begins the trading session and initializes all required components.

2. Market Feed Handler:
   - Receives real-time or simulated market data (e.g., price, volume).
   - Feeds data to the Market Data Processor.

3. Market Data Processor:
   - Processes incoming data, cleans it, and calculates any necessary indicators (e.g., moving averages).
   - Feeds processed data to the Trading Strategy Factory.

4. Trading Strategy Factory:
   - Chooses the appropriate trading strategy based on configuration or dynamic selection.
   - Examples include:
     - Mean Reversion: Evaluates trades based on deviations from price means (e.g., Z-Score).
     - Trend Following: Looks for upward or downward trends using indicators like SMA (Simple Moving Average) or EMA (Exponential Moving Average).
     - Arbitrage: Looks for price discrepancies between two correlated markets to exploit arbitrage opportunities.

5. Risk Management:
   - Evaluates the risk of each trade based on thresholds or user-defined parameters.
   - Ensures the trade decision aligns with risk tolerance.

6. Order Execution:
   - Sends trade orders (e.g., buy/sell) to the market or brokerage API.
   - Ensures that the orders are executed as close to the strategy’s decision as possible.

7. Trading Engine:
   - Coordinates the trading process, ensuring each component (Market Feed, Strategy, Risk, Order Execution) runs in sync.
   - Monitors overall system health, handles any errors, and prepares for the next trading cycle.

8. End of Trading Cycle:
   - Marks the completion of a trading cycle and prepares to start the next cycle.

This diagram represents a Trading System Data Flow Diagram (DFD), illustrating each component's data flow and operational flow in a trading application.
