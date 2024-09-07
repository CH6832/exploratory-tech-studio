package com.fintech.algotrading.tradingserverengine;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

public class ITradingEngineServerTest {

    @Test
    public void testRunCompletesSuccessfully() throws ExecutionException, InterruptedException {
    	ITradingEngineServerTest server = new ITradingEngineServerTest();
        CompletableFuture<Void> cancellationToken = new CompletableFuture<>();

        CompletableFuture<Void> resultFuture = server.run(cancellationToken);

        // Complete the cancellation token to stop the server
        cancellationToken.complete(null);

        // Assert that the result future completes successfully
        resultFuture.get(); // This should not throw an exception
    }

    private CompletableFuture<Void> run(CompletableFuture<Void> cancellationToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Test
    public void testRunHandlesException() {
    	ITradingEngineServer server = (ITradingEngineServer) new ITradingEngineServerTest();
        CompletableFuture<Void> cancellationToken = new CompletableFuture<>();

        CompletableFuture<Void> resultFuture = server.run(cancellationToken);

        // Simulate an interruption by canceling the token
        cancellationToken.completeExceptionally(new RuntimeException("Simulated Exception"));

        // Assert that the result future completes exceptionally
        assertThrows(CompletionException.class, resultFuture::get);
    }

    @Test
    public void testCloseMethod() {
    	ITradingEngineServer server = (ITradingEngineServer) new ITradingEngineServerTest();

        // No exception should be thrown when close is called
        assertDoesNotThrow(server::close);
    }
}
