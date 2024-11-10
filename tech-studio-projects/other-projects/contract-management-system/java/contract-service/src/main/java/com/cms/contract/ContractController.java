/*

- http://localhost:8050/contracts
{
  "title": "Contract 123",
  "description": "This is a sample contract for testing.",
  "status": "Active",
  "parties": [
    { "name": "Party A", "role": "Seller" },
    { "name": "Party B", "role": "Buyer" }
  ],
  "pricing": {
    "baseRate": 1000,
    "currency": "USD",
    "paymentTerms": "Net 30"
  },
  "documents": [
    {
      "filename": "contract_123.pdf",
      "url": "http://example.com/contract_123.pdf",
      "uploadedDate": "2024-11-10T10:00:00Z",
      "version": 1
    }
  ]
}


- http://localhost:8050/contracts/1
{
  "id": "1",
  "title": "Contract 123",
  "description": "This is a sample contract for testing.",
  "status": "Active",
  "parties": [
    { "name": "Party A", "role": "Seller" },
    { "name": "Party B", "role": "Buyer" }
  ],
  "pricing": {
    "baseRate": 1000,
    "currency": "USD",
    "paymentTerms": "Net 30"
  },
  "documents": [
    {
      "filename": "contract_123.pdf",
      "url": "http://example.com/contract_123.pdf",
      "uploadedDate": "2024-11-10T10:00:00Z",
      "version": 1
    }
  ]
}


- http://localhost:8050/contracts/search?title=Contract%20123
{
  "id": "1",
  "title": "Contract 123",
  "description": "This is a sample contract for testing.",
  "status": "Active",
  "parties": [
    { "name": "Party A", "role": "Seller" },
    { "name": "Party B", "role": "Buyer" }
  ],
  "pricing": {
    "baseRate": 1000,
    "currency": "USD",
    "paymentTerms": "Net 30"
  },
  "documents": [
    {
      "filename": "contract_123.pdf",
      "url": "http://example.com/contract_123.pdf",
      "uploadedDate": "2024-11-10T10:00:00Z",
      "version": 1
    }
  ]
}

 */

package com.cms.contract;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cms.contract.datamodel.Contract;

import java.util.List;
import java.util.Optional;

/**
 * The ContractController class provides endpoints for managing contracts.
 * It handles HTTP requests related to creating, retrieving, updating, and deleting contracts.
 * It communicates with the ContractService to perform business logic.
 */
@RestController
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService contractService;
    private final ApplicationEventPublisher eventPublisher;
    private final ContractLoggingClient loggingClient;

    /**
     * Constructor to inject the ContractService and EventPublisher dependencies.
     *
     * @param contractService The contract service that handles contract operations
     * @param eventPublisher The event publisher to publish events for auditing
     */
    public ContractController(ContractService contractService, ApplicationEventPublisher eventPublisher, ContractLoggingClient loggingClient) {
        this.contractService = contractService;
        this.eventPublisher = eventPublisher;
        this.loggingClient = loggingClient;
    }

    
    
    /**
     * Endpoint to create a new contract.
     * @param contract The contract data to be created
     * @return The created contract with HTTP status 201 (Created)
     */
    @PostMapping
    public ResponseEntity<Contract> createContract(@RequestBody Contract contract) {
        // Ensure contract is valid before creating
        if (contract == null || contract.getTitle() == null || contract.getTitle().isEmpty()) {
            return ResponseEntity.badRequest().build(); // Bad request if contract data is invalid
        }

        Contract createdContract = contractService.createContract(contract);
        eventPublisher.publishEvent(new ContractAuditEvent(this, "CREATE new contract with ID: " + createdContract.getId()));
        
        // Log the creation of a new contract
        loggingClient.log("INFO", "Contract created with ID: " + contract.getId());
        
        return new ResponseEntity<>(createdContract, HttpStatus.CREATED);
    }

    /**
     * Endpoint to fetch all contracts.
     * @return A list of all contracts with HTTP status 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<Contract>> getAllContracts() {
        List<Contract> contracts = contractService.getAllContracts();
        return contracts.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(contracts);
    }

    /**
     * Endpoint to get a contract by its ID.
     * @param id The unique identifier of the contract
     * @return The contract if found, else HTTP status 404 (Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Contract> getContractById(@PathVariable String id) {
        Optional<Contract> contract = contractService.getContractById(id);
        return contract.map(ResponseEntity::ok).orElseGet(() -> {
            eventPublisher.publishEvent(new ContractAuditEvent(this, "ERROR retrieving contract by ID: " + id));
            return ResponseEntity.notFound().build();
        });
    }

    /**
     * Endpoint to search for a contract by title.
     * @param title The title of the contract to search for
     * @return The contract if found, else HTTP status 404 (Not Found)
     */
    @GetMapping("/search")
    public ResponseEntity<Contract> getContractByTitle(@RequestParam String title) {
        if (title == null || title.isEmpty()) {
            return ResponseEntity.badRequest().build(); // Bad request if title is empty
        }
        
        Contract contract = contractService.getContractByTitle(title);
        if (contract == null) {
            eventPublisher.publishEvent(new ContractAuditEvent(this, "ERROR retrieving contract by title: " + title));
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(contract);
    }

    /**
     * Endpoint to update a contract by its ID.
     * @param id The unique identifier of the contract to be updated
     * @param contract The updated contract data
     * @return The updated contract if found, else HTTP status 404 (Not Found)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Contract> updateContract(@PathVariable String id, @RequestBody Contract contract) {
        // Ensure contract is valid before updating
        if (contract == null || id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Contract updatedContract = contractService.updateContract(id, contract);
        if (updatedContract != null) {
            eventPublisher.publishEvent(new ContractAuditEvent(this, "UPDATE contract with ID: " + id));
            return ResponseEntity.ok(updatedContract);
        } else {
            eventPublisher.publishEvent(new ContractAuditEvent(this, "ERROR updating contract with ID: " + id));
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint to delete a contract by its ID.
     * @param id The unique identifier of the contract to be deleted
     * @return HTTP status 204 (No Content) if successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable String id) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().build(); // Bad request if ID is invalid
        }

        boolean isDeleted = contractService.deleteContract(id);
        if (isDeleted) {
            eventPublisher.publishEvent(new ContractAuditEvent(this, "DELETE contract with ID: " + id));
            return ResponseEntity.noContent().build();
        } else {
            eventPublisher.publishEvent(new ContractAuditEvent(this, "ERROR deleting contract with ID: " + id));
            return ResponseEntity.notFound().build();
        }
    }
}
