package com.system.banking.repository;

import com.system.banking.model.User;
import com.system.banking.utils.ExceptionUtils;
import com.system.banking.utils.JsonUtils;

import java.io.File;
import java.util.List;

/**
 * Repository class responsible for handling user data persistence.
 * It interacts with the user data file (e.g., JSON) and provides methods
 * to read, write, and manage user data within the file.
 */
public class UserRepository {

    // Constant for the default path to the users file
    private static final String USERS_FILE_PATH = JsonUtils.USERS_FILE_PATH;

    // Instance variable to hold the path to the user data file
    private final String userFilepath;

    /**
     * Default constructor that uses the default file path for user data.
     */
    public UserRepository() {
        this.userFilepath = USERS_FILE_PATH;  // Use the constant default path
    }

    /**
     * Constructor that allows for a custom file path for user data.
     *
     * @param userFilepath The custom file path for the user data file.
     */
    public UserRepository(String userFilepath) {
        // If a custom file path is provided, use it; otherwise, use the default
        this.userFilepath = userFilepath != null ? userFilepath : USERS_FILE_PATH;
    }

    /**
     * Reads all user data from the file.
     * If the user data file is missing, a custom exception is thrown.
     *
     * @return A list of User objects read from the file.
     * @throws FileMissingException if the user data file does not exist.
     */
    public List<User> readUsers() {
        File file = new File(USERS_FILE_PATH);

        // Check if the file exists, if not, throw a custom exception
        if (!file.exists()) {
            throw new ExceptionUtils.FileMissingException("User data file not found at: " + USERS_FILE_PATH);
        }

        // If file exists, proceed with reading the user data
        return JsonUtils.readJson(USERS_FILE_PATH, User.class);
    }

    /**
     * Retrieves a user by their user ID.
     *
     * @param userId The unique ID of the user.
     * @return The User object with the specified userId, or null if not found.
     */
    public User getUserById(String userId) {
        List<User> users = readUsers();  // Read all users from the file
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;  // Return the user if found
            }
        }
        return null;  // Return null if no user is found with the specified ID
    }

    /**
     * Retrieves a user by their full name (case insensitive).
     *
     * @param fullName The full name of the user.
     * @return The User object with the specified fullName, or null if not found.
     */
    public User getUserByFullName(String fullName) {
        List<User> users = readUsers();  // Read all users from the file
        for (User user : users) {
            if (user.getFullName().equalsIgnoreCase(fullName)) {
                return user;  // Return the user if found
            }
        }
        return null;  // Return null if no user is found with the specified full name
    }

    /**
     * Retrieves a user by their email address (case insensitive).
     *
     * @param email The email address of the user.
     * @return The User object with the specified email, or null if not found.
     */
    public User getUserByEmail(String email) {
        List<User> users = readUsers();  // Read all users from the file
        for (User user : users) {
            if (user.getEmailAddress().equalsIgnoreCase(email)) {
                return user;  // Return the user if found
            }
        }
        return null;  // Return null if no user is found with the specified email
    }

    /**
     * Generates the next available user ID by finding the highest existing user ID
     * and incrementing it by 1.
     *
     * @return The next available user ID as a String.
     */
    public String generateNextUserId() {
        List<User> users = readUsers();  // Read all users from the file
        int maxUserId = users.stream()
                .mapToInt(user -> Integer.parseInt(user.getUserId()))  // Convert user IDs to integers
                .max()  // Find the maximum user ID
                .orElse(0);  // If there are no users, default to 0
        return String.valueOf(maxUserId + 1);  // Return the next user ID as a string
    }

    /**
     * Writes the provided list of users to the user data file (JSON format).
     *
     * @param users The list of User objects to write to the file.
     */
    public void writeUsers(List<User> users) {
        // Write the list of users to the JSON file
        JsonUtils.writeJson(users, USERS_FILE_PATH);
    }
}
