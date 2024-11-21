# Crypto Trading Simulator

## Project Overview

The **Crypto Trading Simulator** is a cloud-native application designed to simulate cryptocurrency trading, allowing users to practice their investment strategies without real financial risk. The application leverages modern microservice architecture, powered by **Spring Boot** for the backend and **Angular** for the frontend. It integrates real-time data and allows for seamless interaction between the front-end user interface and backend services via an API Gateway.

---

## Features

- **User Authentication & Authorization**: Secure login and profile management via JWT tokens.
- **Trade Simulation**: Execute and track cryptocurrency trades with real-time updates.
- **Portfolio Management**: View and analyze portfolio performance, including asset allocation and historical growth.
- **Real-Time Notifications**: WebSocket integration for instant trade updates and price alerts.
- **Responsive UI**: Built with **Bootstrap** and modern UX principles for a polished user experience.

---

## System Architecture

The Crypto Trading Simulator is built using a **Microservice Architecture** consisting of the following components:

- **Frontend**:
  - Developed using **Angular**, a powerful web framework for dynamic user interfaces.
  - Communicates with backend services via HTTP calls through an **API Gateway**.
  - Utilizes **Bootstrap 5** for responsive design and a modern UI.

- **Backend**:
  - **Spring Boot** based microservices:
    - **User Service**: Manages user authentication, profiles, and authorization.
    - **Trade Service**: Handles the execution of cryptocurrency trades, validating transactions, and maintaining trade history.
    - **Portfolio Service**: Provides portfolio insights, including performance metrics and asset allocation.
  - **API Gateway**: Routes incoming requests to appropriate services and provides centralized authentication and security checks.

- **Real-Time Features**:
  - **WebSocket** integration in the **Trade Service** for live trade updates and notifications.

- **Database**:
  - **PostgreSQL** databases for the **User Service**, **Trade Service**, and **Portfolio Service** to persist data.

---

## Technologies Used

- **Frontend**:
  - Angular 15
  - Bootstrap 5
  - TypeScript, HTML, and CSS

- **Backend**:
  - Spring Boot (for all microservices)
  - Spring Cloud Gateway (API Gateway)
  - WebSockets (for real-time updates)
  - PostgreSQL (for data persistence)
  - JWT (for authentication)

- **Build Tools**:
  - Maven (for Java backend)
  - Node.js and npm (for Angular frontend)

- **DevOps**:
  - Docker (for containerization of services)

---

## Installation & Setup

### Backend Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-repository/crypto-trading-simulator.git
   cd crypto-trading-simulator
   ```

2. **Run the Backend Microservices**:
   - Navigate to each service folder (`user-service`, `trade-service`, `portfolio-service`) and run the following Maven command:
     ```bash
     mvn spring-boot:run
     ```
   - Alternatively, you can run all services using **Docker Compose** if Docker is set up:
     ```bash
     docker-compose up
     ```

3. **Configure the Database**:
   - Ensure that each microservice has access to a running **PostgreSQL** instance. You can configure database credentials in the `application.properties` file for each service.

4. **Access the Services**:
   - The **API Gateway** runs on `http://localhost:8080` and routes requests to the respective services.

---

### Frontend Setup

1. **Clone the Repository** (if not already cloned):
   ```bash
   git clone https://github.com/your-repository/crypto-trading-simulator.git
   cd crypto-trading-simulator/frontend
   ```

2. **Install Dependencies**:
   Ensure **Node.js** and **npm** are installed, then run:
   ```bash
   npm install
   ```

3. **Run the Angular Frontend**:
   ```bash
   ng serve
   ```

4. **Access the Frontend**:
   The frontend will be available at `http://localhost:4200`. It will communicate with the backend services via the API Gateway at `http://localhost:8080`.

---

## Testing

### Unit Tests (Backend)

Each microservice contains comprehensive **unit tests** to validate business logic and individual components.

- **User Service Tests**: Ensure correct user registration, authentication, and JWT token validation.
- **Trade Service Tests**: Validate trade execution, balance checks, and historical trade retrieval.
- **Portfolio Service Tests**: Verify portfolio calculations, performance analytics, and asset tracking.

Run the unit tests with the following Maven command inside each service:

```bash
mvn test
```

### Integration Tests (Backend)

Integration tests are provided to validate the interaction between services. These tests verify end-to-end workflows, such as:

- **User registration and authentication flow**
- **Trade execution and portfolio updates**

Run integration tests with the following Maven command:

```bash
mvn verify
```

### Frontend Tests

The frontend includes **unit tests** and **e2e tests** using **Karma** and **Protractor**.

- To run unit tests:
  ```bash
  ng test
  ```

- To run e2e tests:
  ```bash
  ng e2e
  ```

---

## Application Features

1. **User Authentication**: 
   - JWT tokens are used for secure login and authorization.
   - Role-based access controls (RBAC) are implemented to restrict sensitive actions.

2. **Trade Simulation**:
   - Execute buy and sell trades for simulated cryptocurrency assets.
   - Track trade history and performance.

3. **Portfolio Management**:
   - Users can view their portfolio's total value and individual asset performance.
   - Portfolio analytics provide insights into diversification and historical growth trends.

4. **Real-Time Notifications**:
   - WebSocket integration sends real-time updates to users on trade status and price alerts.

---

## Security Considerations

- **JWT Authentication**: 
   - All API requests are secured using JWT tokens.
   - Tokens are validated in the **API Gateway** to ensure only authorized users can access protected routes.

- **Role-Based Access Control**:
   - Different roles (e.g., Admin, User) are implemented to control access to various parts of the system.

- **HTTPS**: 
   - All services are configured to use HTTPS for secure communication, ensuring data protection in transit.

---

## Future Improvements

- **Real-Time Trading**: Integrate actual cryptocurrency data feeds for live trading.
- **Analytics Dashboard**: Add detailed visual analytics for users to view trading patterns and portfolio trends.
- **Multi-Currency Support**: Extend the system to support multiple currencies and exchanges for a broader simulation experience.

---

## Conclusion

The **Crypto Trading Simulator** is a full-stack web application that mimics a trading platform, allowing users to practice trading cryptocurrencies and manage their portfolios. With its modern architecture using Spring Boot and Angular, the app provides a highly interactive and scalable platform to practice and improve trading strategies. It leverages best practices in security, testing, and UI/UX design, providing a robust and seamless experience.

---

## License

MIT License. See the LICENSE file for more details.
