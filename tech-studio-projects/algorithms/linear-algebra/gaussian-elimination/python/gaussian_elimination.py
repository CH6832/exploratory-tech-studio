#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""gaussian_elimination.py

The Gaussian Elimination algorithm is used to solve a system of linear equations of the form Ax = b, where A is the coefficient matrix, x is the solution vector, and b is the right-hand side vector.

    Partial Pivoting: During each iteration of the forward elimination phase, the algorithm performs partial pivoting to ensure numerical stability. It swaps rows to move the largest absolute value in the current column to the diagonal position.

    Forward Elimination: The algorithm performs row operations to eliminate coefficients below the diagonal, making the coefficient matrix upper triangular.

    Back Substitution: Once the coefficient matrix is in upper triangular form, the algorithm performs back substitution to solve for the solution vector x.

    Example Usage: In the if __name__ == "__main__": block, an example usage of the gaussian_elimination function is provided. We define a coefficient matrix A and a right-hand side vector b, and then call the gaussian_elimination function to find the solution vector x. Finally, we print the solution.

.. _PEP 0000:
    https://peps.python.org/pep-0000/
"""


import numpy as np
from typing import Tuple


def main() -> None:
    """Driving code and main function"""
    # Example: Solve a system of linear equations
    A = np.array([[2, 1, -1], [-3, -1, 2], [-2, 1, 2]])
    b = np.array([8, -11, -3])

    solution = gaussian_elimination(A, b)

    print("Solution:", solution)
 
    return None


def gaussian_elimination(A: np.ndarray, b: np.ndarray) -> np.ndarray:
    """Solve a system of linear equations using Gaussian Elimination.

    Args:
        A (np.ndarray): Coefficient matrix.
        b (np.ndarray): Right-hand side vector.

    Returns:
        np.ndarray: Solution vector.
    """
    n = len(b)
    augmented_matrix = np.hstack((A, b.reshape(-1, 1)))
    
    for i in range(n):
        # Partial pivoting
        maximum_index = np.argmax(abs(augmented_matrix[i:, i])) + i
        if maximum_index != i:
            augmented_matrix[[i, maximum_index]] = augmented_matrix[[maximum_index, i]]

        # Forward elimination
        pivot = augmented_matrix[i, i]
        augmented_matrix[i] /= pivot
        for j in range(i+1, n):
            factor = augmented_matrix[j, i]
            augmented_matrix[j] -= factor * augmented_matrix[i]

    # Back substitution
    x = np.zeros(n)
    for i in range(n-1, -1, -1):
        x[i] = augmented_matrix[i, -1]
        for j in range(i+1, n):
            x[i] -= augmented_matrix[i, j] * x[j]

    return x


if __name__ == '__main__':
    main()

