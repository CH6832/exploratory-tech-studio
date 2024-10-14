package com.contractmanagement.system;

import com.contractmanagement.system.User;
import com.contractmanagement.system.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service // Marks this class as a Spring service component
public class UserService {

    @Autowired
    private UserRepository userRepository; // Injecting the UserRepository bean

    @Autowired
    private PasswordEncoder passwordEncoder; // For encrypting passwords

    // Create a new user
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt the password before saving
        return userRepository.save(user); // Save the user to the database
    }

    // Retrieve a user by username
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username); // Find user by username
    }

    // Update user details
    public User updateUser(Long id, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            updatedUser.setId(id); // Ensure the ID is set for the update
            return userRepository.save(updatedUser); // Save the updated user
        }
        throw new RuntimeException("User not found with id: " + id);
    }

    // Delete a user by ID
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id); // Delete the user from the database
    }
}