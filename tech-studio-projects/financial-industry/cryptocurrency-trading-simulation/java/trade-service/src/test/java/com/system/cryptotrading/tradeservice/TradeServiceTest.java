package com.system.cryptotrading.tradeservice;

import com.crypto.tradeservice.model.Trade;
import com.crypto.tradeservice.repository.TradeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TradeServiceTest {

    @Mock
    private TradeRepository tradeRepository;

    @InjectMocks
    private TradeService tradeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecuteTrade() {
        // Arrange
        Trade trade = new Trade();
        trade.setUserId(1L);
        trade.setAsset("Bitcoin");
        trade.setAmount(1.5);

        when(tradeRepository.save(any(Trade.class))).thenReturn(trade);

        // Act
        Trade result = tradeService.executeTrade(trade);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getUserId());
        assertEquals("Bitcoin", result.getAsset());
        assertEquals(1.5, result.getAmount());
        verify(tradeRepository, times(1)).save(any(Trade.class));
    }

    @Test
    public void testGetTradeHistory() {
        // Arrange
        Trade trade1 = new Trade();
        trade1.setUserId(1L);
        trade1.setAsset("Bitcoin");
        trade1.setAmount(1.0);

        Trade trade2 = new Trade();
        trade2.setUserId(1L);
        trade2.setAsset("Ethereum");
        trade2.setAmount(2.0);

        when(tradeRepository.findByUserId(1L)).thenReturn(List.of(trade1, trade2));

        // Act
        List<Trade> trades = tradeService.getTradeHistory(1L);

        // Assert
        assertNotNull(trades);
        assertEquals(2, trades.size());
        assertEquals("Bitcoin", trades.get(0).getAsset());
        assertEquals("Ethereum", trades.get(1).getAsset());
        verify(tradeRepository, times(1)).findByUserId(1L);
    }

    @Test
    public void testGetTradeHistory_NoTrades() {
        // Arrange
        when(tradeRepository.findByUserId(999L)).thenReturn(List.of());

        // Act
        List<Trade> trades = tradeService.getTradeHistory(999L);

        // Assert
        assertTrue(trades.isEmpty());
        verify(tradeRepository, times(1)).findByUserId(999L);
    }
}
