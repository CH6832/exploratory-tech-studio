package com.fintech.algotrading.orderbook;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class IReadOnlyOrderbookTest {

    @Test
    public void testContainsOrder() {
        Map<Long, OrderbookEntry> orders = new HashMap<>();
        OrderbookEntry entry = new OrderbookEntry();
        long orderId = 123L;
        orders.put(orderId, entry);

        OrderbookSpread spread = new OrderbookSpread(Optional.of(100L), Optional.of(105L));
        IReadOnlyOrderbook orderbook = new IReadOnlyOrderbook(orders, spread);

        assertTrue(orderbook.containsOrder(orderId));
        assertFalse(orderbook.containsOrder(999L)); // Non-existent order
    }

    @Test
    public void testGetSpread() {
        OrderbookSpread spread = new OrderbookSpread(Optional.of(100L), Optional.of(105L));
        IReadOnlyOrderbook orderbook = new IReadOnlyOrderbook(Collections.emptyMap(), spread);

        OrderbookSpread retrievedSpread = orderbook.getSpread();
        assertEquals(spread.getBid(), retrievedSpread.getBid());
        assertEquals(spread.getAsk(), retrievedSpread.getAsk());
        assertEquals(Optional.of(5L), retrievedSpread.getSpread()); // Ask - Bid
    }

    @Test
    public void testGetCount() {
        Map<Long, OrderbookEntry> orders = new HashMap<>();
        orders.put(1L, new OrderbookEntry());
        orders.put(2L, new OrderbookEntry());
        orders.put(3L, new OrderbookEntry());

        OrderbookSpread spread = new OrderbookSpread(Optional.of(100L), Optional.of(105L));
        IReadOnlyOrderbook orderbook = new IReadOnlyOrderbook(orders, spread);

        assertEquals(3, orderbook.getCount());
    }
}
