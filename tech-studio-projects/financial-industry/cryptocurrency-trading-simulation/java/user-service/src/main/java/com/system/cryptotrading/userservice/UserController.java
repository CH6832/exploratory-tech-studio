package com.system.cryptotrading.userservice;

import com.system.User;
import com.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing user-related operations.
 * Handles user registration, login, and profile retrieval.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint for registering a new user.
     * @param user the user object containing user details.
     * @return the created user object.
     */
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    /**
     * Endpoint for fetching a user profile by ID.
     * @param userId the unique identifier for the user.
     * @return the user profile.
     */
    @GetMapping("/{userId}")
    public User getUserProfile(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }
}
