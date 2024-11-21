package com.system.cryptotrading.userservice;

import com.crypto.user.model.User;
import com.crypto.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setUsername("john_doe");
        user.setPassword("password123"); // In a real test, this would be hashed
    }

    @Test
    public void testCreateUser() {
        // Arrange
        when(userRepository.save(user)).thenReturn(user);

        // Act
        User result = userService.createUser(user);

        // Assert
        assertNotNull(result);
        assertEquals("john_doe", result.getUsername());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testAuthenticateUser_Success() {
        // Arrange
        when(userRepository.findByUsername("john_doe")).thenReturn(Optional.of(user));

        // Act
        User result = userService.authenticateUser("john_doe", "password123");

        // Assert
        assertNotNull(result);
        assertEquals("john_doe", result.getUsername());
    }

    @Test
    public void testAuthenticateUser_Failure_InvalidPassword() {
        // Arrange
        when(userRepository.findByUsername("john_doe")).thenReturn(Optional.of(user));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> userService.authenticateUser("john_doe", "wrongpassword"));
    }

    @Test
    public void testGetUserById() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act
        User result = userService.getUserById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("john_doe", result.getUsername());
    }

    @Test
    public void testGetUserById_NotFound() {
        // Arrange
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        // Act
        User result = userService.getUserById(999L);

        // Assert
        assertNull(result);
    }
}
