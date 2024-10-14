package com.contractmanagement.system;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity // Enable Spring Security
public class SecurityConfig extends WebSecurityConfiguration {

    @SuppressWarnings({ "deprecation", "removal" })
	protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // Disable CSRF protection for simplicity
            .authorizeHttpRequests()
            .requestMatchers("/users/**").permitAll() // Allow access to all user-related endpoints
            .anyRequest().authenticated(); // Require authentication for all other requests
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCrypt for password encryption
    }
}