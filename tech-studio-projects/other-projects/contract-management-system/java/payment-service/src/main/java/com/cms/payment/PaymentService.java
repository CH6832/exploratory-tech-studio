package com.cms.payment;

import org.springframework.stereotype.Service;
import java.util.UUID;

/**
 * The PaymentService class is responsible for simulating payment processing.
 * This service generates dummy data to represent a payment transaction,
 * including a unique payment ID and a default success status.
 */
@Service
public class PaymentService {

    /**
     * Simulates processing a payment transaction.
     * Generates a unique ID for each payment and sets a default status.
     *
     * @param customerName  The name of the customer making the payment.
     * @param amount        The payment amount.
     * @param paymentMethod The method of payment (e.g., "Credit Card", "PayPal").
     * @return A Payment object containing the payment details and status.
     */
    public Payment processPayment(String customerName, double amount, String paymentMethod) {
        
        // Generate a unique identifier for the payment transaction
        String paymentId = UUID.randomUUID().toString();
        
        // Set the default payment status to "SUCCESS" for the simulation
        String paymentStatus = "SUCCESS";

        // Simulate a delay to represent processing time
        try {
            Thread.sleep(1000);  // Wait 1 second to mimic processing
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Create a Payment object with the transaction details and return it
        return new Payment(paymentId, customerName, amount, paymentMethod, paymentStatus);
    }
}
