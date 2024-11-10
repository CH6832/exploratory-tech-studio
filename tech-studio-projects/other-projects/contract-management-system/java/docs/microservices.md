# Microservices Architecture

## Overview
The Contract Management System uses a microservices architecture, allowing services to operate independently and scale as needed. Each service has a single responsibility, and they communicate over well-defined APIs.

## Diagram

```

                     +-----------------------+
                     |     Authentication    |
                     |        Service        |
                     +-----------------------+
                                |
                                |
                                v
        +-----------------------+--------------------+
        |                        |                    |
        v                        v                    v
 +----------------+     +----------------+    +----------------+   +----------------+
 | Contract       |     | Notification   |    | Logging        |   | Analytics      |
 | Service        |     | Service        |    | Service        |   | Service        |
 +----------------+     +----------------+    +----------------+   +----------------+
        |
        v
 +--------------------+
 |  MongoDB Database  |
 +--------------------+
 
```

### Services:
- **Authentication Service**: Manages user login, registration, and session handling.
- **Contract Service**: Handles contract lifecycle operations.
- **Notification Service**: Sends notifications or emails to users.
- **Logging Service**: Handles logging of all actions and service statuses for auditing purposes.
- **Analytics Service**: Collects data and provides analytics or reports related to contracts.

## Service-to-Service Communication
- **REST APIs**: For communication between contract service, notification service, and other services.
- **Event-Driven**: Kafka or RabbitMQ for decoupled messaging between services (e.g., after contract creation, a notification event is published).

### 4. **Database Design (MongoDB) (`database-design.md`)**

# Database Design

## MongoDB Collection Structure

MongoDB stores each contract as a document. The collection name is `contracts`.

## Contract Document Structure

```
{
  "_id": ObjectId("605c72ef153207001f24e6b6"), 
  "title": "Contract A",
  "partyA": "Company X",
  "partyB": "Company Y",
  "startDate": "2024-01-01T00:00:00",
  "endDate": "2025-01-01T00:00:00",
  "status": "active",  // or "expired", "draft"
  "createdDate": "2024-01-01T00:00:00",
  "updatedDate": "2024-01-02T12:00:00"
}
```

### Indexes
- **Primary Index**: `_id` (ObjectId)
- **Secondary Indexes**:
  - `title` (for searching contracts by title)
  - `status` (for querying active or expired contracts)

### Relationships
MongoDB is a NoSQL database, so we will **not** have foreign key constraints. However, we may reference users (in case we want to track which user created or modified a contract).

### 5. **REST API Design (`rest-api-design.md`)**

# REST API Design

## Contract Service API Endpoints

### 1. Create a New Contract
- **Method**: POST
- **Endpoint**: `/contracts`
- **Request Body**: 
  ```json
  {
    "title": "Contract A",
    "partyA": "Company X",
    "partyB": "Company Y",
    "startDate": "2024-01-01",
    "endDate": "2025-01-01",
    "status": "draft"
  }
  ```
- **Response**: 
  ```json
  {
    "id": "605c72ef153207001f24e6b6",
    "title": "Contract A",
    "partyA": "Company X",
    "partyB": "Company Y",
    "startDate": "2024-01-01",
    "endDate": "2025-01-01",
    "status": "draft"
  }
  ```

### 2. Get a Contract by ID
- **Method**: GET
- **Endpoint**: `/contracts/{id}`
- **Response**:
  ```json
  {
    "id": "605c72ef153207001f24e6b6",
    "title": "Contract A",
    "partyA": "Company X",
    "partyB": "Company Y",
    "startDate": "2024-01-01",
    "endDate": "2025-01-01",
    "status": "active"
  }
  ```

### 3. Delete a Contract by ID
- **Method**: DELETE
- **Endpoint**: `/contracts/{id}`
- **Response**: 
  ```json
  {
    "message": "Contract deleted successfully"
  }
  ```

### 4. Update a Contract
- **Method**: PUT
- **Endpoint**: `/contracts/{id}`
- **Request Body**:
  ```json
  {
    "title": "Updated Contract A",
    "partyA": "Company X",
    "partyB": "Company Z",
    "startDate": "2024-01-01",
    "endDate": "2025-01-01",
    "status": "active"
  }
  ```
- **Response**: 
  ```json
  {
    "id": "605c72ef153207001f24e6b6",
    "title": "Updated Contract A",
    "partyA": "Company X",
    "partyB": "Company Z",
    "startDate": "2024-01-01",
    "endDate": "2025-01-01",
    "status": "active"
  }
  ```

### 6. **Deployment Architecture (`deployment-architecture.md`)**

# Deployment Architecture

## Overview

The Contract Management System is designed to run in a cloud-native environment, such as AWS, GCP, or Azure. Each microservice can be deployed in isolated containers using Docker and orchestrated using Kubernetes.

## Diagram

```
  +-------------------+           +---------------------+      +---------------------+
  | Contract Service  |           | Authentication      |      | Notification Service |
  | (Dockerized)      |           | Service (Dockerized)|      | (Dockerized)         |
  +-------------------+           +---------------------+      +---------------------+
          |                               |                             |
          +----------+    +--------------+--------------+             |
                     |    |                             |             |
                     v    v                             v             v
               +-------------+                    +-----------------+  +-----------------+
               |   MongoDB   |                    | Kubernetes Pod  |  |   Kubernetes    |
               |   (MongoDB  |                    |   (Contract     |  |   Cluster       |
               |    Atlas)   |                    |    Service)     |  |   (Auto-scaling)|
               +-------------+                    +-----------------+  +-----------------+
```

- **Microservices** are containerized using **Docker** and deployed on **Kubernetes** clusters.
- **MongoDB** is hosted on **MongoDB Atlas** for managed database services.
- Services are scaled based on **Kubernetes' auto-scaling**.
- **CI/CD** pipelines are used for automated deployment.

## Full diagram

```

+-----------------------------------------------+
|                 API Gateway                   |
|      (Handles requests, routing, and security)|
+-----------------------------------------------+
            |                        |
            v                        v
  +-------------------+           +-------------------+
  | Authentication    |           | Notification      |
  | Service           |           | Service           |
  | (User login, JWT) |           | (Email/SMS alerts)|
  +-------------------+           +-------------------+
            |                        |
            v                        v
  +-------------------+           +-------------------+
  | Contract Service  |<--------->| Logging Service   |
  | (CRUD contracts)  |           | (Central logging) |
  +-------------------+           +-------------------+
            |                        |
            v                        v
  +--------------------+      +-------------------+
  |   MongoDB Database |      | Analytics Service |
  |   (MongoDB Atlas)  |      | (Reports, Data)   |
  +--------------------+      +-------------------+

```

### Description of the Diagram:

1. **API Gateway**:
   - The entry point for all external requests, handling routing and security (e.g., authentication and authorization) for all services.
   - Could be implemented with Spring Cloud Gateway or Zuul.

2. **Authentication Service**:
   - Manages user authentication and session handling.
   - Issues JWT tokens for user authentication, ensuring secure access to the system.
   - Communicates with the Contract Service and other services for authorization checks.

3. **Contract Service**:
   - The core service for managing contracts, handling the creation, update, retrieval, and deletion of contracts.
   - Interacts with the **MongoDB Database** to persist contract data.
   - Sends events to the **Notification Service** (e.g., when a contract is created or updated).

4. **Notification Service**:
   - Sends notifications (such as email, SMS, or push notifications) to users based on contract-related events.
   - Triggered by the Contract Service or other services.
   - For example, notifications can be sent when a contract is created, updated, or about to expire.

5. **Logging Service**:
   - Aggregates logs from all services for centralized monitoring and debugging.
   - It interacts with the **Contract Service** and other services to collect logs.
   - Logs are stored and visualized, possibly using tools like **ELK stack** (Elasticsearch, Logstash, Kibana).

6. **Analytics Service**:
   - Gathers data from contracts and other services to generate analytics and reports.
   - Provides insights into contract trends, user behavior, and contract performance.
   - Typically includes features like data aggregation, trend analysis, and reporting.

7. **MongoDB Database**:
   - The system’s persistent storage, using **MongoDB** to store contract data in a **NoSQL** format.
   - It is hosted on **MongoDB Atlas** (a managed database service), providing high availability, scalability, and ease of management.
   - Stores the contracts, as well as metadata such as contract status, start/end dates, and party details.

### Communication Patterns:

- **REST APIs**: All services expose RESTful APIs for inter-service communication, especially for data operations (e.g., contract creation, updating, retrieval).
- **Event-Driven**: The **Notification Service** might listen for events (e.g., contract created, contract updated) via Kafka or RabbitMQ to decouple the communication.
- **Database**: The **Contract Service** interacts directly with the **MongoDB Database**, storing and retrieving contract data.

### Optional Scaling & Fault Tolerance Considerations:

- **Service Discovery**: Kubernetes or Consul can be used to ensure service discovery and manage communication between services.
- **Auto-scaling**: Using **Kubernetes** for auto-scaling each service based on demand.
- **Resilience**: Circuit breakers (using **Hystrix**) and retries should be considered for handling failures gracefully.





