package com.example.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main entry point for the Spring Boot server application.
 * This class contains the main method to run the application.
 */
@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        // Launches the Spring Boot application
        SpringApplication.run(ServerApplication.class, args);
    }

    /**
     * Simple REST controller to check if the server is up and running.
     */
    @RestController
    public static class HealthCheckController {

        /**
         * Health check endpoint that returns a simple "Server is running" message.
         *
         * @return a String message to confirm the server is up
         */
        @GetMapping("/health")
        public String checkHealth() {
            return "Server is running!";
        }
    }
}