package com.system.cryptotrading.tradeservice;

import javax.persistence.*;

/**
 * Represents a trade in the system.
 * Stores trade information such as trade amount, asset, and user details.
 */
@Entity
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String asset;
    private Double amount;

    // Getters and Setters
}
