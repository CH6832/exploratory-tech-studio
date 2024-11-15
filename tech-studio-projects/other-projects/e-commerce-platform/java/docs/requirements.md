# Functional Requirements

## 1. User Authentication
- Users should be able to log in using their credentials (username/password).
- Login information is validated using the `LoginController.java` class.
- If login is successful, the user is redirected to the product browsing page (`shop.jsp`).

## 2. Product Browsing and Adding to Cart
- The user should be able to browse a list of products displayed on `shop.jsp`.
- Users can add products to their shopping cart.
- The cart will store the product ID and quantity.

## 3. Shopping Cart Management
- The user can view the cart's content on `cart.jsp`.
- Users can:
  - Update product quantities in the cart (`UpdateCart.java`).
  - Remove items from the cart (`RemoveFromCart.java`).
  - View the total price of the items in the cart.
- Cart data is stored in the user session for persistence during the browsing session.

## 4. Payment Processing
- When the user is ready to checkout, they fill in payment details (name, card number, expiry date, CVV) on the payment page.
- The payment details are processed via `PaymentProcessing.java`.
- The system simulates payment processing and redirects the user to either:
  - `paymentsuccess.jsp` if payment is successful.
  - `paymenterror.jsp` if payment fails.
- Upon successful payment, the cart is cleared.

## 5. Database Interaction
- Product data (ID, name, price, description) is fetched from a database using `DBConnection.java`.
- Transaction records (user information, cart contents, payment status) are stored in the database.

## 6. Error Handling
- If a user attempts to access a restricted page without logging in, they should be redirected to the login page.
- If an invalid action is performed (e.g., adding an unavailable product to the cart), appropriate error messages are displayed.

