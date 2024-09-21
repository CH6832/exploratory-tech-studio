#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""MathOperations.py

Mathematical operations for the SymClone library.
"""

# https://docs.pylint.org/
# pylint: disable=line-too-long, trailing-whitespace, multiple-statements, fixme, locally-disabled

import math
from typing import Any, Callable, List

class MathOperations:
    """
    Class providing various mathematical operations on expressions.

    This class contains static methods to perform mathematical operations such as derivative calculation,
    numerical integration, limit computation, and Taylor series expansion.
    """

    @staticmethod
    def derivative(expression: Callable[[float], float], value: float, h: float = 1e-5) -> float:
        """Compute the derivative of an expression with respect to a given symbol numerically.

        Parameters:
        expression (Callable[[float], float]): The expression to differentiate.
        value (float): The point at which to compute the derivative.
        h (float): Step size for numerical differentiation.
        """
        
        return (expression(value + h) - expression(value - h)) / (2 * h)

    @staticmethod
    def integral(expression: Callable[[float], float], lower_limit: float, upper_limit: float, num_intervals: int = 1000) -> float:
        """Compute the definite integral of an expression over a specified range numerically.

        Parameters:
        expression (Callable[[float], float]): The expression to integrate.
        lower_limit (float) -- The lower limit of integration.
        upper_limit (float) -- The upper limit of integration.
        num_intervals (int) -- Number of intervals for numerical integration.
        """
        interval_width = (upper_limit - lower_limit) / num_intervals
        integral = 0.5 * (expression(lower_limit) + expression(upper_limit))
        for i in range(1, num_intervals):
            integral += expression(lower_limit + i * interval_width)

        return integral * interval_width

    @staticmethod
    def limit(expression: Callable[[float], float], value: float, h: float = 1e-5) -> float:
        """Compute the limit of an expression as a value approaches a specified point numerically.

        Parameters:
        expression (Callable[[float], float]): The expression to evaluate.
        value (float): The value toward which the limit is computed.
        h (float): A small step size to approach the limit.
        """

        return (expression(value + h) + expression(value - h)) / 2

    @staticmethod
    def series_expansion(expression: Callable[[float], float], point: float, order: int) -> List[float]:
        """Compute the Taylor series expansion of an expression around a specified point up to a given order.

        Parameters:
        expression (Callable[[float], float]): The expression to expand.
        point (float) -- The point around which the series expansion is computed.
        order (int) -- The order of the series expansion.
        """
        coefficients = [expression(point)]
        for i in range(1, order + 1):
            derivative = MathOperations.derivative(expression, point, h=1e-5)
            coefficients.append(derivative / math.factorial(i))

        return coefficients
