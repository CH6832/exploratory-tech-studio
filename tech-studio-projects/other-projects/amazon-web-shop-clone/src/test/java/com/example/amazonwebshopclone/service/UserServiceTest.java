package com.example.amazonwebshopclone.service;

import com.example.amazonwebshopclone.dto.UserDTO;
import com.example.amazonwebshopclone.model.User;
import com.example.amazonwebshopclone.repository.UserRepository;
import com.example.amazonwebshopclone.util.SecurityUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private SecurityUtil securityUtil;

    @InjectMocks
    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setId(1L);
        user.setName("Test User");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserDTO userDTO = userService.getUserById(1L);
        assertEquals("Test User", userDTO.getName());
    }

    // Additional test cases
}
