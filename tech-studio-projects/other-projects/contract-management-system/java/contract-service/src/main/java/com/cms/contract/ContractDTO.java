package com.cms.contract;

import java.time.LocalDate;

/**
 * The ContractDTO (Data Transfer Object) class is used to transfer contract data between the layers of the application.
 * It is typically used to represent a contract in a simplified manner without the persistence logic (like MongoDB annotations).
 */
public class ContractDTO {

    private String id;
    private String title;
    private String partyA;
    private String partyB;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    /**
     * Constructor to create a new ContractDTO object.
     * This constructor is used for initializing a contract with its core properties.
     * 
     * @param title The title of the contract
     * @param partyA The name of the first party involved in the contract
     * @param partyB The name of the second party involved in the contract
     * @param startDate The start date of the contract
     * @param endDate The end date of the contract
     * @param status The status of the contract (e.g., "draft", "active", "expired")
     */
    public ContractDTO(String title, String partyA, String partyB, LocalDate startDate, LocalDate endDate, String status) {
        this.setTitle(title);
        this.setPartyA(partyA);
        this.setPartyB(partyB);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setStatus(status);
    }

    /**
     * Get the contract ID.
     * 
     * @return The ID of the contract
     */
    public String getId() {
        return id;
    }

    /**
     * Set the contract ID.
     * 
     * @param id The ID to set for the contract
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the title of the contract.
     * 
     * @return The title of the contract
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of the contract.
     * 
     * @param title The title to set for the contract
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the name of the first party involved in the contract.
     * 
     * @return The name of the first party (party A)
     */
    public String getPartyA() {
        return partyA;
    }

    /**
     * Set the name of the first party involved in the contract.
     * 
     * @param partyA The name of the first party to set
     */
    public void setPartyA(String partyA) {
        this.partyA = partyA;
    }

    /**
     * Get the name of the second party involved in the contract.
     * 
     * @return The name of the second party (party B)
     */
    public String getPartyB() {
        return partyB;
    }

    /**
     * Set the name of the second party involved in the contract.
     * 
     * @param partyB The name of the second party to set
     */
    public void setPartyB(String partyB) {
        this.partyB = partyB;
    }

    /**
     * Get the start date of the contract.
     * 
     * @return The start date of the contract
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Set the start date of the contract.
     * 
     * @param startDate The start date to set for the contract
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Get the end date of the contract.
     * 
     * @return The end date of the contract
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Set the end date of the contract.
     * 
     * @param endDate The end date to set for the contract
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Get the status of the contract (e.g., "draft", "active", "expired").
     * 
     * @return The current status of the contract
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the status of the contract.
     * 
     * @param status The status to set for the contract
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
