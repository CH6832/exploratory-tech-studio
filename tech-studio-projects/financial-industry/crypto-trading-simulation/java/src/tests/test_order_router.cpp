#include <gtest/gtest.h>
#include "core/order_router/OrderRouter.h"

class OrderRouterTest : public ::testing::Test {
protected:
    OrderRouter orderRouter;

    void SetUp() override {
        // Initialize resources before each test
        orderRouter.start();
    }

    void TearDown() override {
        // Clean up resources after each test
        orderRouter.stop();
    }
};

TEST_F(OrderRouterTest, RouteOrder) {
    // Test routing an order
    EXPECT_NO_THROW(orderRouter.routeOrder());
}

TEST_F(OrderRouterTest, StartStop) {
    // Test starting and stopping the order router
    EXPECT_NO_THROW(orderRouter.start());
    EXPECT_NO_THROW(orderRouter.stop());
}
