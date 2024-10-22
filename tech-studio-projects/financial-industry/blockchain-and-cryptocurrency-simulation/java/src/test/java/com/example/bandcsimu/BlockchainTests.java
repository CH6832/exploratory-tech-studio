package com.example.bandcsimu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the Blockchain class. It tests core functionalities 
 * such as genesis block creation, transaction management, block addition, and chain validity.
 * 
 * The tests aim to ensure that the blockchain works as intended, maintaining its integrity 
 * by ensuring correct block and transaction handling.
 */
class BlockchainTests {

    // Blockchain instance used in each test case.
    private Blockchain blockchain;

    /**
     * Initializes a new instance of the Blockchain before each test.
     * 
     * Why it's important:
     * - The @BeforeEach annotation ensures that each test starts with a fresh, 
     *   independent Blockchain instance, avoiding cross-test interference.
     */
    @BeforeEach
    void setUp() {
        // Initialize the blockchain before every test.
        blockchain = new Blockchain();
    }

    /**
     * Tests the creation of the Genesis Block, the first block in the chain.
     * 
     * What we are testing:
     * - That the blockchain creates a valid Genesis Block at the start.
     * - The Genesis Block should have an index of 0.
     * 
     * Why it's important:
     * - The Genesis Block is the foundation of the blockchain. 
     *   If it is not properly initialized, the entire chain structure could fail.
     */
    @Test
    void testCreateGenesisBlock() {
        // Assert that the blockchain has created the Genesis Block, and it's not null.
        assertNotNull(blockchain.getLatestBlock(), 
                      "Genesis Block should exist upon blockchain initialization.");

        // Assert that the Genesis Block has the correct index, which should be 0.
        assertEquals(0, blockchain.getLatestBlock().getIndex(), 
                     "Genesis Block should have an index of 0.");
    }

    /**
     * Tests adding a transaction to the pending transaction list of the blockchain.
     * 
     * What we are testing:
     * - That transactions can be added to the pending transaction list.
     * - Verifies that the transaction list grows by one after a transaction is added.
     * 
     * Why it's important:
     * - Transactions are central to blockchain functionality, and they must 
     *   be managed correctly before being added to blocks. This test ensures 
     *   that the blockchain correctly handles transaction queuing.
     */
    @Test
    void testAddTransaction() {
        // Create a transaction from Alice to Bob for an amount of 50.
        Transaction transaction = new Transaction("Alice", "Bob", 50);

        // Add the transaction to the blockchain's pending transaction list.
        blockchain.addTransaction(transaction);

        // Assert that the pending transaction list now contains exactly 1 transaction.
        assertEquals(1, blockchain.getPendingTransactionList().size(), 
                     "Pending transaction list should contain 1 transaction after adding.");
    }

    /**
     * Tests the addition of a new block to the blockchain after transactions are added.
     * 
     * What we are testing:
     * - After a transaction is added, a block is created and added to the blockchain.
     * - The size of the blockchain should increase by one block (including the Genesis Block).
     * 
     * Why it's important:
     * - Adding blocks is how a blockchain grows and processes transactions. 
     *   This test ensures that blocks are created and added correctly, growing the chain.
     */
    @Test
    void testAddBlock() {
        // Create a transaction and add it to the pending transactions list.
        Transaction transaction = new Transaction("Alice", "Bob", 50);
        blockchain.addTransaction(transaction);

        // Add a new block to the blockchain containing this transaction.
        blockchain.addBlock(Arrays.asList(transaction.getTransactionData()));

        // Assert that the blockchain now contains 2 blocks: the Genesis Block and the new block.
        assertEquals(2, blockchain.printChain(), 
                     "Blockchain should have 2 blocks (Genesis + 1 new block) after adding a block.");
    }

    /**
     * Tests if the blockchain is valid after adding legitimate blocks and transactions.
     * 
     * What we are testing:
     * - That the blockchain correctly validates itself after valid transactions and blocks.
     * 
     * Why it's important:
     * - Chain validity is critical to the security and correctness of the blockchain. 
     *   This test ensures that the blockchain remains valid when blocks are added properly.
     */
    @Test
    void testIsChainValid() {
        // Add a transaction and block to the blockchain.
        Transaction transaction = new Transaction("Alice", "Bob", 50);
        blockchain.addTransaction(transaction);
        blockchain.addBlock(Arrays.asList(transaction.getTransactionData()));

        // Assert that the blockchain is valid after adding the block.
        assertTrue(blockchain.isChainValid(), 
                   "Blockchain should be valid after adding legitimate transactions and blocks.");
    }

    /**
     * Tests the blockchain's ability to detect an invalid chain.
     * 
     * What we are testing:
     * - Deliberately adding an invalid block and verifying the chain's validation mechanism.
     * 
     * Why it's important:
     * - Blockchain security depends on its ability to detect tampering or invalid blocks.
     *   This test ensures the blockchain can correctly flag a chain as invalid if a 
     *   block with invalid data is added.
     */
    @Test
    void testInvalidChain() {
        // Add an invalid block (containing an invalid transaction) to the blockchain.
        blockchain.addBlock(Arrays.asList("Invalid Transaction"));

        // Assert that the blockchain is now invalid due to the invalid block.
        assertFalse(blockchain.isChainValid(), 
                    "Blockchain should be invalid if an invalid block is added.");
    }
}
