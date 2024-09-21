package com.fintech.algotrading.instrument;

/**
 * Class representing a financial security.
 */
public class Security {

    private final long id;
    private final String name;
    private final String symbol;
    private final String market;

    /**
     * Constructor to initialize a Security object.
     *
     * @param id      The unique identifier of the security.
     * @param name    The name of the security.
     * @param symbol  The symbol or ticker of the security.
     * @param market  The market where the security is traded.
     */
    public Security(long id, String name, String symbol, String market) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.market = market;
    }

    /**
     * Gets the unique identifier of the security.
     *
     * @return The unique identifier.
     */
    public long getId() {
        return id;
    }

    /**
     * Gets the name of the security.
     *
     * @return The name of the security.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the symbol or ticker of the security.
     *
     * @return The symbol of the security.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Gets the market where the security is traded.
     *
     * @return The market of the security.
     */
    public String getMarket() {
        return market;
    }

    @Override
    public String toString() {
        return "Security{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", symbol='" + symbol + '\'' +
               ", market='" + market + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Security security = (Security) o;

        return id == security.id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}
