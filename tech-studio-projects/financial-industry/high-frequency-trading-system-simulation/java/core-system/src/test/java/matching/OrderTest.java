package matching;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.hft.matching.Order;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    private static final String BUY_ORDER_TYPE = "BUY";
    private static final String SELL_ORDER_TYPE = "SELL";
    private static final String VALID_ORDER_ID = "ORD12345";
    private static final String INVALID_ORDER_ID = "ORD-123";
    private static final double VALID_PRICE = 100.5;
    private static final double INVALID_PRICE = -1.0;
    private static final int VALID_QUANTITY = 100;
    private static final int INVALID_QUANTITY = -10;

    private Order buyOrder;
    private Order sellOrder;

    @BeforeEach
    public void setup() {
        buyOrder = new Order(VALID_ORDER_ID, BUY_ORDER_TYPE, VALID_QUANTITY, VALID_PRICE);
        sellOrder = new Order(VALID_ORDER_ID, SELL_ORDER_TYPE, VALID_QUANTITY, VALID_PRICE);
    }

    @Test
    public void testOrderCreation() {
        assertNotNull(buyOrder, "Buy order should be created successfully.");
        assertNotNull(sellOrder, "Sell order should be created successfully.");
    }

    @Test
    public void testValidOrderID() {
        assertTrue(buyOrder.getOrderId().matches("ORD\\d+"), "Order ID should match the expected format.");
        assertEquals(VALID_ORDER_ID, buyOrder.getOrderId(), "Order ID should be the one provided.");
    }

    @Test
    public void testInvalidOrderID() {
        Order invalidOrder = new Order(INVALID_ORDER_ID, BUY_ORDER_TYPE, VALID_QUANTITY, VALID_PRICE);
        assertFalse(invalidOrder.getOrderId().matches("ORD\\d+"), "Invalid Order ID should not match the expected format.");
    }

    @Test
    public void testValidOrderType() {
        assertEquals(BUY_ORDER_TYPE, buyOrder.getOrderType(), "Order type should be 'BUY'.");
        assertEquals(SELL_ORDER_TYPE, sellOrder.getOrderType(), "Order type should be 'SELL'.");
    }

    @Test
    public void testInvalidOrderType() {
        // Assuming that Order should only accept 'BUY' or 'SELL' types
        Order invalidOrder = new Order(VALID_ORDER_ID, "INVALID_TYPE", VALID_QUANTITY, VALID_PRICE);
        assertNotEquals("BUY", invalidOrder.getOrderType(), "Order type should be valid and either 'BUY' or 'SELL'.");
        assertNotEquals("SELL", invalidOrder.getOrderType(), "Order type should be valid and either 'BUY' or 'SELL'.");
    }

    @Test
    public void testValidQuantity() {
        assertEquals(VALID_QUANTITY, buyOrder.getQuantity(), "Quantity should be valid and equal to the given quantity.");
    }

    @Test
    public void testInvalidQuantity() {
        Order invalidOrder = new Order(VALID_ORDER_ID, BUY_ORDER_TYPE, INVALID_QUANTITY, VALID_PRICE);
        assertTrue(invalidOrder.getQuantity() < 0, "Order quantity should be non-negative.");
    }

    @Test
    public void testValidPrice() {
        assertEquals(VALID_PRICE, buyOrder.getPrice(), "Price should be valid and equal to the given price.");
    }

    @Test
    public void testInvalidPrice() {
        Order invalidOrder = new Order(VALID_ORDER_ID, BUY_ORDER_TYPE, VALID_QUANTITY, INVALID_PRICE);
        assertTrue(invalidOrder.getPrice() < 0, "Order price should be a positive value.");
    }

    @Test
    public void testOrderEquality() {
        Order duplicateBuyOrder = new Order(VALID_ORDER_ID, BUY_ORDER_TYPE, VALID_QUANTITY, VALID_PRICE);
        assertEquals(buyOrder, duplicateBuyOrder, "Orders with the same ID should be considered equal.");
    }

    @Test
    public void testOrderHashCode() {
        Order duplicateBuyOrder = new Order(VALID_ORDER_ID, BUY_ORDER_TYPE, VALID_QUANTITY, VALID_PRICE);
        assertEquals(buyOrder.hashCode(), duplicateBuyOrder.hashCode(), "Orders with the same ID should have the same hash code.");
    }

    @Test
    public void testOrderToString() {
        String expectedString = "Order{id='ORD12345', type='BUY', quantity=100, price=100.5}";
        assertEquals(expectedString, buyOrder.toString(), "Order's toString method should return the correct string representation.");
    }

    @Test
    public void testOrderIDChange() {
        String newOrderId = "ORD98765";
        buyOrder.setOrderId(newOrderId);
        assertEquals(newOrderId, buyOrder.getOrderId(), "Order ID should be updated correctly.");
    }

    @Test
    public void testOrderTypeChange() {
        buyOrder.setOrderType(SELL_ORDER_TYPE);
        assertEquals(SELL_ORDER_TYPE, buyOrder.getOrderType(), "Order type should be updated correctly.");
    }

    @Test
    public void testOrderQuantityChange() {
        buyOrder.setQuantity(150);
        assertEquals(150, buyOrder.getQuantity(), "Order quantity should be updated correctly.");
    }

    @Test
    public void testOrderPriceChange() {
        buyOrder.setPrice(105.0);
        assertEquals(105.0, buyOrder.getPrice(), "Order price should be updated correctly.");
    }
}
