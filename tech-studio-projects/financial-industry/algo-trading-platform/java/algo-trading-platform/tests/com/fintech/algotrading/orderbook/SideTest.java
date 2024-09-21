package com.fintech.algotrading.orderbook;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SideTest {

    @Test
    public void testSideInitialization() {
        // Test for BID side
        Side bidSide = new Side(Side.Type.BID);
        assertEquals(Side.Type.BID, bidSide.getSideType(), "Side type should be BID.");
        assertTrue(bidSide.isBid(), "Side should be identified as BID.");
        assertFalse(bidSide.isAsk(), "Side should not be identified as ASK.");
        assertEquals("BID", bidSide.toString(), "Side should have string representation of 'BID'.");

        // Test for ASK side
        Side askSide = new Side(Side.Type.ASK);
        assertEquals(Side.Type.ASK, askSide.getSideType(), "Side type should be ASK.");
        assertFalse(askSide.isBid(), "Side should not be identified as BID.");
        assertTrue(askSide.isAsk(), "Side should be identified as ASK.");
        assertEquals("ASK", askSide.toString(), "Side should have string representation of 'ASK'.");

        // Test for EMPTY side
        Side emptySide = new Side(Side.Type.EMPTY);
        assertEquals(Side.Type.EMPTY, emptySide.getSideType(), "Side type should be EMPTY.");
        assertFalse(emptySide.isBid(), "Side should not be identified as BID.");
        assertFalse(emptySide.isAsk(), "Side should not be identified as ASK.");
        assertEquals("EMPTY", emptySide.toString(), "Side should have string representation of 'EMPTY'.");
    }

    @Test
    public void testEqualsAndHashCode() {
        // Test equality for same sides
        Side bidSide1 = new Side(Side.Type.BID);
        Side bidSide2 = new Side(Side.Type.BID);
        assertEquals(bidSide1, bidSide2, "Two BID sides should be equal.");
        assertEquals(bidSide1.hashCode(), bidSide2.hashCode(), "Hash codes should be the same for equal sides.");

        // Test equality for different sides
        Side askSide = new Side(Side.Type.ASK);
        Side emptySide = new Side(Side.Type.EMPTY);
        assertNotEquals(bidSide1, askSide, "BID and ASK sides should not be equal.");
        assertNotEquals(bidSide1, emptySide, "BID and EMPTY sides should not be equal.");
        assertNotEquals(askSide, emptySide, "ASK and EMPTY sides should not be equal.");
    }
}
