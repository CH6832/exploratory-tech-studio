package com.system.banking.model;

/**
 * Represents a financial transaction in the banking system.
 * This class models a transaction's details such as transaction ID,
 * account ID, transaction type, amount, date, status, and description.
 */
public class Transaction {

    // Field for the unique transaction ID
    private String transactionId;

    // Field for the account ID associated with the transaction
    private String accountId;

    // Field for the type of transaction (e.g., "withdrawal", "deposit")
    private String transactionType;

    // Field for the amount of money involved in the transaction
    private double amount;

    // Field for the date when the transaction occurred
    private String date;

    // Field for the current status of the transaction (e.g., "completed", "pending")
    private String status;

    // Field for a description of the transaction
    private String description;

    // Field for the destination account ID (used for transfers)
    private String destinationAccountId;

    /**
     * Gets the transaction ID.
     *
     * @return The transaction ID
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the transaction ID.
     *
     * @param transactionId The transaction ID to set
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Gets the account ID associated with the transaction.
     *
     * @return The account ID
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * Sets the account ID associated with the transaction.
     *
     * @param accountId The account ID to set
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * Gets the type of the transaction (e.g., "withdrawal", "deposit").
     *
     * @return The transaction type
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Sets the type of the transaction.
     *
     * @param transactionType The transaction type to set
     */
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * Gets the amount involved in the transaction.
     *
     * @return The amount of the transaction
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the amount involved in the transaction.
     *
     * @param amount The amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Gets the date when the transaction occurred.
     *
     * @return The date of the transaction
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date when the transaction occurred.
     *
     * @param date The date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the status of the transaction (e.g., "completed", "pending").
     *
     * @return The status of the transaction
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the transaction.
     *
     * @param status The status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the description of the transaction.
     *
     * @return The description of the transaction
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the transaction.
     *
     * @param description The description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the destination account ID for transfer transactions.
     *
     * @return The destination account ID
     */
    public String getDestinationAccountId() {
        return destinationAccountId;
    }

    /**
     * Sets the destination account ID for transfer transactions.
     *
     * @param destinationAccountId The destination account ID to set
     */
    public void setDestinationAccountId(String destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }
}
