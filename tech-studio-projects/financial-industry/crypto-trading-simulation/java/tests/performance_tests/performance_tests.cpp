#include <gtest/gtest.h>
#include <chrono>
#include "core/market_maker/MarketMaker.h"
#include "core/order_router/OrderRouter.h"
#include "core/data_feeds/MarketDataFeed.h"

// Helper function to simulate order routing time
void simulateOrderRouting(OrderRouter& orderRouter) {
    // Simulate some work for order routing
    for (int i = 0; i < 10000; ++i) {
        orderRouter.routeOrder(); // Call routeOrder multiple times
    }
}

// Performance Test for OrderRouter
TEST(OrderRouterPerformanceTest, RoutingPerformance) {
    OrderRouter orderRouter;
    orderRouter.start(); // Start the order router

    auto start = std::chrono::high_resolution_clock::now(); // Start time measurement
    simulateOrderRouting(orderRouter); // Simulate order routing
    auto end = std::chrono::high_resolution_clock::now(); // End time measurement

    std::chrono::duration<double, std::milli> duration = end - start; // Calculate duration
    std::cout << "Order routing took: " << duration.count() << " ms" << std::endl;

    orderRouter.stop(); // Stop the order router
    EXPECT_LE(duration.count(), 500); // Expect routing to take less than 500ms
}

// Performance Test for MarketMaker
TEST(MarketMakerPerformanceTest, MarketMakingPerformance) {
    MarketMaker marketMaker;
    marketMaker.start(); // Start the market maker

    auto start = std::chrono::high_resolution_clock::now(); // Start time measurement
    for (int i = 0; i < 1000; ++i) {
        // Simulate market making logic
        marketMaker.start(); // This should represent some market-making logic
        marketMaker.stop();  // This should represent stopping the market-making logic
    }
    auto end = std::chrono::high_resolution_clock::now(); // End time measurement

    std::chrono::duration<double, std::milli> duration = end - start; // Calculate duration
    std::cout << "Market making took: " << duration.count() << " ms" << std::endl;

    EXPECT_LE(duration.count(), 1000); // Expect total market-making to take less than 1000ms
    marketMaker.stop(); // Ensure market maker is stopped
}
