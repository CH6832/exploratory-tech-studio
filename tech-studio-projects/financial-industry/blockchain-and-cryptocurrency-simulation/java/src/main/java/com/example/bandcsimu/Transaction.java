package com.example.bandcsimu;

/**
 * The Transaction class represents a financial transaction in the blockchain.
 * Each transaction contains information about the sender, receiver, amount,
 * and an optional signature for validation.
 */
public class Transaction {
    private String sender;     // The address of the sender
    private String receiver;   // The address of the receiver
    private double amount;     // The amount of currency being transferred
    private String signature;  // The digital signature of the transaction

    /**
     * Constructor for creating a new transaction.
     *
     * @param sender   The address of the sender.
     * @param receiver The address of the receiver.
     * @param amount   The amount of currency to be transferred.
     */
    public Transaction(String sender, String receiver, double amount) {
        this.sender = sender;                    // Set the sender's address
        this.receiver = receiver;                // Set the receiver's address
        this.amount = amount;                    // Set the transaction amount
        String data = getTransactionData();     // Generate transaction data
        // this.signature = signTransaction(data); // Signature generation placeholder
    }

    // Getters for accessing transaction properties

    /**
     * Gets the sender's address.
     *
     * @return The address of the sender.
     */
    public String getSender() {
        return sender;
    }

    /**
     * Gets the receiver's address.
     *
     * @return The address of the receiver.
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * Gets the transaction amount.
     *
     * @return The amount of currency being transferred.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Gets the digital signature of the transaction.
     *
     * @return The transaction signature.
     */
    public String getSignature() {
        return signature;
    }

    /**
     * Generates the transaction data as a string.
     * This data can be used for signing or hashing purposes.
     *
     * @return A string representation of the transaction data.
     */
    public String getTransactionData() {
        return sender + receiver + amount; // Concatenate sender, receiver, and amount
    }
}
