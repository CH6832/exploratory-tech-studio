/*

- http://localhost:8081/api/payments/process?customerName=JaneDoe&amount=150.0&paymentMethod=CreditCard"
{
  "paymentId": "abc123",
  "customerName": "Jane Doe",
  "amount": 150.0,
  "paymentMethod": "CreditCard",
  "paymentStatus": "SUCCESS"
}

- http://localhost:8081/api/payments/abc123"
{
  "paymentId": "abc123",
  "customerName": "John Doe",
  "amount": 100.0,
  "paymentMethod": "Credit Card",
  "paymentStatus": "SUCCESS"
}

*/

package com.cms.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /**
     * Simulate a payment process.
     * @param customerName Customer's name
     * @param amount Payment amount
     * @param paymentMethod Method of payment (e.g., "Credit Card", "PayPal")
     * @return Payment details with status
     */
    @PostMapping("/process")
    public ResponseEntity<Payment> processPayment(
            @RequestParam String customerName,
            @RequestParam double amount,
            @RequestParam String paymentMethod) {

        // Call the PaymentService to process the payment
        Payment payment = paymentService.processPayment(customerName, amount, paymentMethod);

        // Return the payment details in the response
        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    }

    /**
     * Get the status of a payment by paymentId.
     * @param paymentId Payment identifier
     * @return Payment details with status
     */
    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> getPaymentStatus(@PathVariable String paymentId) {
        // In a real scenario, this would query a database to fetch the payment by its ID.
        // For simulation purposes, we return a dummy payment object.
        Payment payment = new Payment(paymentId, "John Doe", 100.0, "Credit Card", "SUCCESS");
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }
}
