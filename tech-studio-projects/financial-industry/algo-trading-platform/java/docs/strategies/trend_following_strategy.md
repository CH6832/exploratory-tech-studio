# Trend Following (Momentum) Strategy

---

## Overview

**Trend Following** strategies aim to capitalize on sustained price movements in one direction. The premise is that "the trend is your friend," meaning that assets showing momentum will continue moving in the same direction.

### Key Indicators Used

- **Moving Average Crossovers**: Detects changes in trends by crossing of short-term and long-term averages.
- **Relative Strength Index (RSI)**: Measures recent price changes to identify overbought/oversold assets.
- **MACD (Moving Average Convergence Divergence)**: Measures momentum and provides crossover signals.

### Mathematical Background

Trend following relies on **time series momentum** and assumes that prices will move in the direction of the current trend. Moving averages are used to smooth out price data and identify trends.

#### 1. Moving Average Crossover

- **Formulas**:
  - Short-Term EMA = EMA of prices over a short period (e.g., 10 days)
  - Long-Term EMA = EMA of prices over a longer period (e.g., 50 days)

- **Signal**:
  - **Buy** when the short-term EMA crosses above the long-term EMA (upward trend).
  - **Sell** when the short-term EMA crosses below the long-term EMA (downward trend).

#### 2. Relative Strength Index (RSI)

RSI measures the speed and change of recent price movements and is used to detect overbought or oversold conditions.

- **Formula**:
  $RSI = 100 - \frac{100}{1 + RS}$

  Where:
  
  - $(RS = \frac{\text{Average Gain over n periods}}{\text{Average Loss over n periods}})$

- **Signal**:
  - **Buy** if RSI < 30 (indicating oversold).
  - **Sell** if RSI > 70 (indicating overbought).

### Practical Considerations

- **Market Conditions**: Trend following strategies work best in trending markets.
- **Risk Management**: Set stop-loss limits to protect against trend reversals.

---
