package com.contractmanagement.system;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // Disable CSRF protection for simplicity
            .authorizeRequests()
            .antMatchers("/users/**", "/contracts/**", "/inventory/**").authenticated() // Protect all routes
            .anyRequest().permitAll(); // Allow other requests without authentication
    }
}