<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: #f0f0f0;
            padding: 50px;
        }
        h1 {
            color: red;
        }
        .message {
            font-size: 18px;
            color: #333;
        }
        .back-button {
            padding: 10px 20px;
            background-color: #FF5733;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .back-button:hover {
            background-color: #c84e26;
        }
    </style>
</head>
<body>
    <h1>Payment Failed</h1>
    <div class="message">
        Unfortunately, there was an issue processing your payment. Please try again.
    </div>
    <br>
    <a href="<%= request.getContextPath() %>/cart" class="back-button">Return to Cart</a>

</body>
</html>