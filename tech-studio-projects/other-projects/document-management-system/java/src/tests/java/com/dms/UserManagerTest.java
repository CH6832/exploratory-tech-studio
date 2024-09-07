package com.dms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserManagerTest {

    private UserManager userManager;

    @BeforeEach
    public void setUp() {
        userManager = new UserManager();
    }

    @Test
    public void testGetUserByUsername() {
        User user = userManager.getUserByUsername("admin");
        assertNotNull(user, "User should be found for valid username.");
        assertEquals("admin", user.getUsername(), "Username should match.");
    }

    @Test
    public void testGetUserByNonExistentUsername() {
        User user = userManager.getUserByUsername("nonexistent");
        assertNull(user, "User should not be found for non-existent username.");
    }
}
