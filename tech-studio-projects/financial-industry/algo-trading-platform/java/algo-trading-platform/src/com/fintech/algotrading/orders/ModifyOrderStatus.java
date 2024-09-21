package com.fintech.algotrading.orders;

/**
 * Class representing the status of a modified order.
 */
public class ModifyOrderStatus {

    private long orderId;
    private String status;
    private String modificationDetails;
    private long timestamp;

    /**
     * Constructor to initialize a ModifyOrderStatus object.
     *
     * @param orderId The unique identifier of the modified order.
     * @param status The status of the modified order (e.g., "Modified", "Updated").
     * @param modificationDetails Details about the modification made to the order.
     * @param timestamp The time when the modification status was set.
     */
    public ModifyOrderStatus(long orderId, String status, String modificationDetails, long timestamp) {
        this.orderId = orderId;
        this.status = status;
        this.modificationDetails = modificationDetails;
        this.timestamp = timestamp;
    }

    /**
     * Default constructor for ModifyOrderStatus.
     */
    public ModifyOrderStatus() {
        // Default initialization
    }

    /**
     * Gets the unique identifier of the modified order.
     *
     * @return The unique identifier of the modified order.
     */
    public long getOrderId() {
        return orderId;
    }

    /**
     * Sets the unique identifier of the modified order.
     *
     * @param orderId The unique identifier of the modified order.
     */
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets the status of the modified order.
     *
     * @return The status of the modified order.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the modified order.
     *
     * @param status The status of the modified order.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets details about the modification made to the order.
     *
     * @return Details about the modification made to the order.
     */
    public String getModificationDetails() {
        return modificationDetails;
    }

    /**
     * Sets details about the modification made to the order.
     *
     * @param modificationDetails Details about the modification made to the order.
     */
    public void setModificationDetails(String modificationDetails) {
        this.modificationDetails = modificationDetails;
    }

    /**
     * Gets the timestamp when the modification status was set.
     *
     * @return The timestamp when the modification status was set.
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp when the modification status was set.
     *
     * @param timestamp The timestamp when the modification status was set.
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ModifyOrderStatus{" +
               "orderId=" + orderId +
               ", status='" + status + '\'' +
               ", modificationDetails='" + modificationDetails + '\'' +
               ", timestamp=" + timestamp +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModifyOrderStatus that = (ModifyOrderStatus) o;

        return orderId == that.orderId;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(orderId);
    }
}
