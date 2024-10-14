package com.contractmanagement.system;

import com.contractmanagement.system.Contract;
import com.contractmanagement.system.ContractRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class ContractService<MessageProducer> {
    @Autowired
    private ContractRepository contractRepository; // Injecting the ContractRepository bean

    @Autowired
    private MessageProducer messageProducer;
    
    // Create a new contract
    public Contract createContract(Contract contract) {
        // ((Object) messageProducer).sendMessage("Contract created with ID: " + contract.getId());
        // return contract;
        return contractRepository.save(contract); // Save the contract to the database
        
    }

    // Retrieve a contract by its ID
    public Contract getContractById(Long id) {
        Optional<Contract> contract = contractRepository.findById(id);
        return contract.orElseThrow(() -> new RuntimeException("Contract not found with id: " + id));
    }

    // Update an existing contract
    public Contract updateContract(Long id, Contract updatedContract) {
        // Check if the contract exists
        if (!contractRepository.existsById(id)) {
            throw new RuntimeException("Contract not found with id: " + id);
        }
        updatedContract.setId(id); // Ensure the ID is set for the update
        return contractRepository.save(updatedContract); // Save the updated contract
    }

    // Delete a contract by its ID
    public void deleteContract(Long id) {
        if (!contractRepository.existsById(id)) {
            throw new RuntimeException("Contract not found with id: " + id);
        }
        contractRepository.deleteById(id); // Delete the contract from the database
    }
}
