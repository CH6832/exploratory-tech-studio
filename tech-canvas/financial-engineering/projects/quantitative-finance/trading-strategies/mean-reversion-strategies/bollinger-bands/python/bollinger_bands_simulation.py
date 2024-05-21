import numpy as np
import matplotlib.pyplot as plt

# Generate random price data
np.random.seed(0)
n = 100
prices = np.cumsum(np.random.randn(n))

# Calculate moving average and standard deviation
window_size = 20
rolling_mean = np.convolve(prices, np.ones(window_size)/window_size, mode='valid')
rolling_std = np.std(prices[:window_size-1])

# Calculate upper and lower Bollinger Bands
upper_band = rolling_mean + 2 * rolling_std
lower_band = rolling_mean - 2 * rolling_std

# Plot results
plt.figure(figsize=(10, 6))
plt.plot(prices, label='Price')
plt.plot(rolling_mean, label='Rolling Mean')
plt.plot(upper_band, label='Upper Bollinger Band')
plt.plot(lower_band, label='Lower Bollinger Band')
plt.fill_between(range(window_size-1, n), upper_band, lower_band, color='gray', alpha=0.3)
plt.legend()
plt.title('Bollinger Bands Simulation')
plt.xlabel('Time')
plt.ylabel('Price')
plt.show()
