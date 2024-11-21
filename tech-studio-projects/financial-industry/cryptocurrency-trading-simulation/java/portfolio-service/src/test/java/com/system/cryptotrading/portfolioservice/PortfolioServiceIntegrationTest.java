package com.system.cryptotrading.portfolioservice;

import com.crypto.portfolio.model.Portfolio;
import com.crypto.portfolio.model.Asset;
import com.crypto.portfolio.repository.PortfolioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class PortfolioServiceIntegrationTest {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private PortfolioService portfolioService;

    private Portfolio portfolio;

    @BeforeEach
    public void setUp() {
        portfolio = new Portfolio();
        portfolio.setUserId(1L);
        portfolio.setAssets(List.of(
                new Asset("Bitcoin", 2, 20000),
                new Asset("Ethereum", 10, 1500)
        ));
        portfolioRepository.save(portfolio);
    }

    @Test
    public void testGetPortfolioByUserId_Integration() {
        Portfolio result = portfolioService.getPortfolioByUserId(1L);
        assertNotNull(result);
        assertEquals(1L, result.getUserId());
        assertEquals(2, result.getAssets().size());
    }

    @Test
    public void testCalculateTotalValue_Integration() {
        Portfolio result = portfolioService.getPortfolioByUserId(1L);
        double totalValue = portfolioService.calculateTotalValue(result);
        assertEquals(41000, totalValue);
    }
}
