#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""linear_regression.py

Linear regression is a statistical method used to model the relationship between a dependent variable and one or more independent variables. The goal is to find the coefficients (parameters) of the linear equation that best fits the observed data.

    Matrix Formulation: The linear regression problem can be represented in matrix form as X * coefficients = y, where X is the matrix of independent variables (features), coefficients is the vector of unknown parameters, and y is the vector of observed output values.

    Least Squares Method: The coefficients are computed using the least squares method, which minimizes the sum of squared differences between the observed and predicted output values.

    Coefficient of Determination (R^2): The coefficient of determination, often denoted as R^2, measures the proportion of the variance in the dependent variable that is predictable from the independent variables. It ranges from 0 to 1, with higher values indicating a better fit of the model to the data.

    Example Usage: In the if __name__ == "__main__": block, an example usage of the linear_regression function is provided. We define a matrix of input data X and corresponding output values y, and then call the linear_regression function to find the coefficients and the coefficient of determination. Finally, we print the results.

.. _PEP 0000:
    https://peps.python.org/pep-0000/
"""


import numpy as np
from typing import Tuple


def main() -> None:
    """Driving code and main function"""
    # Example: Linear regression
    X = np.array([[1, 2], [1, 3], [1, 4], [1, 5]])
    y = np.array([2, 3, 4, 5])

    coefficients, r_squared = linear_regression(X, y)

    print("Coefficients:", coefficients)
    print("Coefficient of Determination (R^2):", r_squared)
 
    return None


def linear_regression(X: np.ndarray, y: np.ndarray) -> Tuple[np.ndarray, float]:
    """Perform linear regression to find the coefficients and the coefficient of determination (R^2).

    Args:
        X (np.ndarray): Matrix of independent variables (features).
        y (np.ndarray): Vector of dependent variable (target).

    Returns:
        Tuple[np.ndarray, float]: A tuple containing the coefficients (parameters) and the coefficient of determination.
    """
    # Add a column of ones for the intercept term
    X_with_intercept = np.column_stack([np.ones(len(X)), X])

    # Compute the coefficients using the least squares method
    coefficients = np.linalg.lstsq(X_with_intercept, y, rcond=None)[0]

    # Compute the predicted values
    y_pred = np.dot(X_with_intercept, coefficients)

    # Compute the total sum of squares
    ss_total = np.sum((y - np.mean(y)) ** 2)

    # Compute the residual sum of squares
    ss_residual = np.sum((y - y_pred) ** 2)

    # Compute the coefficient of determination (R^2)
    r_squared = 1 - (ss_residual / ss_total)

    return coefficients, r_squared


if __name__ == '__main__':
    main()

