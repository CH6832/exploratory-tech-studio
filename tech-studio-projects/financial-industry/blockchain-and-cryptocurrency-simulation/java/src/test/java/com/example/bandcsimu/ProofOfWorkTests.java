package com.example.bandcsimu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the ProofOfWork class, which simulates the Proof-of-Work (PoW) 
 * consensus mechanism. The PoW algorithm ensures that miners perform computational work to mine 
 * blocks, contributing to the security and decentralization of the blockchain.
 * 
 * These tests validate that:
 * - Blocks are mined successfully.
 * - The mined hash satisfies the difficulty requirement.
 * 
 * PoW ensures that miners expend computational resources to validate blocks, which maintains 
 * the integrity and security of decentralized systems.
 */
class ProofOfWorkTests {

    // Instances of Blockchain and ProofOfWork to be used in each test case.
    private Blockchain blockchain;
    private ProofOfWork proofOfWork;

    /**
     * This method is executed before each test, setting up the Blockchain and ProofOfWork instances.
     * 
     * Why it's important:
     * - The @BeforeEach annotation ensures that each test starts with a clean state.
     * - The ProofOfWork object is initialized with a difficulty level of 4 (example), which 
     *   simulates a relatively moderate difficulty for mining.
     * - Setting up a fresh blockchain allows for consistent and isolated test cases.
     */
    @BeforeEach
    void setUp() {
        blockchain = new Blockchain(); // Initialize a new blockchain instance.
        proofOfWork = new ProofOfWork(blockchain, 4); // Create a ProofOfWork instance with difficulty level 4.
    }

    /**
     * Tests the mining of a block using the Proof of Work algorithm.
     * 
     * What we are testing:
     * - That the Proof of Work algorithm can successfully mine a new block.
     * - That the resulting block hash satisfies the difficulty requirement (i.e., the hash 
     *   starts with the correct number of leading zeros based on the difficulty).
     * 
     * Why it's important:
     * - PoW ensures that miners expend computational resources to add new blocks, 
     *   securing the blockchain and preventing attacks like double-spending. 
     *   This test ensures that mining works correctly and adheres to the required difficulty.
     */
    @Test
    void testMineBlock() {
        // Create a new block (without any transactions, for simplicity) in the blockchain.
        Block newBlock = blockchain.createNewBlock(null); // Passing null since no transactions are provided.

        // Use the ProofOfWork instance to mine the new block.
        String minedHash = proofOfWork.mineBlock();

        // Assert that the mined hash is not null, ensuring that a block was successfully mined.
        assertNotNull(minedHash, "The mined hash should not be null after mining a block.");

        // Assert that the mined hash starts with the expected number of leading zeros (according to difficulty).
        // In this case, with a difficulty of 4, the hash should start with "0000".
        assertTrue(minedHash.startsWith("0000"), 
                   "The mined hash should start with '0000' to satisfy the difficulty requirement.");
    }
}
