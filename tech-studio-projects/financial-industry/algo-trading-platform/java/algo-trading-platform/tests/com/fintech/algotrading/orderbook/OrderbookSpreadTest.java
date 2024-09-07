package com.fintech.algotrading.orderbook;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class OrderbookSpreadTest {

    @Test
    public void testGetBid() {
        OrderbookSpread spread = new OrderbookSpread(Optional.of(100L), Optional.of(200L));
        assertEquals(Optional.of(100L), spread.getBid(), "The bid price should match the one set in the constructor.");
    }

    @Test
    public void testGetAsk() {
        OrderbookSpread spread = new OrderbookSpread(Optional.of(100L), Optional.of(200L));
        assertEquals(Optional.of(200L), spread.getAsk(), "The ask price should match the one set in the constructor.");
    }

    @Test
    public void testGetSpreadBothPresent() {
        OrderbookSpread spread = new OrderbookSpread(Optional.of(100L), Optional.of(200L));
        assertEquals(Optional.of(100L), spread.getSpread(), "The spread should be the difference between ask and bid prices.");
    }

    @Test
    public void testGetSpreadBidNotPresent() {
        OrderbookSpread spread = new OrderbookSpread(Optional.empty(), Optional.of(200L));
        assertEquals(Optional.empty(), spread.getSpread(), "The spread should be empty when bid price is not present.");
    }

    @Test
    public void testGetSpreadAskNotPresent() {
        OrderbookSpread spread = new OrderbookSpread(Optional.of(100L), Optional.empty());
        assertEquals(Optional.empty(), spread.getSpread(), "The spread should be empty when ask price is not present.");
    }

    @Test
    public void testGetSpreadBothNotPresent() {
        OrderbookSpread spread = new OrderbookSpread(Optional.empty(), Optional.empty());
        assertEquals(Optional.empty(), spread.getSpread(), "The spread should be empty when both bid and ask prices are not present.");
    }
}
