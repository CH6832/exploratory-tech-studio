#include "OrderCore.h"

// Constructor implementation.
OrderCore::OrderCore(long orderId, const std::string& username, int securityId)
    : OrderId(orderId), Username(username), SecurityId(securityId) {
    // Initialization is handled in the member initializer list.
}

// Getter for the Order ID.
long OrderCore::GetOrderId() const {
    return OrderId;
}

// Getter for the Username.
std::string OrderCore::GetUsername() const {
    return Username;
}

// Getter for the Security ID.
int OrderCore::GetSecurityId() const {
    return SecurityId;
}
