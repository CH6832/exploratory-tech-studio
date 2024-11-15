<%@ page import="java.sql.*, java.util.*, model.ShoppingCart" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Sample variables; replace with actual data retrieval logic
    String title = "DIY Store Products"; // Title of the page
    String topHeader = "Welcome to the DIY Store"; // Top header
    String bottomText = "Thank you for visiting our store."; // Bottom text

    // Retrieve or create a shopping cart in the session
    ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
    if (cart == null) {
        cart = new ShoppingCart();
        session.setAttribute("cart", cart);
    }    		
    		
	// Example Product class
	class Product {
	    int katalog_nr;
	    String name;
	    String pr_beschr;
	    double price;

	    Product(int katalog_nr, String name, String pr_beschr, double price) {
	        this.katalog_nr = katalog_nr;
	        this.name = name;
	        this.pr_beschr = pr_beschr;
	        this.price = price;
	    }
	}
	
    // Simulated product data
    List<Product> all = new ArrayList<>(); // This should be fetched from the database
    int n_products = all.size(); // Total number of products
    int n_start = 0; // Starting index for pagination
    int n_end = Math.min(n_products - 1, n_start + 10); // End index for pagination
    int n_per_page = 10; // Number of products per page

    // Sample data for demonstration
    Map<Integer, Double> productPrices = new HashMap<>();
    all.add(new Product(1, "Hammer", "High-quality hammer", 19.99));
    all.add(new Product(2, "Screwdriver", "Versatile screwdriver", 9.99));
    all.add(new Product(3, "Wrench", "Adjustable wrench for various sizes", 14.99));
    all.add(new Product(4, "Drill", "Powerful cordless drill", 79.99));
    all.add(new Product(5, "Pliers", "Multi-purpose pliers", 12.99));
    all.add(new Product(6, "Tape Measure", "25-foot tape measure", 7.99));
    all.add(new Product(7, "Level", "Laser level for precision", 39.99));
    all.add(new Product(8, "Utility Knife", "Retractable utility knife", 4.99));
    all.add(new Product(9, "Saw", "Hand saw for woodworking", 18.99));
    all.add(new Product(10, "Chisel Set", "5-piece chisel set", 24.99));
    all.add(new Product(11, "Safety Glasses", "Protective eyewear", 9.99));
    all.add(new Product(12, "Work Gloves", "Durable work gloves", 11.99));
    all.add(new Product(13, "Nail Gun", "Electric nail gun for quick fastening", 99.99));
    all.add(new Product(14, "Wood Glue", "Strong adhesive for wood projects", 5.99));
    all.add(new Product(15, "Drill Bit Set", "Assorted drill bits for various materials", 29.99));
    all.add(new Product(16, "Sandpaper", "Pack of assorted grit sandpaper", 6.99));
    all.add(new Product(17, "Paint Brushes", "Set of professional paint brushes", 15.99));
    all.add(new Product(18, "Paint Roller", "Heavy-duty paint roller", 10.99));
    all.add(new Product(19, "Ladder", "6-foot step ladder", 89.99));
    all.add(new Product(20, "Extension Cord", "50-foot heavy-duty extension cord", 24.99));
    all.add(new Product(21, "Sanding Block", "Ergonomic sanding block", 3.99));
    all.add(new Product(22, "Drill Press", "Bench drill press for precision drilling", 149.99));
    all.add(new Product(23, "Router", "Wood router for shaping edges", 119.99));
    all.add(new Product(24, "Table Saw", "10-inch table saw for woodworking", 299.99));
    all.add(new Product(25, "Circular Saw", "Cordless circular saw", 129.99));
    all.add(new Product(26, "Jigsaw", "Variable speed jigsaw for cutting curves", 79.99));
    all.add(new Product(27, "Angle Grinder", "Cordless angle grinder", 89.99));
    all.add(new Product(28, "Soldering Iron", "Electric soldering iron for electronics", 19.99));
    all.add(new Product(29, "Digital Multimeter", "Multi-functional digital multimeter", 29.99));
    all.add(new Product(30, "Tool Box", "Heavy-duty tool box for storage", 39.99));
    all.add(new Product(31, "Pocket Knife", "Multi-tool pocket knife", 12.99));
    all.add(new Product(32, "Tile Cutter", "Manual tile cutter for home projects", 59.99));
    all.add(new Product(33, "Pressure Washer", "Electric pressure washer for cleaning", 199.99));
    all.add(new Product(34, "Lawn Mower", "Self-propelled lawn mower", 399.99));
    all.add(new Product(35, "Chainsaw", "Cordless chainsaw for cutting trees", 179.99));
    all.add(new Product(36, "Workbench", "Heavy-duty workbench for projects", 149.99));
    all.add(new Product(37, "Safety Helmet", "Protective helmet for construction", 29.99));
    all.add(new Product(38, "First Aid Kit", "Comprehensive first aid kit", 24.99));
    all.add(new Product(39, "Flashlight", "Rechargeable LED flashlight", 15.99));
    all.add(new Product(40, "Tool Belt", "Durable tool belt for easy access", 19.99));
    all.add(new Product(41, "Garden Trowel", "Hand trowel for gardening", 6.99));
    all.add(new Product(42, "Pruning Shears", "Sharp pruning shears for trimming", 12.99));
    all.add(new Product(43, "Hedge Trimmer", "Cordless hedge trimmer", 129.99));
    all.add(new Product(44, "Sledgehammer", "Heavy-duty sledgehammer", 34.99));
    all.add(new Product(45, "Pickaxe", "Steel pickaxe for digging", 29.99));
    all.add(new Product(46, "Shovel", "Durable garden shovel", 18.99));
    all.add(new Product(47, "Rake", "Metal rake for yard work", 14.99));
    all.add(new Product(48, "Garden Hose", "50-foot expandable garden hose", 24.99));
    all.add(new Product(49, "Sprinkler", "Adjustable garden sprinkler", 9.99));
    all.add(new Product(50, "Wheelbarrow", "Heavy-duty wheelbarrow for hauling", 99.99));
    // Add more products as needed...
    
    for (Product product : all) {
        productPrices.put(product.katalog_nr, product.price);
    }

    // Handle form submission for adding items to cart
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        for (Product product : all) {
            String itemKey = "item" + product.katalog_nr;
            String itemValue = request.getParameter(itemKey);
            if (itemValue != null && !itemValue.isEmpty()) {
                int quantity = Integer.parseInt(itemValue);
                if (quantity > 0) {
                    cart.addItem(product.katalog_nr, quantity);
                }
            }
        }
    }    
    
    // Handle view cart action
    String action = request.getParameter("action");
    if ("View Cart".equals(action)) {
        response.sendRedirect("cart.jsp");
        return; // Prevent further processing
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title><%= title %></title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/shop.css">
</head>
<body>
    
    <div id="header">
        <h1>DIY Store Products</h1>
    </div>
    <div class="intro">
        <p>Your one-stop shop for all things DIY!</p>
    </div>
    <div class="table-container">
        <form action="<%= request.getContextPath() %>/shop.jsp" method="POST">
            <p style="text-align:right">
                <input id="shoppingcart" type="submit" name="action" value="View Cart">
            </p>
        </form>
        
        <h1 align="center">Available Products</h1>
        <p align="right"><%= all.size() %> Product(s) found</p>
        
        <table>
            <tr>
                <th>Catalog No</th>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Quantity</th>
            </tr>
            <form action="<%= request.getContextPath() %>/shop.jsp" method="POST">
                <%
                    for (Product product : all) {
                %>
                        <tr>
                            <td align="center"><%= product.katalog_nr %></td>
                            <td align="center"><%= product.name %></td>
                            <td align="center"><%= product.pr_beschr %></td>
                            <td align="right"><%= product.price %> Euro</td>
                            <td align="center"><input type="text" name="item<%= product.katalog_nr %>" value="0" size="4"></td>
                        </tr>
                <%
                    }
                %>
                <tr>
                    <td colspan="2">
                    	<input type="submit" value="Other Category">
                    </td>
                    <td colspan="2">
                    	<input type="submit" name="action" value="Add to Cart">
                    </td>
                    <td colspan="2" align="right">
                        <input type="submit" name="action" value="Previous">
                        <input type="submit" name="action" value="Next">
                    </td>
                </tr>
            </form>
        </table>
    </div>
</body>
</html>
