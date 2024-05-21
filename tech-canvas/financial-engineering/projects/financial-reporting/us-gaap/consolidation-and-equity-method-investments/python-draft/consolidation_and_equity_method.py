#!/usr/bin/env python
# -*- coding: utf-8 -*-


"""consolidation_and_equity_method.py

This script demonstrates consolidation of financial statements of subsidiaries
and application of the equity method to account for investments in associates and joint ventures,
as outlined in ASC 810 (Consolidation) and ASC 323 (Investments—Equity Method and Joint Ventures).
"""


from typing import List, Dict


def main():
    """Driving code."""
    subsidiary_1 = Entity("Subsidiary 1", total_assets=1000000, total_liabilities=500000, net_income=50000)
    subsidiary_2 = Entity("Subsidiary 2", total_assets=800000, total_liabilities=300000, net_income=40000)
    investee_net_income = 60000 # net income of the investee entity
    investor_percentage = 0.5 # percentage of ownership or significant influence

    # consolidate financial statements of subsidiaries
    consolidated_financials = consolidate_financial_statements([subsidiary_1, subsidiary_2])
    print("Consolidated Financial Statements:")
    for item, value in consolidated_financials.items():
        print(item + ":", value)

    # apply equity method for investment in associates
    investor_share_of_net_income = apply_equity_method(investee_net_income, investor_percentage)
    print("Investor's Share of Net Income (Equity Method):", investor_share_of_net_income)


class Entity:
    """
    The class represents a business entity.
    """

    def __init__(self, name: str, total_assets: float, total_liabilities: float, net_income: float) -> None:
        """Initializes an instance of the Entity class with the given attributes.
        
        Parameters:
        name (str) -- The name of the entity.
        total_assets (float) -- The total value of assets owned by the entity.
        total_liabilities (float) -- The total value of liabilities owed by the entity.
        net_income (float) -- The net income of the entity.
        """
        self.name = name
        self.total_assets = total_assets
        self.total_liabilities = total_liabilities
        self.net_income = net_income

        return None


def consolidate_financial_statements(entities: List[Entity]) -> Dict[str, float]:
    """Consolidate financial statements of subsidiaries.

    Parameters:
    entities (List[Entity]) -- List of subsidiary entities.

    Returns:
    Dict[str, float] -- Consolidated financial statement items (total assets, total liabilities, net income).
    """
    total_assets = sum(entity.total_assets for entity in entities)
    total_liabilities = sum(entity.total_liabilities for entity in entities)
    net_income = sum(entity.net_income for entity in entities)
    return {"Total Assets": total_assets, "Total Liabilities": total_liabilities, "Net Income": net_income}


def apply_equity_method(investee_net_income: float, investor_percentage: float) -> float:
    """Apply the equity method to account for investments in associates and joint ventures.

    Parameters:
    investee_net_income (float) -- Net income of the investee entity.
    investor_percentage (float) -- Percentage of ownership or significant influence exerted by the investor.

    Returns:
    float -- Investor's share of investee net income.
    """
    investor_share_of_net_income = investee_net_income * investor_percentage
    return investor_share_of_net_income


if __name__ == "__main__":
    main()
