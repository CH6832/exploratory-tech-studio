# Banking Application

A fully functional banking application built with Spring Boot, showcasing account management, customer information, and transaction handling. The application is structured using a layered architecture with RESTful APIs.

## Features

- **Account Management**: Create, update, delete, and retrieve bank accounts.
- **Customer Management**: Manage customer information and their associated accounts.
- **Transaction Handling**: Deposit, withdraw, and transfer funds between accounts.
- **Security**: Role-based access control using Spring Security.
- **Docker Support**: Easily deployable using Docker.

## Technologies Used

- **Java 17**: The latest LTS version for building the application.
- **Spring Boot**: For building the RESTful web service.
- **Spring Data JPA**: For database interaction.
- **H2 Database**: In-memory database for development and testing.
- **Spring Security**: For securing the application.
- **Maven**: For project management and dependency management.

## Getting Started

### Prerequisites

- Java 17 or later
- Maven 3.8.4 or later
- Docker (optional, for containerization)

### Installation

1. **Clone the repository**:
    ```bash
    git clone https://github.com/yourusername/banking-application.git
    cd banking-application
    ```

2. **Build the application**:
    ```bash
    mvn clean package
    ```

3. **Run the application locally**:
    ```bash
    mvn spring-boot:run
    ```

    The application will start on `http://localhost:8080`.

### API Endpoints

#### Account Endpoints

- **Get All Accounts**: `GET /api/accounts`
- **Get Account by ID**: `GET /api/accounts/{id}`
- **Create Account**: `POST /api/accounts`
- **Update Account**: `PUT /api/accounts/{id}`
- **Delete Account**: `DELETE /api/accounts/{id}`
- **Deposit Money**: `POST /api/accounts/{id}/deposit?amount={amount}`
- **Withdraw Money**: `POST /api/accounts/{id}/withdraw?amount={amount}`

#### Customer Endpoints

- **Get All Customers**: `GET /api/customers`
- **Get Customer by ID**: `GET /api/customers/{id}`
- **Create Customer**: `POST /api/customers`
- **Update Customer**: `PUT /api/customers/{id}`
- **Delete Customer**: `DELETE /api/customers/{id}`

#### Transaction Endpoints

- **Get All Transactions**: `GET /api/transactions`
- **Get Transaction by ID**: `GET /api/transactions/{id}`
- **Get Transactions by Account ID**: `GET /api/transactions/account/{accountId}`
- **Deposit Transaction**: `POST /api/transactions/deposit?accountId={id}&amount={amount}`
- **Withdraw Transaction**: `POST /api/transactions/withdraw?accountId={id}&amount={amount}`
- **Transfer Transaction**: `POST /api/transactions/transfer?fromAccountNumber={fromAccountNumber}&toAccountNumber={toAccountNumber}&amount={amount}`

### Security

- The application uses basic authentication.
- Access to the endpoints requires the user to have the `ADMIN` role.

### Docker Support

1. **Build the Docker image**:
    ```bash
    docker build -t banking-app .
    ```

2. **Run the Docker container**:
    ```bash
    docker run -p 8443:8443 banking-app
    ```

The application will be accessible at `http://localhost:8443`.

### License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
