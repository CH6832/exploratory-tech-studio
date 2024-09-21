package com.fintech.algotrading.instrument;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SecurityTest {

    @Test
    public void testSecurityConstructor() {
        Security security = new Security(1L, "Apple Inc.", "AAPL", "NASDAQ");
        assertNotNull(security, "Security object should be instantiated successfully");
        assertEquals(1L, security.getId(), "ID should match the provided value");
        assertEquals("Apple Inc.", security.getName(), "Name should match the provided value");
        assertEquals("AAPL", security.getSymbol(), "Symbol should match the provided value");
        assertEquals("NASDAQ", security.getMarket(), "Market should match the provided value");
    }

    @Test
    public void testGetId() {
        Security security = new Security(2L, "Microsoft Corp.", "MSFT", "NASDAQ");
        assertEquals(2L, security.getId(), "GetId should return the correct ID");
    }

    @Test
    public void testGetName() {
        Security security = new Security(3L, "Alphabet Inc.", "GOOGL", "NASDAQ");
        assertEquals("Alphabet Inc.", security.getName(), "GetName should return the correct name");
    }

    @Test
    public void testGetSymbol() {
        Security security = new Security(4L, "Amazon.com Inc.", "AMZN", "NASDAQ");
        assertEquals("AMZN", security.getSymbol(), "GetSymbol should return the correct symbol");
    }

    @Test
    public void testGetMarket() {
        Security security = new Security(5L, "Tesla Inc.", "TSLA", "NASDAQ");
        assertEquals("NASDAQ", security.getMarket(), "GetMarket should return the correct market");
    }

    @Test
    public void testToString() {
        Security security = new Security(6L, "Netflix Inc.", "NFLX", "NASDAQ");
        String expected = "Security{id=6, name='Netflix Inc.', symbol='NFLX', market='NASDAQ'}";
        assertEquals(expected, security.toString(), "ToString should return the correct string representation");
    }

    @Test
    public void testEqualsSameObject() {
        Security security = new Security(7L, "Facebook Inc.", "FB", "NASDAQ");
        assertTrue(security.equals(security), "Equals should return true when comparing the same object");
    }

    @Test
    public void testEqualsDifferentObjectSameId() {
        Security security1 = new Security(8L, "Uber Technologies", "UBER", "NYSE");
        Security security2 = new Security(8L, "Lyft Inc.", "LYFT", "NASDAQ");
        assertTrue(security1.equals(security2), "Equals should return true when comparing two objects with the same ID");
    }

    @Test
    public void testEqualsDifferentObjectDifferentId() {
        Security security1 = new Security(9L, "Twitter Inc.", "TWTR", "NYSE");
        Security security2 = new Security(10L, "Snap Inc.", "SNAP", "NYSE");
        assertFalse(security1.equals(security2), "Equals should return false when comparing two objects with different IDs");
    }

    @Test
    public void testEqualsNull() {
        Security security = new Security(11L, "Square Inc.", "SQ", "NYSE");
        assertFalse(security.equals(null), "Equals should return false when comparing with null");
    }

    @Test
    public void testEqualsDifferentClass() {
        Security security = new Security(12L, "PayPal Holdings", "PYPL", "NASDAQ");
        String differentClassObject = "This is a string";
        assertFalse(security.equals(differentClassObject), "Equals should return false when comparing with an object of a different class");
    }

    @Test
    public void testHashCodeConsistency() {
        Security security = new Security(13L, "Nvidia Corporation", "NVDA", "NASDAQ");
        int initialHashCode = security.hashCode();
        assertEquals(initialHashCode, security.hashCode(), "HashCode should be consistent when called multiple times");
    }

    @Test
    public void testHashCodeEquality() {
        Security security1 = new Security(14L, "Intel Corporation", "INTC", "NASDAQ");
        Security security2 = new Security(14L, "Advanced Micro Devices", "AMD", "NASDAQ");
        assertEquals(security1.hashCode(), security2.hashCode(), "HashCode should be the same for objects with the same ID");
    }

    @Test
    public void testHashCodeInequality() {
        Security security1 = new Security(15L, "Alibaba Group", "BABA", "NYSE");
        Security security2 = new Security(16L, "JD.com Inc.", "JD", "NASDAQ");
        assertNotEquals(security1.hashCode(), security2.hashCode(), "HashCode should be different for objects with different IDs");
    }
}
