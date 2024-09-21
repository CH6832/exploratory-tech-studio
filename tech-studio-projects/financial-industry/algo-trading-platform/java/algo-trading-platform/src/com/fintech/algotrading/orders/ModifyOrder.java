package com.fintech.algotrading.orders;

import java.util.Optional;

//Class representing a modified order.
public class ModifyOrder {
 private final IOrderCore orderCore;
 private final long price;
 private final int quantity;
 private final boolean isBuySide;

 // Constructor to initialize the ModifyOrder with core order data, modification price, quantity, and buy/sell side.
 public ModifyOrder(IOrderCore orderCore, long price, int quantity, boolean isBuySide) {
     this.orderCore = orderCore;
     this.price = price;
     this.quantity = quantity;
     this.isBuySide = isBuySide;
 }

 // Getter for the modification price.
 public long getPrice() {
     return price;
 }

 // Getter for the modification quantity.
 public int getQuantity() {
     return quantity;
 }

 // Getter for the buy/sell side.
 public boolean isBuySide() {
     return isBuySide;
 }

 // Convert this modify order to a cancel order.
 public CancelOrder toCancelOrder() {
     return new CancelOrder();
 }

 // Convert this modify order to a new order.
 public Order toNewOrder() {
     return new Order(price, null, price, quantity, isBuySide);
 }

 // Getters from IOrderCore.
 public long getOrderId() {
     return orderCore.getOrderId();
 }

 public long getUsername() {
     return orderCore.getUsername();
 }

 public long getSecurityId() {
     return orderCore.getSecurityId();
 }
}
