package com.cms.contract;

import com.cms.audit.AuditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // Service layer that handles the business logic for contracts
    private final ContractService contractService;
    private final AuditService auditService;

    /**
     * Constructor to inject the ContractService dependency.
     * 
     * @param contractService The contract service that handles contract operations
     */
    public ContractController(ContractService contractService, AuditService auditService) {
        this.contractService = contractService;
        this.auditService = auditService;
    }

    // Endpoint to fetch contract by its title
    @GetMapping("/search")
    public ResponseEntity<Contract> getContractByTitle(@RequestParam("title") String title) {
        Contract contract = contractService.getContractByTitle(title);
        
        // If the contract is not found, return a 404 response
        if (contract == null) {
        	auditService.logAction("ERROR retrieving contract by title: " + title);
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(contract);
    }
    
    /**
     * Endpoint to create a new contract.
     * 
     * @param contract The contract data to be created
     * @return The created contract with HTTP status 201 (Created)
     */
    @PostMapping
    public ResponseEntity<Contract> createContract(@RequestBody Contract contract) {
        Contract createdContract = contractService.createContract(contract);
        return new ResponseEntity<>(createdContract, HttpStatus.CREATED);
    }

    /**
     * Endpoint to get all contracts.
     * 
     * @return A list of all contracts with HTTP status 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<Contract>> getAllContracts() {
        List<Contract> contracts = contractService.getAllContracts();
        return new ResponseEntity<>(contracts, HttpStatus.OK);
    }

    /**
     * Endpoint to get a contract by its ID.
     * 
     * @param id The unique identifier of the contract
     * @return The contract if found, else HTTP status 404 (Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Contract> getContractById(@PathVariable String id) {
        Optional<Contract> contract = contractService.getContractById(id);
        return contract.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint to update a contract by its ID.
     * 
     * @param id The unique identifier of the contract to be updated
     * @param contract The updated contract data
     * @return The updated contract if found, else HTTP status 404 (Not Found)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Contract> updateContract(@PathVariable String id, @RequestBody Contract contract) {
        Contract updatedContract = contractService.updateContract(id, contract);
        auditService.logAction("UPDATE contract with ID: " + id);
        return updatedContract != null ? ResponseEntity.ok(updatedContract) : ResponseEntity.notFound().build();
    }

    /**
     * Endpoint to delete a contract by its ID.
     * 
     * @param id The unique identifier of the contract to be deleted
     * @return HTTP status 204 (No Content) if successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable String id) {
    	contractService.deleteContract(id);
    	auditService.logAction("Delete contract by ID: " + id);
    	return ResponseEntity.noContent().build();
    }
    
    // @GetMapping
    /*
    public List<Contract> getContracts() {
        List<Contract> contracts = contractService.getAllContracts();
        System.out.println("Contracts from repository: " + contracts);
        return contracts;
    }
    */
}
