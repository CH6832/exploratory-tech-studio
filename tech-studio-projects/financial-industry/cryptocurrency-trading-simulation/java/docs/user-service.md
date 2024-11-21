### **API Gateway Documentation**

#### **Table of Contents**
- Overview
- Route Configuration
- Authentication Workflow
- Custom Filters

---

#### **Overview**
The API Gateway is a crucial component of the Crypto Trading Simulator architecture, serving as the primary entry point for frontend clients. It acts as an intermediary that routes requests to the appropriate backend services, ensuring that the requests are processed by the correct microservices based on their paths. The API Gateway handles several important functions, including request routing, security, load balancing, and monitoring.

By abstracting the complexity of microservices communication, the API Gateway simplifies the interaction between the frontend and the backend, while providing a centralized point for managing cross-cutting concerns such as authentication and logging.

---

#### **Route Configuration**
The routes for different backend services are defined in the `application.yml` configuration file. These routes ensure that the requests are correctly forwarded to the appropriate microservice based on the URL paths specified.

Each service has its own designated path and URI, which the API Gateway uses to forward requests. The configuration of routes is essential for proper request routing and ensuring that each microservice is correctly accessed.

Example route configuration:

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: user-service
          uri: http://localhost:8081
          predicates:
            - Path=/api/users/**
        - id: trade-service
          uri: http://localhost:8082
          predicates:
            - Path=/api/trades/**
```

In the above example:
- Requests to `/api/users/**` are routed to the **User Service** located at `http://localhost:8081`.
- Requests to `/api/trades/**` are forwarded to the **Trade Service** at `http://localhost:8082`.

This route configuration is defined in YAML format to provide flexibility and ease of management. It ensures that each microservice receives the requests it is designed to handle, without direct exposure to the frontend.

---

#### **Authentication Workflow**
The API Gateway plays a pivotal role in the authentication process. Before routing requests to the backend services, it ensures that the request is properly authenticated by validating the provided **JWT (JSON Web Token)**.

The authentication workflow proceeds as follows:

1. **Client Request with JWT Token**: 
   - The client (frontend) sends a request to the API Gateway, including a JWT token in the `Authorization` header. This token is typically issued upon successful user login and serves as proof of the user's identity.

2. **Token Validation**: 
   - The API Gateway uses a custom authentication filter to validate the JWT token. The filter ensures that the token is not expired, has not been tampered with, and is associated with a valid user. This process includes decoding the JWT and verifying its signature against the configured secret key or public key.

3. **Forwarding Valid Requests**: 
   - If the token is valid, the API Gateway forwards the request to the respective backend service. The request is then processed by the corresponding microservice.
   - If the token is invalid or missing, the API Gateway responds with an authentication error, typically a `401 Unauthorized` status code, and does not forward the request to the backend service.

This workflow ensures that only authenticated requests are processed, providing a secure mechanism for protecting sensitive operations and data.

---

#### **Custom Filters**
The API Gateway is designed to intercept requests using custom filters, allowing for additional processing before requests are forwarded to the backend services. These filters can be used for a variety of purposes, including logging, request validation, and header modification. Custom filters enhance the functionality of the API Gateway by providing centralized control over cross-cutting concerns.

The key types of custom filters include:

- **Logging**:
  - Filters can capture information about each incoming request, including the request path, HTTP method, headers, and other relevant details. This is useful for auditing and debugging purposes, ensuring that every request is logged for traceability.
  
- **Request Validation**:
  - Filters can validate the incoming request parameters to ensure that they meet the expected format or business rules. For instance, before forwarding a request to the **Trade Service**, a filter could check if the request includes valid trade data or if the user has sufficient balance.
  
- **Header Modification**:
  - Filters can modify request headers before they are forwarded. This is especially useful for tasks like adding security tokens, modifying content types, or adding custom headers that the backend services require for specific processing.
  
Custom filters provide flexibility in how requests are handled by the API Gateway, allowing developers to implement specific logic that applies to all or some requests.
