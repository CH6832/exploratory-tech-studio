package com.fintech.algotrading.orderbook;

import com.fintech.algotrading.orders.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LimitTest {

    private Limit limit;
    private OrderbookEntry entry1;
    private OrderbookEntry entry2;

    @BeforeEach
    public void setUp() {
        // Create instances of OrderbookEntry and Order
        Order order1 = new Order(1L, 100, 50, true, "user1", 101);
        Order order2 = new Order(2L, 200, 50, false, "user2", 102);
        
        entry1 = new OrderbookEntry(order1, null);
        entry2 = new OrderbookEntry(order2, entry1);
        
        // Initialize the Limit object
        limit = new Limit(50);
        limit.setHead(entry2);
        limit.setTail(entry1);
    }

    @Test
    public void testGetPrice() {
        assertEquals(50, limit.getPrice(), "Price should be 50.");
    }

    @Test
    public void testSetPrice() {
        limit.setPrice(100);
        assertEquals(100, limit.getPrice(), "Price should be updated to 100.");
    }

    @Test
    public void testGetLevelOrderCount() {
        assertEquals(2, limit.getLevelOrderCount(), "Level order count should be 2.");
    }

    @Test
    public void testGetOrderLevelQuantity() {
        assertEquals(300, limit.getOrderLevelQuantity(), "Total order quantity should be 300.");
    }

    @Test
    public void testGetLevelOrderRecords() {
        List<OrderRecord> orderRecords = limit.getLevelOrderRecords();
        assertNotNull(orderRecords, "Order records should not be null.");
        assertEquals(2, orderRecords.size(), "There should be 2 order records.");
        
        OrderRecord record1 = orderRecords.get(0);
        assertEquals(1L, record1.getOrderId(), "Order ID of the first record should be 1.");
        assertEquals(100, record1.getQuantity(), "Quantity of the first record should be 100.");
        assertEquals(50, record1.getPrice(), "Price of the first record should be 50.");
        assertTrue(record1.isBuySide(), "The first record should be a buy side order.");
        assertEquals("user1", record1.getUsername(), "Username of the first record should be 'user1'.");

        OrderRecord record2 = orderRecords.get(1);
        assertEquals(2L, record2.getOrderId(), "Order ID of the second record should be 2.");
        assertEquals(200, record2.getQuantity(), "Quantity of the second record should be 200.");
        assertEquals(50, record2.getPrice(), "Price of the second record should be 50.");
        assertFalse(record2.isBuySide(), "The second record should be a sell side order.");
        assertEquals("user2", record2.getUsername(), "Username of the second record should be 'user2'.");
    }

    @Test
    public void testIsEmpty() {
        assertFalse(limit.isEmpty(), "Limit should not be empty.");
        
        // Set the limit to be empty
        limit.setHead(null);
        limit.setTail(null);
        assertTrue(limit.isEmpty(), "Limit should be empty.");
    }

    @Test
    public void testGetSide() {
        assertEquals(Side.BID, limit.getSide(), "Side should be BID.");

        // Change the order to be a sell side and test
        entry1.getOrder().setBuySide(false);
        assertEquals(Side.ASK, limit.getSide(), "Side should be ASK.");
        
        // Set the limit to be empty
        limit.setHead(null);
        limit.setTail(null);
        assertEquals(Side.EMPTY, limit.getSide(), "Side should be EMPTY.");
    }
}
