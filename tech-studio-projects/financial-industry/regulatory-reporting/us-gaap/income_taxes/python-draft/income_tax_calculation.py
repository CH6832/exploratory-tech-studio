#!/usr/bin/env python
# -*- coding: utf-8 -*-


"""income_tax_calculation.py

This script calculates income tax provisions and deferred tax assets/liabilities
based on applicable tax rates, tax laws, and temporary differences between book and tax bases
of assets and liabilities, as governed by ASC 740 (Income Taxes).
"""


from typing import Dict


def main() -> None:
    """Driving code."""
    # Example usage
    income_before_tax = 1000000  # Income before tax
    tax_rate = 0.2  # Applicable tax rate
    temporary_differences = {
        "Depreciation Expense": 50000,  # Temporary difference due to accelerated depreciation
        "Accrued Expenses": -20000  # Temporary difference due to accrued expenses
    }

    # Calculate income tax provision
    income_tax_provision = calculate_income_tax_provision(income_before_tax, tax_rate)
    print("Income Tax Provision:", income_tax_provision)

    # Calculate deferred tax assets/liabilities
    deferred_tax_assets_liabilities = calculate_deferred_tax_assets_liabilities(temporary_differences, tax_rate)
    print("Deferred Tax Assets/Liabilities:", deferred_tax_assets_liabilities)

    return None


def calculate_income_tax_provision(income_before_tax: float, tax_rate: float) -> float:
    """Calculate the income tax provision.

    Parameters:
    income_before_tax (float) -- Income before tax.
    tax_rate (float) -- Applicable tax rate.

    Returns:
    float -- Income tax provision.
    """
    income_tax_provision = income_before_tax * tax_rate
    
    return income_tax_provision


def calculate_deferred_tax_assets_liabilities(temporary_differences: Dict[str, float], tax_rate: float) -> Dict[str, float]:
    """Calculate deferred tax assets/liabilities based on temporary differences between book and tax bases.

    Parameters:
    temporary_differences (Dict[str, float]): Dictionary containing temporary differences between
                                              book and tax bases of assets and liabilities.
    tax_rate (float): Applicable tax rate.

    Returns:
    Dict[str, float]: Dictionary containing deferred tax assets and liabilities.
    """
    deferred_tax_assets_liabilities = {}
    for item, difference in temporary_differences.items():
        deferred_tax_assets_liabilities[item] = difference * tax_rate
    
    return deferred_tax_assets_liabilities


if __name__ == "__main__":
    main()
