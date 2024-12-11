package com.example.login;


import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/register")
public class UserServlet extends HttpServlet {
    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if (action.equals("/register")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String email = request.getParameter("email");

            if (!password.equals(confirmPassword)) {
                response.sendRedirect("register.jsp?error=Passwords do not match");
                return;
            }

            if (userService.register(username, password, email)) {
                response.sendRedirect("login.jsp");
            } else {
                response.sendRedirect("register.jsp?error=Username already exists");
            }
        } else if (action.equals("/login")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (userService.login(username, password)) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect("welcome.jsp");
            } else {
                response.sendRedirect("login.jsp?error=Invalid username or password");
            }
        }
    }
}

