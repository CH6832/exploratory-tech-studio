import gym
from gym import spaces
import numpy as np

class HVACEnv(gym.Env):
    def __init__(self, data, target):
        super(HVACEnv, self).__init__()
        
        self.data = data
        self.target = target
        self.current_step = 0
        self.action_space = spaces.Box(low=15, high=30, shape=(1,), dtype=np.float32)
        self.observation_space = spaces.Box(low=-np.inf, high=np.inf, shape=(data.shape[1],), dtype=np.float32)

    def reset(self):
        self.current_step = 0
        return self.data.iloc[self.current_step].values

    def step(self, action):
        current_state = self.data.iloc[self.current_step]
        current_consumption = self.target[self.current_step]

        # Simulate the effect of the action (temperature setpoint adjustment)
        energy_consumption = self.simulate_energy_consumption(action, current_state)

        # Calculate reward (negative energy consumption)
        reward = -energy_consumption

        self.current_step += 1
        done = self.current_step >= len(self.data) - 1
        next_state = self.data.iloc[self.current_step].values if not done else None

        return next_state, reward, done, {}

    def simulate_energy_consumption(self, setpoint, state):
        # Simplified simulation function
        return np.abs(setpoint - state['air_temperature']) * 10 + state['wind_speed']
