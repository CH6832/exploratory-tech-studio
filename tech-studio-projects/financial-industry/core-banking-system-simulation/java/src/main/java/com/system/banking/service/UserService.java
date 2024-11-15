package com.system.banking.service;

import com.system.banking.model.User;
import com.system.banking.repository.UserRepository;
import com.system.banking.utils.Logging;

import java.io.IOException;
import java.util.List;

/**
 * Service class for user-related operations in the banking system.
 * This class provides methods for user registration, login, fetching all users,
 * and finding a user by email.
 */
public class UserService {
    private UserRepository userRepository;

    /**
     * Constructor for UserService.
     * Initializes the user repository to load users from storage, if available.
     *
     * @param userRepository The repository to manage user data.
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Registers a new user with the provided name, email, and password.
     * Validates input fields to ensure they are not empty, then creates a new user
     * and saves them to the user repository.
     *
     * @param name     The name of the new user.
     * @param email    The email address of the new user.
     * @param password The password for the new user.
     * @throws IOException If an error occurs while writing the user to the file.
     */
    public void registerUser(String name, String email, String password) throws IOException {
        // Check if name is provided
        if (name == null || name.trim().isEmpty()) {
            System.err.println("Error: The 'name' field is required and cannot be empty.");
            System.exit(1);  // Exit the program with an error code
        }

        // Check if email is provided
        if (email == null || email.trim().isEmpty()) {
            System.err.println("Error: The 'email' field is required and cannot be empty.");
            System.exit(1);  // Exit the program with an error code
        }

        // Check if password is provided
        if (password == null || password.trim().isEmpty()) {
            System.err.println("Error: The 'password' field is required and cannot be empty.");
            System.exit(1);  // Exit the program with an error code
        }

        // Create a new user with generated ID and provided details
        User user = new User();
        user.setUserId(userRepository.generateNextUserId());
        user.setFullName(name);
        user.setEmailAddress(email);
        user.setPassword(password); // In production, consider hashing the password for security

        // Add the new user to the list of existing users
        List<User> users = userRepository.readUsers();
        users.add(user);

        // Save the updated list of users to the repository
        userRepository.writeUsers(users);

        System.out.println("User registered successfully.");
    }

    /**
     * Logs in a user by verifying the provided email and password.
     *
     * @param email    The email address of the user.
     * @param password The password of the user.
     * @return True if login is successful; otherwise, false.
     */
    public boolean loginUser(String email, String password) {
        // Retrieve user by email from the repository
        User user = findUserByEmail(email);

        // Compare entered password with the stored password
        if (user != null && user.getPassword().equals(password)) {
            return true;  // Login successful
        }
        return false;  // Invalid credentials
    }

    /**
     * Helper method to find a user by their email address in the repository.
     *
     * @param email The email address of the user to find.
     * @return The user if found; otherwise, null.
     */
    private User getUserFromRepository(String email) {
        // Fetch all users from the repository
        List<User> usersList = userRepository.readUsers();

        // Search for the user by email
        for (User user : usersList) {
            if (user.getEmailAddress().equalsIgnoreCase(email)) {
                return user;  // Return user if found
            }
        }
        return null;
    }

    /**
     * Finds a user by their email address, validating the email format and logging the process.
     *
     * @param email The email address to search for.
     * @return The user if found; otherwise, null.
     */
    public User findUserByEmail(String email) {
        Logging logger = new Logging();
        try {
            // Validate email format
            if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                logger.warning("Invalid email format: " + email);
                throw new IllegalArgumentException("Invalid email format");
            }

            // Attempt to retrieve the user by email
            User user = getUserFromRepository(email);

            // Log if the user is not found
            if (user == null) {
                logger.warning("User not found with email: " + email);
                return null;
            }

            System.out.print("User found: " + user);
            return user;
        } catch (IllegalArgumentException e) {
            logger.severe("Error: " + e.getMessage());
            return null;
        } catch (Exception e) {
            logger.severe("Unexpected error while finding user by email: " + e.getMessage());
            return null;
        }
    }

    /**
     * Fetches all registered users from the repository.
     *
     * @return A list of all users in the system.
     */
    public List<User> fetchAllUsers() {
        // Retrieve all users from the repository
        return userRepository.readUsers();
    }
}
