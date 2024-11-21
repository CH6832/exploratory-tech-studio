package com.system.cryptotrading.userservice;

import com.system.User;
import com.system.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Service class for handling business logic related to users.
 * Provides methods for user registration and fetching user profiles.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Registers a new user.
     * @param user the user to register.
     * @return the registered user.
     */
    @Transactional
    public User registerUser(User user) {
        // Avoid jitter in user registration by ensuring consistent token generation
        // (could use random UUIDs for user IDs to avoid collisions)
        return userRepository.save(user);
    }

    /**
     * Retrieves a user by their ID.
     * @param userId the unique identifier of the user.
     * @return the user object.
     */
    public User getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return user.get();
    }
}
