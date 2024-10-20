<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>DIY Store</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
    <div id="wrapper">
        <%
            // Handling form submission
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                String email = request.getParameter("email");
                String password = request.getParameter("password");

                if (email != null && password != null) {
                    Connection dbConnection = null;
                    PreparedStatement preparedStatement = null;
                    ResultSet resultSet = null;

                    try {
                        // Load the JDBC driver (modify as per your driver)
                        Class.forName("com.mysql.cj.jdbc.Driver"); // or other driver for your DB
                        
                        // Setup the connection (replace with your own DB connection details)
                        String url = "jdbc:mysql://localhost:3306/your_database";
                        String user = "root"; // DB user
                        String pass = "password"; // DB password

                        dbConnection = DriverManager.getConnection(url, user, pass);

                        // Query to fetch user details
                        String sql = "SELECT * FROM benutzer WHERE userEmail = ?";
                        preparedStatement = dbConnection.prepareStatement(sql);
                        preparedStatement.setString(1, email);
                        resultSet = preparedStatement.executeQuery();

                        if (resultSet.next()) {
                            String dbPassword = resultSet.getString("userPassword");

                            // Simulate password matching (You should use proper hashing like bcrypt)
                            if (password.equals(dbPassword)) {
                                // Store user info in session
                                // HttpSession session = request.getSession();
                                session.setAttribute("userID", resultSet.getInt("userID"));
                                session.setAttribute("userName", resultSet.getString("userName"));
                                session.setAttribute("userRole", resultSet.getString("userRole"));

                                // Redirect to another page (Orders page)
                                response.sendRedirect("Orders-oo.jsp");
                            } else {
                                out.println("Incorrect password. Please try again.");
                            }
                        } else {
                            out.println("No user found with this email.");
                        }
                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                        out.println("Error: " + e.getMessage());
                    } finally {
                        // Clean up
                        if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
                        if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
                        if (dbConnection != null) try { dbConnection.close(); } catch (SQLException ignore) {}
                    }
                }
            }
        %>

        <h1>Welcome to DIY Store</h1>
        <p class="intro-text">Your one-stop shop for all things DIY! Log in to explore exclusive deals, check your orders, and access a wide range of tools and materials.</p>
        
		<form method="post" action="<%= request.getContextPath() %>/auth">
		    <label for="email">E-Mail:</label>
		    <input type="email" name="email" id="email" required>
		
		    <label for="password">Password:</label>
		    <input type="password" name="password" id="password" required>
		
		    <input type="submit" name="action" value="Log In">
		    <input type="submit" name="action" value="Sign Up">
		</form>
		
		<div class="forgot-password">
		    <a href="#">Forgot your password?</a>
		</div>

        <!-- Hyperlink to the shop page -->
        <a href="<%= request.getContextPath() %>/shop.jsp" class="shop-link">Go to Shop</a>

        <!-- Login Form -->
        <!-- <form method="post" action="<%= request.getRequestURI() %>">
            E-Mail: <input type="email" name="email" required><br>
            Password: <input type="password" name="password" required><br>
            <input type="submit" name="send" value="Login">
        </form> -->
    </div>
</body>
</html>
