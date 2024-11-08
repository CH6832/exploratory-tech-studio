package com.cms.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

/**
 * The SearchServiceApplication class serves as the entry point for the Spring Boot application.
 * <p>
 * This class contains the main method that bootstraps the entire application, starting the Spring
 * context, which includes initializing the necessary beans and configurations. It is marked with
 * the @SpringBootApplication annotation, which is a combination of @Configuration, @EnableAutoConfiguration,
 * and @ComponentScan. It signals that this is a Spring Boot application and should be the primary source
 * for configuration and setup.
 * </p>
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.cms.contract", "com.cms.search"})
public class SearchServiceApplication {

    /**
     * The main method is the entry point for running the Spring Boot application.
     * It initializes the Spring context and launches the embedded web server.
     * 
     * @param args Command-line arguments passed when starting the application (not used here)
     */
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SearchServiceApplication.class, args);
        Environment env = context.getEnvironment();
        String port = env.getProperty("server.port", "8051");
        System.out.println(String.format("Search Service is running at http://localhost:%s", port));
    }
}
