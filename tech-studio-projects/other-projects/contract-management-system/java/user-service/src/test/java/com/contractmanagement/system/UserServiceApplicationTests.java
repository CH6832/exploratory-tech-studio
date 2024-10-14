package com.contractmanagement.system;

import com.contractmanagement.system.User;
import com.contractmanagement.system.UserRepository;
import com.contractmanagement.system.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceApplicationTests {

    @InjectMocks
    private UserService userService; // Inject the UserService to be tested

    @Mock
    private UserRepository userRepository; // Mock UserRepository

    @Mock
    private PasswordEncoder passwordEncoder; // Mock PasswordEncoder

    private User user; // Sample user for testing

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        user = new User("testUser", "password", "test@example.com");
    }

    @Test
    void contextLoads() {
        // Check if the application context loads successfully
    }

    @Test
    void createUserTest() {
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        assertEquals("testUser", createdUser.getUsername());
        verify(passwordEncoder, times(1)).encode(user.getPassword());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void getUserByUsernameTest() {
        when(userRepository.findByUsername("testUser")).thenReturn(user);

        User foundUser = userService.getUserByUsername("testUser");

        assertNotNull(foundUser);
        assertEquals("testUser", foundUser.getUsername());
        verify(userRepository, times(1)).findByUsername("testUser");
    }

    @Test
    void updateUserTest() {
        User updatedUser = new User("updatedUser", "newPassword", "updated@example.com");
        updatedUser.setId(1L);
        
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        User result = userService.updateUser(1L, updatedUser);

        assertNotNull(result);
        assertEquals("updatedUser", result.getUsername());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void deleteUserTest() {
        when(userRepository.existsById(1L)).thenReturn(true);

        assertDoesNotThrow(() -> userService.deleteUser(1L));
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteUserNotFoundTest() {
        when(userRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.deleteUser(1L);
        });

        assertEquals("User not found with id: 1", exception.getMessage());
        verify(userRepository, never()).deleteById(1L);
    }
}
