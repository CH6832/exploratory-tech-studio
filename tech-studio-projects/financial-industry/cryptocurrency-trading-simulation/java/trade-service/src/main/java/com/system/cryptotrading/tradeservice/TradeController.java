package com.system.cryptotrading.tradeservice;

import com.crypto.tradeservice.model.Trade;
import com.crypto.tradeservice.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for handling trade-related operations.
 * Provides endpoints for trade execution and fetching trade history.
 */
@RestController
@RequestMapping("/api/trades")
public class TradeController {

    private final TradeService tradeService;

    @Autowired
    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    /**
     * Executes a new trade.
     * @param trade the trade object containing trade details.
     * @return the executed trade.
     */
    @PostMapping("/execute")
    public Trade executeTrade(@RequestBody Trade trade) {
        return tradeService.executeTrade(trade);
    }

    /**
     * Fetches trade history for a user.
     * @param userId the user ID.
     * @return a list of trades.
     */
    @GetMapping("/history/{userId}")
    public List<Trade> getTradeHistory(@PathVariable Long userId) {
        return tradeService.getTradeHistory(userId);
    }
}

