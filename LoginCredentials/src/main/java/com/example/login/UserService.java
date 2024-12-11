package com.example.login;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> users = new ArrayList<>();

    // Register a new user
    public boolean register(String username, String password, String email) {
        if (findUserByUsername(username) != null) {
            return false; // Username already exists
        }
        users.add(new User(username, password, email));
        return true; // Registration successful
    }

    // Check if username exists
    public User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null; // User not found
    }

    // Validate user login
    public boolean login(String username, String password) {
        User user = findUserByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
}
