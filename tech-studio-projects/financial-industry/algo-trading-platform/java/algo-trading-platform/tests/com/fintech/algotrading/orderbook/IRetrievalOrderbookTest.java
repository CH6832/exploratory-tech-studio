package com.fintech.algotrading.orderbook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test class for IRetrievalOrderbook
public class IRetrievalOrderbookTest {

    private MockRetrievalOrderbook orderbook;
    private OrderbookEntry entry1;
    private OrderbookEntry entry2;

    @BeforeEach
    public void setUp() {
        // Initialize OrderbookEntry instances
        entry1 = new OrderbookEntry(1, "Entry1");
        entry2 = new OrderbookEntry(2, "Entry2");

        // Initialize MockRetrievalOrderbook
        orderbook = new MockRetrievalOrderbook();
        orderbook.addOrderEntry(entry1);
        orderbook.addOrderEntry(entry2);
    }

    @Test
    public void testGetOrderEntry() {
        OrderbookEntry result = orderbook.getOrderEntry(1);
        assertNotNull(result, "Order entry should not be null.");
        assertEquals(1, result.getOrderId(), "Order ID should be 1.");
        assertEquals("Entry1", result.getDescription(), "Order description should be 'Entry1'.");
    }

    @Test
    public void testContainsOrder() {
        assertTrue(orderbook.containsOrder(1), "Orderbook should contain order with ID 1.");
        assertFalse(orderbook.containsOrder(3), "Orderbook should not contain order with ID 3.");
    }

    @Test
    public void testOrderEntryAfterRemoval() {
        orderbook.removeOrderEntry(1);
        assertFalse(orderbook.containsOrder(1), "Orderbook should not contain order with ID 1 after removal.");
        assertNull(orderbook.getOrderEntry(1), "Order entry with ID 1 should be null after removal.");
    }
}
