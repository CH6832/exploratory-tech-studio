#!/usr/bin/env python
# -*- coding: utf-8 -*-


"""impairment_testing.py

This script assesses impairment of assets by comparing their carrying amounts to their recoverable amounts,
as prescribed by standards like ASC 350 (Intangibles—Goodwill and Other) and ASC 360 (Property, Plant, and Equipment).
"""


from typing import Union


def main() -> None:
    """Driving code."""
    # example usage
    carrying_amount_goodwill = 1000000 # carrying amount of goodwill
    recoverable_amount_goodwill = 900000 # recoverable amount of goodwill

    carrying_amount_property = 1500000 # carrying amount of property, plant, and equipment
    recoverable_amount_property = 1600000 # recoverable amount of property, plant, and equipment

    # assess impairment of goodwill
    goodwill_impairment = assess_impairment(carrying_amount_goodwill, recoverable_amount_goodwill)
    print("Goodwill Impairment Loss:", goodwill_impairment)

    # assess impairment of property, plant, and equipment
    property_impairment = assess_impairment(carrying_amount_property, recoverable_amount_property)
    print("Property Impairment Loss:", property_impairment)

    return None


def assess_impairment(carrying_amount: float, recoverable_amount: float) -> Union[float, str]:
    """Assess impairment of an asset by comparing its carrying amount to its recoverable amount.

    Parameters:
    carrying_amount (float) -- Carrying amount of the asset.
    recoverable_amount (float) -- Recoverable amount of the asset.
    """
    if carrying_amount > recoverable_amount:
        impairment_loss = carrying_amount - recoverable_amount
        return impairment_loss
    else:
        return "No impairment detected."


if __name__ == "__main__":
    main()
