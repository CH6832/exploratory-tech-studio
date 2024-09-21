package com.fintech.algotrading.orderbook;

import java.util.*;

import com.fintech.algotrading.instrument.Security;
import com.fintech.algotrading.orders.CancelOrder;
import com.fintech.algotrading.orders.ModifyOrder;
import com.fintech.algotrading.orders.Order;

public class Orderbook {

    private final Security instrument;
    private final Set<Limit> askLimits = new TreeSet<>(Comparator.comparing(Limit::getPrice));
    private final Set<Limit> bidLimits = new TreeSet<>(Comparator.comparing(Limit::getPrice).reversed());
    private final Map<Long, OrderbookEntry> orders = new HashMap<>();

    // Constructor
    public Orderbook(Security instrument) {
        this.instrument = instrument;
    }

    // Getter for the number of orders
    public int getCount() {
        return orders.size();
    }

    // Add an order to the order book
    public void addOrder(Order order) {
        Limit baseLimit = new Limit(order.getPrice());
        addOrderToLimit(order, baseLimit, order.isBuySide() ? bidLimits : askLimits, orders);
    }

    // Static method to add an order to a limit level
    private static void addOrderToLimit(Order order, Limit baseLimit,
                                        Set<Limit> limitLevels, Map<Long, OrderbookEntry> internalBook) {
        Optional<Limit> existingLimit = limitLevels.stream().filter(l -> l.equals(baseLimit)).findFirst();
        Limit limit = existingLimit.orElse(baseLimit);

        if (!existingLimit.isPresent()) {
            limitLevels.add(baseLimit);
        }

        OrderbookEntry orderbookEntry = new OrderbookEntry();
        if (limit.getHead() == null) {
            limit.setHead(orderbookEntry);
            limit.setTail(orderbookEntry);
        } else {
            OrderbookEntry tailPointer = limit.getTail();
            tailPointer.setNext(orderbookEntry);
            orderbookEntry.setPrevious(tailPointer);
            limit.setTail(orderbookEntry);
        }
        internalBook.put(order.getOrderId(), orderbookEntry);
    }

    // Change an existing order
    public void changeOrder(ModifyOrder modifyOrder) {
        OrderbookEntry orderbookEntry = orders.get(modifyOrder.getOrderId());
        if (orderbookEntry != null) {
            removeOrderFromLimit(modifyOrder.toCancelOrder(), orderbookEntry, orders);
            addOrder(modifyOrder.toNewOrder());
        }
    }

    // Check if an order exists
    public boolean containsOrder(long orderId) {
        return orders.containsKey(orderId);
    }

    // Get all bid orders
    public List<OrderbookEntry> getBidOrders() {
        List<OrderbookEntry> orderbookEntries = new ArrayList<>();
        for (Limit bidLimit : bidLimits) {
            if (bidLimit.isEmpty()) continue;
            OrderbookEntry entry = bidLimit.getHead();
            while (entry != null) {
                orderbookEntries.add(entry);
                entry = entry.getNext();
            }
        }
        return orderbookEntries;
    }

    // Get all ask orders
    public List<OrderbookEntry> getAskOrders() {
        List<OrderbookEntry> orderbookEntries = new ArrayList<>();
        for (Limit askLimit : askLimits) {
            if (askLimit.isEmpty()) continue;
            OrderbookEntry entry = askLimit.getHead();
            while (entry != null) {
                orderbookEntries.add(entry);
                entry = entry.getNext();
            }
        }
        return orderbookEntries;
    }

    // Get the current spread
    public OrderbookSpread getSpread() {
        Limit bestAsk = askLimits.stream().filter(l -> !l.isEmpty()).findFirst().orElse(null);
        Limit bestBid = bidLimits.stream().filter(l -> !l.isEmpty()).findFirst().orElse(null);

        Long bestAskPrice = bestAsk != null ? bestAsk.getPrice() : null;
        Long bestBidPrice = bestBid != null ? bestBid.getPrice() : null;

        return new OrderbookSpread(Optional.ofNullable(bestBidPrice), Optional.ofNullable(bestAskPrice));
    }

    // Remove an order from the order book
    public void removeOrder(CancelOrder cancelOrder) {
        OrderbookEntry orderbookEntry = orders.get(cancelOrder.getOrderId());
        if (orderbookEntry != null) {
            removeOrderFromLimit(cancelOrder, orderbookEntry, orders);
        }
    }

    // Static method to remove an order from a limit level
    private static void removeOrderFromLimit(CancelOrder cancelOrder, OrderbookEntry orderbookEntry,
                                             Map<Long, OrderbookEntry> internalBook) {
        Limit limit = orderbookEntry.getParentLimit();
        if (orderbookEntry.getPrevious() != null && orderbookEntry.getNext() != null) {
            orderbookEntry.getNext().setPrevious(orderbookEntry.getPrevious());
            orderbookEntry.getPrevious().setNext(orderbookEntry.getNext());
        } else if (orderbookEntry.getPrevious() != null) {
            orderbookEntry.getPrevious().setNext(null);
        } else if (orderbookEntry.getNext() != null) {
            orderbookEntry.getNext().setPrevious(null);
        }

        if (limit.getHead() == orderbookEntry && limit.getTail() == orderbookEntry) {
            limit.setHead(null);
            limit.setTail(null);
        } else if (limit.getHead() == orderbookEntry) {
            limit.setHead(orderbookEntry.getNext());
        } else if (limit.getTail() == orderbookEntry) {
            limit.setTail(orderbookEntry.getPrevious());
        }

        internalBook.remove(cancelOrder.getOrderId());
    }
}
