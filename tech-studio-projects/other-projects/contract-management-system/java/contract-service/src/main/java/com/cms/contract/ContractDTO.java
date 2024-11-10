package com.cms.contract;

import java.util.List;
import java.util.Date;

/**
 * ContractDTO represents a simplified view of the Contract entity 
 * for transferring contract data over the network, especially in 
 * API responses.
 * <p>
 * This Data Transfer Object (DTO) is designed to provide a subset of the 
 * data from the original Contract entity, focusing on the relevant 
 * details needed by the client and avoiding the complexity of internal 
 * data structures.
 * </p>
 */
public class ContractDTO {
    
    // Unique identifier of the contract (mapped from the database entity)
    private String id;
    
    // Title of the contract
    private String title;
    
    // Description of the contract, providing details about the contract
    private String description;
    
    // Current status of the contract (e.g., active, expired, etc.)
    private String status;
    
    // List of party names involved in the contract (simplified view from original `Party` objects)
    private List<String> partyNames;
    
    // Date when the contract was created
    private Date createdDate;

    // Default constructor
    public ContractDTO() {}

    /**
     * Constructor to create a ContractDTO with specified values.
     * 
     * @param id The unique identifier of the contract.
     * @param title The title of the contract.
     * @param description The description of the contract.
     * @param status The current status of the contract.
     * @param partyNames The list of party names involved in the contract.
     * @param createdDate The date when the contract was created.
     */
    public ContractDTO(String id, String title, String description, String status, List<String> partyNames, Date createdDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.partyNames = partyNames;
        this.createdDate = createdDate;
    }

    // Getters and Setters

    /**
     * Gets the unique identifier of the contract.
     * 
     * @return The contract's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the contract.
     * 
     * @param id The contract's ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the title of the contract.
     * 
     * @return The contract's title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the contract.
     * 
     * @param title The contract's title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description of the contract.
     * 
     * @return The contract's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the contract.
     * 
     * @param description The contract's description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the current status of the contract.
     * 
     * @return The contract's status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the current status of the contract.
     * 
     * @param status The contract's status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the list of party names involved in the contract.
     * 
     * @return A list of party names.
     */
    public List<String> getPartyNames() {
        return partyNames;
    }

    /**
     * Sets the list of party names involved in the contract.
     * 
     * @param partyNames A list of party names.
     */
    public void setPartyNames(List<String> partyNames) {
        this.partyNames = partyNames;
    }

    /**
     * Gets the date when the contract was created.
     * 
     * @return The creation date of the contract.
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the date when the contract was created.
     * 
     * @param createdDate The creation date of the contract.
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
