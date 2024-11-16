package com.cms.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the ConfigService Spring Boot application.
 * This class is responsible for starting the Spring Boot application.
 * The @SpringBootApplication annotation enables auto-configuration, component scanning, 
 * and additional configuration for the application.
 */
@SpringBootApplication
public class ConfigServiceApplication {

    /**
     * Main method that launches the Spring Boot application.
     * The SpringApplication.run() method is the entry point that bootstraps the application.
     * It starts the embedded web server and initializes the Spring context.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        // Run the Spring Boot application and pass the current class (ConfigServiceApplication) and arguments.
        SpringApplication.run(ConfigServiceApplication.class, args);
    }
}
