package com.cms.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class PaymentServiceApplicationTests {

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentController paymentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test to ensure that the Spring ApplicationContext loads without errors.
     * If any exceptions are thrown during the startup process, the test will fail,
     * signaling a configuration issue.
     */
    @Test
    void contextLoads() {
    }

    /**
     * Test for processing a payment through the PaymentService.
     * This test verifies that the payment service successfully processes a payment and
     * returns a Payment object with the expected attributes.
     */
    @Test
    void testProcessPayment() {
        // Arrange: Create a dummy payment response and set up the mock service behavior
        String paymentId = "12345";
        Payment payment = new Payment(paymentId, "John Doe", 100.0, "Credit Card", "SUCCESS");
        when(paymentService.processPayment("John Doe", 100.0, "Credit Card")).thenReturn(payment);

        // Act: Call the controller's processPayment method
        ResponseEntity<Payment> response = paymentController.processPayment("John Doe", 100.0, "Credit Card");

        // Assert: Verify the response and status
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(paymentId, response.getBody().getPaymentId());
        assertEquals("John Doe", response.getBody().getCustomerName());
        assertEquals(100.0, response.getBody().getAmount());
        assertEquals("Credit Card", response.getBody().getPaymentMethod());
        assertEquals("SUCCESS", response.getBody().getPaymentStatus());
    }

    /**
     * Test for retrieving the status of a specific payment.
     * This test verifies that the controller correctly returns the payment status for
     * a given payment ID.
     */
    @Test
    void testGetPaymentStatus() {
        // Arrange: Create a dummy payment and mock the service behavior
        String paymentId = "12345";
        Payment payment = new Payment(paymentId, "Jane Smith", 150.0, "PayPal", "SUCCESS");
        when(paymentService.processPayment("Jane Smith", 150.0, "PayPal")).thenReturn(payment);

        // Act: Retrieve payment status using the controller's getPaymentStatus method
        ResponseEntity<Payment> response = paymentController.getPaymentStatus(paymentId);

        // Assert: Verify the response and status
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(paymentId, response.getBody().getPaymentId());
        assertEquals("Jane Smith", response.getBody().getCustomerName());
        assertEquals(150.0, response.getBody().getAmount());
        assertEquals("PayPal", response.getBody().getPaymentMethod());
        assertEquals("SUCCESS", response.getBody().getPaymentStatus());
    }

    /**
     * Test to verify that an invalid payment request returns an appropriate response.
     * This test simulates a scenario where a payment could not be processed,
     * expecting a null response from the payment service.
     */
    @Test
    void testProcessPaymentFailure() {
        // Arrange: Mock the service to return null for an invalid request
        when(paymentService.processPayment("Invalid User", 0, "Invalid Method")).thenReturn(null);

        // Act: Attempt to process the invalid payment
        ResponseEntity<Payment> response = paymentController.processPayment("Invalid User", 0, "Invalid Method");

        // Assert: Verify that a 400 Bad Request status is returned with no body
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(null, response.getBody());
    }
}
