package com.example.bandcsimu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTests {

    @Test
    void testTransactionInitialization() {
        Transaction transaction = new Transaction("Alice", "Bob", 50);
        assertEquals("Alice", transaction.getSender());
        assertEquals("Bob", transaction.getReceiver());
        assertEquals(50, transaction.getAmount());
    }

    @Test
    void testGetTransactionData() {
        Transaction transaction = new Transaction("Alice", "Bob", 50);
        String data = transaction.getTransactionData();
        assertEquals("AliceBob50.0", data); // Adjust based on your actual implementation
    }
}
