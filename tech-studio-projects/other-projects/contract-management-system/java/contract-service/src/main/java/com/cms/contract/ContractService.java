package com.cms.contract;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * The ContractService class contains the business logic related to managing contracts.
 * <p>
 * This service interacts with the ContractRepository to perform CRUD (Create, Read, Update, Delete) operations
 * on the contract documents in the MongoDB database. It handles the core business logic and data manipulation
 * before passing the data to the controller layer for API responses.
 * </p>
 */
@Service
public class ContractService {

    // The repository for interacting with the Contract collection in MongoDB
    private final ContractRepository contractRepository;


    // Method to find contract by title
    public Contract getContractByTitle(String title) {
        return contractRepository.findByTitle(title);
    }
    
    /**
     * Constructor to inject the ContractRepository dependency.
     * 
     * @param contractRepository The repository to interact with MongoDB for contract data
     */
    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    /**
     * Create a new contract and save it to the database.
     * 
     * @param contract The contract to be created
     * @return The created contract, with an ID and any other properties set by the database
     */
    public Contract createContract(Contract contract) {
        return contractRepository.save(contract);
    }

    /**
     * Get a list of all contracts stored in the database.
     * 
     * @return A list of all contracts
     */
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    /**
     * Get a contract by its unique ID.
     * 
     * @param id The ID of the contract to retrieve
     * @return An Optional containing the contract if it exists, or empty if not found
     */
    public Optional<Contract> getContractById(String id) {
        return contractRepository.findById(id);
    }

    /**
     * Update an existing contract in the database.
     * <p>
     * If a contract with the given ID exists, the contract is updated with the new data. If the contract does not exist,
     * the method returns null.
     * </p>
     * 
     * @param id The ID of the contract to update
     * @param contract The contract data to update with
     * @return The updated contract, or null if no contract was found with the given ID
     */
    public Contract updateContract(String id, Contract contract) {
        // Find the existing contract by ID
        Optional<Contract> existingContract = contractRepository.findById(id);
        
        // If the contract exists, update it with the new data
        if (existingContract.isPresent()) {
            contract.setId(id); // Set the ID of the existing contract to the updated contract
            // contract.setUpdatedDate(contract.getUpdatedDate()); // Set the updated date to ensure the contract is updated
            return contractRepository.save(contract); // Save the updated contract
        } else {
            return null; // Return null if the contract was not found
        }
    }

    /**
     * Delete a contract by its unique ID.
     * 
     * @param id The ID of the contract to delete
     */
    public void deleteContract(String id) {
        contractRepository.deleteById(id); // Call the repository to delete the contract by ID
    }
}
