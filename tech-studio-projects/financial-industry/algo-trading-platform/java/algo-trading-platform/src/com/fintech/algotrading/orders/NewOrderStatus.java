package com.fintech.algotrading.orders;

/**
 * Class representing the status of a new order.
 */
public class NewOrderStatus {

    private long orderId;
    private String status;
    private String message;
    private long timestamp;

    /**
     * Constructor to initialize a NewOrderStatus object.
     *
     * @param orderId The unique identifier of the order.
     * @param status The status of the new order (e.g., "Created", "Pending").
     * @param message A message or description related to the status.
     * @param timestamp The time when the status was set.
     */
    public NewOrderStatus(long orderId, String status, String message, long timestamp) {
        this.orderId = orderId;
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    /**
     * Default constructor for NewOrderStatus.
     */
    public NewOrderStatus() {
        // Initialization code here if needed
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
     * Sets the unique identifier of the order.
     *
     * @param orderId The unique identifier of the order.
     */
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets the status of the new order.
     *
     * @return The status of the new order.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the new order.
     *
     * @param status The status of the new order.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the message related to the status.
     *
     * @return The message related to the status.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message related to the status.
     *
     * @param message The message related to the status.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the timestamp when the status was set.
     *
     * @return The timestamp when the status was set.
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp when the status was set.
     *
     * @param timestamp The timestamp when the status was set.
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "NewOrderStatus{" +
               "orderId=" + orderId +
               ", status='" + status + '\'' +
               ", message='" + message + '\'' +
               ", timestamp=" + timestamp +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewOrderStatus that = (NewOrderStatus) o;

        return orderId == that.orderId;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(orderId);
    }
}
