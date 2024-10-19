# Cloud Intrusion Detection System (Cloud IDS)

## Overview

The **Cloud Intrusion Detection System (Cloud IDS)** is a comprehensive security solution designed to monitor cloud environments for suspicious activities and potential threats. This system uses packet sniffing and log analysis to detect anomalies in network traffic and application logs, generating alerts for further investigation.

### Features

- Real-time packet sniffing using Pcap4j.
- Log analysis for detecting suspicious log entries.
- Alert management with a RESTful API.
- Persistent storage for alerts using a relational database.
- User-friendly web interface (to be implemented).
- Integration with notification systems (to be implemented).
- Modular and scalable architecture following SOLID principles.

## Technologies Used

- **Java**: Programming language used to develop the application.
- **Spring Boot**: Framework for building the application.
- **Pcap4j**: Library for packet capture and analysis.
- **JPA / Hibernate**: For database interactions.
- **Log4j**: Logging framework.
- **JUnit**: For unit testing.
- **Maven**: Build and dependency management tool.

## Requirements

- Java Development Kit (JDK) 11 or higher
- Maven 3.6.0 or higher
- MySQL or H2 Database (for development and testing)

## Installation

1. **Clone the repository**:

   ```bash
   git clone https://github.com/yourusername/cloud-ids.git
   cd cloud-ids
   ```

2. **Navigate to the project directory**:

   ```bash
   cd cloud-ids
   ```

3. **Build the project**:

   ```bash
   mvn clean install
   ```

4. **Set up the database**:

   - For **H2**, no setup is needed, as it runs in memory.
   - For **MySQL**, create a database named `cloud_ids`:

   ```sql
   CREATE DATABASE cloud_ids;
   ```

   Update `src/main/resources/application.properties` with your MySQL credentials:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/cloud_ids
   spring.datasource.username=yourusername
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   ```

## Running the Application

1. **Run the application**:

   ```bash
   mvn spring-boot:run
   ```

2. **Access the API**:

   The application will start on `http://localhost:8080`. Use tools like **Postman** or **curl** to interact with the API.

   - Get all alerts:

     ```bash
     curl -X GET http://localhost:8080/alerts
     ```

   - Create a new alert (JSON example):

     ```bash
     curl -X POST http://localhost:8080/alerts -H "Content-Type: application/json" -d '{"id":"1","message":"Test alert","timestamp":"2024-10-18T12:00:00"}'
     ```

## Testing

To run unit tests, use the following command:

```bash
mvn test
```

This command will execute all tests in the `src/test/java` directory and provide a report of the results.

## Usage

1. **Packet Sniffing**: The `PacketSnifferService` will capture network packets and analyze them for suspicious activities.
2. **Log Analysis**: The `LogAnalyzerService` can analyze logs to identify any anomalies or errors that may indicate a security threat.
3. **Alerts**: Alerts can be generated from both packet analysis and log analysis. They are stored in the database and can be retrieved through the API.

### Future Enhancements

- **Web Interface**: A frontend application (using React or Angular) for better visualization and management of alerts.
- **Notification System**: Integration with email or messaging services to notify users of critical alerts.
- **Advanced Analysis**: Implement machine learning algorithms for more intelligent anomaly detection.

## Contribution

Contributions to this project are welcome! Please fork the repository and submit a pull request with your improvements.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Acknowledgments

- [Pcap4j](https://pcap4j.github.io/) - for packet capture functionality.
- [Spring Boot](https://spring.io/projects/spring-boot) - for building the application.
- [JUnit](https://junit.org/junit5/) - for testing framework.
```

### Summary

This `README.md` file provides a clear and comprehensive overview of the Cloud Intrusion Detection System, detailing its purpose, features, setup, usage, and future plans. Make sure to customize the GitHub repository URL and adjust any specific configurations based on your project structure or environment.
