<%@ page import="java.util.*, model.ShoppingCart, controller.RemoveFromCart, controller.UpdateCart" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
    Map<Integer, Double> productPrices = new HashMap<>(); // Store product prices
    productPrices.put(1, 19.99); // Replace with actual prices
    productPrices.put(2, 9.99);
    // Add all products here (as in previous response)

    double totalPrice = cart != null ? cart.getTotalPrice(productPrices) : 0.0; // Handle null cart
%>
<!DOCTYPE html>
<html>
<head>
    <title>Your Cart</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/shop.css">
</head>
<body>
    <div id="header">
        <h1>Your Shopping Cart</h1>
    </div>
    <div class="table-container">
        <table>
            <tr>
                <th>Catalog No</th>
                <th>Name</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Action</th>
            </tr>
            <%
                if (cart != null && !cart.getItems().isEmpty()) {
                    for (Map.Entry<Integer, Integer> entry : cart.getItems().entrySet()) {
                        int productId = entry.getKey();
                        int quantity = entry.getValue();
                        Double price = productPrices.get(productId); // Use Double to allow null

                        // Check if price is available
                        if (price == null) {
                        	price = 0.0;
                            out.println("<tr><td colspan='5'>Price not available for product ID: " + productId + "</td></tr>");
                            continue; // Skip to the next iteration
                        }
                        else {
                        	out.println("<td>" + price * quantity + " Euro</td>");
                        }
            %>
                        <tr>
                            <td><%= productId %></td>
                            <td><!-- Fetch product name from database or use a predefined list --></td>
                            <td>
                                <form method="post" action="<%= request.getContextPath() %>/updateCart">
                                    <input type="hidden" name="productId" value="<%= productId %>">
                                    <input type="hidden" name="quantity" value="<%= quantity %>">
                                    <button type="submit" name="action" value="decrease">-</button>
                                    <input type="text" name="currentQuantity" value="<%= quantity %>" size="2" readonly>
                                    <button type="submit" name="action" value="increase">+</button>
                                </form>
                            </td>
                            <td><%= price * quantity %> Euro</td>
                            <td>
                                <form method="post" action="<%= request.getContextPath() %>/removeFromCart">
                                    <input type="hidden" name="productId" value="<%= productId %>">
                                    <input type="submit" value="Delete">
                                </form>
                            </td>
                        </tr>
            <%
                    }
                } else {
            %>
                    <tr>
                        <td colspan="5">Your cart is empty.</td>
                    </tr>
            <%
                }
            %>
        </table>
        <h3>Total: <%= totalPrice %> Euro</h3>
    </div>
    
    <div id="header2">
        <h1>Payment</h1>
    </div>
	<div class="payment-container">
	    <form method="post" action="<%= request.getContextPath() %>/processPayment">
	        <label for="name">Name:</label><br>
	        <input type="text" name="name" required><br>
	        
	        <label for="cardNumber">Credit Card Number:</label><br>
	        <input type="text" name="cardNumber" required><br>
	
	        <label for="expiryDate">Expiration Date (MM/YY):</label><br>
	        <input type="text" name="expiryDate" required><br>
	
	        <label for="cvv">CVV:</label><br>
	        <input type="text" name="cvv" required><br>
	
	        <input type="submit" value="Pay Now">
	    </form>
	</div>    
</body>
</html>
