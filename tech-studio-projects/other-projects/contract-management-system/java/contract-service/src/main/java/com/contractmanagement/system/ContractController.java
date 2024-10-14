package com.contractmanagement.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contracts") // Base URL for contract-related endpoints
public class ContractController {

    @Autowired
    private ContractService contractService; // Injecting ContractService bean

    // POST /contracts - Create a new contract
    @PostMapping
    public ResponseEntity<Contract> createContract(@RequestBody Contract contract) {
        // Call the service to create a contract and return it with a CREATED status
        return new ResponseEntity<>(contractService.createContract(contract), HttpStatus.CREATED);
    }

    // GET /contracts/{id} - Retrieve a contract by ID
    @GetMapping("/{id}")
    public ResponseEntity<Contract> getContract(@PathVariable Long id) {
        // Call the service to fetch the contract by ID and return it
        return ResponseEntity.ok(contractService.getContractById(id));
    }

    // PUT /contracts/{id} - Update an existing contract by ID
    @PutMapping("/{id}")
    public ResponseEntity<Contract> updateContract(@PathVariable Long id, @RequestBody Contract updatedContract) {
        // Call the service to update the contract and return the updated contract
        return ResponseEntity.ok(contractService.updateContract(id, updatedContract));
    }

    // DELETE /contracts/{id} - Delete a contract by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable Long id) {
        // Call the service to delete the contract and return NO CONTENT status
        contractService.deleteContract(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
