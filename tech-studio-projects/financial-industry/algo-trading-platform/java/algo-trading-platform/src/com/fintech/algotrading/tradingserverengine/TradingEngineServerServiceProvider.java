package com.fintech.algotrading.tradingserverengine;

import java.util.HashMap;
import java.util.Map;

// Service provider class
public class TradingEngineServerServiceProvider {
    // Map to store service instances
    private static final Map<Class<?>, Object> services = new HashMap<>();

    // Method to register a service
    public static <T> void registerService(Class<T> serviceClass, T serviceInstance) {
        services.put(serviceClass, serviceInstance);
    }

    // Method to get a service
    @SuppressWarnings("unchecked")
    public static <T> T getService(Class<T> serviceClass) {
        return (T) services.get(serviceClass);
    }

    // Static block to initialize default services (if any)
    static {
        // Initialize default services here if needed
        // Example: registerService(MyService.class, new MyServiceImpl());
    }

	public static Object getServiceMap() {
		// TODO Auto-generated method stub
		return null;
	}
}