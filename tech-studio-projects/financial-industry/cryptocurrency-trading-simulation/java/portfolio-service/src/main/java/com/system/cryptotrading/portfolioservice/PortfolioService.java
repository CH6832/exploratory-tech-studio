package com.system.cryptotrading.portfolioservice;

import com.crypto.portfolioservice.model.Portfolio;
import com.crypto.portfolioservice.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for handling portfolio-related business logic.
 */
@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;

    @Autowired
    public PortfolioService(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    /**
     * Retrieves the portfolio for a given user.
     * @param userId the user ID.
     * @return the user's portfolio.
     */
    public Portfolio getPortfolio(Long userId) {
        return portfolioRepository.findByUserId(userId);
    }
}
