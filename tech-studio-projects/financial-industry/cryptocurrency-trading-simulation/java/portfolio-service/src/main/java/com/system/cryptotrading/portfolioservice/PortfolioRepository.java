package com.system.cryptotrading.portfolioservice;

import com.crypto.portfolioservice.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing portfolio data.
 */
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    Portfolio findByUserId(Long userId);
}
