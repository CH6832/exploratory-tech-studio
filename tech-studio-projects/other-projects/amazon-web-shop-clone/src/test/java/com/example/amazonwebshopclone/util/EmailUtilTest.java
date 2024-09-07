package com.example.amazonwebshopclone.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmailUtilTest {

    @Test
    void testValidEmail() {
        String email = "test@example.com";
        boolean isValid = EmailUtil.isValidEmail(email);
        assertThat(isValid).isTrue();
    }

    @Test
    void testInvalidEmail() {
        String email = "invalid-email";
        boolean isValid = EmailUtil.isValidEmail(email);
        assertThat(isValid).isFalse();
    }
}
