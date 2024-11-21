package com.system.cryptotrading.portfolioservice;

import com.crypto.portfolio.model.Portfolio;
import com.crypto.portfolio.model.Asset;
import com.crypto.portfolio.repository.PortfolioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PortfolioServiceTest {

    @Mock
    private PortfolioRepository portfolioRepository;

    @InjectMocks
    private PortfolioService portfolioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPortfolioByUserId() {
        // Arrange
        Portfolio portfolio = new Portfolio();
        portfolio.setUserId(1L);
        portfolio.setAssets(Arrays.asList(new Asset("Bitcoin", 2), new Asset("Ethereum", 10)));

        when(portfolioRepository.findByUserId(1L)).thenReturn(portfolio);

        // Act
        Portfolio result = portfolioService.getPortfolioByUserId(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getUserId());
        assertEquals(2, result.getAssets().size());
        assertEquals("Bitcoin", result.getAssets().get(0).getName());
        assertEquals(2, result.getAssets().get(0).getAmount());
    }

    @Test
    public void testCalculateTotalValue() {
        // Arrange
        Portfolio portfolio = new Portfolio();
        portfolio.setAssets(Arrays.asList(
                new Asset("Bitcoin", 2, 20000), // 2 BTC @ 20,000
                new Asset("Ethereum", 10, 1500) // 10 ETH @ 1500
        ));

        // Act
        double totalValue = portfolioService.calculateTotalValue(portfolio);

        // Assert
        assertEquals(41000, totalValue);
    }

    @Test
    public void testPortfolioNotFound() {
        // Arrange
        when(portfolioRepository.findByUserId(999L)).thenReturn(null);

        // Act
        Portfolio result = portfolioService.getPortfolioByUserId(999L);

        // Assert
        assertNull(result);
    }
}
