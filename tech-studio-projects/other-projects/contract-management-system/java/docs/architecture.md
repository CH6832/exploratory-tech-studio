# System Architecture Overview

## Objective
The Contract Management System (CMS) is a distributed application built using microservices architecture. It is designed to allow the creation, storage, and management of contracts in a secure and scalable way. The system utilizes a MongoDB database to store contract details and uses a Spring Boot-based microservices approach for modularity.

## Architecture Diagram
The following diagram illustrates the architecture of the Contract Management System:

```
                       +------------------------+
                       |       Contract         |
                       |       Service          |
                       |  (Spring Boot API)     |
                       +-----------+------------+
                                   |
                                   |
                                   v
                       +------------------------+
                       |      Contract          |
                       |       Repository       |
                       |    (MongoDB Database)  |
                       +------------------------+
                                   ^
                                   |
                                   |
                       +------------------------+
                       |     Frontend (UI)      |
                       |   (React / Angular)    |
                       +------------------------+
                                   ^
                                   |
                                   |
                       +------------------------+
                       |  Authentication &      |
                       |   Authorization Service|
                       +------------------------+
                                   |
                                   |
                                   v
                       +------------------------+
                       |     Other Services     |
                       +------------------------+
```

### 2. **Component Design (`component-design.md`)**

# Component Design

The Contract Management System is broken into multiple components to ensure separation of concerns, scalability, and maintainability.

## Key Components

### 1. **Contract Service**
- **Responsibilities**: Handles all operations related to contracts (create, update, delete, view). This service exposes REST endpoints to interact with the database and provides business logic for contract management.
- **Technology**: Spring Boot, MongoDB
- **Key Interfaces**: 
  - REST API endpoints for managing contracts (`/contracts`, `/contracts/{id}`)
  - Contract Repository interface for MongoDB operations

### 2. **Authentication Service**
- **Responsibilities**: Handles user authentication and authorization. Secures all other services through JWT-based authentication.
- **Technology**: Spring Security, JWT, OAuth2
- **Key Interfaces**: 
  - Login and registration endpoints (`/auth/login`, `/auth/register`)
  - JWT token generation and validation

### 3. **Frontend**
- **Responsibilities**: Provides a user interface (UI) for interacting with the Contract Management System. Users can view, create, edit, and delete contracts.
- **Technology**: React or Angular
- **Key Interfaces**: 
  - Forms for contract creation and updates
  - List and detail views for contracts
  - Authentication pages

### 4. **Database (MongoDB)**
- **Responsibilities**: Stores contract data and metadata.
- **Technology**: MongoDB
- **Data Model**: Contracts stored as documents with fields such as `id`, `title`, `partyA`, `partyB`, `startDate`, `endDate`.

### 5. **API Gateway (Optional)**
- **Responsibilities**: Acts as the entry point for all incoming requests. It routes requests to the appropriate microservice.
- **Technology**: Spring Cloud Gateway, NGINX (optional)

## Communication Between Services
- **Synchronous**: RESTful communication for user-facing API calls between the frontend and contract service.
- **Asynchronous**: Messaging queues (like RabbitMQ or Kafka) for internal service communication if needed.
