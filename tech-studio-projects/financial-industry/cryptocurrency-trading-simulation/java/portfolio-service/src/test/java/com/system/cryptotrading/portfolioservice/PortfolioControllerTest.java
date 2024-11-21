package com.system.cryptotrading.portfolioservice;

import com.crypto.portfolio.model.Portfolio;
import com.crypto.portfolio.service.PortfolioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class PortfolioControllerTest {

    @Mock
    private PortfolioService portfolioService;

    @InjectMocks
    private PortfolioController portfolioController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(portfolioController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetPortfolioByUserId() throws Exception {
        Portfolio portfolio = new Portfolio();
        portfolio.setUserId(1L);
        portfolio.setAssets(List.of(new Asset("Bitcoin", 2), new Asset("Ethereum", 10)));

        when(portfolioService.getPortfolioByUserId(1L)).thenReturn(portfolio);

        mockMvc.perform(get("/api/portfolio/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.assets[0].name").value("Bitcoin"))
                .andExpect(jsonPath("$.assets[1].name").value("Ethereum"));
    }

    @Test
    public void testCalculatePortfolioValue() throws Exception {
        Portfolio portfolio = new Portfolio();
        portfolio.setUserId(1L);
        portfolio.setAssets(List.of(
                new Asset("Bitcoin", 2, 20000),
                new Asset("Ethereum", 10, 1500)
        ));

        when(portfolioService.calculateTotalValue(portfolio)).thenReturn(41000.0);

        mockMvc.perform(get("/api/portfolio/1/value"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(41000.0));
    }
}
