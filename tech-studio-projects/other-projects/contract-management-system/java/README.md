# Contract Management System (CMS)

This **Contract Management System (CMS)** exemplifies a high-standard implementation of a scalable and modular microservices architecture, suited for environments that demand efficient contract lifecycle management, strict auditing, detailed reporting, and real-time monitoring. This project leverages containerized **Spring Boot** microservices and a comprehensive observability stack with **Prometheus** and **Grafana** for monitoring, making it ideal for businesses requiring transparency, reliability, and security in contract management.

## Project Objective and Scope

Developed to meet industry standards, this CMS is crafted to support complex contract workflows, secure handling of sensitive information, and extensive audit logging for traceability. With a focus on scalability, this project allows for independent scaling, modularity, and seamless inter-service communication, providing a model that promotes data transparency, accountability, and compliance with organizational requirements.

## Key Features of the Contract Management System

### **Microservices Architecture**

The CMS system is composed of independent microservices, each focused on a specific aspect of contract management, allowing for dedicated scaling, isolated deployments, and simplified maintenance. Key services include:

- **Contract Service**: Manages contract lifecycles, offering endpoints for creating, reading, updating, and deleting contracts, which serve as the main interface for contract operations.
- **Payment Service**: Manages contract-related financial transactions, enabling secure processing and documentation of payments.
- **Audit Service**: Tracks actions and provides a detailed, timestamped audit trail for compliance, logging significant actions like updates and deletions.
- **Report Service**: Generates detailed reports for administrators and stakeholders, covering contract summaries, financial overviews, and audit histories.
- **Search Service**: Offers advanced search functionality within MongoDB, empowering users to locate specific contract information efficiently.

### **Monitoring and Visualization with Prometheus and Grafana**

The CMS incorporates Prometheus for data collection and Grafana for dashboard-based visualization. Together, they allow comprehensive performance tracking across services, including request latency, resource usage, and system health. Recommended dashboards such as the Spring Boot Monitoring Dashboard (ID: 4350) facilitate comprehensive insights into microservice performance.

### **Detailed Auditing and Compliance Logging**

The **Audit Service** logs all critical actions within the CMS, ensuring accountability, transparency, and traceability for compliance with internal and external regulatory requirements. Actions are logged with metadata such as user identity, action type, and timestamp, providing a complete history of all system interactions.

### **Advanced Reporting Capabilities**

The **Report Service** generates and exports data-driven reports, supporting key business insights across contract, audit, and payment services. Reports can be customized for specific business needs and are exportable for easy dissemination among stakeholders.

### **Enhanced Contract Search**

The **Search Service** enables powerful search functionality within MongoDB, offering advanced filtering capabilities to streamline the retrieval of contract information. By integrating search within the CMS, users can locate specific contracts and records promptly, even as data volumes grow.

### **Centralized Logging System**

Through the **Logging Service**, all CMS services log actions and errors centrally, facilitating effective debugging and monitoring. This service collects logs asynchronously, ensuring the logging process does not impact the main application workflow.

### **Security and Scalability**

Each microservice is fully containerized, providing flexibility in deployment, scaling, and security management. Sensitive information, such as payment data and audit logs, is managed according to best practices, ensuring data confidentiality and integrity. The independent scalability of each service also allows for resource optimization according to demand.

## Core Technologies and Frameworks

- **Spring Boot**: Framework for microservice development, providing modular and lightweight architecture for each service.
- **MongoDB**: NoSQL database with flexible schema design for storing contracts, audit logs, and search data.
- **Prometheus and Grafana**: Integrated for metrics collection and dashboard visualization, offering a real-time view into service performance and health.
- **RabbitMQ**: Message broker for asynchronous, reliable communication between services, enhancing data flow and performance.

## Development, Testing, and Deployment

The CMS is built with industry-standard practices for testing and deployment to ensure reliability and maintainability.

### Testing Strategies

- **Unit Testing**: Each microservice is unit-tested to verify individual functionalities.
- **Integration Testing**: Tests are implemented to validate inter-service communication and data consistency.
- **Load Testing**: Performance is monitored under high-traffic scenarios using metrics from Prometheus, validating system resilience.

### Opening and Running a Single Microservice in Eclipse Java EE IDE

To run an individual microservice, follow these steps:

1. **Clone the Project Repository** and import the specific microservice (e.g., `contract-service`) into your Eclipse workspace.
2. **Open Eclipse** and navigate to **File > Import**.
3. Select **Maven > Existing Maven Projects** and locate the microservice directory.
4. Once imported, right-click on the project, go to **Run As > Spring Boot App** to start the microservice.

### Example: Configuring and Running the Contract Service

1. **Import `contract-service` into Eclipse**: Ensure that you have all dependencies correctly resolved by updating the Maven project if needed.
2. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```
3. Access the Contract Service endpoints as specified in the API documentation.

## Accessing the System

After deployment, the following interfaces will be accessible:

- **Prometheus**: Exposes system metrics, available for collection and analysis.
- **Grafana**: Visualizes Prometheus metrics through secure dashboards.
- **CMS REST API**: Provides endpoints across services, allowing integration and user interactions.

## License

This project is released under the MIT License, allowing for free use, modification, and distribution with attribution. The complete license text can be found in the [LICENSE](LICENSE) file.