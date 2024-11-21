package com.system.cryptotrading.tradeservice;

import com.crypto.tradeservice.model.Trade;
import com.crypto.tradeservice.service.TradeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class TradeControllerTest {

    @Mock
    private TradeService tradeService;

    @InjectMocks
    private TradeController tradeController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(tradeController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testExecuteTrade() throws Exception {
        // Arrange
        Trade trade = new Trade();
        trade.setUserId(1L);
        trade.setAsset("Bitcoin");
        trade.setAmount(1.5);

        when(tradeService.executeTrade(any(Trade.class))).thenReturn(trade);

        // Act & Assert
        mockMvc.perform(post("/api/trades/execute")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(trade)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.asset").value("Bitcoin"))
                .andExpect(jsonPath("$.amount").value(1.5));

        verify(tradeService, times(1)).executeTrade(any(Trade.class));
    }

    @Test
    public void testGetTradeHistory() throws Exception {
        // Arrange
        Trade trade1 = new Trade();
        trade1.setUserId(1L);
        trade1.setAsset("Bitcoin");
        trade1.setAmount(1.0);

        Trade trade2 = new Trade();
        trade2.setUserId(1L);
        trade2.setAsset("Ethereum");
        trade2.setAmount(2.0);

        when(tradeService.getTradeHistory(1L)).thenReturn(List.of(trade1, trade2));

        // Act & Assert
        mockMvc.perform(get("/api/trades/history/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value(1))
                .andExpect(jsonPath("$[0].asset").value("Bitcoin"))
                .andExpect(jsonPath("$[1].userId").value(1))
                .andExpect(jsonPath("$[1].asset").value("Ethereum"));

        verify(tradeService, times(1)).getTradeHistory(1L);
    }

    @Test
    public void testGetTradeHistory_NotFound() throws Exception {
        // Arrange
        when(tradeService.getTradeHistory(999L)).thenReturn(List.of());

        // Act & Assert
        mockMvc.perform(get("/api/trades/history/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("No trades found for the user"));

        verify(tradeService, times(1)).getTradeHistory(999L);
    }
}
