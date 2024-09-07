#ifndef ORDER_CORE_H
#define ORDER_CORE_H

#include <string>
#include "IOrderCore.h"

// The OrderCore class implements the IOrderCore interface.
class OrderCore : public IOrderCore {
public:
    // Constructor to initialize the OrderCore object.
    // @param orderId: The ID of the order.
    // @param username: The username associated with the order.
    // @param securityId: The security ID for the order.
    OrderCore(long orderId, const std::string& username, int securityId);

    // Getter for the Order ID.
    // @return The Order ID.
    long GetOrderId() const;

    // Getter for the Username.
    // @return The Username.
    std::string GetUsername() const;

    // Getter for the Security ID.
    // @return The Security ID.
    int GetSecurityId() const;

private:
    // The ID of the order.
    long OrderId;

    // The username associated with the order.
    std::string Username;

    // The security ID for the order.
    int SecurityId;
};

#endif // ORDER_CORE_H
