#!/usr/bin/env python3
# -*- coding: utf-8 -*-


"""portfolio_optimization.py"""


import numpy as np
from scipy.optimize import minimize


def main() -> None:
    """Driving code."""
    # Example usage:
    returns = np.array([0.10, 0.08, 0.12])
    cov_matrix = np.array([[0.005, -0.010, 0.004],
                        [-0.010, 0.040, -0.002],
                        [0.004, -0.002, 0.023]])
    risk_free_rate = 0.03
    optimized_weights = optimize_portfolio(returns, cov_matrix, risk_free_rate)
    print("Optimized Portfolio Weights:", optimized_weights)

    return None


def calculate_portfolio_return(weights, returns):
    """Calculate the return value of a portfolio."""

    return np.sum(weights * returns)


def calculate_portfolio_volatility(weights, cov_matrix):
    """Calculate the volatility of a portfolio."""

    return np.sqrt(np.dot(weights.T, np.dot(cov_matrix, weights)))


def calculate_sharpe_ratio(weights, returns, cov_matrix, risk_free_rate):
    """Calculate the sharp ratio."""
    portfolio_return = calculate_portfolio_return(weights, returns)
    portfolio_volatility = calculate_portfolio_volatility(weights, cov_matrix)
    
    return (portfolio_return - risk_free_rate) / portfolio_volatility


def optimize_portfolio(returns, cov_matrix, risk_free_rate):
    """Perform portfolio optimization."""    
    num_assets = len(returns)
    initial_weights = np.ones(num_assets) / num_assets
    bounds = tuple((0, 1) for _ in range(num_assets))
    constraints = ({'type': 'eq', 'fun': lambda weights: np.sum(weights) - 1})
    result = minimize(lambda weights: -calculate_sharpe_ratio(weights, returns, cov_matrix, risk_free_rate),
                      initial_weights, method='SLSQP', bounds=bounds, constraints=constraints)
    
    return result.x


if __name__ == "__main__":
    main()
