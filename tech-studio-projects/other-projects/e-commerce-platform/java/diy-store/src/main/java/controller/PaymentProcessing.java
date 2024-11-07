package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;

public class PaymentProcessing {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String name = request.getParameter("name");
	    String cardNumber = request.getParameter("cardNumber");
	    String expiryDate = request.getParameter("expiryDate");
	    String cvv = request.getParameter("cvv");
	    
	    // Simulated payment processing logic
	    // Here you would normally validate and process the payment
	    boolean paymentSuccess = true; // Simulate payment success

	    if (paymentSuccess) {
	        // Clear the cart after successful payment
	        javax.servlet.http.HttpSession session = request.getSession();
	        session.removeAttribute("cart");
	        
	        // Redirect to a success page or show a success message
	        response.sendRedirect("paymentSuccess.jsp");
	    } else {
	        // Handle payment failure (e.g., redirect to an error page)
	        response.sendRedirect("paymentError.jsp");
	    }
	}

}