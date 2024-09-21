#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""newtons_method.py

Newton's method is an iterative root-finding algorithm that starts with an initial guess and refines it iteratively to find a root of a given function. Here's how the algorithm works:

    Initialization: Start with an initial guess x0x0​.

    Iteration: Repeat the following steps until convergence or a maximum number of iterations:
        Compute the change in xx using the formula: Δx=−f(x)f′(x)Δx=−f′(x)f(x)​, where f(x)f(x) is the function and f′(x)f′(x) is its derivative.
        Update the value of xx using x:=x+Δxx:=x+Δx.
        Check for convergence: If ∣Δx∣<tol∣Δx∣<tol, where tol is a predefined tolerance, exit the loop.

    Termination: Return the final value of xx and the number of iterations.

In the example usage, we demonstrate how to find a root of a simple function f(x)=x2−4f(x)=x2−4 using Newton's method. We provide the function f(x)f(x), its derivative f′(x)f′(x), an initial guess x0x0​, and call the newtons_method function to obtain the root and the number of iterations required for convergence. Adjustments to the initial guess, tolerance, and maximum number of iterations can be made based on the specific problem being solved.

.. _PEP 0000:
    https://peps.python.org/pep-0000/
"""


from typing import Callable, Tuple
import numpy as np


def main() -> None:
    """Driving code and main function"""
    # Example: Find a root of a function using Newton's method
    def f(x: float) -> float:
        return x**2 - 4

    def df(x: float) -> float:
        return 2 * x

    x0 = 2.0
    root, iterations = newtons_method(f, df, x0)
    print("Root:", root)
    print("Number of iterations:", iterations)
 
    return None


def newtons_method(f: Callable[[float], float], df: Callable[[float], float], x0: float, tol: float = 1e-6, max_iter: int = 1000) -> Tuple[float, int]:
    """Find a root of a function using Newton's method.

    Args:
        f (Callable[[float], float]): The function for which to find the root.
        df (Callable[[float], float]): The derivative of the function.
        x0 (float): Initial guess for the root.
        tol (float, optional): Tolerance for convergence. Defaults to 1e-6.
        max_iter (int, optional): Maximum number of iterations. Defaults to 1000.

    Returns:
        Tuple[float, int]: The root and the number of iterations.
    """
    x = x0
    for i in range(max_iter):
        delta_x = -f(x) / df(x)
        x += delta_x
        if abs(delta_x) < tol:
            break
    return x, i + 1


if __name__ == '__main__':
    main()

