package com.fintech.algotrading.orderbook;

import java.util.Optional;

import com.fintech.algotrading.orders.*;

/**
 * Class representing an entry in the order book.
 */
public class OrderbookEntry {
    private Order order = null;
    private Limit parentLimit = null;
    private OrderbookEntry previous;
    private OrderbookEntry next;

    /**
     * Constructs an OrderEntry with the given order and parent limit.
     *
     * @param order      The order associated with this entry.
     * @param parentLimit The limit at which this entry is placed.
     * @return 
     */
    public void OrderEntry(Order order, Limit parentLimit) {
        this.order = order;
        this.parentLimit = parentLimit;
    }

    /**
     * Gets the order associated with this entry.
     *
     * @return The order.
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Gets the parent limit of this entry.
     *
     * @return The parent limit.
     */
    public Limit getParentLimit() {
        return parentLimit;
    }

    /**
     * Gets the previous entry in the linked list.
     *
     * @return The previous entry, or null if this is the first entry.
     */
    public OrderbookEntry getPrevious() {
        return previous;
    }

    /**
     * Sets the previous entry in the linked list.
     *
     * @param previous The previous entry to set.
     */
    public void setPrevious(OrderbookEntry previous) {
        this.previous = previous;
    }

    /**
     * Gets the next entry in the linked list.
     *
     * @return The next entry, or null if this is the last entry.
     */
    public OrderbookEntry getNext() {
        return next;
    }

    /**
     * Sets the next entry in the linked list.
     *
     * @param next The next entry to set.
     */
    public void setNext(OrderbookEntry next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "OrderEntry{" +
               "order=" + order +
               ", parentLimit=" + parentLimit +
               ", previous=" + (previous != null ? previous.getOrder() : "null") +
               ", next=" + (next != null ? next.getOrder() : "null") +
               '}';
    }
}
