package com.system.cryptotrading.portfolioservice;

import javax.persistence.*;

/**
 * Represents a user's portfolio.
 */
@Entity
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Double balance;
    private String asset;

    // Getters and Setters
}
