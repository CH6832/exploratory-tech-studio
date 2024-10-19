package com.example.bandcsimu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProofOfWorkTests {

    private Blockchain blockchain;
    private ProofOfWork proofOfWork;

    @BeforeEach
    void setUp() {
        blockchain = new Blockchain();
        proofOfWork = new ProofOfWork(blockchain, 4); // Example difficulty
    }

    @Test
    void testMineBlock() {
        Block newBlock = blockchain.createNewBlock(null);
        String minedHash = proofOfWork.mineBlock();
        assertNotNull(minedHash);
        assertTrue(minedHash.startsWith("0000")); // Adjust based on difficulty
    }
}
