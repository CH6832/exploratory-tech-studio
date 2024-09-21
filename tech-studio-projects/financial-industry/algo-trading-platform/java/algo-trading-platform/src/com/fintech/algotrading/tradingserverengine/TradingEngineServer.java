package com.fintech.algotrading.tradingserverengine;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import com.fintech.algotrading.logging.ILogger;

public class TradingEngineServer {

    private final ILogger logger;
    private final TradingEngineServerConfiguration config;
    private final AtomicBoolean stopping = new AtomicBoolean(false);
    private Future<?> futureTask;
    private final ThreadPoolExecutor executor;

    public TradingEngineServer(ILogger logger, TradingEngineServerConfiguration config) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger cannot be null");
        }
        if (config == null) {
            throw new IllegalArgumentException("Configuration cannot be null");
        }
        this.logger = logger;
        this.config = config;
        this.executor = (ThreadPoolExecutor) Executors.newSingleThreadExecutor();
    }

    // Starts the server and returns a CompletableFuture that will be completed when the server stops.
    public CompletableFuture<Void> run(CompletableFuture<Void> cancellationToken) {
        logger.information("TradingEngineServer", "Starting Trading Engine Server");

        // Create a CompletableFuture to manage thread completion
        CompletableFuture<Void> stopFuture = new CompletableFuture<>();

        // Start the Execute method in a separate thread
        futureTask = executor.submit(() -> execute(cancellationToken, stopFuture));

        // Return the future that will be completed when the thread completes
        return stopFuture;
    }

    // Stops the server by setting the stopping flag and shutting down the executor.
    public void stop() {
        stopping.set(true);
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }

    // The main execution loop for the server.
    private void execute(CompletableFuture<Void> cancellationToken, CompletableFuture<Void> stopFuture) {
        logger.information("TradingEngineServer", "Started Execute method");

        try {
            while (!cancellationToken.isDone() && !stopping.get()) {
                // Simulate trading operations
                Thread.sleep(100);
                logger.information("TradingEngineServer", "Running trading engine operations...");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("TradingEngineServer", "Execution interrupted: " + e.getMessage());
        } catch (Exception e) {
            logger.error("TradingEngineServer", "Exception in Execute method: " + e.getMessage());
        } finally {
            logger.information("TradingEngineServer", "Stopping Trading Engine Server");
            stopFuture.complete(null);
        }
    }

	public TradingEngineServerConfiguration getConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}
}
