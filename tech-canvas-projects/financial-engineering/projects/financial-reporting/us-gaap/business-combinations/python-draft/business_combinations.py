#!/usr/bin/env python
# -*- coding: utf-8 -*-


"""business_combinations.py

This script performs purchase price allocation and measures goodwill or bargain purchase gain
as part of business combination accounting under ASC 805 (Business Combinations).
"""


from typing import Dict


def main():
    """main program and entrypoint."""
    consideration_transferred = 1000000  # fair value of consideration transferred
    fair_values = {
        "identifiable_assets": 800000,  # fair value of identifiable assets acquired
        "liabilities_assumed": 500000,  # fair value of liabilities assumed
        "non_controlling_interests": 100000  # fair value of non-controlling interests
    }

    # perform purchase price allocation
    allocation_results = perform_purchase_price_allocation(consideration_transferred, fair_values)

    print("Identifiable Assets:", allocation_results["identifiable_assets"])
    print("Liabilities Assumed:", allocation_results["liabilities_assumed"])
    print("Non-Controlling Interests:", allocation_results["non_controlling_interests"])
    print("Goodwill or Bargain Purchase Gain:", allocation_results["goodwill_or_bargain_purchase_gain"])


def perform_purchase_price_allocation(consideration_transferred: float, fair_values: Dict[str, float]) -> Dict[str, float]:
    """Perform purchase price allocation.

    Keyword arguments:
    consideration_transferred (float): Fair value of consideration transferred.
    fair_values (Dict[str, float]): Dictionary containing the fair values of identifiable assets acquired,
                                     liabilities assumed, and non-controlling interests.

    Returns:
    Dict[str, float]: Dictionary containing the allocated values of identifiable assets acquired,
                      liabilities assumed, and any excess consideration (goodwill) or bargain purchase gain.

    Process of PPA:
    1. Identifying Assets and Liabilities: Determine all the identifiable assets and liabilities acquired in the acquisition.
    These can include tangible assets (such as property, plant, and equipment), intangible assets (such as patents, trademarks,
    and customer relationships), and liabilities (such as accounts payable and long-term debt).
    2. Determining Fair Values: Assess the fair values of the identifiable assets and liabilities acquired. This may involve various
    valuation techniques, such as market-based approaches, income approaches, or cost approaches, depending on the nature of the
    assets and liabilities.
    3. Allocating Purchase Price: Allocate the purchase price among the identifiable assets and liabilities based on their fair values.
    This step requires judgment and may involve negotiation between the buyer and seller.
    4. Recording Entries: Record the allocated purchase price on the acquirer's balance sheet. This may involve creating new asset and
    liability accounts, as well as recognizing any excess purchase price as goodwill.
    5. Amortization and Impairment: Intangible assets acquired through the acquisition are typically subject to amortization over their
    useful lives and tested for impairment annually or when there are indications of impairment.    
    """
    identifiable_assets = fair_values.get("identifiable_assets", 0)
    liabilities_assumed = fair_values.get("liabilities_assumed", 0)
    non_controlling_interests = fair_values.get("non_controlling_interests", 0)

    goodwill_or_bargain_purchase_gain = consideration_transferred - identifiable_assets - liabilities_assumed - non_controlling_interests

    allocation_results = {
        "identifiable_assets": identifiable_assets,
        "liabilities_assumed": liabilities_assumed,
        "non_controlling_interests": non_controlling_interests,
        "goodwill_or_bargain_purchase_gain": goodwill_or_bargain_purchase_gain
    }

    return allocation_results


if __name__ == "__main__":
    main()
