package com.system.cryptotrading.tradeservice;

import com.crypto.tradeservice.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing trade data.
 */
public interface TradeRepository extends JpaRepository<Trade, Long> {
    List<Trade> findByUserId(Long userId);
}
