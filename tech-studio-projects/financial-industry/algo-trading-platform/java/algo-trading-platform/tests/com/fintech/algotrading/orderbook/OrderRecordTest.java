package com.fintech.algotrading.orderbook;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderRecordTest {

    @Test
    public void testOrderRecordConstructorAndGetters() {
        // Create an instance of OrderRecord
        OrderRecord record = new OrderRecord(
            12345L, // orderId
            100,    // quantity
            1500L,  // price
            true,   // isBuySide
            "user1",// username
            567,    // securityId
            1       // theoreticalQueuePosition
        );

        // Assert that the fields are correctly initialized
        assertEquals(12345L, record.getOrderId(), "Order ID should match the one set in the constructor.");
        assertEquals(100, record.getQuantity(), "Quantity should match the one set in the constructor.");
        assertEquals(1500L, record.getPrice(), "Price should match the one set in the constructor.");
        assertTrue(record.isBuySide(), "Buy side flag should match the one set in the constructor.");
        assertEquals("user1", record.getUsername(), "Username should match the one set in the constructor.");
        assertEquals(567, record.getSecurityId(), "Security ID should match the one set in the constructor.");
        assertEquals(1, record.getTheoreticalQueuePosition(), "Theoretical queue position should match the one set in the constructor.");
    }
}
