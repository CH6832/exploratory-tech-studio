#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""monte_carlo.py

The Monte Carlo method is a statistical technique used to estimate numerical results by random sampling. In this implementation, we use the Monte Carlo method to estimate the value of pi by simulating random points within a unit square and determining the ratio of points that fall within the unit circle.

    Algorithm: We generate num_samples random points uniformly distributed within the unit square [-1, 1] x [-1, 1]. For each point, we calculate its distance from the origin and count how many points fall within the unit circle (i.e., have a distance <= 1 from the origin). The ratio of points inside the circle to the total number of points approximates the ratio of the area of the unit circle to the area of the unit square, which is π/4.

    Implementation: The function monte_carlo_pi takes the number of samples num_samples as input and iterates through the algorithm to estimate the value of pi using the Monte Carlo method. It returns the estimated value of pi.

    Example Usage: In the if __name__ == "__main__": block, an example usage of the monte_carlo_pi function is provided. We specify the number of samples, call the function to estimate pi, and then print the result. Increasing the number of samples generally leads to a more accurate estimate of pi.

.. _PEP 0000:
    https://peps.python.org/pep-0000/
"""


import random
from typing import Tuple


def main() -> None:
    """Driving code and main function"""
    # Example: Monte Carlo Pi Estimation
    num_samples = 1000000
    pi_estimate = monte_carlo_pi(num_samples)
    print(f"Estimated value of pi using {num_samples} samples: {pi_estimate}")
 
    return None


def monte_carlo_pi(num_samples: int) -> float:
    """Estimate the value of pi using the Monte Carlo method.

    Args:
        num_samples (int): Number of random samples to generate.

    Returns:
        float: Estimated value of pi.
    """
    num_points_inside_circle = 0

    for _ in range(num_samples):
        x = random.uniform(-1, 1)
        y = random.uniform(-1, 1)
        distance_squared = x**2 + y**2

        if distance_squared <= 1:
            num_points_inside_circle += 1

    pi_estimate = 4 * num_points_inside_circle / num_samples
    return pi_estimate


if __name__ == '__main__':
    main()

