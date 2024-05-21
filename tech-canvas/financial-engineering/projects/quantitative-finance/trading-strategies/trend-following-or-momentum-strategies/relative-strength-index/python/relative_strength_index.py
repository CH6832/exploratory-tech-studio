import numpy as np
import matplotlib.pyplot as plt

# Generate random price data
np.random.seed(0)
n = 100
prices = np.cumsum(np.random.randn(n))

# Calculate RSI
window = 14
delta = np.diff(prices)
gain = np.where(delta > 0, delta, 0)
loss = np.where(delta < 0, -delta, 0)
avg_gain = np.mean(gain[:window])
avg_loss = np.mean(loss[:window])
rs = avg_gain / avg_loss
rsi = 100 - (100 / (1 + rs))

# Plot results
plt.figure(figsize=(10, 6))
plt.plot(prices, label='Price')
plt.plot(range(1, n), rsi, label='RSI')
plt.axhline(y=70, color='r', linestyle='--', label='Overbought (70)')
plt.axhline(y=30, color='g', linestyle='--', label='Oversold (30)')
plt.legend()
plt.title('Relative Strength Index (RSI) Simulation')
plt.xlabel('Time')
plt.ylabel('Value')
plt.show()
