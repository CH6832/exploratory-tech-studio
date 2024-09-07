#ifndef MODIFY_ORDER_H
#define MODIFY_ORDER_H

#include <memory>
#include "IOrderCore.h"
#include "Order.h"
#include "CancelOrder.h"

// The ModifyOrder class represents a request to modify an existing order.
class ModifyOrder : public IOrderCore {
public:
    // Constructor to initialize the ModifyOrder with core order data, modification price, quantity, and buy/sell side.
    // @param orderCore: The core data of the order being modified.
    // @param modifyPrice: The new price of the order.
    // @param modifyQuantity: The new quantity of the order.
    // @param isBuySide: True if the order is on the buy side; otherwise, false.
    ModifyOrder(std::shared_ptr<IOrderCore> orderCore, long modifyPrice, unsigned int modifyQuantity, bool isBuySide);

    // Getter for the modification price.
    // @return The new price of the order.
    long GetPrice() const;

    // Getter for the modification quantity.
    // @return The new quantity of the order.
    unsigned int GetQuantity() const;

    // Getter for the buy/sell side.
    // @return True if the order is on the buy side; otherwise, false.
    bool IsBuySide() const;

    // Convert this modify order to a cancel order.
    // @return A new CancelOrder representing the cancellation of this order.
    std::shared_ptr<CancelOrder> ToCancelOrder() const;

    // Convert this modify order to a new order.
    // @return A new Order representing the updated order.
    std::shared_ptr<Order> ToNewOrder() const;

    // Getters from IOrderCore.
    // @return The order ID from the core order data.
    long GetOrderId() const override;

    // @return The username from the core order data.
    long GetUsername() const override;

    // @return The security ID from the core order data.
    long GetSecurityId() const override;

private:
    // The core data of the order.
    std::shared_ptr<IOrderCore> _orderCore;

    // The new price of the order.
    long _price;

    // The new quantity of the order.
    unsigned int _quantity;

    // Indicates if the order is on the buy side.
    bool _isBuySide;
};

#endif // MODIFY_ORDER_H
