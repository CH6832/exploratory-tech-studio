#include "ModifyOrder.h"

// Constructor to initialize the ModifyOrder with core order data, modification price, quantity, and buy/sell side.
ModifyOrder::ModifyOrder(std::shared_ptr<IOrderCore> orderCore, long modifyPrice, unsigned int modifyQuantity, bool isBuySide)
    : _orderCore(orderCore), _price(modifyPrice), _quantity(modifyQuantity), _isBuySide(isBuySide) {
}

// Getter for the modification price.
long ModifyOrder::GetPrice() const {
    return _price;
}

// Getter for the modification quantity.
unsigned int ModifyOrder::GetQuantity() const {
    return _quantity;
}

// Getter for the buy/sell side.
bool ModifyOrder::IsBuySide() const {
    return _isBuySide;
}

// Convert this modify order to a cancel order.
std::shared_ptr<CancelOrder> ModifyOrder::ToCancelOrder() const {
    return std::make_shared<CancelOrder>(*this);
}

// Convert this modify order to a new order.
std::shared_ptr<Order> ModifyOrder::ToNewOrder() const {
    return std::make_shared<Order>(*this);
}

// Getters from IOrderCore.
long ModifyOrder::GetOrderId() const {
    return _orderCore->GetOrderId();
}

long ModifyOrder::GetUsername() const {
    return _orderCore->GetUsername();
}

long ModifyOrder::GetSecurityId() const {
    return _orderCore->GetSecurityId();
}
