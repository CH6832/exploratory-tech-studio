package com.example.bandcsimu;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the Block class, designed to validate its behavior.
 * These tests cover the creation of a Block object and the retrieval of its computed hash.
 * 
 * The tests ensure that the Block class operates as expected, verifying:
 * - The block's index, previous hash, and transactions.
 * - The generation of a valid hash for a block.
 * 
 * Test-driven development (TDD) methodology is employed here to ensure that the Block 
 * class works correctly before it's integrated with other components of the system.
 */
class BlockTests {

    /**
     * Tests the creation of a Block object and verifies the properties of the block.
     * 
     * What we are testing:
     * - Whether the block index is correctly set.
     * - Whether the previous hash (which in this case is "0" since it's the Genesis Block) 
     *   is properly initialized.
     * - Whether the transactions list contains the correct "Genesis Block" transaction.
     * 
     * Why it's important:
     * - Validating the block creation process ensures the system is correctly forming 
     *   blockchain units from the start. A misconfigured block could compromise the integrity 
     *   of the entire blockchain.
     */
    @Test
    void testBlockCreation() {
        // Creating a new Block object: the Genesis block of the blockchain.
        Block block = new Block(0, "0", Collections.singletonList("Genesis Block"));

        // Assert that the block's index is set correctly to 0 (the first block in the chain).
        assertEquals(0, block.getIndex(), "Block index should be 0 for the Genesis block.");

        // Assert that the previous hash for the Genesis block is set to "0", meaning no previous block.
        assertEquals("0", block.getPreviousHash(), "Previous hash should be '0' for the Genesis block.");

        // Assert that the transactions list contains the expected transaction, i.e., "Genesis Block".
        assertEquals("Genesis Block", block.getTransactions().get(0), "First transaction should be 'Genesis Block'.");
    }

    /**
     * Tests the generation of a block hash to ensure that it is properly created and not null.
     * 
     * What we are testing:
     * - That the block's hash is generated correctly upon block creation.
     * - That the hash is not null, meaning the hashing process is functioning as expected.
     * 
     * Why it's important:
     * - The hash is a key component of blockchain integrity. It uniquely identifies each block
     *   based on its content, and any alteration in the block would result in a different hash.
     * - Ensuring that the block has a valid (non-null) hash is critical for blockchain security,
     *   as the chain's validity relies on properly linked block hashes.
     */
    @Test
    void testGetHash() {
        // Creating a new Block object, specifically the Genesis block with a transaction.
        Block block = new Block(0, "0", Collections.singletonList("Genesis Block"));

        // Generate the hash for the block.
        String hash = block.getHash();

        // Assert that the block's hash is not null, ensuring that the hash was successfully created.
        assertNotNull(hash, "Block hash should not be null after block creation.");
    }
}
