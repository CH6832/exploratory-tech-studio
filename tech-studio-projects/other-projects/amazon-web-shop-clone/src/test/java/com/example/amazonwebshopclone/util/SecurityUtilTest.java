package com.example.amazonwebshopclone.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SecurityUtilTest {

    @Test
    void testHashPassword() {
        String rawPassword = "password123";
        String hashedPassword = SecurityUtil.hashPassword(rawPassword);
        assertThat(hashedPassword).isNotEqualTo(rawPassword);
        assertThat(SecurityUtil.verifyPassword(rawPassword, hashedPassword)).isTrue();
    }
}
