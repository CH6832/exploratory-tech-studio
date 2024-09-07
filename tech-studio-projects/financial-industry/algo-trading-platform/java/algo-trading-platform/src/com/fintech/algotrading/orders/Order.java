package com.fintech.algotrading.orders;

import com.fintech.algotrading.instrument.Security;

/**
 * Class representing a trading order.
 */
public class Order {

    private final long orderId;
    private final Security security;
    private final double price;
    private final int quantity;
    private final boolean isBuySide;

    /**
     * Constructor to initialize an Order object.
     *
     * @param orderId  The unique identifier of the order.
     * @param security The security associated with the order.
     * @param price    The price of the order.
     * @param quantity The quantity of the order.
     * @param isBuySide True if the order is a buy order; false if it is a sell order.
     */
    public Order(long orderId, Security security, double price, int quantity, boolean isBuySide) {
        this.orderId = orderId;
        this.security = security;
        this.price = price;
        this.quantity = quantity;
        this.isBuySide = isBuySide;
    }

	/**
     * Gets the unique identifier of the order.
     *
     * @return The unique identifier of the order.
     */
    public long getOrderId() {
        return orderId;
    }

    /**
     * Gets the security associated with the order.
     *
     * @return The security of the order.
     */
    public Security getSecurity() {
        return security;
    }

    /**
     * Gets the price of the order.
     *
     * @return The price of the order.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the quantity of the order.
     *
     * @return The quantity of the order.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Checks if the order is a buy order.
     *
     * @return True if the order is a buy order; false if it is a sell order.
     */
    public boolean isBuySide() {
        return isBuySide;
    }

    @Override
    public String toString() {
        return "Order{" +
               "orderId=" + orderId +
               ", security=" + security +
               ", price=" + price +
               ", quantity=" + quantity +
               ", isBuySide=" + isBuySide +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return orderId == order.orderId;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(orderId);
    }

	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getSecurityId() {
		// TODO Auto-generated method stub
		return 0;
	}
}
