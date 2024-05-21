#!/usr/bin/env python
# -*- coding: utf-8 -*-


"""fair_value_calculation.py

This script calculates the fair value of assets and liabilities using various valuation techniques,
including the market approach, income approach, and cost approach, in accordance with ASC 820 (Fair Value Measurement).
"""


from typing import List


def main():
    """Driving code."""
    # Example usage
    comparable_prices = [1000, 1200, 1100, 1050, 1150]
    discounted_cash_flows = [500, 600, 700, 800, 900]
    replacement_cost = 2000
    depreciation = 0.1
    discount_rate = 0.05

    market_fair_value = market_approach_valuation(comparable_prices)
    income_fair_value = income_approach_valuation(discounted_cash_flows, discount_rate)
    cost_fair_value = cost_approach_valuation(replacement_cost, depreciation)

    print("Fair Value (Market Approach):", market_fair_value)
    print("Fair Value (Income Approach):", income_fair_value)
    print("Fair Value (Cost Approach):", cost_fair_value)


def market_approach_valuation(comparable_prices: List[float]) -> float:
    """Calculate fair value using the market approach.
    
    Parameters:
    comparable_prices (List[float]): List of comparable prices of similar assets.

    Returns:
    float: Fair value calculated using the market approach.
    """
    fair_value = sum(comparable_prices) / len(comparable_prices)
    return fair_value


def income_approach_valuation(discounted_cash_flows: List[float], discount_rate: float) -> float:
    """
    Calculate fair value using the income approach (DCF).

    Args:
    discounted_cash_flows (List[float]): List of discounted cash flows.
    discount_rate (float): Discount rate used in the discounted cash flow analysis.

    Returns:
    float: Fair value calculated using the income approach.
    """
    fair_value = sum(discounted_cash_flows) / (1 + discount_rate) ** len(discounted_cash_flows)
    return fair_value


def cost_approach_valuation(replacement_cost: float, depreciation: float) -> float:
    """
    Calculate fair value using the cost approach.

    Args:
    replacement_cost (float): Replacement cost of the asset.
    depreciation (float): Depreciation rate applied to the replacement cost.

    Returns:
    float: Fair value calculated using the cost approach.
    """
    fair_value = replacement_cost * (1 - depreciation)
    return fair_value


if __name__ == "__main__":
    main()
