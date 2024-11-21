# Arbitrage Strategy

---

## Overview

An **Arbitrage** strategy exploits price discrepancies between two markets or related assets to make a risk-free profit. Common types of arbitrage include **statistical arbitrage** and **pairs trading**.

### Key Indicators Used

- **Price Correlation**: Measures the degree to which two assets move together.
- **Spread Calculation**: Calculates the difference between the prices of two assets.

### Mathematical Background

Arbitrage relies on identifying **statistical mispricing** between correlated assets and capitalizing on temporary deviations. The most common approach uses **pairs trading** to hedge positions.

#### 1. Pairs Trading Spread Calculation

If two assets (A) and (B) are highly correlated, their price spread can be used to identify trade signals.

- **Formula**:
  $\text{Spread} = P_A - P_B$
  
  Where:
  - $(P_A) = Price of asset A$
  - $(P_B) = Price of asset B$

- **Signal**:
  - **Buy** asset A and **sell** asset B if the spread widens beyond historical average (expecting reversion).
  - **Sell** asset A and **buy** asset B if the spread tightens (expecting divergence).

#### 2. Cointegration Test (Advanced)

Cointegration testing checks if a stable, long-term relationship exists between two assets, a necessary condition for arbitrage.

- **Formula**:
  - Cointegration score (e.g., using Engle-Granger test).

### Practical Considerations

- **Market Conditions**: Arbitrage is best in markets with low volatility and high liquidity.
- **Execution Speed**: Speed of execution is critical, especially in high-frequency environments.
- **Risk Management**: While arbitrage is considered low-risk, mispricing may persist longer than expected; tight limits are recommended.

---
