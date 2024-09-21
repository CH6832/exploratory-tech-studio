#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""Expression.py

Expressions for the SymClone library.
"""

# https://docs.pylint.org/
# pylint: disable=line-too-long, trailing-whitespace, multiple-statements, fixme, locally-disabled

import math
from typing import Any, Union
from symclone.Symbol import Symbol

class Expression:
    """
    Class to represent mathematical expressions and perform various operations on them.
    """

    def __init__(self, expr: Union[str, 'Expression', Symbol, float, int]) -> None:
        """Initialize an Expression instance with the given expression.

        Parameters:
        expr -- The expression, which can be a string, another Expression, a Symbol, or a number.
        """
        self.expr = expr

        return None

    def __str__(self):
        """Convert the Expression instance to a string representation."""
        
        return str(self.expr)

    def __repr__(self):
        """Return the string representation of the expression."""
        
        return f"Expression('{self.expr}')"

    def substitute(self, symbol_values: dict) -> 'Expression':
        """Substitute symbolic variables with a numerical one.

        Parameters:
        symbol_values -- Dictionary mapping symbols to numerical values.
        """
        # get original expressions
        expr = self.expr
        for symbol, value in symbol_values.items():
            expr = expr.replace(str(symbol), str(value))
        
        # return new expression object with substituted values
        return Expression(expr)

    def evaluate(self, symbol_values: dict = None) -> float:
        """Evaluate the expression numerically.

        Parameters:
        symbol_values -- Optional dictionary mapping symbols to numerical values.
        """
        if symbol_values:
            # substitute symbols with their values and get the resulting expression
            substituted_expr = self.substitute(symbol_values).expr
        else:
            # if no symbol_values provided, use the original expression
            substituted_expr = self.expr
        
        return eval(substituted_expr)

    def pow(self, base, exponent) -> float:
        """Custom implementation of exponentiation."""
        
        return base ** exponent

    def derivative(self, symbol: Symbol, h: float = 1e-5) -> 'Expression':
        """Compute the derivative of the expression with respect to the given symbol.

        Parameters:
        symbol -- Symbol with respect to which the derivative is computed.
        h -- Small step size for numerical differentiation.
        """
        # substitute symbol with (symbol + h) in the expression
        expr_at_x = self.expr.replace(str(symbol), f'({str(symbol)} + {h})')
        # compute the difference quotient expression using forward difference formula
        diff_expr = f'(({expr_at_x}) - ({self.expr})) / {h}'
        
        return Expression(diff_expr)

    def integral(self, symbol: Symbol, limits: tuple = None) -> float:
        """Compute the integral of the expression with respect to the given symbol.

        Parameters:
        symbol -- Symbol with respect to which the integral is computed.
        limits -- Optional integration limits as a tuple (lower_limit, upper_limit).
        """
        if limits:
            # unpack lower and upper limits
            lower_limit, upper_limit = limits
            # perform numerical integration using the specified limits
            integral_value = self._numerical_integration(symbol, lower_limit, upper_limit)
        else:
            # raise an error if integration limits are not provided
            raise ValueError("Integration limits must be provided.")

        # return the numerical value of the definite integral
        return integral_value

    def _numerical_integration(self, symbol: Symbol, lower_limit: float, upper_limit: float, num_intervals: int = 1000) -> float:
        """Helper function to compute numerical integration.
        
        Parameters:
        symbol (Symbol) -- The variable of integration.
        lower_limit -- The lower bound of the integration interval.
        upper_limit -- The upper bound of the integration interval.
        num_intervals -- The number of sub-intervals for the integration. Default is 1000.      
        """
        # calculate the width of each interval
        interval_width = (upper_limit - lower_limit) / num_intervals
        # initialize the total area
        total_area = 0.0
        for i in range(num_intervals):
            # calculate the x-value of the midpoint of the current interval
            x_value = lower_limit + i * interval_width
            # evaluate the function at the midpoint to get the height
            height = self.evaluate({symbol: x_value})
            # add the area of the rectangle (width * height) to the total area
            total_area += height * interval_width

        # return the total area, which approximates the integral
        return total_area
    
    def limit_of_expression(self, symbol: Symbol, value: float, epsilon: float = 1e-7, tolerance: float = 1e-5) -> float:
        """Compute the limit of the expression as the given symbol approaches the specified value.

        Parameters:
        symbol -- Symbol whose limit is computed.
        value -- Value toward which the symbol approaches.
        epsilon -- Small step size to check the limit from both sides.
        tolerance -- Tolerance range to consider the limit as existing.
        """
        try:
            # evaluate the expression at value - epsilon and value + epsilon
            left_limit = self.evaluate({symbol: value - epsilon})
            right_limit = self.evaluate({symbol: value + epsilon})
            # check if the difference between left and right limits is within tolerance
            if abs(left_limit - right_limit) < tolerance:
                # if so, return the average of left and right limits as the limit
                return (left_limit + right_limit) / 2
            else:
                # if not, raise an error indicating that the limit does not exist
                raise ValueError("Limit does not exist.")
        except Exception as e:
            # catch any errors that occur during limit calculation and raise a ValueError with an informative message
            raise ValueError(f"Error in limit calculation: {e}")

    def series_expansion_of_expression(self, symbol: Symbol, point: float, order: int) -> list:
        """Compute the series expansion of the expression around the specified point up to the given order.

        Parameters:
        symbol -- Symbol around which the series expansion is computed.
        point -- Point around which the series expansion is computed.
        order -- Order of the series expansion.
        """
        # initialize the list to store the series coefficients with the value of the function at the center
        series = [self.evaluate({symbol: point})]
        # initialize the factorial value for computing coefficients
        factorial = 1
        for i in range(1, order + 1):
            # update the factorial value for the current order
            factorial *= i
            # compute the i-th order derivative
            derivative_expr = self.derivative(symbol)
            # evaluate the derivative expression at the center point and divide by factorial to get the coefficient
            coefficient = derivative_expr.evaluate({symbol: point}) / factorial
            # append the coefficient to the series list
            series.append(coefficient)
        
        # return the list of series coefficients
        return series
