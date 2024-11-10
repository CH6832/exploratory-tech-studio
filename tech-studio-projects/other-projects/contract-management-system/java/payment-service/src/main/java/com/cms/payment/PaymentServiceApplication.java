package com.cms.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Payment Service application.
 * <p>
 * This class launches the Payment Service microservice using Spring Boot.
 * It initializes the Spring application context and starts the embedded server.
 * </p>
 */
@SpringBootApplication
public class PaymentServiceApplication {

    /**
     * The main method, which serves as the entry point for the Payment Service application.
     * <p>
     * This method uses Spring Boot's {@link SpringApplication#run} to bootstrap the application,
     * starting the embedded web server and initializing the Spring context.
     * </p>
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        // Run the application by initializing the Spring Boot application context.
        SpringApplication.run(PaymentServiceApplication.class, args);
    }
}
