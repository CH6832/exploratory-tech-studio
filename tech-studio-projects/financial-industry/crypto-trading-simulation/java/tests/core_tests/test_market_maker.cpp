#include <gtest/gtest.h>
#include "core/market_maker/MarketMaker.h"

TEST(MarketMakerTest, StartStop) {
    MarketMaker mm;
    mm.start();
    EXPECT_NO_THROW(mm.stop());
}
