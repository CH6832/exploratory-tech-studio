#!/usr/bin/env python
# -*- coding: utf-8 -*-


"""segment_reporting.py

This script demonstrates segment reporting by identifying and aggregating operating segments,
calculating segment revenue, profit or loss, and segment assets, in accordance with ASC 280 (Segment Reporting).
"""


from typing import Dict


def main():
    """Driving code and example usage."""
    segments = {
        "Segment A": OperatingSegment("Segment A", revenue=1000000, expenses=800000, assets=500000),
        "Segment B": OperatingSegment("Segment B", revenue=800000, expenses=600000, assets=400000),
        "Segment C": OperatingSegment("Segment C", revenue=600000, expenses=400000, assets=300000)
    }

    # aggregate segment results
    aggregated_results = aggregate_segment_results(segments)
    print("Segment Reporting Results:")
    for item, value in aggregated_results.items():
        print(item + ":", value)

    return None


class OperatingSegment:
    """
    A class representing an operating segment of a business.
    """

    def __init__(self, name: str, revenue: float, expenses: float, assets: float) -> None:
        """Initializes an instance of the OperatingSegment class with the provided attributes.
        
        Parameters:
        name (str) -- The name of the operating segment.
        revenue (float) -- The revenue generated by the operating segment.
        expenses (float) -- The expenses incurred by the operating segment.
        assets (float) -- The total assets of the operating segment.
        """        
        self.name = name
        self.revenue = revenue
        self.expenses = expenses
        self.assets = assets

        return None


def aggregate_segment_results(segments: Dict[str, OperatingSegment]) -> Dict[str, float]:
    """Aggregate segment results for segment reporting purposes.

    Parameters:
    segments (Dict[str, OperatingSegment]) -- Dictionary containing operating segments.

    Returns:
    Dict[str, float] -- Aggregated segment results (total revenue, total expenses, total assets).
    """
    total_revenue = sum(segment.revenue for segment in segments.values())
    total_expenses = sum(segment.expenses for segment in segments.values())
    total_assets = sum(segment.assets for segment in segments.values())

    return {"Total Revenue": total_revenue, "Total Expenses": total_expenses, "Total Assets": total_assets}


if __name__ == "__main__":
    main()
