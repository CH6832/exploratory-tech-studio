### **API Gateway Documentation**

#### **Table of Contents**
- Overview
- Key Responsibilities
- Route Configuration
- Authentication Workflow
- Custom Filters
- Load Balancing and Scalability
- Error Handling and Resilience
- Monitoring and Logging

---

#### **Overview**
The API Gateway serves as the central entry point for all client interactions in the Crypto Trading Simulator application. As a reverse proxy, it manages incoming requests, routes them to appropriate backend microservices, and ensures that services are accessible in a streamlined and secure manner. The API Gateway plays a crucial role in simplifying system architecture by decoupling clients from direct communication with individual services.

Built on **Spring Cloud Gateway**, the gateway acts as an intermediary, providing functionalities such as request routing, authentication, security enforcement, response aggregation, and system monitoring. This allows the backend microservices to remain isolated and independently scalable while offering a unified interface to the client-side.

---

#### **Key Responsibilities**
The API Gateway is tasked with several key responsibilities, which include:

1. **Request Routing**: The gateway determines the appropriate microservice to handle incoming requests based on predefined URL patterns and routing configurations.
2. **Authentication & Authorization**: The gateway validates incoming requests by checking the JWT tokens, ensuring that only authorized users can access protected resources.
3. **Security Enforcement**: As the first line of defense, the gateway enforces security protocols such as HTTPS, JWT validation, and rate limiting to prevent unauthorized access and malicious attacks.
4. **Response Aggregation**: For operations that require data from multiple services, the gateway aggregates responses from various backend microservices into a single, consolidated response for the client.
5. **Monitoring**: The gateway tracks system metrics such as response times, error rates, and request volumes, providing essential information for performance analysis and troubleshooting.

---

#### **Route Configuration**
Routing in the API Gateway is the process of mapping incoming requests to appropriate backend services. This routing configuration is defined in a YAML file (`application.yml`), which contains the necessary rules for directing requests to the correct service. 

Routes are defined with specific predicates that match URL patterns and allow the gateway to apply filters and route requests to designated services. For example, if a request URL starts with `/api/users`, it will be forwarded to the user service, while requests beginning with `/api/trades` will go to the trade service. 

In addition to the routing, various filters can be applied to modify requests or responses, such as header adjustments, path rewrites, or logging.

---

#### **Authentication Workflow**
Authentication within the API Gateway ensures that only valid, authorized requests reach the backend microservices. This is done through the validation of **JWT tokens**. When a client makes a request to the gateway, the JWT token included in the `Authorization` header is extracted and validated.

The authentication process follows these steps:

1. The client includes a JWT token in the request header.
2. The gateway checks the presence of the token and validates it against an authentication service.
3. The token's validity is verified by checking its signature, expiration, and embedded claims (e.g., user ID and roles).
4. If the token is valid, the request proceeds to the appropriate microservice; otherwise, the gateway responds with an authentication error (e.g., HTTP 401 Unauthorized).

This ensures that sensitive operations such as trade execution or portfolio management are only accessible by authenticated and authorized users.

---

#### **Custom Filters**
Filters in the API Gateway allow you to intercept and modify requests and responses as they pass through the gateway. They provide a powerful mechanism for customizing behavior, adding cross-cutting concerns like logging, authentication, and request validation.

Filters can be divided into the following types:

1. **Global Filters**: These filters are applied to all requests passing through the gateway, regardless of the route. Common use cases include logging, request timing, and security enforcement.
2. **Route-Specific Filters**: These filters are specific to certain routes and only apply to requests that match the route configuration. For example, a filter for validating input data for the trade service.

Some common examples of filter functionality include:
- **Logging**: Captures metadata such as the request path, headers, and timestamp for auditing and debugging purposes.
- **Request Validation**: Ensures that necessary headers or parameters are included in the request.
- **Header Modification**: Adds custom headers (e.g., `X-Service-Name`) to the request before forwarding it to the backend service.
- **Rate Limiting**: Controls the number of requests a user or service can make within a given time frame to prevent abuse.

---

#### **Load Balancing and Scalability**
The API Gateway enhances the scalability and reliability of the system by implementing **load balancing**. Load balancing ensures that traffic is distributed across multiple instances of a service to prevent any single instance from being overwhelmed.

The API Gateway typically integrates with service discovery tools like **Eureka** or **Consul**, which register available instances of services. As new service instances are added or removed, the gateway dynamically adjusts to these changes and routes traffic accordingly. This integration allows the gateway to distribute requests based on real-time availability and service health, ensuring optimal load distribution.

**Round-robin load balancing** is the most commonly used strategy, where requests are distributed evenly across all available instances. This helps achieve high availability, fault tolerance, and the ability to scale services independently.

---

#### **Error Handling and Resilience**
The API Gateway is equipped with mechanisms to handle errors gracefully and ensure the resilience of the system. In the event of failures, such as an unavailable microservice or invalid requests, the gateway responds with appropriate error messages and status codes.

1. **Invalid Requests**: If a request is missing required parameters or is malformed, the gateway will return an HTTP 400 Bad Request response.
2. **Authentication Failures**: If the JWT token is invalid or expired, the gateway will return a 401 Unauthorized status.
3. **Service Unavailability**: If a backend service is unavailable, the gateway will return a 503 Service Unavailable response or apply a fallback mechanism if configured (e.g., providing default data or error messages).
4. **Rate Limiting**: If a user exceeds their rate limit, the gateway can throttle or reject the request with a 429 Too Many Requests response.

The API Gateway can also implement **circuit breakers** to handle service failures gracefully by halting further requests to a service until it recovers, preventing cascading failures.

---

#### **Monitoring and Logging**
Monitoring and logging are essential for maintaining the health and performance of the system. The API Gateway plays a central role in collecting metrics and logging events that help track system activity, diagnose issues, and optimize performance.

1. **Metrics**: The gateway can be configured to export performance metrics, such as the number of requests handled, response times, error rates, and service health, to monitoring tools like **Prometheus**. This allows operators to observe the overall health of the system and detect any anomalies.
2. **Centralized Logging**: All logs from the gateway can be collected and stored in a centralized logging system like **ELK (Elasticsearch, Logstash, and Kibana)** or **Splunk**. This enables efficient troubleshooting by allowing the development and operations teams to correlate logs from the gateway with those from the microservices.

Key metrics typically tracked by the API Gateway include:
- **Request Count**: The number of incoming requests over time.
- **Response Times**: The latency of requests handled by the gateway.
- **Error Rates**: The frequency of failed requests (e.g., 4xx or 5xx responses).

By integrating with such tools, the gateway enables proactive system monitoring and allows for fast identification and resolution of issues.
