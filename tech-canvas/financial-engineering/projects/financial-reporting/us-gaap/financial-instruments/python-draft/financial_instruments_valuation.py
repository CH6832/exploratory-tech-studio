#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
financial_instruments_valuation.py

This script demonstrates valuation of financial instruments such as derivatives, debt securities, and equity securities
under standards like ASC 815 (Derivatives and Hedging), ASC 320 (Investments—Debt and Equity Securities),
and ASC 825 (Financial Instruments).
"""

from typing import List, Tuple

def main():
    # Example usage
    notional_amount_derivative = 1000000
    risk_factor = 50
    volatility = 0.2
    time_to_maturity = 1

    face_value_debt_security = 1000000
    market_interest_rate = 0.05
    remaining_time_to_maturity_debt_security = 1

    current_price_equity_security = 50
    number_of_shares_equity_security = 20000

    # Calculate fair value of derivative
    derivative_value = calculate_derivative_value(notional_amount_derivative, risk_factor, volatility, time_to_maturity)
    print("Fair Value of Derivative:", derivative_value)

    # Calculate fair value of debt security
    debt_security_value = calculate_debt_securities_value(face_value_debt_security, market_interest_rate, remaining_time_to_maturity_debt_security)
    print("Fair Value of Debt Security:", debt_security_value)

    # Calculate fair value of equity security
    equity_security_value = calculate_equity_securities_value(current_price_equity_security, number_of_shares_equity_security)
    print("Fair Value of Equity Security:", equity_security_value)


def calculate_derivative_value(notional_amount: float, risk_factor: float, volatility: float, time_to_maturity: float) -> float:
    """
    Calculate the fair value of a derivative using the Black-Scholes model.

    Args:
    notional_amount (float): Notional amount of the derivative.
    risk_factor (float): Underlying asset price or rate.
    volatility (float): Volatility of the underlying asset.
    time_to_maturity (float): Time to maturity of the derivative.

    Returns:
    float: Fair value of the derivative.
    """
    d1 = (risk_factor / (volatility * (time_to_maturity ** 0.5)))
    d2 = d1 - volatility * (time_to_maturity ** 0.5)
    fair_value = notional_amount * (risk_factor * norm.cdf(d1) - norm.cdf(d2))
    return fair_value

def calculate_debt_securities_value(face_value: float, market_interest_rate: float, remaining_time_to_maturity: float) -> float:
    """
    Calculate the fair value of debt securities using the present value of future cash flows.

    Args:
    face_value (float): Face value of the debt security.
    market_interest_rate (float): Market interest rate applicable to the debt security.
    remaining_time_to_maturity (float): Remaining time to maturity of the debt security.

    Returns:
    float: Fair value of the debt security.
    """
    fair_value = face_value / ((1 + market_interest_rate) ** remaining_time_to_maturity)
    return fair_value

def calculate_equity_securities_value(current_price: float, number_of_shares: int) -> float:
    """
    Calculate the fair value of equity securities.

    Args:
    current_price (float): Current market price of the equity security.
    number_of_shares (int): Number of shares of the equity security.

    Returns:
    float: Fair value of the equity security.
    """
    fair_value = current_price * number_of_shares
    return fair_value

if __name__ == "__main__":
    main()
