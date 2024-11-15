# Architecture Overview

This Java-based e-commerce platform is built using a combination of Servlets, JSP, and JDBC (Java Database Connectivity). The system is organized into several modules that handle core business logic, payment processing, user authentication, shopping cart management, and database interactions. 

## Key Components:

1. **Frontend:**
   - **JSP Pages**: Used for rendering the user interface (UI).
     - `cart.jsp`: Displays the shopping cart items and allows the user to update quantities or remove items.
     - `index.jsp`: Home page that welcomes the user and displays available products.
     - `shop.jsp`: Lists products and provides options to add them to the cart.
     - `paymentsuccess.jsp` and `paymenterror.jsp`: Handle the payment success and failure scenarios.
   - **HTML/CSS/JS**: Basic HTML structure with CSS for styling and JavaScript for client-side interactions.

2. **Backend (Controller Layer):**
   - **LoginController.java**: Handles user login, session management, and redirects.
   - **RemoveFromCart.java**: Handles the removal of items from the shopping cart.
   - **UpdateCart.java**: Allows updating the quantities of items in the cart.
   - **PaymentProcessing.java**: Handles payment processing logic, including clearing the cart after a successful payment.
   - **DBConnection.java**: Manages database connections for retrieving product data and processing payments.
   - **ShoppingCart.java**: Contains the logic to handle the cart's contents, adding/removing products, and calculating the total price.

3. **Database Layer:**
   - The system uses a **relational database** (e.g., MySQL) to store product details and user transaction records.
   - Products are stored in a `products` table with columns like `product_id`, `name`, `price`, `description`.
   - Transaction records for purchases are stored in a `transactions` table, including user details and payment status.

4. **Flow of Application:**
   - Users browse products via `index.jsp` or `shop.jsp` and can add them to the cart.
   - The `ShoppingCart` class handles cart items, and users can modify cart contents using `UpdateCart.java` or `RemoveFromCart.java`.
   - Once the cart is ready, users proceed to the payment page, where the `PaymentProcessing.java` servlet processes the payment.
   - After payment, the cart is cleared, and users are redirected to either `paymentsuccess.jsp` or `paymenterror.jsp` based on the outcome.

## Technologies:
- **Java 8** (or newer)
- **Servlet API**
- **JSP** (JavaServer Pages)
- **JDBC** for database interaction
- **HTML, CSS, JavaScript**
- **Apache Tomcat** (or any other servlet container)

