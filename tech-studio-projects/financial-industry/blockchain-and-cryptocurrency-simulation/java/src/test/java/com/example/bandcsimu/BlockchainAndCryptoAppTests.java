package com.example.bandcsimu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the Blockchain class, ensuring that the 
 * core functionalities such as block creation, transaction management, 
 * chain validation, and block addition are functioning correctly.
 * 
 * The goal is to validate the integrity of the blockchain system, as blockchains 
 * rely heavily on proper sequential linking of blocks and transactions for security.
 */
class BlockchainTests {

    // Blockchain object to be used in all test cases
    private Blockchain blockchain;

    /**
     * This method sets up the blockchain instance before each test is run.
     * 
     * Why it's important:
     * - Setting up a new Blockchain object ensures that tests are isolated
     *   and there is no shared state that could affect the results of each test.
     */
    @BeforeEach
    void setUp() {
        // Initializes the blockchain before each test.
        blockchain = new Blockchain();
    }

    /**
     * Tests that the blockchain correctly creates the Genesis Block.
     * 
     * What we are testing:
     * - The presence of the Genesis Block.
     * - That the Genesis Block has an index of 0.
     * 
     * Why it's important:
     * - The Genesis Block is the foundation of any blockchain. Ensuring it is 
     *   created properly ensures the chain's initial structure is valid.
     */
    @Test
    void testCreateGenesisBlock() {
        // Assert that the latest block is not null, meaning the Genesis Block has been created.
        assertNotNull(blockchain.getLatestBlock(), "Blockchain should create a Genesis Block upon initialization.");

        // Assert that the index of the Genesis Block is 0 (as it should be the first block in the chain).
        assertEquals(0, blockchain.getLatestBlock().getIndex(), "Genesis Block should have an index of 0.");
    }

    /**
     * Tests that a transaction can be correctly added to the blockchain's pending transaction list.
     * 
     * What we are testing:
     * - That a transaction can be successfully added to the list of pending transactions.
     * - The size of the pending transactions list after adding a transaction.
     * 
     * Why it's important:
     * - Transactions are a fundamental component of blockchains, and they must 
     *   be handled correctly before being bundled into blocks. Testing this ensures 
     *   that the system correctly handles user-initiated operations.
     */
    @Test
    void testAddTransaction() {
        // Create a new transaction between Alice and Bob.
        Transaction transaction = new Transaction("Alice", "Bob", 50);

        // Add the transaction to the pending transaction list of the blockchain.
        blockchain.addTransaction(transaction);

        // Assert that the pending transaction list contains exactly one transaction.
        assertEquals(1, blockchain.getPendingTransactionList().size(), 
                     "Pending transaction list should contain 1 transaction after adding a transaction.");
    }

    /**
     * Tests the addition of a new block to the blockchain.
     * 
     * What we are testing:
     * - That after adding a block, the blockchain has two blocks: the Genesis Block and the new one.
     * 
     * Why it's important:
     * - Adding blocks is the key functionality of a blockchain, as blocks 
     *   store transactions. The test ensures the chain grows correctly after a block is added.
     */
    @Test
    void testAddBlock() {
        // Create a new transaction and add it to the blockchain's pending transactions.
        Transaction transaction = new Transaction("Alice", "Bob", 50);
        blockchain.addTransaction(transaction);

        // Add a new block to the blockchain containing this transaction.
        blockchain.addBlock(Arrays.asList(transaction.getTransactionData()));

        // Assert that the blockchain now contains two blocks (the Genesis Block + 1 new block).
        assertEquals(2, blockchain.printChain(), 
                     "Blockchain should contain 2 blocks (Genesis + new block) after a block is added.");
    }

    /**
     * Tests the blockchain's ability to verify its own validity.
     * 
     * What we are testing:
     * - That the blockchain is valid after adding legitimate blocks with proper transactions.
     * 
     * Why it's important:
     * - A valid blockchain ensures the integrity and trustworthiness of the data. 
     *   This test ensures that the blockchain's validation mechanism is functioning correctly 
     *   and can detect any invalid changes.
     */
    @Test
    void testIsChainValid() {
        // Add a transaction and block to the blockchain.
        Transaction transaction = new Transaction("Alice", "Bob", 50);
        blockchain.addTransaction(transaction);
        blockchain.addBlock(Arrays.asList(transaction.getTransactionData()));

        // Assert that the blockchain is valid after adding a legitimate transaction and block.
        assertTrue(blockchain.isChainValid(), 
                   "Blockchain should be valid after adding a legitimate transaction and block.");
    }

    /**
     * Tests the blockchain's validation mechanism by deliberately adding an invalid block.
     * 
     * What we are testing:
     * - That the blockchain correctly identifies an invalid block in the chain.
     * 
     * Why it's important:
     * - Detecting invalid blocks is crucial for blockchain security. This test ensures 
     *   the blockchain can detect a tampered or improperly added block, maintaining its integrity.
     */
    @Test
    void testInvalidChain() {
        // Add an invalid block (containing a malformed transaction).
        blockchain.addBlock(Arrays.asList("Invalid Transaction"));

        // Assert that the blockchain is invalid due to the malformed block.
        assertFalse(blockchain.isChainValid(), 
                    "Blockchain should be invalid if a block with invalid transactions is added.");
    }
}
