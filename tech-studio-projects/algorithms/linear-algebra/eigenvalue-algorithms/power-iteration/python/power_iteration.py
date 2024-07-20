#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""power_iteration.py

The Power Iteration Algorithm is an iterative method used to find the dominant eigenvalue (the eigenvalue with the largest magnitude) and its corresponding eigenvector of a square matrix. It is a simple and efficient method for computing these eigenpairs.

    Initialization: We start with an initial guess for the eigenvector v, typically a random vector. We normalize this vector to have unit length.

    Iterations: During each iteration, we multiply the matrix A by the current eigenvector v to obtain a new vector. We then normalize this new vector to maintain unit length. This process is repeated for a specified number of iterations.

    Convergence: As the number of iterations increases, the power iteration converges towards the dominant eigenpair of the matrix A. The dominant eigenvalue is given by the Rayleigh quotient eigenvalue = v^T A v, where v is the corresponding eigenvector.

    Example Usage: In the if __name__ == "__main__": block, an example usage of the power_iteration function is provided. We define a matrix A and specify the number of iterations for the power iteration algorithm. We then call the power_iteration function to find the dominant eigenvalue and corresponding eigenvector of A. Finally, we print the results.

.. _PEP 0000:
    https://peps.python.org/pep-0000/
"""


from sys import maxsize
from itertools import permutations
from typing import List
import numpy as np


def main() -> None:
    """Driving code and main function"""
    # Example: Find the dominant eigenvalue and eigenvector of a matrix
    A = np.array([[4, -2, 2], [-2, 2, -4], [2, -4, 11]])
    num_iterations = 100

    dominant_eigenvalue, corresponding_eigenvector = power_iteration(A, num_iterations)

    print("Dominant eigenvalue:", dominant_eigenvalue)
    print("Corresponding eigenvector:", corresponding_eigenvector)
 
    return None


def power_iteration(A: np.ndarray, num_iterations: int) -> Tuple[float, np.ndarray]:
    """Approximate the dominant eigenvalue and corresponding eigenvector of a square matrix 
    using the Power Iteration Algorithm.

    Args:
        A (np.ndarray): Square matrix.
        num_iterations (int): Number of iterations for power iteration.

    Returns:
        Tuple[float, np.ndarray]: A tuple containing the dominant eigenvalue and the corresponding eigenvector.
    """
    n = A.shape[0]
    v = np.random.rand(n)
    v /= np.linalg.norm(v)

    for _ in range(num_iterations):
        v = np.dot(A, v)
        v /= np.linalg.norm(v)

    eigenvalue = np.dot(v, np.dot(A, v))
    return eigenvalue, v


if __name__ == '__main__':
    main()

