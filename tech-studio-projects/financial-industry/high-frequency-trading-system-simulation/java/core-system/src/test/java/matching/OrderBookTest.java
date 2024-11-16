package matching;

import com.hft.matching.Order;
import com.hft.matching.OrderBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class OrderBookTest {
    private OrderBook orderBook;

    @BeforeEach
    public void setup() {
        orderBook = new OrderBook();
    }

    @Test
    public void testAddOrder() {
        Order order = new Order("BUY", 100.0, 10, true);
        orderBook.addOrder(order);
        assertEquals(String.valueOf(1), "Order should be added to the order book", orderBook.getOrders().size());
    }

    @Test
    public void testRemoveOrder() {
        Order order = new Order("BUY", 100.0, 10, false);
        orderBook.addOrder(order);
        orderBook.removeOrder(order);
        assertEquals(String.valueOf(0), orderBook.getOrders().size(), "Order should be removed from the order book");
    }
}
