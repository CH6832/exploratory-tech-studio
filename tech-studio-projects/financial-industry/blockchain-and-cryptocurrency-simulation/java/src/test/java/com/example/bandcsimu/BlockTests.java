package com.example.bandcsimu;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class BlockTests {

    @Test
    void testBlockCreation() {
        Block block = new Block(0, "0", Collections.singletonList("Genesis Block"));
        assertEquals(0, block.getIndex());
        assertEquals("0", block.getPreviousHash());
        assertEquals("Genesis Block", block.getTransactions().get(0));
    }

    @Test
    void testGetHash() {
        Block block = new Block(0, "0", Collections.singletonList("Genesis Block"));
        String hash = block.getHash();
        assertNotNull(hash);
    }
}
