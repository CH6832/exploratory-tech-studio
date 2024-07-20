#!/usr/bin/env python
# -*- coding: utf-8 -*-


"""pension_and_postretirement_benefits.py

This script estimates the present value of pension obligations and other postretirement benefits,
including actuarial valuations and assumptions, in compliance with ASC 715 (Compensation—Retirement Benefits)
and ASC 715-60 (Other Postretirement Benefits).
"""


from typing import List


def main() -> None:
    """Driving code"""
    # example usage
    pension_payments = 50000 * 10  # projected pension benefit payments of $50,000 per year for 10 years
    postretirement_benefit_payments = 30000 * 15  # projected postretirement benefit payments of $30,000 per year for 15 years
    discount_rate = 0.05 # discount rate

    # estimate present value of pension obligations
    present_value_pension = present_value_of_pension_obligations(pension_payments, discount_rate)
    print("Present Value of Pension Obligations:", present_value_pension)

    # estimate present value of postretirement benefits
    present_value_postretirement = present_value_of_postretirement_benefits(postretirement_benefit_payments, discount_rate)
    print("Present Value of Postretirement Benefits:", present_value_postretirement)

    return None


def present_value_of_pension_obligations(pension_payments: List[float], discount_rate: float) -> float:
    """Estimate the present value of pension obligations using the projected benefit payments and discount rate.

    Parameters:
    pension_payments (List[float]): List of projected benefit payments over the pension period.
    discount_rate (float): Discount rate used to calculate the present value.

    Returns:
    float: Present value of pension obligations.
    """
    present_value = sum([payment / ((1 + discount_rate) ** (index + 1)) for index, payment in enumerate(pension_payments)])
    
    return present_value


def present_value_of_postretirement_benefits(benefit_payments: List[float], discount_rate: float) -> float:
    """Estimate the present value of other postretirement benefits using the projected benefit payments and discount rate.

    Parameters:
    benefit_payments (List[float]): List of projected benefit payments over the postretirement period.
    discount_rate (float): Discount rate used to calculate the present value.

    Returns:
    float: Present value of other postretirement benefits.
    """
    present_value = sum([payment / ((1 + discount_rate) ** (index + 1)) for index, payment in enumerate(benefit_payments)])
    
    return present_value


if __name__ == "__main__":
    main()
