# DIY Store Web Application

This is a simple web-based application for a DIY store. It allows users to browse products, add items to their cart, and proceed with a simulated payment. The project demonstrates the use of Java Servlets and JSP (JavaServer Pages) for building a dynamic web application.

## Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Running the Server](#running-the-server)
- [Accessing the Application](#accessing-the-application)
- [Sample URLs](#sample-urls)
- [Further Improvements](#further-improvements)

## Project Overview

This project implements a shopping experience where users can:
- Browse a list of DIY products (such as tools).
- Add products to a shopping cart.
- View, update, or remove items from the cart.
- Simulate a payment process.

It uses servlets to manage the server-side logic and JSP for rendering the UI.

## Features

- Product catalog listing
- Add products to the shopping cart
- View the cart and update product quantities
- Simulated payment flow
- Dynamic page rendering using JSP

## Technologies Used

- **Java 8+**: For writing servlets and the backend logic.
- **Servlets and JSP**: For handling requests and generating dynamic web pages.
- **Tomcat Server**: To host the Java-based web application.
- **HTML/CSS**: For the frontend UI.
- **Maven**: For managing dependencies and building the project.

## Project Structure

```
diy-store
├── src
│   ├── controller
│   │   └── AddToCartServlet.java
│   │   └── RemoveFromCartServlet.java
│   │   └── UpdateCartServlet.java
│   │   └── PaymentServlet.java
│   ├── model
│   │   └── ShoppingCart.java
│   │   └── Product.java
│   ├── view
│   │   └── cart.jsp
│   │   └── shop.jsp
├── WebContent
│   ├── css
│   │   └── shop.css
│   ├── WEB-INF
│   │   └── web.xml
│   └── index.jsp
└── pom.xml
```

- **`src/controller`**: Contains the servlets for handling different actions such as adding, removing, and updating items in the cart.
- **`src/model`**: Contains the model classes (Product, ShoppingCart) that represent the business logic.
- **`src/view`**: Contains the JSP pages (cart.jsp, shop.jsp) for the frontend.
- **`WebContent`**: Contains static resources such as CSS files and the `web.xml` configuration for the servlet mappings.

## Installation

### Prerequisites

Make sure you have the following installed:

1. **Java 8+**: Ensure you have a JDK installed. You can check your Java version using `java -version`.
2. **Apache Tomcat (v9 or above)**: [Download and install Tomcat](https://tomcat.apache.org/download-90.cgi).
3. **Maven**: You can install Maven from [here](https://maven.apache.org/install.html).

### Clone the Repository

```bash
git clone https://github.com/yourusername/diy-store.git
cd diy-store
```

### Configure Tomcat (Optional)

If you haven't already installed Tomcat, follow these steps:

1. Download and extract Tomcat.
2. Set up an environment variable `CATALINA_HOME` pointing to the Tomcat installation directory.
3. Copy the compiled `.war` file from the target folder to the `webapps` folder in Tomcat.

### Configure Maven

Make sure you have the `pom.xml` file correctly configured for Maven:

```xml
<dependencies>
    <!-- Servlet API -->
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>4.0.1</version>
        <scope>provided</scope>
    </dependency>

    <!-- JSP API -->
    <dependency>
        <groupId>javax.servlet.jsp</groupId>
        <artifactId>javax.servlet.jsp-api</artifactId>
        <version>2.3.3</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

## Running the Server

1. **Build the project with Maven:**

   Inside the project folder, run the following command to build the project:

   ```bash
   mvn clean install
   ```

   This will generate a `.war` file in the `target` folder.

2. **Deploy to Tomcat:**

   - Copy the generated `.war` file (for example, `diy-store-test.war`) to the `webapps` folder of your Tomcat installation.
   - Start the Tomcat server:

     ```bash
     $CATALINA_HOME/bin/startup.sh    # on Linux/Mac
     $CATALINA_HOME\bin\startup.bat   # on Windows
     ```

   - Alternatively, run Tomcat from within your IDE if configured (e.g., Eclipse or IntelliJ IDEA).

3. **Verify Deployment:**

   Visit the following URL to check if the application is deployed correctly:

   ```
   http://localhost:8080/diy-store-test/
   ```

## Accessing the Application

Once Tomcat is running and the application is deployed:

1. **Home Page:**

   The home page (with the product catalog) can be accessed at:

   ```
   http://localhost:8080/diy-store-test/shop.jsp
   ```

2. **Cart Page:**

   You can view the shopping cart by clicking "View Cart" in the UI or by directly visiting:

   ```
   http://localhost:8080/diy-store-test/cart.jsp
   ```

3. **Simulated Payment:**

   After adding items to the cart, you can proceed to the payment section by visiting:

   ```
   http://localhost:8080/diy-store-test/payment.jsp
   ```

## Sample URLs

- **Product Catalog:** `/shop.jsp`
- **Shopping Cart:** `/cart.jsp`
- **Simulated Payment:** `/payment.jsp`

## Further Improvements

- **Database Integration:** Connect the application to a database to persist product data and cart information.
- **Enhanced UI:** Improve the look and feel using modern CSS frameworks like Bootstrap or Materialize.
- **Security Features:** Implement user authentication and HTTPS for secure payments.
- **Real Payment Integration:** Use a payment gateway API (e.g., Stripe, PayPal) for actual payment processing.

## License

This project is open source and available under the [MIT License](LICENSE).

---

This `README.md` should help anyone new to the project understand how to set it up and run it, and where to access the main features. Adjust the repository URL or specific details as necessary for your project.