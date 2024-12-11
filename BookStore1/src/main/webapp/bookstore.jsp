<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="com.example.bookstore1.Book" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Online Bookstore</title>
</head>
<body>
<h2>Bookstore Inventory</h2>

<%
// Initialize your book store and check if the session already contains it
List<Book> books = (List<Book>) session.getAttribute("books");
if (books == null) {
    books = new ArrayList<>();
    session.setAttribute("books", books);
}

// Handle form submission to add a new book
String title = request.getParameter("title");
String author = request.getParameter("author");
String genre = request.getParameter("genre");
String priceStr = request.getParameter("price");
String inStockStr = request.getParameter("inStock");

if (title != null && author != null && genre != null && priceStr != null) {
    try {
        double price = Double.parseDouble(priceStr);
        boolean inStock = inStockStr != null;

        // Create a new book object and add it to the list
        Book newBook = new Book(title, author, genre, price, inStock);
        books.add(newBook);
        session.setAttribute("books", books); // Update session with the new list
    } catch (NumberFormatException e) {
        request.setAttribute("error", "Invalid price format");
    }
}
%>


<c:if test="${not empty error}">
    <div style="color: red;">
        ${error}
    </div>
</c:if>

<br />
<table border="1">
    <thead>
    <tr>
        <th>Title</th>
        <th>Author</th>
        <th>Genre</th>
        <th>Price ($)</th>
        <th>Availability</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="book" items="${books}">
        <tr>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.genre}</td>
            <td><fmt:formatNumber value="${book.price}" type="currency" /></td>
            <td>
                <c:choose>
                    <c:when test="${book.inStock}">In Stock</c:when>
                    <c:otherwise>Out of Stock</c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<a href="admin.jsp">Admin: Add New Book</a>

</body>
</html>
