# API Documentation

## 1. **LoginController.java**
   - **POST** `/login`
     - **Description**: Handles user login.
     - **Request Parameters**:
       - `username`: The username of the user.
       - `password`: The password of the user.
     - **Response**: Redirects to `shop.jsp` on successful login, or `login.jsp` on failure.

## 2. **UpdateCart.java**
   - **POST** `/updateCart`
     - **Description**: Updates the quantity of a product in the cart.
     - **Request Parameters**:
       - `productId`: The ID of the product.
       - `quantity`: The new quantity to set for the product.
     - **Response**: Redirects back to `cart.jsp` with the updated cart.

## 3. **RemoveFromCart.java**
   - **POST** `/removeFromCart`
     - **Description**: Removes a product from the cart.
     - **Request Parameters**:
       - `productId`: The ID of the product to be removed.
     - **Response**: Redirects to `cart.jsp` after removal.

## 4. **PaymentProcessing.java**
   - **POST** `/processPayment`
     - **Description**: Processes payment information, simulates payment, and handles cart clearing.
     - **Request Parameters**:
       - `name`: The cardholder's name.
       - `cardNumber`: The credit card number.
       - `expiryDate`: The card expiry date (MM/YY).
       - `cvv`: The card's CVV.
     - **Response**: Redirects to `paymentsuccess.jsp` or `paymenterror.jsp` based on the outcome of the payment.

## 5. **DBConnection.java**
   - **Description**: Establishes a connection to the database to retrieve product data and store transaction records.
   - **Methods**:
     - `getConnection()`: Returns a database connection.

