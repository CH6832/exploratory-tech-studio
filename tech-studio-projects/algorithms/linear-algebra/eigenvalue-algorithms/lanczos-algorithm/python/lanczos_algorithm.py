#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""lanczos_algorithm.py

The Lanczos Algorithm is an iterative method used to approximate the smallest eigenvalue and corresponding eigenvector of a real symmetric matrix. It is particularly useful for large sparse matrices, such as those encountered in quantum mechanics and numerical simulations.

    Lanczos Iteration: The algorithm iteratively constructs an orthogonal basis for the Krylov subspace of the matrix A, spanned by the vectors v, Av, A^2v, ..., A^(k-1)v, where v is an initial vector.

    Lanczos Triadiagonalization: During each iteration, the Lanczos algorithm computes a tridiagonal matrix T that represents the projection of A onto the Krylov subspace. This tridiagonal matrix is similar to A, and its eigenvalues are approximations of the eigenvalues of A.

    Eigenvalue Estimation: After a fixed number of iterations (k), the Lanczos algorithm computes the eigenvalues and eigenvectors of the tridiagonal matrix T. The smallest eigenvalue of T corresponds to an approximation of the smallest eigenvalue of A, and its corresponding eigenvector approximates the corresponding eigenvector of A.

    Example Usage: In the if __name__ == "__main__": block, an example usage of the lanczos_algorithm function is provided. We define a symmetric matrix A, an initial vector v, and the number of Lanczos iterations k. We then call the lanczos_algorithm function to find the smallest eigenvalue and corresponding eigenvector of A. Finally, we print the results.

.. _PEP 0000:
    https://peps.python.org/pep-0000/
"""


from sys import maxsize
from itertools import permutations
from typing import List, Tuple
import numpy as np
from typing import Callable, Tuple


def main():
    """Driving code"""
    # Example: Find the smallest eigenvalue and eigenvector of a symmetric matrix
    A = np.array([[4, -2, 2], [-2, 2, -4], [2, -4, 11]])
    v = np.array([1, 1, 1])
    k = 10

    smallest_eigenvalue, corresponding_eigenvector = lanczos_algorithm(A, v, k)

    print("Smallest eigenvalue:", smallest_eigenvalue)
    print("Corresponding eigenvector:", corresponding_eigenvector)    


def lanczos_algorithm(A: np.ndarray, v: np.ndarray, k: int) -> Tuple[np.ndarray, np.ndarray]:
    """Approximate the smallest eigenvalue and corresponding eigenvector of a symmetric matrix
    using the Lanczos Algorithm.

    Args:
        A (np.ndarray): Symmetric matrix.
        v (np.ndarray): Initial vector for Lanczos iteration.
        k (int): Number of Lanczos iterations.

    Returns:
        Tuple[np.ndarray, np.ndarray]: A tuple containing the smallest eigenvalue and the corresponding eigenvector.
    """
    n = A.shape[0]
    V = np.zeros((n, k+1))
    T = np.zeros((k, k))

    V[:, 0] = v / np.linalg.norm(v)

    for i in range(k):
        w = np.dot(A, V[:, i])
        alpha = np.dot(w, V[:, i])
        w -= alpha * V[:, i]
        
        if i > 0:
            w -= beta * V[:, i-1]
        
        beta = np.linalg.norm(w)
        V[:, i+1] = w / beta
        T[i, i] = alpha
        if i > 0:
            T[i, i-1] = beta
            T[i-1, i] = beta

    eigenvalues, eigenvectors = np.linalg.eig(T)
    smallest_eigenvalue = np.min(eigenvalues)
    index = np.argmin(eigenvalues)
    corresponding_eigenvector = eigenvectors[:, index]

    return smallest_eigenvalue, corresponding_eigenvector


if __name__ == '__main__':
    main()

