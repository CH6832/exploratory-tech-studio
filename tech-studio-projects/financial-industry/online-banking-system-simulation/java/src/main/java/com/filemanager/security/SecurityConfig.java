package com.example.banking.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Security configuration for the banking application.
 * This class configures the security settings, including access control
 * and password encoding mechanisms.
 */
/*
 * The @Configuration annotation indicates that the class contains one or more
 * @Bean methods, which will be processed by the Spring container to generate bean
 * definitions and service requests. This marks the class as a source of bean
 * definitions, allowing Spring to manage the lifecycle and dependencies of the
 * objects created within it. It is typically used to group related configuration
 * settings and bean declarations in a single class for better organization and readability.
 */
@Configuration
/*
 * The @EnableWebSecurity annotation enables Spring Security's web security support 
 * and provides the necessary configuration for securing web applications. It allows for 
 * the customization of security settings, such as authentication and authorization rules, 
 * by creating a WebSecurityConfigurerAdapter.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Configures HTTP security settings for the application.
     * This method specifies the access rules for different endpoints,
     * enables basic HTTP authentication, and disables CSRF protection.
     *
     * @param http the HttpSecurity object to configure.
     * @throws Exception if an error occurs during configuration.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // Allow access to customer, account, and transaction endpoints only for ADMIN role
                .antMatchers("/api/customers/**").hasRole("ADMIN")
                .antMatchers("/api/accounts/**").hasRole("ADMIN")
                .antMatchers("/api/transactions/**").hasRole("ADMIN")
                // Any other requests must be authenticated
                .anyRequest().authenticated()
                .and()
                // Enable basic HTTP authentication
                .httpBasic()
                .and()
                // Disable CSRF protection as it may not be needed for API
                .csrf().disable();
    }

    /**
     * Defines a PasswordEncoder bean using BCrypt for hashing passwords.
     * This encoder is used for encoding passwords during registration
     * and verifying passwords during authentication.
     *
     * @return a PasswordEncoder instance.
     */
    /*
     * The @Bean annotation indicates that a method produces a bean that should
     * be managed by the Spring container. This allows for the creation of shared
     * objects or services that can be injected into other components using Spring's
     * dependency injection. In this case, the passwordEncoder() method provides a
     * PasswordEncoder bean for securely encoding passwords.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
