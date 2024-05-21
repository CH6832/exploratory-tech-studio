#!/usr/bin/env python
# -*- coding: utf-8 -*-


"""revenue_recognition.py

This script demonstrates revenue recognition from contracts with customers,
applying the principles of ASC 606 (Revenue from Contracts with Customers).
"""


from typing import List


def main() -> None:
    """Driving code"""
    # Example usage
    prices = [5000, 7000, 3000]  # Prices of individual performance obligations
    total_price = estimate_transaction_price(prices)

    # Allocate transaction price to each performance obligation
    allocated_prices = allocate_transaction_price(prices, total_price)
    print("Allocated Transaction Prices:", allocated_prices)

    return None


def estimate_transaction_price(prices: List[float]) -> float:
    """Estimate the transaction price by summing up the prices of individual performance obligations.

    Parameters:
    prices (List[float]): List of prices of individual performance obligations.

    Returns:
    float: Estimated transaction price.
    """
    transaction_price = sum(prices)
    return transaction_price


def allocate_transaction_price(prices: List[float], total_price: float) -> List[float]:
    """Allocate the transaction price to each performance obligation based on its relative standalone selling price.

    Parameters:
    prices (List[float]): List of prices of individual performance obligations.
    total_price (float): Total transaction price.

    Returns:
    List[float]: Allocated transaction price for each performance obligation.
    """
    allocated_prices = [price / total_price for price in prices]
    return allocated_prices


if __name__ == "__main__":
    main()
