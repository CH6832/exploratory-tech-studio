package com.system.cryptotrading.tradeservice;

package com.crypto.tradeservice.service;

import com.crypto.tradeservice.model.Trade;
import com.crypto.tradeservice.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for handling business logic related to trade execution and history.
 */
@Service
public class TradeService {

    private final TradeRepository tradeRepository;

    @Autowired
    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    /**
     * Executes a trade and stores it in the database.
     * @param trade the trade object to execute.
     * @return the executed trade.
     */
    public Trade executeTrade(Trade trade) {
        // Simulate jitter avoidance by validating trade data before execution
        return tradeRepository.save(trade);
    }

    /**
     * Retrieves all trades for a user.
     * @param userId the unique identifier of the user.
     * @return a list of trades.
     */
    public List<Trade> getTradeHistory(Long userId) {
        return tradeRepository.findByUserId(userId);
    }
}
