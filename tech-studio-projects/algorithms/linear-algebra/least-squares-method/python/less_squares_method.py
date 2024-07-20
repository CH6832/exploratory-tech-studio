#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""travelling_salesman_problem.py

The program implements a solution to the Traveling Salesman Problem (TSP), a classic problem
in graph theory and combinatorial optimization. The goal of the TSP is to find the shortest
possible route that visits every city exactly once and returns to the original city. In this
program, cities are repressented as vertices of a graph, and the distances between them are
represented by edge weights in the graph.

.. _PEP 0000:
    https://peps.python.org/pep-0000/
"""


import numpy as np
from typing import Tuple


def main() -> None:
    """Driving code and main function"""
    # Example: Linear regression using the Least Squares Method
    X = np.array([[1, 2], [1, 3], [1, 4], [1, 5]])
    y = np.array([2, 3, 4, 5])

    coefficients = least_squares_method(X, y)

    print("Coefficients:", coefficients)
 
    return None


def least_squares_method(X: np.ndarray, y: np.ndarray) -> np.ndarray:
    """Solve a linear regression problem using the Least Squares Method.

    Args:
        X (np.ndarray): Matrix of independent variables (features).
        y (np.ndarray): Vector of dependent variable (target).

    Returns:
        np.ndarray: Vector of coefficients (parameters).
    """
    X_transpose = np.transpose(X)
    coefficients = np.dot(np.linalg.inv(np.dot(X_transpose, X)), np.dot(X_transpose, y))
    return coefficients


if __name__ == '__main__':
    main()

