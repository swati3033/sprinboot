<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.example.login.UserService" %>
<%@ page import="com.example.login.User" %>

<html>
<head>
    <title>User Login</title>
</head>
<body>
    <h1>Login</h1>
    <form action="login" method="post">
        <label>Username:</label>
        <input type="text" name="username" required /><br />

        <label>Password:</label>
        <input type="password" name="password" required /><br />

        <input type="submit" value="Login" />
    </form>

    <c:if test="${not empty param.error}">
        <p style="color:red;">${param.error}</p>
    </c:if>
</body>
</html>
