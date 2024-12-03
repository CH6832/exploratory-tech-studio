package com.efilingusgaap.financialfilingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the Financial Filing Service application.
 * <p>
 * This class is used to launch the Spring Boot application. It contains the {@code main} method,
 * which is the entry point for running the application. The application is configured with the
 * {@link SpringBootApplication} annotation, which enables auto-configuration and component scanning
 * for the application's components.
 * </p>
 * <p>
 * The application uses Spring Boot's embedded server to run and manage all HTTP requests to the
 * services and controllers provided by this application.
 * </p>
 */
@SpringBootApplication
public class FinancialuserserviceApplication {

	/**
	 * Main method to start the Spring Boot application.
	 * <p>
	 * This method initializes the Spring context and runs the application on the embedded server.
	 * </p>
	 *
	 * @param args command-line arguments passed during application startup (not used in this case)
	 */
	public static void main(String[] args) {
		// Runs the Spring Boot application
		SpringApplication.run(FinancialuserserviceApplication.class, args);
	}
}
