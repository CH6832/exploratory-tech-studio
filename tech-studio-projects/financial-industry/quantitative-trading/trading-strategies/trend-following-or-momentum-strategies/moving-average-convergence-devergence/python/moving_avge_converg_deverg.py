import numpy as np
import matplotlib.pyplot as plt

# Generate random price data
np.random.seed(0)
n = 100
prices = np.cumsum(np.random.randn(n))

# Calculate MACD
short_window = 12
long_window = 26
signal_window = 9
short_ema = np.convolve(prices, np.ones(short_window)/short_window, mode='valid')
long_ema = np.convolve(prices, np.ones(long_window)/long_window, mode='valid')
macd_line = short_ema - long_ema
signal_line = np.convolve(macd_line, np.ones(signal_window)/signal_window, mode='valid')

# Plot results
plt.figure(figsize=(10, 6))
plt.plot(prices, label='Price')
plt.plot(range(long_window-1, n), macd_line, label='MACD Line')
plt.plot(range(long_window-1, n), signal_line, label='Signal Line')
plt.legend()
plt.title('MACD (Moving Average Convergence Divergence) Simulation')
plt.xlabel('Time')
plt.ylabel('Value')
plt.show()
