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
            color: green;
        }
        .message {
            font-size: 18px;
            color: #333;
        }
        .back-button {
            padding: 10px 20px;
            background-color: #007BFF;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .back-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h1>Payment Successful!</h1>
    <div class="message">
        Thank you for your purchase. Your payment has been processed successfully.
    </div>
    <br>
    <a href="<%= request.getContextPath() %>/" class="back-button">Return to Home</a>
</body>
</html>