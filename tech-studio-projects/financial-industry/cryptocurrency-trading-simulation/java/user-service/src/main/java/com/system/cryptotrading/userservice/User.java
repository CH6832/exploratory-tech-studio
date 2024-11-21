package com.system.cryptotrading.userservice;

import javax.persistence.*;

/**
 * Represents a user entity in the system.
 * Used for storing and retrieving user information from the database.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    // Getters and Setters
}
