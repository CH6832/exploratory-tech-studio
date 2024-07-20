#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""qr_algorithm.py

The QR Algorithm is an iterative method used to compute the eigenvalues and eigenvectors of a square matrix. It is based on the idea of decomposing the matrix A into the product of an orthogonal matrix Q and an upper triangular matrix R, such that A = QR.

    Initialization: We start with an initial guess for the matrix A. In this implementation, we initialize the matrix Q as the identity matrix.

    Iterations: During each iteration of the QR Algorithm, we decompose the matrix A into the product of Q and R using the QR decomposition. We then update the matrix A to be the product of R and Q, effectively shifting the matrix towards its upper Hessenberg form, which is diagonalizable.

    Convergence: As the number of iterations increases, the matrix A converges to a triangular matrix with the eigenvalues along its diagonal. The columns of Q represent the corresponding eigenvectors of A.

    Example Usage: In the if __name__ == "__main__": block, an example usage of the qr_algorithm function is provided. We define a matrix A and specify the number of iterations for the QR Algorithm. We then call the qr_algorithm function to find the eigenvalues and eigenvectors of A. Finally, we print the results.

.. _PEP 0000:
    https://peps.python.org/pep-0000/
"""


import numpy as np
from typing import Tuple


def main() -> None:
    """Driving code and main function"""
    # Example: Find the eigenvalues and eigenvectors of a matrix
    A = np.array([[4, -2, 2], [-2, 2, -4], [2, -4, 11]])
    num_iterations = 100

    eigenvalues, eigenvectors = qr_algorithm(A, num_iterations)

    print("Eigenvalues:", eigenvalues)
    print("Eigenvectors:", eigenvectors)
 
    return None


def qr_algorithm(A: np.ndarray, num_iterations: int) -> Tuple[np.ndarray, np.ndarray]:
    """Approximate the eigenvalues and eigenvectors of a square matrix using the QR Algorithm.

    Args:
        A (np.ndarray): Square matrix.
        num_iterations (int): Number of iterations for the QR Algorithm.

    Returns:
        Tuple[np.ndarray, np.ndarray]: A tuple containing the eigenvalues and eigenvectors.
    """
    n = A.shape[0]
    Q = np.eye(n)
    for _ in range(num_iterations):
        Q, R = np.linalg.qr(np.dot(A, Q))
        A = np.dot(R, Q)

    eigenvalues = np.diag(A)
    eigenvectors = Q
    return eigenvalues, eigenvectors


if __name__ == '__main__':
    main()

