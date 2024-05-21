import numpy as np
import matplotlib.pyplot as plt

# Generate correlated random price data
np.random.seed(0)
n = 100
price_A = np.cumsum(np.random.randn(n))
price_B = price_A + np.random.randn(n)

# Calculate spread between prices
spread = price_A - price_B

# Define threshold for trading
entry_threshold = 1.5
exit_threshold = 0.5

# Initialize positions
positions = np.zeros(n)

# Pairs trading strategy
for i in range(1, n):
    if spread[i] > entry_threshold:
        positions[i] = -1  # Sell A, buy B
    elif spread[i] < -entry_threshold:
        positions[i] = 1  # Buy A, sell B
    elif abs(spread[i]) < exit_threshold:
        positions[i] = 0  # Exit positions

# Plot results
plt.figure(figsize=(10, 6))
plt.plot(price_A, label='Asset A')
plt.plot(price_B, label='Asset B')
plt.scatter(np.where(positions == -1), price_A[positions == -1], color='red', marker='v', label='Sell A, Buy B')
plt.scatter(np.where(positions == 1), price_A[positions == 1], color='green', marker='^', label='Buy A, Sell B')
plt.legend()
plt.title('Pairs Trading Simulation')
plt.xlabel('Time')
plt.ylabel('Price')
plt.show()
