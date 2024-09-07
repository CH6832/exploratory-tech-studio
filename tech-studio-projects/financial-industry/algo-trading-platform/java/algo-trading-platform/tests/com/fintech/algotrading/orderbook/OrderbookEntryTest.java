package com.fintech.algotrading.orderbook;

import com.fintech.algotrading.orders.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderbookEntryTest {

    private OrderbookEntry entry;
    private Order order;
    private Limit limit;

    @BeforeEach
    public void setUp() {
        // Initialize objects for testing
        order = new Order(1L, 100, 50L, true, "user1", 1);
        limit = new Limit(50L);
        entry = new OrderbookEntry(order, limit);
    }

    @Test
    public void testGetOrder() {
        assertEquals(order, entry.getOrder(), "The order should match the one set in the constructor.");
    }

    @Test
    public void testGetParentLimit() {
        assertEquals(limit, entry.getParentLimit(), "The parent limit should match the one set in the constructor.");
    }

    @Test
    public void testGetPrevious() {
        OrderbookEntry previousEntry = new OrderbookEntry(new Order(2L, 200, 50L, true, "user2", 2), limit);
        entry.setPrevious(previousEntry);
        assertEquals(previousEntry, entry.getPrevious(), "The previous entry should match the one set using setPrevious.");
    }

    @Test
    public void testSetPrevious() {
        OrderbookEntry previousEntry = new OrderbookEntry(new Order(2L, 200, 50L, true, "user2", 2), limit);
        entry.setPrevious(previousEntry);
        assertEquals(previousEntry, entry.getPrevious(), "The previous entry should be correctly set.");
    }

    @Test
    public void testGetNext() {
        OrderbookEntry nextEntry = new OrderbookEntry(new Order(3L, 150, 50L, false, "user3", 3), limit);
        entry.setNext(nextEntry);
        assertEquals(nextEntry, entry.getNext(), "The next entry should match the one set using setNext.");
    }

    @Test
    public void testSetNext() {
        OrderbookEntry nextEntry = new OrderbookEntry(new Order(3L, 150, 50L, false, "user3", 3), limit);
        entry.setNext(nextEntry);
        assertEquals(nextEntry, entry.getNext(), "The next entry should be correctly set.");
    }

    @Test
    public void testToString() {
        // Create expected toString output
        String expected = "OrderEntry{" +
                          "order=" + order +
                          ", parentLimit=" + limit +
                          ", previous=null" +
                          ", next=null" +
                          '}';
        assertEquals(expected, entry.toString(), "The toString method should return the correct string representation.");
    }
}
