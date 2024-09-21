#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""jacobi_iteration.py

The Jacobi Iteration method is an iterative numerical technique used to solve a system of linear equations of the form Ax = b, where A is the coefficient matrix, x is the solution vector, and b is the right-hand side vector.

    Jacobi Iteration: In each iteration, the method updates the solution vector x using the formula x_new = D^(-1)(b - Rx), where D is the diagonal matrix of A, and R is the remainder matrix obtained by subtracting D from A.

    Convergence Criteria: The iteration continues until a specified maximum number of iterations is reached or until the change in the solution vector x falls below a given tolerance value.

    Example Usage: In the if __name__ == "__main__": block, an example usage of the jacobi_iteration function is provided. We define a coefficient matrix A, a right-hand side vector b, and specify the maximum number of iterations. We then call the jacobi_iteration function to find the solution vector x. Finally, we print the solution.

.. _PEP 0000:
    https://peps.python.org/pep-0000/
"""


import numpy as np
from typing import Tuple


def main() -> None:
    """Driving code and main function"""
    # Example: Solve a system of linear equations
    A = np.array([[2, -1, 0], [-1, 2, -1], [0, -1, 2]])
    b = np.array([1, 0, 1])
    max_iterations = 100

    solution = jacobi_iteration(A, b, max_iterations)

    print("Solution:", solution)
 
    return None


    """Solve a system of linear equations using the Jacobi Iteration method.

    Args:
        A (np.ndarray): Coefficient matrix.
        b (np.ndarray): Right-hand side vector.
        max_iterations (int): Maximum number of iterations.
        tolerance (float, optional): Tolerance for convergence. Defaults to 1e-10.

    Returns:
        np.ndarray: Solution vector.
    """
    n = len(b)
    x = np.zeros(n)
    D = np.diag(np.diag(A))
    R = A - D

    for _ in range(max_iterations):
        x_new = np.dot(np.linalg.inv(D), b - np.dot(R, x))
        if np.linalg.norm(x_new - x) < tolerance:
            return x_new
        x = x_new

    return x


if __name__ == '__main__':
    main()

