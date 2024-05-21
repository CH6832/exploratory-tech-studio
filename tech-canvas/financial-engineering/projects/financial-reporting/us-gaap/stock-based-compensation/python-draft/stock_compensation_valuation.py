#!/usr/bin/env python
# -*- coding: utf-8 -*-


"""stock_compensation_valuation.py

This script estimates the fair value of stock options and recognizes compensation expense over the requisite service period,
in accordance with ASC 718 (Compensation—Stock Compensation).
"""


import numpy as np
from scipy.stats import norm


def main() -> None:
    """Driving code"""
    # example usage
    stock_price = 100  # current market price of the underlying stock
    exercise_price = 90  # exercise price of the option
    risk_free_rate = 0.05  # risk-free interest rate
    volatility = 0.2  # volatility of the underlying stock
    time_to_maturity = 1  # time to maturity of the option
    service_period = 4  # requisite service period in years

    # calculate fair value of the stock option using Black-Scholes model
    option_value = calculate_black_scholes_option_value(stock_price, exercise_price, risk_free_rate, volatility, time_to_maturity)
    print("Fair Value of Stock Option:", option_value)

    # recognize compensation expense over the requisite service period
    compensation_expense = recognize_compensation_expense(option_value, service_period)
    print("Compensation Expense Recognized:", compensation_expense)

    return None


def calculate_black_scholes_option_value(stock_price: float, exercise_price: float, risk_free_rate: float,
                                         volatility: float, time_to_maturity: float) -> float:
    """Calculate the fair value of a stock option using the Black-Scholes option pricing model.

    Parameters:
    stock_price (float) -- Current market price of the underlying stock.
    exercise_price (float) -- Exercise price of the option.
    risk_free_rate (float) -- Risk-free interest rate.
    volatility (float) -- Volatility of the underlying stock.
    time_to_maturity (float) -- Time to maturity of the option.

    Returns:
    float -- Fair value of the stock option.
    """
    d1 = (np.log(stock_price / exercise_price) + (risk_free_rate + 0.5 * volatility ** 2) * time_to_maturity) / (volatility * np.sqrt(time_to_maturity))
    d2 = d1 - volatility * np.sqrt(time_to_maturity)
    option_value = stock_price * norm.cdf(d1) - exercise_price * np.exp(-risk_free_rate * time_to_maturity) * norm.cdf(d2)
    
    return option_value


def recognize_compensation_expense(option_value: float, service_period: float) -> float:
    """Recognize compensation expense over the requisite service period.

    Parameters:
    option_value (float) -- Fair value of the stock option.
    service_period (float) -- Requisite service period over which the compensation expense is recognized.

    Returns:
    float -- Compensation expense recognized for the current period.
    """
    compensation_expense = option_value / service_period
    
    return compensation_expense


if __name__ == "__main__":
    main()
