package com.example.bandcsimu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BlockchainTests {

    private Blockchain blockchain;

    @BeforeEach
    void setUp() {
        blockchain = new Blockchain();
    }

    @Test
    void testCreateGenesisBlock() {
        assertNotNull(blockchain.getLatestBlock());
        assertEquals(0, blockchain.getLatestBlock().getIndex());
    }

    @Test
    void testAddTransaction() {
        Transaction transaction = new Transaction("Alice", "Bob", 50);
        blockchain.addTransaction(transaction);
        assertEquals(1, blockchain.getPendingTransactionList().size());
    }

    @Test
    void testAddBlock() {
        Transaction transaction = new Transaction("Alice", "Bob", 50);
        blockchain.addTransaction(transaction);
        blockchain.addBlock(Arrays.asList(transaction.getTransactionData()));
        assertEquals(2, blockchain.printChain()); // Genesis + 1 new block
    }

    @Test
    void testIsChainValid() {
        Transaction transaction = new Transaction("Alice", "Bob", 50);
        blockchain.addTransaction(transaction);
        blockchain.addBlock(Arrays.asList(transaction.getTransactionData()));
        assertTrue(blockchain.isChainValid());
    }

    @Test
    void testInvalidChain() {
        blockchain.addBlock(Arrays.asList("Invalid Transaction"));
        assertFalse(blockchain.isChainValid());
    }
}
