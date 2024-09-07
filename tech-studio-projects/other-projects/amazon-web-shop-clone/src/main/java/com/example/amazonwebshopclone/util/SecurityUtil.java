package com.example.amazonwebshopclone.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Utility class for security-related operations, including password hashing and verification.
 */
public class SecurityUtil {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Hashes the given plain text password using BCrypt.
     *
     * @param rawPassword The plain text password to be hashed.
     * @return The hashed password.
     */
    public static String hashPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * Verifies if the given plain text password matches the hashed password.
     *
     * @param rawPassword     The plain text password to be verified.
     * @param encodedPassword The hashed password to compare with.
     * @return True if the plain text password matches the hashed password, false otherwise.
     */
    public static boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public String encodePassword(String password) {
        return password;
    }
}
