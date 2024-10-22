package com.example.bandcsimu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the Transaction class, which represents 
 * a simple financial transaction between two parties in a blockchain context.
 * 
 * The tests validate that:
 * - The Transaction object is properly initialized with sender, receiver, and amount.
 * - The transaction data is correctly formatted and returned.
 * 
 * Transactions are fundamental to blockchain systems, representing the transfer 
 * of assets between participants, so it's crucial that they are correctly implemented.
 */
class TransactionTests {

    /**
     * Tests the initialization of a Transaction object.
     * 
     * What we are testing:
     * - That the constructor correctly sets the sender, receiver, and amount fields.
     * - That the getters return the expected values.
     * 
     * Why it's important:
     * - Accurate initialization ensures that transactions are correctly recorded and 
     *   processed within the blockchain. This test ensures that the Transaction class 
     *   correctly holds the necessary information about the transfer.
     */
    @Test
    void testTransactionInitialization() {
        // Create a new transaction from Alice to Bob, transferring 50 units.
        Transaction transaction = new Transaction("Alice", "Bob", 50);

        // Verify that the sender is correctly set to "Alice".
        assertEquals("Alice", transaction.getSender(), "The sender should be Alice.");

        // Verify that the receiver is correctly set to "Bob".
        assertEquals("Bob", transaction.getReceiver(), "The receiver should be Bob.");

        // Verify that the amount is correctly set to 50 units.
        assertEquals(50, transaction.getAmount(), "The amount should be 50 units.");
    }

    /**
     * Tests the retrieval of the transaction data string.
     * 
     * What we are testing:
     * - That the getTransactionData method correctly returns a concatenated string 
     *   representing the transaction details (sender, receiver, and amount).
     * 
     * Why it's important:
     * - Transaction data is often hashed and included in blocks for validation purposes. 
     *   Ensuring the correct formatting of this data is crucial for accurate block generation 
     *   and validation in the blockchain. This test guarantees that the transaction details 
     *   are formatted as expected.
     */
    @Test
    void testGetTransactionData() {
        // Create a new transaction from Alice to Bob, transferring 50 units.
        Transaction transaction = new Transaction("Alice", "Bob", 50);

        // Retrieve the formatted transaction data string.
        String data = transaction.getTransactionData();

        // Verify that the transaction data is formatted as expected.
        // The expected format is "AliceBob50.0" (adjust based on your actual implementation).
        assertEquals("AliceBob50.0", data, 
            "The transaction data should be formatted as 'AliceBob50.0'.");
    }
}
