package com.example.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
        // Run the Spring Boot application
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        
        // Fetching the CommandLineClient bean and invoking its run method
        CommandLineClient client = context.getBean(CommandLineClient.class);
        client.runClient();
	}
}
