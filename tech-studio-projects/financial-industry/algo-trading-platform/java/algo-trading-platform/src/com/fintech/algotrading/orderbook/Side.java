package com.fintech.algotrading.orderbook;

public class Side {

    // Enum to represent the possible sides of an order book
    public enum Type {
    	EMPTY,
        BID,  // Represents the buy side of the order book
        ASK   // Represents the sell side of the order book
    }

	public static final Side BID = null;

	public static final Side ASK = null;

	public static final Side EMPTY = null;

    private final Type sideType;

    // Constructor to initialize the Side object
    public Side(Type sideType) {
        this.sideType = sideType;
    }

    // Getter to return the side type (BID or ASK)
    public Type getSideType() {
        return sideType;
    }

    // Method to check if this side is the bid side
    public boolean isBid() {
        return sideType == Type.BID;
    }

    // Method to check if this side is the ask side
    public boolean isAsk() {
        return sideType == Type.ASK;
    }

    // Method to get a string representation of the side
    @Override
    public String toString() {
        return sideType.name();
    }

    // Equals and hashCode methods to compare Side objects
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Side side = (Side) o;

        return sideType == side.sideType;
    }

    @Override
    public int hashCode() {
        return sideType != null ? sideType.hashCode() : 0;
    }
}
