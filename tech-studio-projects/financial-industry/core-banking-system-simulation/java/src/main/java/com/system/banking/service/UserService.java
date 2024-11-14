package com.system.banking.service;

import com.system.banking.model.User;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.system.banking.repository.UserRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.logging.Logger;

public class UserService {
    private static final String FILE_NAME = "users.json";
    private List<User> userDatabase;

    // Constructor: Load users from file (if exists)
    public UserService(UserRepository userRepository) {
        this.userDatabase = loadUsersFromFile();
    }

    // Method to save a user to the list and persist to file
    public void saveUser(User user) {
        // Check if user already exists
        if (userExists(user.getEmailAddress())) {
            System.out.println("User with this email already exists.");
        } else {
            userDatabase.add(user);
            saveUsersToFile();
            System.out.println("User saved successfully.");
        }
    }

    // Method to check if user exists by email
    private boolean userExists(String email) {
        return userDatabase.stream()
                .anyMatch(existingUser -> existingUser.getEmailAddress().equals(email));
    }

    // Load users from file
    private List<User> loadUsersFromFile() {
        try (Reader reader = new FileReader(FILE_NAME)) {
            Gson gson = new Gson();
            Type userListType = new TypeToken<List<User>>() {}.getType();
            return gson.fromJson(reader, userListType);
        } catch (IOException e) {
            // If file doesn't exist or is empty, return an empty list
            return new ArrayList<>();
        }
    }

    // Save users list to file
    private void saveUsersToFile() {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            Gson gson = new Gson();
            gson.toJson(userDatabase, writer);
        } catch (IOException e) {
            System.out.println("Error saving user data to file.");
        }
    }

    // Method to get a user by email
    public User getUserByEmail(String email) {
        return userDatabase.stream()
                .filter(user -> user.getEmailAddress().equals(email))
                .findFirst()
                .orElse(null);
    }

    // Optional: print users for testing purposes
    public void printUsers() {
        userDatabase.forEach(System.out::println);
    }

    public User getUserById(String number) {
        return userDatabase.stream()
                .filter(user -> user.getEmailAddress().equals(number))
                .findFirst()
                .orElse(null);
    }

    public void registerUser(String name, String email, String password) throws IOException {
        // Initialize the UserRepository
        UserRepository userRepository = new UserRepository();

        // Create a new user from the provided details
        User user = new User();
        user.setFullName(name);
        user.setEmailAddress(email);
        user.setPassword(password); // In production, consider hashing the password for security

        // Read existing users from the repository
        List<User> users = userRepository.readUsers();

        // Add the new user to the list
        users.add(user);

        // Save the updated list of users back to the repository
        userRepository.writeUsers(users);

        System.out.println("User registered successfully.");
    }

    public boolean loginUser(String email, String password) {
        // Retrieve the user by email (from in-memory or a repository)
        User user = findUserByEmail(email);

        // Compare the entered password with the stored plain-text password
        if (user != null && user.getPassword().equals(password)) {
            return true;  // Login successful
        }
        return false;  // Invalid credentials
    }

    private User findUserByEmail(String email) {
        Logger logger = null;
        try {
            // Validate email format
            if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                logger.warning("Invalid email format: " + email);
                throw new IllegalArgumentException("Invalid email format");
            }

            // Fetch the user by email from your repository or in-memory data structure
            // Simulate reading from a repository (e.g., database or JSON file)
            User user = getUserFromRepository(email);

            // If no user is found, log and return null or throw an exception
            if (user == null) {
                logger.warning("User not found with email: " + email);
                return null;  // Return null or throw an exception
            }

            return user;  // Return the found user
        } catch (IllegalArgumentException e) {
            // Handle specific error if email is invalid
            logger.severe("Error: " + e.getMessage());
            return null;
        } catch (Exception e) {
            // Handle any unexpected errors, such as repository issues
            logger.severe("Unexpected error while finding user by email: " + e.getMessage());
            return null;
        }
    }

    private User getUserFromRepository(String email) {
        // Simulating data retrieval from an in-memory list or file
        // This should ideally be your database or JSON reading logic
        List<User> usersList = fetchAllUsers();
        for (User user : usersList) {  // usersList is a list of users fetched from the repository
            if (user.getEmailAddress().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;  // User not found
    }

    // Method to fetch all users and store them in usersList
    public List<User> fetchAllUsers() {
        // Fetch the list of all users from the UserRepository
        UserRepository userRepository = null;
        List<User> usersList = userRepository.readUsers();

        // Return the list of users
        return usersList;
    }

}


