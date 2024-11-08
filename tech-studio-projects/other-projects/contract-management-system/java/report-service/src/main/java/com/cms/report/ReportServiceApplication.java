package com.cms.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the Report Service application.
 * This class starts up the Spring Boot application, which includes all the necessary components
 * such as controllers, services, and repositories, and begins running the report service.
 */
@SpringBootApplication
public class ReportServiceApplication {

    /**
     * The main method to start the Spring Boot application.
     * It initializes the application context, loads configurations, and starts the embedded web server.
     * 
     * @param args Command line arguments passed during application startup
     */
    public static void main(String[] args) {
        // Run the Spring Boot application
        SpringApplication.run(ReportServiceApplication.class, args);
    }
}
