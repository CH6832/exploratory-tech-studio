import numpy as np
import matplotlib.pyplot as plt

# Generate random price data
np.random.seed(0)
n = 100
prices = np.cumsum(np.random.randn(n))

# Define parameters
window = 14

# Calculate Stochastic Oscillator
low_min = np.minimum.accumulate(prices)
high_max = np.maximum.accumulate(prices)
stochastic_oscillator = 100 * (prices - low_min) / (high_max - low_min)

# Plot results
plt.figure(figsize=(10, 6))
plt.plot(prices, label='Price')
plt.plot(range(window-1, n), stochastic_oscillator, label='Stochastic Oscillator')
plt.axhline(y=80, color='r', linestyle='--', label='Overbought (80)')
plt.axhline(y=20, color='g', linestyle='--', label='Oversold (20)')
plt.legend()
plt.title('Stochastic Oscillator Simulation')
plt.xlabel('Time')
plt.ylabel('Value')
plt.show()
