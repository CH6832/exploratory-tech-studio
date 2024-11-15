package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/processPayment")
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
	    	jakarta.servlet.http.HttpSession session = request.getSession();
	        session.removeAttribute("cart");
	        
	        // Redirect to a success page or show a success message
	        response.sendRedirect("paymentsuccess.jsp");
	    } else {
	        // Handle payment failure (e.g., redirect to an error page)
	        response.sendRedirect("paymenterror.jsp");
	    }
	}

}