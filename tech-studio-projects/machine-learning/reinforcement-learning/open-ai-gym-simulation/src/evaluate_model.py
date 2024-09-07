import pandas as pd
from stable_baselines3 import PPO
from src.hvac_env import HVACEnv

# Load processed data
features = pd.read_csv('data/processed/features.csv')
target = pd.read_csv('data/processed/target.csv')

# Create environment
env = HVACEnv(features, target)

# Load the trained PPO model
model = PPO.load("models/ppo_hvac")

# Test the trained agent
state = env.reset()
done = False
total_reward = 0

while not done:
    action, _states = model.predict(state)
    state, reward, done, _ = env.step(action)
    total_reward += reward

print("Total reward (negative energy consumption):", total_reward)
