#include <gtest/gtest.h>
#include "core/market_maker/MarketMaker.h"
#include "core/risk_management/RiskManager.h"
#include "core/order_router/OrderRouter.h"
#include "core/data_feeds/MarketDataFeed.h"

class MarketMakerTest : public ::testing::Test {
protected:
    MarketMaker marketMaker;

    void SetUp() override {
        marketMaker.start();
    }

    void TearDown() override {
        marketMaker.stop();
    }
};

TEST_F(MarketMakerTest, StartStop) {
    EXPECT_NO_THROW(marketMaker.start());
    EXPECT_NO_THROW(marketMaker.stop());
}

TEST_F(MarketMakerTest, RiskManagement) {
    RiskManager riskManager;
    EXPECT_NO_THROW(riskManager.manageRisk());
}

TEST_F(MarketMakerTest, OrderRouting) {
    OrderRouter orderRouter;
    EXPECT_NO_THROW(orderRouter.routeOrder());
}

TEST_F(MarketMakerTest, MarketDataFeed) {
    MarketDataFeed marketDataFeed;
    EXPECT_NO_THROW(marketDataFeed.start());
    EXPECT_NO_THROW(marketDataFeed.stop());
}
