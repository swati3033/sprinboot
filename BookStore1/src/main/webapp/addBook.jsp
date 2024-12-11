<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add New Book</title>
</head>
<body>
<h2>Add a New Book</h2>

<c:if test="${not empty error}">
    <div style="color: red;">
        ${error}
    </div>
</c:if>

<form action="bookstore.jsp" method="post">
    <table>
        <tr>
            <td>Title:</td>
            <td><input type="text" name="title" required /></td>
        </tr>
        <tr>
            <td>Author:</td>
            <td><input type="text" name="author" required /></td>
        </tr>
        <tr>
            <td>Genre:</td>
            <td>
                <select name="genre" required>
                    <option value="">Select Genre</option>
                    <option value="Fiction">Fiction</option>
                    <option value="Classic">Classic</option>
                    <option value="Dystopian">Dystopian</option>
                    <option value="Fantasy">Fantasy</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Price ($):</td>
            <td><input type="number" step="0.01" name="price" required /></td>
        </tr>
        <tr>
            <td>In Stock:</td>
            <td><input type="checkbox" name="inStock" checked /></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Add Book" /></td>
        </tr>
    </table>
</form>

<br />
<a href="bookstore.jsp">Back to Bookstore</a>

</body>
</html>
