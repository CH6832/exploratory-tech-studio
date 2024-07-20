import numpy as np
import matplotlib.pyplot as plt

# Generate random price data
np.random.seed(0)
n = 100
prices = np.cumsum(np.random.randn(n))

# Calculate mean and standard deviation
window_size = 20
rolling_mean = np.convolve(prices, np.ones(window_size)/window_size, mode='valid')
rolling_std = np.std(prices[:window_size-1])

# Define channels
upper_channel = rolling_mean + rolling_std
lower_channel = rolling_mean - rolling_std

# Generate signals
buy_signal = (prices < lower_channel).astype(int)
sell_signal = (prices > upper_channel).astype(int)

# Plot results
plt.figure(figsize=(10, 6))
plt.plot(prices, label='Price')
plt.plot(rolling_mean, label='Rolling Mean')
plt.plot(upper_channel, label='Upper Channel', linestyle='--')
plt.plot(lower_channel, label='Lower Channel', linestyle='--')
plt.scatter(np.where(buy_signal == 1), prices[buy_signal == 1], color='green', marker='^', label='Buy Signal')
plt.scatter(np.where(sell_signal == 1), prices[sell_signal == 1], color='red', marker='v', label='Sell Signal')
plt.legend()
plt.title('Mean-Reversion Channel Strategy Simulation')
plt.xlabel('Time')
plt.ylabel('Price')
plt.show()
