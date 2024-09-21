package com.fintech.algotrading.orderbook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

// Mock implementation of IOrderEntryOrderbook for testing purposes
class MockOrderEntryOrderbook implements IOrderEntryOrderbook {
    private final List<OrderbookEntry> entries;

    public MockOrderEntryOrderbook(List<OrderbookEntry> entries) {
        this.entries = entries;
    }

    @Override
    public void addOrderEntry(OrderbookEntry entry) {
        entries.add(entry);
    }

    @Override
    public void removeOrderEntry(long orderId) {
        entries.removeIf(entry -> entry.getOrderId() == orderId);
    }

    @Override
    public List<OrderbookEntry> getAllOrderEntries() {
        return entries;
    }
}

// Test class for IOrderEntryOrderbook
public class IOrderEntryOrderbookTest {

    private IOrderEntryOrderbook orderbook;
    private OrderbookEntry entry1;
    private OrderbookEntry entry2;

    @BeforeEach
    public void setUp() {
        // Initialize with a mock list
        List<OrderbookEntry> mockEntries = new java.util.ArrayList<>();
        orderbook = new MockOrderEntryOrderbook(mockEntries);

        // Initialize OrderbookEntry instances
        entry1 = new OrderbookEntry(1, "Order1");
        entry2 = new OrderbookEntry(2, "Order2");
    }

    @Test
    public void testAddOrderEntry() {
        // Act
        orderbook.addOrderEntry(entry1);
        orderbook.addOrderEntry(entry2);

        // Assert
        List<OrderbookEntry> entries = orderbook.getAllOrderEntries();
        assertEquals(2, entries.size(), "There should be 2 entries.");
        assertTrue(entries.contains(entry1), "Entry1 should be in the list.");
        assertTrue(entries.contains(entry2), "Entry2 should be in the list.");
    }

    @Test
    public void testRemoveOrderEntry() {
        // Setup
        orderbook.addOrderEntry(entry1);
        orderbook.addOrderEntry(entry2);

        // Act
        orderbook.removeOrderEntry(1);

        // Assert
        List<OrderbookEntry> entries = orderbook.getAllOrderEntries();
        assertEquals(1, entries.size(), "There should be 1 entry left.");
        assertFalse(entries.contains(entry1), "Entry1 should be removed.");
        assertTrue(entries.contains(entry2), "Entry2 should still be in the list.");
    }

    @Test
    public void testGetAllOrderEntries() {
        // Act
        orderbook.addOrderEntry(entry1);
        orderbook.addOrderEntry(entry2);

        // Assert
        List<OrderbookEntry> entries = orderbook.getAllOrderEntries();
        assertEquals(2, entries.size(), "There should be 2 entries.");
        assertTrue(entries.contains(entry1), "Entry1 should be in the list.");
        assertTrue(entries.contains(entry2), "Entry2 should be in the list.");
    }
}
