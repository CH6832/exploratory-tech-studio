#include <gtest/gtest.h>
#include "core/order_router/OrderRouter.h"

TEST(OrderRouterTest, RouteOrder) {
    OrderRouter router;
    router.start();
    EXPECT_NO_THROW(router.routeOrder());
    router.stop();
}
