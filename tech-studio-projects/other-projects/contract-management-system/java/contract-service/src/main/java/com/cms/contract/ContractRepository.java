package com.cms.contract;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cms.contract.datamodel.Contract;

/**
 * ContractRepository interface extends MongoRepository and provides methods 
 * for performing CRUD (Create, Read, Update, Delete) operations on Contract documents 
 * in the MongoDB database.
 * <p>
 * The repository interacts with the `Contract` collection in MongoDB.
 * By extending `MongoRepository`, it inherits several methods for CRUD operations.
 * Additional custom queries can also be added here if needed.
 * </p>
 */
public interface ContractRepository extends MongoRepository<Contract, String> {

    /**
     * Retrieve all contracts from the database.
     * 
     * @return A list of all contracts in the database
     */
    List<Contract> findAll();

    /**
     * Save a contract to the database. This method can be used for both creating
     * and updating a contract.
     * 
     * @param contract The contract to be saved (either new or updated)
     * @return The saved contract, including any updated values like generated ID
     */
    @SuppressWarnings("unchecked")
	Contract save(Contract contract);

    /**
     * Retrieve a contract by its unique ID.
     * 
     * @param id The ID of the contract to retrieve
     * @return An Optional containing the contract if found, or an empty Optional if not found
     */
    Optional<Contract> findById(String id);

    /**
     * Delete a contract by its unique ID.
     * 
     * @param id The ID of the contract to delete
     */
    void deleteById(String id);
    
    // Custom query method to find a contract by its title
    Contract findByTitle(String title);
}
