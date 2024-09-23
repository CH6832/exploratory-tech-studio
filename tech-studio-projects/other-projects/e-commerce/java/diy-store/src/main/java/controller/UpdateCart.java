package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ShoppingCart;

public class UpdateCart {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String action = request.getParameter("action");
	    HttpSession session = request.getSession();
	    ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
	    
	    if (cart == null) {
	        cart = new ShoppingCart();
	        session.setAttribute("cart", cart);
	    }

	    if ("update".equals(action)) {
	        int productId = Integer.parseInt(request.getParameter("productId"));
	        int quantity = Integer.parseInt(request.getParameter("quantity"));
	        
	        if (quantity > 0) {
	            cart.addItem(productId, quantity);
	        } else {
	            cart.removeItem(productId); // Assuming you have a method to remove an item
	        }
	        
	        session.setAttribute("cart", cart);
	    }

	    response.sendRedirect("cart.jsp"); // Redirect to the cart page
	}
}
