#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""simulated_annealing.py

The Simulated Annealing (SA) algorithm is a probabilistic optimization technique inspired by the process of annealing in metallurgy. Here's how the algorithm works:

    Initialization: Start with an initial solution and an initial temperature.

    Iteration: Repeat the following steps until convergence or a maximum number of iterations:
        Generate a new solution by perturbing the current solution.
        Evaluate the objective function value for the new solution.
        If the new solution is better (lower objective function value), accept it as the current solution.
        If the new solution is worse, accept it with a certain probability based on the temperature and the magnitude of the deterioration.
        Decrease the temperature according to a cooling schedule.

    Termination: Return the best solution found and its corresponding objective function value.

In the example usage, we demonstrate how to minimize a simple objective function using Simulated Annealing. We define the objective function to minimize, provide an initial solution, set the initial temperature, cooling rate, and maximum number of iterations, and call the simulated_annealing function to obtain the best solution and its corresponding objective function value. Adjustments to parameters such as the temperature, cooling rate, and perturbation magnitude can be made based on the specific optimization problem being solved.

.. _PEP 0000:
    https://peps.python.org/pep-0000/
"""


from typing import Callable, Tuple
import numpy as np


def main() -> None:
    """Driving code and main function"""
    # Example: Minimize a simple objective function using Simulated Annealing
    def objective_function(x: np.ndarray) -> float:
        return np.sum(np.square(x))

    initial_solution = np.array([2.0, 3.0, 1.5])
    temperature = 100.0
    cooling_rate = 0.95
    max_iter = 1000

    best_solution, best_energy = simulated_annealing(objective_function, initial_solution, temperature, cooling_rate, max_iter)
    print("Best solution:", best_solution)
    print("Best energy:", best_energy)
 
    return None


def simulated_annealing(objective_function: Callable[[np.ndarray], float], initial_solution: np.ndarray, temperature: float, cooling_rate: float, max_iter: int) -> Tuple[np.ndarray, float]:
    """Solve optimization problems using Simulated Annealing.

    Args:
        objective_function (Callable[[np.ndarray], float]): Objective function to minimize.
        initial_solution (np.ndarray): Initial solution vector.
        temperature (float): Initial temperature parameter.
        cooling_rate (float): Cooling rate parameter.
        max_iter (int): Maximum number of iterations.

    Returns:
        Tuple[np.ndarray, float]: Best solution found and its corresponding objective function value.
    """
    current_solution = initial_solution.copy()
    current_energy = objective_function(current_solution)
    best_solution = current_solution.copy()
    best_energy = current_energy

    for _ in range(max_iter):
        proposed_solution = current_solution + np.random.normal(scale=0.1, size=current_solution.shape)
        proposed_energy = objective_function(proposed_solution)
        delta_energy = proposed_energy - current_energy

        if delta_energy < 0 or np.random.rand() < np.exp(-delta_energy / temperature):
            current_solution = proposed_solution
            current_energy = proposed_energy

            if current_energy < best_energy:
                best_solution = current_solution
                best_energy = current_energy

        temperature *= cooling_rate

    return best_solution, best_energy


if __name__ == '__main__':
    main()

