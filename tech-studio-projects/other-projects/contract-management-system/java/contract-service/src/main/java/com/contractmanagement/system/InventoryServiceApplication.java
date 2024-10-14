package com.contractmanagement.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        // Start the Spring Boot application
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    // Example flow of the application:
    // 1. When the application starts, the main method is executed.
    // 2. Spring Boot initializes the application context, scanning for components, including the ContractController.
    // 3. The ContractController is ready to handle HTTP requests at the /contracts endpoint.
    // 4. When a client sends a POST request to /contracts, the createContract method is invoked.
    // 5. This method calls contractService.createContract(contract) to handle the business logic of creating a new contract.
    // 6. Upon successful creation, a 201 Created response is returned to the client, along with the created contract details.
}
