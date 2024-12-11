<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.login.UserService" %>
<%@ page import="com.example.login.User" %>

<html>
<head>
    <title>Welcome</title>
</head>
<body>
    <h1>Welcome, ${sessionScope.username}!</h1>
    <a href="logout">Logout</a>
</body>
</html>
