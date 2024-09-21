import gym
from stable_baselines3 import PPO
import pandas as pd
from src.hvac_env import HVACEnv

# Load processed data
features = pd.read_csv('data/processed/features.csv')
target = pd.read_csv('data/processed/target.csv')

# Create environment
env = HVACEnv(features, target)

# Instantiate the PPO agent
model = PPO('MlpPolicy', env, verbose=1)

# Train the PPO agent
model.learn(total_timesteps=10000)

# Save the trained model
model.save("models/ppo_hvac")
