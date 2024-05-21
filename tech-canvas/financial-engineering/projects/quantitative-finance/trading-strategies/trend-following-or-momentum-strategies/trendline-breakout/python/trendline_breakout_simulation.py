import numpy as np
import matplotlib.pyplot as plt

# Generate random price data
np.random.seed(0)
n = 100
prices = np.cumsum(np.random.randn(n))

# Calculate trendline using linear regression
trendline = np.polyfit(range(n), prices, 1)
regression_line = np.polyval(trendline, range(n))

# Identify breakout points
breakout_points = np.where(prices > regression_line)[0]

# Plot results
plt.figure(figsize=(10, 6))
plt.plot(prices, label='Price')
plt.plot(regression_line, label='Trendline', linestyle='--')
plt.scatter(breakout_points, prices[breakout_points], color='green', marker='^', label='Breakout Point')
plt.legend()
plt.title('Trendline Breakout Simulation')
plt.xlabel('Time')
plt.ylabel('Price')
plt.show()
