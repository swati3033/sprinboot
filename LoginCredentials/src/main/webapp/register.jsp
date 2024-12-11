<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.example.login.UserService" %>
<%@ page import="com.example.login.User" %>

<%
    UserService userService = new UserService();
%>

<html>
<head>
    <title>User Registration</title>
</head>
<body>
    <h1>Register</h1>
    <form action="register" method="post">
        <label>Username:</label>
        <input type="text" name="username" required /><br />

        <label>Password:</label>
        <input type="password" name="password" required /><br />

        <label>Email:</label>
        <input type="email" name="email" required /><br />

        <label>Confirm Password:</label>
        <input type="password" name="confirmPassword" required /><br />

        <input type="submit" value="Register" />
    </form>

    <c:if test="${not empty param.error}">
        <p style="color:red;">${param.error}</p>
    </c:if>
</body>
</html>
