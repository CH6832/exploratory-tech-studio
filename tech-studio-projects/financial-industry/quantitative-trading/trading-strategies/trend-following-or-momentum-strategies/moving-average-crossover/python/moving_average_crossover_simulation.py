import numpy as np
import matplotlib.pyplot as plt

# Generate random price data
np.random.seed(0)
n = 100
prices = np.cumsum(np.random.randn(n))

# Calculate moving averages
short_window = 10
long_window = 30
short_ma = np.convolve(prices, np.ones(short_window)/short_window, mode='valid')
long_ma = np.convolve(prices, np.ones(long_window)/long_window, mode='valid')

# Generate signals
buy_signal = (short_ma > long_ma).astype(int)
sell_signal = (short_ma < long_ma).astype(int)

# Plot results
plt.figure(figsize=(10, 6))
plt.plot(prices, label='Price')
plt.plot(short_ma, label=f'{short_window}-Day Moving Average')
plt.plot(long_ma, label=f'{long_window}-Day Moving Average')
plt.scatter(np.where(buy_signal == 1), prices[short_window-1:][buy_signal == 1], color='green', marker='^', label='Buy Signal')
plt.scatter(np.where(sell_signal == 1), prices[short_window-1:][sell_signal == 1], color='red', marker='v', label='Sell Signal')
plt.legend()
plt.title('Moving Average Crossover Simulation')
plt.xlabel('Time')
plt.ylabel('Price')
plt.show()
