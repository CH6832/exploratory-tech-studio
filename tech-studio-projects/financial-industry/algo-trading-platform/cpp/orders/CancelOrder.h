#include "OrderStatusCreator.h"
#include "CancelOrderStatus.h"
#include "NewOrderStatus.h"
#include "ModifyOrderStatus.h"

// Implementation of GenerateCancelOrderStatus method.
CancelOrderStatus OrderStatusCreator::GenerateCancelOrderStatus(const CancelOrder& co) {
    // Create and return a new CancelOrderStatus object.
    return CancelOrderStatus();
}

// Implementation of GenerateNewOrderStatus method.
NewOrderStatus OrderStatusCreator::GenerateNewOrderStatus(const Order& order) {
    // Create and return a new NewOrderStatus object.
    return NewOrderStatus();
}

// Implementation of GenerateModifyOrderStatus method.
ModifyOrderStatus OrderStatusCreator::GenerateModifyOrderStatus(const ModifyOrder& modifyOrder) {
    // Create and return a new ModifyOrderStatus object.
    return ModifyOrderStatus();
}
