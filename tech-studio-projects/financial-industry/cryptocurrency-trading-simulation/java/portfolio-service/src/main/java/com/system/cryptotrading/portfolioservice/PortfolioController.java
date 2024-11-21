package com.system.cryptotrading.portfolioservice;

import com.crypto.portfolioservice.model.Portfolio;
import com.crypto.portfolioservice.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for handling portfolio-related operations.
 */
@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {

    private final PortfolioService portfolioService;

    @Autowired
    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    /**
     * Endpoint for retrieving current portfolio.
     * @param userId the user ID.
     * @return the portfolio.
     */
    @GetMapping("/{userId}")
    public Portfolio getPortfolio(@PathVariable Long userId) {
        return portfolioService.getPortfolio(userId);
    }
}
