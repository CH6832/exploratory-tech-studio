package com.fintech.algotrading.tradingserverengine;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public interface ITradingEngineServer {
    /**
     * Runs the trading engine server engine and returns a CompletableFuture
     * that will complete when the engine stops running or an exception is thrown.
     *
     * @param cancellationToken A CompletableFuture that can be used to signal cancellation.
     * @return A CompletableFuture that represents the running of the engine.
     */
    CompletableFuture<Void> run(CompletableFuture<Void> cancellationToken);

    /**
     * Default method to provide a way to handle cleanup if needed.
     * This is similar to the virtual destructor in C++ for cleanup.
     */
    default void close() {
        // Default implementation can be provided here if needed.
        // In Java, you typically use try-with-resources or explicit close methods for cleanup.
    }
}
