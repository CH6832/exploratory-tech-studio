package com.fintech.algotrading.orderbook;

import com.fintech.algotrading.instrument.Security;
import com.fintech.algotrading.orders.CancelOrder;
import com.fintech.algotrading.orders.ModifyOrder;
import com.fintech.algotrading.orders.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class OrderbookTest {

    private Orderbook orderbook;
    private Security security;

    @BeforeEach
    public void setUp() {
        security = new Security(1L, "Test Security", "TS", "NYSE");
        orderbook = new Orderbook(security);
    }

    @Test
    public void testAddOrder() {
        // Arrange
        Order order = new Order(1L, 100, 50L, true, "user1", 1);

        // Act
        orderbook.addOrder(order);

        // Assert
        assertEquals(1, orderbook.getCount(), "Order count should be 1");
        assertTrue(orderbook.containsOrder(1L), "Order with ID 1 should be present");
    }

    @Test
    public void testChangeOrder() {
        // Arrange
        Order initialOrder = new Order(1L, 100, 50L, true, "user1", 1);
        orderbook.addOrder(initialOrder);

        ModifyOrder modifyOrder = new ModifyOrder(1L, 150, 50L, true, "user1", 1);

        // Act
        orderbook.changeOrder(modifyOrder);

        // Assert
        assertEquals(1, orderbook.getCount(), "Order count should still be 1");
        // Verify if the order has been updated (this assumes you have methods to fetch orders and their details)
    }

    @Test
    public void testRemoveOrder() {
        // Arrange
        Order order = new Order(1L, 100, 50L, true, "user1", 1);
        orderbook.addOrder(order);
        CancelOrder cancelOrder = new CancelOrder();
        // Assuming CancelOrder has a method to set orderId
        cancelOrder.setOrderId(1L);

        // Act
        orderbook.removeOrder(cancelOrder);

        // Assert
        assertEquals(0, orderbook.getCount(), "Order count should be 0 after removal");
        assertFalse(orderbook.containsOrder(1L), "Order with ID 1 should not be present");
    }

    @Test
    public void testGetBidOrders() {
        // Arrange
        Order bidOrder1 = new Order(1L, 100, 50L, true, "user1", 1);
        Order bidOrder2 = new Order(2L, 200, 50L, true, "user2", 2);
        orderbook.addOrder(bidOrder1);
        orderbook.addOrder(bidOrder2);

        // Act
        List<OrderbookEntry> bidOrders = orderbook.getBidOrders();

        // Assert
        assertEquals(2, bidOrders.size(), "There should be 2 bid orders");
    }

    @Test
    public void testGetAskOrders() {
        // Arrange
        Order askOrder = new Order(1L, 100, 50L, false, "user1", 1);
        orderbook.addOrder(askOrder);

        // Act
        List<OrderbookEntry> askOrders = orderbook.getAskOrders();

        // Assert
        assertEquals(1, askOrders.size(), "There should be 1 ask order");
    }

    @Test
    public void testGetSpread() {
        // Arrange
        Order bidOrder = new Order(1L, 100, 50L, true, "user1", 1);
        Order askOrder = new Order(2L, 100, 55L, false, "user2", 2);
        orderbook.addOrder(bidOrder);
        orderbook.addOrder(askOrder);

        // Act
        OrderbookSpread spread = orderbook.getSpread();

        // Assert
        assertTrue(spread.getBidPrice().isPresent(), "Bid price should be present");
        assertTrue(spread.getAskPrice().isPresent(), "Ask price should be present");
        assertEquals(50L, spread.getBidPrice().get(), "Bid price should be 50");
        assertEquals(55L, spread.getAskPrice().get(), "Ask price should be 55");
    }
}
