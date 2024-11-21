### **Crypto Trading Simulator Architecture Documentation**

#### **Table of Contents**
- Overview
- System Design
- Microservices Breakdown
- Data Flow
- API Gateway Design
- Security Architecture
- Scalability Considerations

---

#### **Overview**
The Crypto Trading Simulator is a cloud-native application built with a **microservice architecture**, enabling modularity, flexibility, and scalability. This application allows users to simulate cryptocurrency trading, manage portfolios, and perform real-time trade executions. The system consists of four core components:

1. **Frontend**: A responsive web application built using Angular, designed for user interaction.
2. **Microservices**: A set of independent backend services that handle domain-specific logic (user management, trade execution, and portfolio management).
3. **API Gateway**: A centralized routing, aggregation, and security layer that connects the frontend to the microservices.
4. **Database Layer**: Each microservice has its own database, ensuring domain isolation and data consistency.

These components are designed to work cohesively to deliver an efficient and user-friendly trading simulation experience. The architecture leverages modern practices in cloud-native development, including containerization, decentralized services, and scalability.

---

#### **System Design**
The Crypto Trading Simulator system architecture is comprised of four primary components, each with distinct responsibilities:

1. **Frontend (Angular)**: 
   The Angular frontend is a Single Page Application (SPA) designed to provide users with an interactive interface for viewing market data, executing trades, managing their portfolio, and analyzing performance. The frontend interacts with the backend through API calls, which are routed via the API Gateway.

2. **Microservices**: 
   The backend is split into multiple services, each dedicated to a specific domain:
   - **User Service**: Manages user authentication, profile data, and account-related functionalities.
   - **Trade Service**: Handles trade execution, market order management, and transaction validation.
   - **Portfolio Service**: Tracks users' portfolios, calculates performance, and provides analytics.

3. **API Gateway**: 
   The API Gateway serves as the central access point for the frontend, routing client requests to the appropriate microservice, validating authentication tokens, and aggregating responses when necessary. It acts as a reverse proxy and security layer for all incoming API requests.

4. **Database Layer**: 
   Each microservice has its own dedicated database to ensure separation of concerns and maintain data integrity within each domain. The databases are typically relational (SQL) for transactional consistency, though certain services may use NoSQL depending on the need for scalability or performance.

---

#### **Microservices Breakdown**
The application is divided into distinct microservices that are each responsible for a specific functionality within the system. This division allows for isolated, independent scaling and ensures that services can evolve without affecting one another.

1. **User Service**:
   The User Service handles all user-related activities, including:
   - **Authentication**: Managing user login, registration, and session management.
   - **Authorization**: Ensuring that only authorized users can access certain resources.
   - **User Profiles**: Storing and retrieving user details such as preferences, settings, and account information.

2. **Trade Service**:
   The Trade Service is responsible for handling the core trading functionalities:
   - **Trade Execution**: Processing buy and sell orders on the simulated crypto market.
   - **Order Management**: Managing the state of user orders (pending, executed, canceled).
   - **Transaction Validation**: Verifying that trades comply with simulation rules and that sufficient funds are available for execution.

3. **Portfolio Service**:
   The Portfolio Service focuses on tracking user investments and calculating portfolio performance:
   - **Portfolio Tracking**: Storing and retrieving assets, balances, and holdings for each user.
   - **Performance Calculation**: Calculating and displaying portfolio gains/losses, performance over time, and asset value.
   - **Analytics**: Providing insights and reports about portfolio performance, market trends, and historical data.

Each of these services is designed to be independent, loosely coupled, and deployable at scale.

---

#### **Data Flow**
The data flow within the Crypto Trading Simulator is designed to ensure smooth communication between the frontend, the API Gateway, and the backend microservices.

1. **Frontend Interaction**:
   A user accesses the Angular web application, which serves as the entry point for interaction. The frontend provides a responsive user interface for viewing market data, initiating trades, managing portfolios, and monitoring analytics.

2. **API Request Routing**:
   Once the user interacts with the frontend, the application makes API requests to the backend. These requests are routed through the API Gateway, which acts as an intermediary between the frontend and the microservices.

3. **Microservice Communication**:
   The API Gateway routes requests to the relevant microservices based on predefined URL patterns. Each microservice processes the request according to its domain (e.g., trade execution in the Trade Service, portfolio data in the Portfolio Service, etc.).

4. **Data Persistence**:
   Each microservice interacts with its dedicated database to store and retrieve data. For example, the User Service communicates with the user database to authenticate users and update profile data, while the Trade Service interacts with the trade database to record transactions.

5. **Response Aggregation**:
   In cases where data from multiple services is required for a single client request (e.g., fetching user portfolio performance and executing trades), the API Gateway aggregates responses and returns a consolidated result to the frontend.

---

#### **API Gateway Design**
The API Gateway is the central management point for handling all external requests and routing them to the appropriate backend services. It plays a vital role in:

- **Routing Requests**: The API Gateway uses predefined rules to route client requests to the appropriate microservices based on the request path or headers.
- **Token Validation**: The API Gateway checks the validity of authentication tokens (JWT) to ensure that only authorized users can access protected resources.
- **Response Aggregation**: For requests that require data from multiple services, the API Gateway aggregates responses before sending them back to the client.

The API Gateway ensures that clients can interact with the backend in a simplified manner, without needing to understand the complexities of service communication.

---

#### **Security Architecture**
The security architecture of the Crypto Trading Simulator is built to ensure secure communication, data protection, and user privacy. Key security features include:

1. **JWT (JSON Web Tokens)**:
   Authentication is handled through JWT, which is used to validate requests and ensure that only authenticated users can access protected routes. Each user is issued a token upon login, which must be sent with subsequent requests to access secured resources.

2. **Role-Based Access Control (RBAC)**:
   Role-based access control is implemented to restrict certain operations based on user roles. For example, only users with the "admin" role can perform certain administrative actions, while regular users have access to portfolio management and trade execution.

3. **HTTPS**:
   Secure communication is enforced across the entire system using HTTPS to protect data in transit. All requests from the frontend to the API Gateway and from the gateway to the microservices are transmitted over secure, encrypted channels.

---

#### **Scalability Considerations**
Scalability is a core design principle for the Crypto Trading Simulator, ensuring that the application can handle growing amounts of traffic and data as the user base expands. Key scalability features include:

1. **Independent Service Scaling**:
   Each microservice in the system is designed to be independently scalable. As traffic increases for a particular service (e.g., Trade Service during peak trading hours), additional instances of that service can be deployed without affecting other services.

2. **Stateless Design**:
   The system is designed to be stateless, meaning that no session or state information is stored within the microservices. This enables horizontal scaling, where new instances of services can be spun up or down without the need to maintain session consistency.

3. **Event-Driven Architecture** (Optional):
   An event-driven architecture can be implemented to enable real-time updates and notifications. For example, trade executions, portfolio updates, and price changes can be communicated through events, allowing the system to react dynamically to user actions and market changes.

4. **Load Balancing**:
   The system can scale horizontally through the use of load balancing. Requests are distributed evenly across multiple instances of microservices, ensuring optimal resource utilization and preventing any single instance from being overwhelmed.
