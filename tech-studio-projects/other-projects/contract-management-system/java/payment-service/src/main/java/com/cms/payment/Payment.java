package com.cms.payment;

/**
 * The Payment class represents the details of a payment transaction.
 * This class includes information such as the payment ID, customer name, 
 * amount, payment method, and payment status.
 */
public class Payment {

    // Unique identifier for the payment
    private String paymentId;

    // Name of the customer making the payment
    private String customerName;

    // Amount for the payment
    private double amount;

    // Method of payment (e.g., "Credit Card", "PayPal")
    private String paymentMethod;

    // Status of the payment (e.g., "SUCCESS", "FAILED")
    private String paymentStatus;

    /**
     * Constructs a new Payment with the specified details.
     *
     * @param paymentId     Unique identifier for the payment.
     * @param customerName  Name of the customer making the payment.
     * @param amount        Payment amount.
     * @param paymentMethod Method of payment (e.g., "Credit Card", "PayPal").
     * @param paymentStatus Status of the payment (e.g., "SUCCESS", "FAILED").
     */
    public Payment(String paymentId, String customerName, double amount, String paymentMethod, String paymentStatus) {
        this.paymentId = paymentId;
        this.customerName = customerName;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    /**
     * Gets the unique identifier of the payment.
     *
     * @return The payment ID.
     */
    public String getPaymentId() {
        return paymentId;
    }

    /**
     * Sets the unique identifier of the payment.
     *
     * @param paymentId The new payment ID.
     */
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * Gets the name of the customer who made the payment.
     *
     * @return The customer's name.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the name of the customer who made the payment.
     *
     * @param customerName The new customer name.
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Gets the payment amount.
     *
     * @return The payment amount.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the payment amount.
     *
     * @param amount The new payment amount.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Gets the method of payment used.
     *
     * @return The payment method (e.g., "Credit Card", "PayPal").
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Sets the method of payment used.
     *
     * @param paymentMethod The new payment method.
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Gets the status of the payment.
     *
     * @return The payment status (e.g., "SUCCESS", "FAILED").
     */
    public String getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * Sets the status of the payment.
     *
     * @param paymentStatus The new payment status.
     */
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
