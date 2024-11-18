# Mean Reversion Strategy

---

## Overview

The **Mean Reversion** strategy is based on the assumption that asset prices will revert to their historical mean over time. This strategy aims to identify assets that have deviated significantly from their average and then open positions to profit as they return to this mean level.

### Key Indicators Used

- **Bollinger Bands**: Indicate overbought and oversold conditions based on standard deviations from a moving average.
- **Relative Strength Index (RSI)**: Measures momentum and signals overbought/oversold conditions.
- **Moving Average (MA)**: The historical average price over a specified period.

### Mathematical Background

The mathematical principle behind mean reversion is based on **statistical regression** toward the mean. Here, the deviation of a price from its average can be quantified and used to trigger buy or sell signals.

#### 1. Bollinger Bands

- **Formula**:
  - Upper Band = MA + (SD * n)
  - Lower Band = MA - (SD * n)
  
  Where:
  - **MA** = Moving Average (usually a 20-day SMA)
  - **SD** = Standard deviation of price over the same period
  - **n** = Number of standard deviations (commonly 2)

- **Signal**:
  - **Buy** when the price crosses below the lower Bollinger Band.
  - **Sell** when the price crosses above the upper Bollinger Band.

#### 2. Z-Score Calculation

A **Z-score** measures the number of standard deviations a data point is from the mean.

- **Formula**:
  $Z = \frac{X - \mu}{\sigma}$
  
  Where:
  - $(X)$ = Current price
  - $(\mu)$ = Mean of the historical prices
  - $(\sigma)$ = Standard deviation of historical prices

- **Signal**:
  - **Buy** if Z < -2 (indicating the price is 2 SDs below the mean).
  - **Sell** if Z > 2.

### Practical Considerations

- **Market Conditions**: This strategy performs best in stable, range-bound markets.
- **Risk Management**: Mean reversion strategies can suffer in trending markets; a tight stop-loss is recommended.

---
