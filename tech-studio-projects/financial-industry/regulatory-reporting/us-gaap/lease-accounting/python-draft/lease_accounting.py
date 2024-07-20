#!/usr/bin/env python
# -*- coding: utf-8 -*-


"""lease_accounting.py

This script implements lease accounting requirements under ASC 842 (Leases) for lessees and lessors.
"""


from typing import List, Tuple


def main() -> None:
    """Driving code."""
    # Example usage
    lease_payments = [10000] * 5  # Lease payments of $10,000 per period for 5 periods
    discount_rate = 0.05  # Discount rate
    initial_direct_costs = 1000  # Initial direct costs related to the lease
    lease_incentive = 500  # Lease incentive received
    lease_term = 5  # Lease term in periods

    # Calculate present value of lease payments
    present_value = calculate_present_value_of_lease_payments(lease_payments, discount_rate)

    # Recognize lease assets and liabilities
    lease_asset, lease_liability = recognize_lease_assets_and_liabilities(present_value, initial_direct_costs, lease_incentive, lease_term, discount_rate)

    print("Present Value of Lease Payments:", present_value)
    print("Lease Asset:", lease_asset)
    print("Lease Liability:", lease_liability)

    return None


def calculate_present_value_of_lease_payments(lease_payments: List[float], discount_rate: float) -> float:
    """Calculate the present value of lease payments using the discount rate.

    Parameters:
    lease_payments (List[float]) -- List of lease payments.
    discount_rate (float) -- Discount rate used to calculate present value.

    Returns:
    float -- Present value of lease payments.
    """
    present_value = sum([payment / ((1 + discount_rate) ** (index + 1)) for index, payment in enumerate(lease_payments)])
    
    return present_value


def recognize_lease_assets_and_liabilities(present_value_of_lease_payments: float, initial_direct_costs: float,
                                           lease_incentive: float, lease_term: float, discount_rate: float) -> Tuple[float, float]:
    """Recognize lease assets and liabilities.

    Parameters:
    present_value_of_lease_payments (float) -- Present value of lease payments.
    initial_direct_costs (float) -- Initial direct costs related to the lease.
    lease_incentive (float) -- Lease incentive received.
    lease_term (float) -- Lease term.
    discount_rate (float) -- Discount rate used to calculate present value.

    Returns:
    Tuple[float, float] -- Lease assets and lease liabilities.
    """
    lease_liability = present_value_of_lease_payments + initial_direct_costs - lease_incentive
    lease_asset = lease_liability  # For simplicity, assuming the lease asset equals the lease liability

    return lease_asset, lease_liability


if __name__ == "__main__":
    main()
