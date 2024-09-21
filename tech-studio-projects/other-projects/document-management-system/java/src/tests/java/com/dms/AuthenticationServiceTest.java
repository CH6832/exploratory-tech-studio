package com.dms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationServiceTest {

    private AuthenticationService authService;

    @BeforeEach
    public void setUp() {
        authService = new AuthenticationService();
    }

    @Test
    public void testAuthenticateValidUser() {
        boolean isAuthenticated = authService.authenticate("admin", "admin123");
        assertTrue(isAuthenticated, "Authentication should be successful for valid user.");
    }

    @Test
    public void testAuthenticateInvalidUser() {
        boolean isAuthenticated = authService.authenticate("admin", "wrongpassword");
        assertFalse(isAuthenticated, "Authentication should fail for invalid user.");
    }

    @Test
    public void testAuthenticateNonExistentUser() {
        boolean isAuthenticated = authService.authenticate("nonexistent", "password");
        assertFalse(isAuthenticated, "Authentication should fail for non-existent user.");
    }
}
