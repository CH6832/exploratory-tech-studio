package com.fintech.algotrading.tradingserverengine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TradingEngineServerServiceProviderTest {

    // Sample service interfaces and implementations for testing
    public interface MyService {
        String performAction();
    }

    public static class MyServiceImpl implements MyService {
        @Override
        public String performAction() {
            return "Action performed!";
        }
    }

    public interface AnotherService {
        int getNumber();
    }

    public static class AnotherServiceImpl implements AnotherService {
        @Override
        public int getNumber() {
            return 42;
        }
    }

    @BeforeEach
    public void setUp() {
        // Clear the services map before each test
        // This ensures that tests do not affect each other
        TradingEngineServerServiceProvider.getServiceMap().clear();
    }

    @Test
    public void testRegisterAndRetrieveService() {
        // Arrange
        MyService myService = new MyServiceImpl();
        TradingEngineServerServiceProvider.registerService(MyService.class, myService);

        // Act
        MyService retrievedService = TradingEngineServerServiceProvider.getService(MyService.class);

        // Assert
        assertNotNull(retrievedService, "Service should be registered and retrieved.");
        assertEquals("Action performed!", retrievedService.performAction(), "Service action should match.");
    }

    @Test
    public void testRegisterAndRetrieveMultipleServices() {
        // Arrange
        MyService myService = new MyServiceImpl();
        AnotherService anotherService = new AnotherServiceImpl();
        TradingEngineServerServiceProvider.registerService(MyService.class, myService);
        TradingEngineServerServiceProvider.registerService(AnotherService.class, anotherService);

        // Act
        MyService retrievedMyService = TradingEngineServerServiceProvider.getService(MyService.class);
        AnotherService retrievedAnotherService = TradingEngineServerServiceProvider.getService(AnotherService.class);

        // Assert
        assertNotNull(retrievedMyService, "MyService should be registered and retrieved.");
        assertEquals("Action performed!", retrievedMyService.performAction(), "MyService action should match.");
        assertNotNull(retrievedAnotherService, "AnotherService should be registered and retrieved.");
        assertEquals(42, retrievedAnotherService.getNumber(), "AnotherService number should match.");
    }

    @Test
    public void testServiceNotFound() {
        // Act
        MyService retrievedService = TradingEngineServerServiceProvider.getService(MyService.class);

        // Assert
        assertNull(retrievedService, "Service should not be found if not registered.");
    }

    @Test
    public void testDefaultServiceInitialization() {
        // This test is to check if the static block properly initializes default services
        // Since the static block is empty, this test will pass if no exceptions occur
        assertDoesNotThrow(() -> {
            // Example: Just ensuring no exceptions are thrown during initialization
            ((Object) TradingEngineServerServiceProvider.getServiceMap()).clear(); // Clear services map
        });
    }
}
