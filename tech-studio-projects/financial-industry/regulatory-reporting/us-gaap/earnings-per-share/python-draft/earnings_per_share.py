#!/usr/bin/env python
# -*- coding: utf-8 -*-


"""earnings_per_share.py

This script calculates basic and diluted earnings per share (EPS) figures based on weighted average shares outstanding
and potentially dilutive securities, following guidance provided in ASC 260 (Earnings Per Share).
"""

def main() -> None:
    """Driving code."""
    # Example usage
    net_income = 1000000 # net income
    average_shares_outstanding = 500000 # weighted average shares outstanding
    dilutive_securities = [
        DilutiveSecurity(diluted_shares_issued=50000, is_dilutive=True), # convertible preferred stock
        DilutiveSecurity(diluted_shares_issued=20000, is_dilutive=True) # stock options
    ]

    # create an instance of EarningsPerShare
    eps = EarningsPerShare(net_income, average_shares_outstanding, dilutive_securities)

    # calculate basic and diluted EPS
    basic_eps = eps.calculate_basic_eps()
    diluted_eps = eps.calculate_diluted_eps()

    print("Basic EPS:", basic_eps)
    print("Diluted EPS:", diluted_eps)

    return None


class EarningsPerShare:
    """
    Class represents an Earnings per Share entity.
    """

    def __init__(self, net_income: float, average_shares_outstanding: float, dilutive_securities: list = None) -> None:
        """Initializes an instance of the class with the provided attributes.
        
        Parameters:
        net_income (float) -- The net income of the entity.
        average_shares_outstanding (float) -- The average shares outstanding of the entity.
        dilutive_securities (list, optional) -- A list of dilutive securities. Defaults to None.        
        """
        self.net_income = net_income
        self.average_shares_outstanding = average_shares_outstanding
        self.dilutive_securities = dilutive_securities if dilutive_securities else []

        return None


    def calculate_basic_eps(self) -> float:
        """Calculate basic earnings per share (EPS).

        Returns:
        float: Basic EPS.
        """
        
        return self.net_income / self.average_shares_outstanding


    def calculate_diluted_eps(self) -> float:
        """Calculate diluted earnings per share (EPS).

        Returns:
        float: Diluted EPS.
        """
        diluted_shares_outstanding = self.average_shares_outstanding
        for security in self.dilutive_securities:
            if security.is_dilutive:
                diluted_shares_outstanding += security.diluted_shares_issued
        
        return self.net_income / diluted_shares_outstanding


class DilutiveSecurity:
    """
    A class representing a dilutive security.
    """

    def __init__(self, diluted_shares_issued: float, is_dilutive: bool):
        """Initializes an instance of the DilutiveSecurity class with the provided attributes.
        
        Parameters:
        diluted_shares_issued (float) -- The number of diluted shares issued.
        is_dilutive (bool) -- Indicates whether the security is dilutive or not.
        """        
        self.diluted_shares_issued = diluted_shares_issued
        self.is_dilutive = is_dilutive


if __name__ == "__main__":
    main()
