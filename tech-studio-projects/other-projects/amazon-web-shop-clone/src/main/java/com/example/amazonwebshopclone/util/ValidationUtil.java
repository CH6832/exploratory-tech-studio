package com.example.amazonwebshopclone.util;

import java.util.regex.Pattern;

/**
 * Utility class for performing common validation tasks.
 */
public class ValidationUtil {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    /**
     * Validates if the given email address is in a valid format.
     *
     * @param email The email address to be validated.
     * @return True if the email address is valid, false otherwise.
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * Checks if the given string is not null and not empty.
     *
     * @param str The string to be checked.
     * @return True if the string is neither null nor empty, false otherwise.
     */
    public static boolean isNotEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }

    /**
     * Validates if the given numeric value is within the specified range.
     *
     * @param value The numeric value to be checked.
     * @param min   The minimum acceptable value (inclusive).
     * @param max   The maximum acceptable value (inclusive).
     * @return True if the value is within the range, false otherwise.
     */
    public static boolean isWithinRange(int value, int min, int max) {
        return value >= min && value <= max;
    }

    /**
     * Validates if the given numeric value is positive.
     *
     * @param value The numeric value to be checked.
     * @return True if the value is positive, false otherwise.
     */
    public static boolean isPositive(int value) {
        return value > 0;
    }
}
