#ifndef ORDER_STATUS_CREATOR_H
#define ORDER_STATUS_CREATOR_H

#include "CancelOrder.h"
#include "Order.h"
#include "ModifyOrder.h"

// Forward declarations for status classes.
class CancelOrderStatus;
class NewOrderStatus;
class ModifyOrderStatus;

// The OrderStatusCreator class provides static methods to generate various order statuses.
class OrderStatusCreator {
public:
    // Generates a CancelOrderStatus based on the given CancelOrder.
    // @param co: The CancelOrder object for which the status is generated.
    // @return A new CancelOrderStatus object.
    static CancelOrderStatus GenerateCancelOrderStatus(const CancelOrder& co);

    // Generates a NewOrderStatus based on the given Order.
    // @param order: The Order object for which the status is generated.
    // @return A new NewOrderStatus object.
    static NewOrderStatus GenerateNewOrderStatus(const Order& order);

    // Generates a ModifyOrderStatus based on the given ModifyOrder.
    // @param modifyOrder: The ModifyOrder object for which the status is generated.
    // @return A new ModifyOrderStatus object.
    static ModifyOrderStatus GenerateModifyOrderStatus(const ModifyOrder& modifyOrder);
};

#endif // ORDER_STATUS_CREATOR_H
