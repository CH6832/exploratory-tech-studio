# Spring Boot Microservices with Monitoring, Auditing, and Reporting

This project demonstrates the setup of multiple Spring Boot microservices with monitoring using **Prometheus** and **Grafana**, along with **Audit** and **Report Generation** functionality. The services expose metrics that Prometheus scrapes, and Grafana visualizes the collected data in dashboards. The project also includes audit logging to track actions performed by users and a report generation service for exporting data.

## Services in This Project:
- **contract-service**: Manages contracts and provides endpoints for contract-related operations.
- **payment-service**: Handles payment-related operations and processing.
- **inventory-service**: Manages inventory and stock data.
- **user-service**: Handles user registration, login, and management.
- **audit-service**: Logs and tracks actions performed across services for auditing purposes.
- **report-service**: Generates reports and exports data from services like `contract-service`, `payment-service`, and others.
- **Prometheus**: Collects metrics from the microservices.
- **Grafana**: Visualizes the collected metrics from Prometheus.

## Prerequisites
Make sure you have the following installed:
- Docker
- Docker Compose
- Java (JDK 11 or later) for running Spring Boot microservices
- Maven or Gradle for building the services

## Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-repo/your-project.git
   cd your-project
   ```

2. **Update the Spring Boot Applications**:
   Each Spring Boot application should expose Prometheus metrics at `/actuator/prometheus`. Ensure that the following properties are added to `application.properties` for each service:
   ```properties
   management.endpoints.web.exposure.include=health,info,prometheus
   management.endpoint.prometheus.enabled=true
   ```

3. **Dockerize the Microservices**:
   Each microservice (e.g., `contract-service`, `payment-service`, `audit-service`, etc.) should have its own `Dockerfile`. Ensure you have a `Dockerfile` in each of these directories.

   Example `Dockerfile` for a Spring Boot service:
   ```dockerfile
   FROM openjdk:21-jdk-slim
   COPY target/your-app.jar /usr/app/
   WORKDIR /usr/app
   RUN sh -c 'touch your-app.jar'
   ENTRYPOINT ["java", "-jar", "your-app.jar"]
   ```

4. **Configure Prometheus**:
   Ensure your `prometheus.yml` is properly set up to scrape the metrics from your Spring Boot applications. Below is an example of the configuration:
   ```yaml
   scrape_configs:
     - job_name: 'contract-service'
       metrics_path: '/actuator/prometheus'
       scrape_interval: 5s
       static_configs:
         - targets: ['contract-service:8080']
   
     - job_name: 'payment-service'
       metrics_path: '/actuator/prometheus'
       scrape_interval: 5s
       static_configs:
         - targets: ['payment-service:8080']
   
     - job_name: 'inventory-service'
       metrics_path: '/actuator/prometheus'
       scrape_interval: 5s
       static_configs:
         - targets: ['inventory-service:8080']
   
     - job_name: 'user-service'
       metrics_path: '/actuator/prometheus'
       scrape_interval: 5s
       static_configs:
         - targets: ['user-service:8080']
   
     - job_name: 'audit-service'
       metrics_path: '/actuator/prometheus'
       scrape_interval: 5s
       static_configs:
         - targets: ['audit-service:8081']
   
     - job_name: 'report-service'
       metrics_path: '/actuator/prometheus'
       scrape_interval: 5s
       static_configs:
         - targets: ['report-service:8082']
   ```

5. **Start the Services with Docker Compose**:
   From the project root, run:
   ```bash
   docker-compose up --build
   ```

6. **Access the Services**:
   - Prometheus: [http://localhost:9090](http://localhost:9090)
   - Grafana: [http://localhost:3000](http://localhost:3000)
     - Login with the default username `admin` and password `admin`.

7. **Configure Grafana to Connect to Prometheus**:
   - In the Grafana UI, go to **Configuration → Data Sources → Add data source**.
   - Choose **Prometheus** and set the URL to `http://prometheus:9090`.
   - Save and test the connection.

8. **Import Dashboards**:
   You can import pre-configured dashboards for Spring Boot or create your own to visualize the metrics from Prometheus.

   Example dashboard ID for Spring Boot metrics:
   - [Spring Boot Monitoring Dashboard (ID: 4350)](https://grafana.com/grafana/dashboards/4350)

## Services Overview

### 1. **Audit Service**:
   The `audit-service` tracks and logs actions performed in the system. This service provides audit logs that can be used for auditing purposes, keeping track of actions like creating, updating, or deleting records.

   The service exposes endpoints like:
   - `POST /api/audit/log` - Logs an action with the user performing it.

   **Audit Logging**: Each action (e.g., creating a contract, updating a payment) is logged for traceability. You can integrate the `AuditService` into other services to log actions.

   Example usage in `ContractController`:
   ```java
   @Autowired
   private final AuditService auditService;

   @PostMapping
   public ResponseEntity<Contract> createContract(@RequestBody Contract contract) {
       auditService.logAction("Create contract", "user123");
       Contract createdContract = contractService.createContract(contract);
       return new ResponseEntity<>(createdContract, HttpStatus.CREATED);
   }
   ```

### 2. **Report Service**:
   The `report-service` generates reports based on the data from other services like `contract-service`, `payment-service`, etc. This service can generate various types of reports, such as contract summaries or payment history.

   Example of how to generate a report:
   ```java
   @GetMapping("/generate")
   public ResponseEntity<String> generateReport() {
       try {
           String report = reportService.generateContractReport();
           auditService.logAction("Generate report", "admin");
           return ResponseEntity.ok(report);
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating report");
       }
   }
   ```

## Docker Commands

- **Start services in detached mode**:
  ```bash
  docker-compose up -d
  ```

- **Stop services**:
  ```bash
  docker-compose down
  ```

- **Rebuild the services**:
  ```bash
  docker-compose up --build
  ```
