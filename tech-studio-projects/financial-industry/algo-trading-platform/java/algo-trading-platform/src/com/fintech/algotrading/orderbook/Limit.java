package com.fintech.algotrading.orderbook;

import java.util.ArrayList;
import java.util.List;
import com.fintech.algotrading.orders.Order;

public class Limit {

    private long price;
    private OrderbookEntry head;
    private OrderbookEntry tail;

    public Limit(double price2) {
    	long d = 0;
		this.price = d;
	}

	public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public OrderbookEntry getHead() {
        return head;
    }

    public void setHead(OrderbookEntry head) {
        this.head = head;
    }

    public OrderbookEntry getTail() {
        return tail;
    }

    public void setTail(OrderbookEntry tail) {
        this.tail = tail;
    }

    public int getLevelOrderCount() {
        int orderCount = 0;
        OrderbookEntry headPointer = head;
        while (headPointer != null) {
            if (headPointer.getOrder().getQuantity() != 0) {
                orderCount++;
            }
            headPointer = headPointer.getNext();
        }
        return orderCount;
    }

    public int getOrderLevelQuantity() {
        int orderQuantity = 0;
        OrderbookEntry headPointer = head;
        while (headPointer != null) {
            orderQuantity += headPointer.getOrder().getQuantity();
            headPointer = headPointer.getNext();
        }
        return orderQuantity;
    }

    public List<OrderRecord> getLevelOrderRecords() {
        List<OrderRecord> orderRecords = new ArrayList<>();
        OrderbookEntry headPointer = head;
        int theoreticalQueuePosition = 0;
        while (headPointer != null) {
            Order currentOrder = headPointer.getOrder();
            if (currentOrder.getQuantity() != 0) {
                orderRecords.add(new OrderRecord(
                    currentOrder.getOrderId(),
                    currentOrder.getQuantity(),
                    price,
                    currentOrder.isBuySide(),
                    currentOrder.getUsername(),
                    currentOrder.getSecurityId(),
                    theoreticalQueuePosition
                ));
            }
            theoreticalQueuePosition++;
            headPointer = headPointer.getNext();
        }
        return orderRecords;
    }

    public boolean isEmpty() {
        return head == null && tail == null;
    }

    public Side getSide() {
        if (isEmpty()) {
            return Side.EMPTY;
        } else {
            return head.getOrder().isBuySide() ? Side.BID : Side.ASK;
        }
    }
}

