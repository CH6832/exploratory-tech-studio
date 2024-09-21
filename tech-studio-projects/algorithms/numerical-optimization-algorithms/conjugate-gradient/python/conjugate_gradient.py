#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""conjugate_gradient.py

The Conjugate Gradient (CG) method is an iterative algorithm used to solve systems of linear equations of the form Ax = b, where A is a symmetric positive-definite matrix. Here's how the algorithm works:

    Initialization: Start with an initial guess x0 for the solution.

    Residual Calculation: Compute the residual r0 = b - Ax0.

    Direction Calculation: Choose a search direction p0 = r0.

    Iteration: Repeat the following steps until convergence or a maximum number of iterations:
        Compute Ap = A * p.
        Compute the step size alpha = (r^T * r) / (p^T * A * p).
        Update the solution x = x + alpha * p.
        Update the residual r = r - alpha * A * p.
        Compute the new residual norm rsnew = r^T * r.
        Check for convergence: if rsnew is sufficiently small, exit the loop.
        Update the search direction p = r + (rsnew / rsold) * p, where rsold is the norm of the previous residual.

    Termination: Return the final solution x and the number of iterations.

In the example usage, we demonstrate how to solve a linear system using the Conjugate Gradient method. We define the coefficient matrix A and the right-hand side vector b, provide an initial guess x0 for the solution, and call the conjugate_gradient function to obtain the solution and the number of iterations required for convergence. Adjustments to the tolerance and maximum number of iterations can be made based on the desired level of accuracy and computational resources available.

.. _PEP 0000:
    https://peps.python.org/pep-0000/
"""


from typing import Callable, List, Tuple
import numpy as np


def main() -> None:
    """Driving code and main function"""
    # Example: Solve a linear system using Conjugate Gradient method
    A = np.array([[4, 1], [1, 3]])
    b = np.array([1, 2])
    x0 = np.array([0, 0])
    solution, iterations = conjugate_gradient(A, b, x0)
    print("Solution:", solution)
    print("Number of iterations:", iterations)
 
    return None


def conjugate_gradient(A: np.ndarray, b: np.ndarray, x0: np.ndarray, tol: float = 1e-6, max_iter: int = 1000) -> Tuple[np.ndarray, int]:
    """Solve a linear system Ax = b using the Conjugate Gradient method.

    Args:
        A (np.ndarray): Coefficient matrix.
        b (np.ndarray): Right-hand side vector.
        x0 (np.ndarray): Initial guess for the solution.
        tol (float, optional): Tolerance for convergence. Defaults to 1e-6.
        max_iter (int, optional): Maximum number of iterations. Defaults to 1000.

    Returns:
        Tuple[np.ndarray, int]: Solution vector and number of iterations.
    """
    x = x0.copy()
    r = b - A @ x
    p = r.copy()
    rsold = np.dot(r, r)
    for i in range(max_iter):
        Ap = A @ p
        alpha = rsold / np.dot(p, Ap)
        x = x + alpha * p
        r = r - alpha * Ap
        rsnew = np.dot(r, r)
        if np.sqrt(rsnew) < tol:
            break
        p = r + (rsnew / rsold) * p
        rsold = rsnew
    return x, i+1


if __name__ == '__main__':
    main()

