module trading.engine.server {
    requires java.base;
    requires org.junit.jupiter.api;  // Required for JUnit 5
	
	// Export the packages that other modules can access
    exports com.fintech.algotrading.instrument;
    exports com.fintech.algotrading.logging;
    exports com.fintech.algotrading.orderbook;
    exports com.fintech.algotrading.orders;
    exports com.fintech.algotrading.tradingserverengine;

    // Optionally, specify which modules are open for reflection (if needed for frameworks like Spring)
    // opens com.example.tradingengine.server for spring.core;
}
