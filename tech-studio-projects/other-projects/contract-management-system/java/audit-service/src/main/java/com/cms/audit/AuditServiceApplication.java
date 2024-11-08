package com.cms.audit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The AuditServiceApplication class is the entry point of the Spring Boot application
 * for the Audit Service. This class contains the `main` method which is used to start
 * the Spring Boot application and initialize the application context.
 * 
 * It is annotated with @SpringBootApplication, which is a convenience annotation that
 * combines three important annotations: @Configuration, @EnableAutoConfiguration, and @ComponentScan.
 * These annotations help to set up the Spring Boot application context, enabling automatic
 * configuration and component scanning for the application.
 * 
 * This class does not contain any business logic itself but acts as the launcher for the service.
 */
@SpringBootApplication  // Marks this class as a Spring Boot application entry point
public class AuditServiceApplication {

    /**
     * The main method is the entry point of the application when the service is run.
     * 
     * This method calls `SpringApplication.run()` which boots up the Spring application,
     * initializes the Spring context, and starts the embedded server (e.g., Tomcat).
     * 
     * @param args Command-line arguments passed to the application. These arguments
     *             can be used to pass additional configuration parameters, though in
     *             this case they are not used.
     */
    public static void main(String[] args) {
        // Run the Spring Boot application. This launches the application and starts the server.
        SpringApplication.run(AuditServiceApplication.class, args);
    }
}
