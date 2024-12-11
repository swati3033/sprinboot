<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<html>
<head>
    <title>Online Bookstore</title>
</head>
<body>
<h2>Filter Books By Genre </h2>
<form action="bookstore.jsp" method="get" >
    <fieldset>
        <legend>Filter by Genre</legend>
        <input type="checkbox" name="genre" value="Fiction" 
               <c:if test="${selectedGenres.contains('Fiction')}">checked</c:if> /> Fiction
        <input type="checkbox" name="genre" value="Classic" 
               <c:if test="${selectedGenres.contains('Classic')}">checked</c:if> /> Classic
        <input type="checkbox" name="genre" value="Dystopian" 
               <c:if test="${selectedGenres.contains('Dystopian')}">checked</c:if> /> Dystopian
        <input type="checkbox" name="genre" value="Fantasy" 
               <c:if test="${selectedGenres.contains('Fantasy')}">checked</c:if> /> Fantasy
           <button type="submit" value="Filter">Filter</button>    
    </fieldset>
</form>
<br />

<a href="admin.jsp">Admin: Add New Book</a>

</body>
</html>
