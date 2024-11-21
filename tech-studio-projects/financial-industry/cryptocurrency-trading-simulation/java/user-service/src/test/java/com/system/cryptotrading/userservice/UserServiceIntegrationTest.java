package com.system.cryptotrading.userservice;

import com.crypto.user.model.User;
import com.crypto.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserServiceIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUsername("john_doe");
        user.setPassword("password123");  // In real-world, password would be hashed
        userRepository.save(user);
    }

    @Test
    public void testGetUserById_Integration() {
        User result = userService.getUserById(user.getId());

        assertNotNull(result);
        assertEquals("john_doe", result.getUsername());
    }

    @Test
    public void testCreateUser_Integration() {
        User newUser = new User();
        newUser.setUsername("jane_doe");
        newUser.setPassword("password123"); // In real-world, password would be hashed

        User createdUser = userService.createUser(newUser);

        assertNotNull(createdUser);
        assertEquals("jane_doe", createdUser.getUsername());
    }

    @Test
    public void testAuthenticateUser_Integration() {
        User result = userService.authenticateUser("john_doe", "password123");

        assertNotNull(result);
        assertEquals("john_doe", result.getUsername());
    }
}
