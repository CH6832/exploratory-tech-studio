package com.filemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class to run the banking application.
 * This class serves as the entry point for the Spring Boot application.
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        // Launch the Spring Boot application
        SpringApplication.run(Main.class, args);
        
        System.out.println("The banking application has started.");
    }
}
