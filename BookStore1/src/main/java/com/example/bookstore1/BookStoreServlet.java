package com.example.bookstore1;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.bookstore1.Book;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BookStoreServlet extends HttpServlet{
    private static List<Book> books = new ArrayList<>();
	

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] genreFilters = request.getParameterValues("genre");

        List<Book> filteredBooks;

        // Check if no genre filter is selected
        if (genreFilters == null || genreFilters.length == 0) {
            // If no genre filter is applied, show all books
            filteredBooks = books;
        } else {
            // Convert genreFilters array to a Set for fast lookup
            Set<String> genreSet = new HashSet<>(Arrays.asList(genreFilters));

            // Filter books by selected genres
            filteredBooks = books.stream()
                    .filter(book -> genreSet.contains(book.getGenre()))  // Filter based on genre
                    .collect(Collectors.toList());
        }

        // Set the filteredBooks in request attributes to be accessed in JSP
        request.setAttribute("filteredBooks", filteredBooks);

        // Forward the request to the JSP page
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String genre = req.getParameter("genre");
        double price = 0.0;
        try {
            price = Double.parseDouble(req.getParameter("price"));
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid price format.");
            req.getRequestDispatcher("/admin.jsp").forward(req, resp);
            return;
        }
        boolean inStock = req.getParameter("inStock") != null;

        if (title == null || title.trim().isEmpty() ||
            author == null || author.trim().isEmpty() ||
            genre == null || genre.trim().isEmpty()) {
            req.setAttribute("error", "Title, Author, and Genre are required fields.");
            req.getRequestDispatcher("/admin.jsp").forward(req, resp);
            return;
        }

        books.add(new Book(title, author, genre, price, inStock));

        // Redirect to the user interface after successful addition
        resp.sendRedirect("index.jsp");
    }

		
	
}
