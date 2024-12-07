<%-- 
    Document   : checkEmailSignUp
    Created on : 13-01-2024, 19:28:08
    Author     : Cao Duy Quân
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>checkEmailSignUp</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        .outer-container {
            border: 4px solid #00bcd4; 
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center; 
        }

        label {
            color: #00bcd4; 
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 10px;
            display: block;
        }

        .inner-container {
            background-color: #fff;
            border: 2px solid #00bcd4;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 400px;
            text-align: center; 
        }

        h3 {
            color: red;
        }

        a {
            display: block;
            margin-top: 20px;
            text-decoration: none;
            color: #00bcd4; 
            font-weight: bold;
            transition: color 0.3s;
        }

        a:hover {
            color: #005a6e; 
            text-decoration: underline; 
        }
    </style>
</head>
<body>
    <div class="outer-container">
        <label>Thông Báo</label>
        <div class="inner-container">
            <h3>${message}</h3>
            <a href="signInUp.jsp">Click here to go back to the Login page</a>
        </div>
    </div>
</body>
</html>

