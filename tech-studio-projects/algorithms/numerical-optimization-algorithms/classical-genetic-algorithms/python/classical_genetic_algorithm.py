#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""classical_genetic_algorithm.py

The classical genetic algorithm mimics the process of natural selection and evolution to find the optimal solution to a given problem. Here's how the algorithm works:

    Initialization: Generate an initial population of binary chromosomes randomly.

    Fitness Function: Define a fitness function that evaluates how well each chromosome solves the problem. In this example, the fitness function computes the number of matching bits with the target binary sequence.

    Selection: Select the fittest individuals (parents) from the population based on their fitness values.

    Crossover: Produce offspring through crossover of selected parents. This creates new solutions by combining the genetic material of the parents.

    Mutation: Introduce random changes (mutations) to the offspring chromosomes to maintain genetic diversity in the population.

    Evolution: Repeat the selection, crossover, and mutation steps for a fixed number of generations.

    Termination: After a certain number of generations, return the fittest chromosome found in the final population.

The example usage in the if __name__ == "__main__": block demonstrates how to use the genetic algorithm to evolve towards a target binary sequence. Adjustments to parameters such as the target sequence, number of generations, population size, and mutation rate can be made based on the specific problem being solved.

.. _PEP 0000:
    https://peps.python.org/pep-0000/
"""


import random
from typing import List, Callable, Tuple


def main() -> None:
    """Driving code and main function"""
    # Example: Evolution towards a target binary sequence
    target_sequence = [1, 0, 1, 1, 0, 1, 0, 1]  # Define the target sequence
    num_generations = 100  # Number of generations
    population_size = 100  # Size of the population
    chromosome_length = len(target_sequence)  # Length of each chromosome
    mutation_rate = 0.01  # Probability of mutation for each bit

    fittest_chromosome = genetic_algorithm(target_sequence, chromosome_length, population_size, num_generations, mutation_rate)
    print("Fittest chromosome:", fittest_chromosome)
 
    return None


def generate_population(population_size: int, chromosome_length: int) -> List[List[int]]:
    """Generate an initial population of binary chromosomes.

    Args:
        population_size (int): Number of individuals in the population.
        chromosome_length (int): Length of each chromosome.

    Returns:
        List[List[int]]: Initial population represented as a list of binary chromosomes.
    """
    return [[random.randint(0, 1) for _ in range(chromosome_length)] for _ in range(population_size)]

def fitness_function(chromosome: List[int], target: List[int]) -> int:
    """Compute the fitness value of a chromosome.

    Args:
        chromosome (List[int]): Binary chromosome to evaluate.
        target (List[int]): Target binary sequence.

    Returns:
        int: Fitness value of the chromosome (number of matching bits with the target).
    """
    return sum(c == t for c, t in zip(chromosome, target))

def selection(population: List[List[int]], fitness_fn: Callable[[List[int]], int], target: List[int], num_parents: int) -> List[List[int]]:
    """Perform selection of parents based on fitness.

    Args:
        population (List[List[int]]): Current population.
        fitness_fn (Callable[[List[int]], int]): Fitness function.
        target (List[int]): Target binary sequence.
        num_parents (int): Number of parents to select.

    Returns:
        List[List[int]]: Selected parent chromosomes.
    """
    return sorted(population, key=lambda x: fitness_fn(x, target), reverse=True)[:num_parents]

def crossover(parents: List[List[int]], num_offspring: int) -> List[List[int]]:
    """Perform crossover to produce offspring.

    Args:
        parents (List[List[int]]): Selected parent chromosomes.
        num_offspring (int): Number of offspring to generate.

    Returns:
        List[List[int]]: Offspring chromosomes resulting from crossover.
    """
    offspring = []
    for _ in range(num_offspring):
        parent1, parent2 = random.sample(parents, 2)
        crossover_point = random.randint(1, len(parent1) - 1)
        child = parent1[:crossover_point] + parent2[crossover_point:]
        offspring.append(child)
    return offspring

def mutation(offspring: List[List[int]], mutation_rate: float) -> List[List[int]]:
    """Perform mutation on offspring.

    Args:
        offspring (List[List[int]]): Offspring chromosomes.
        mutation_rate (float): Probability of mutation for each bit.

    Returns:
        List[List[int]]: Mutated offspring chromosomes.
    """
    for i in range(len(offspring)):
        for j in range(len(offspring[i])):
            if random.random() < mutation_rate:
                offspring[i][j] = 1 - offspring[i][j]  # Flip the bit
    return offspring

def genetic_algorithm(target: List[int], chromosome_length: int, population_size: int, num_generations: int, mutation_rate: float) -> List[int]:
    """Perform a classical genetic algorithm to evolve towards a target binary sequence.

    Args:
        target (List[int]): Target binary sequence to evolve towards.
        chromosome_length (int): Length of each chromosome.
        population_size (int): Number of individuals in the population.
        num_generations (int): Number of generations to run the algorithm.
        mutation_rate (float): Probability of mutation for each bit.

    Returns:
        List[int]: The fittest chromosome found after the specified number of generations.
    """
    population = generate_population(population_size, chromosome_length)
    for _ in range(num_generations):
        parents = selection(population, fitness_function, target, population_size // 2)
        offspring = crossover(parents, population_size - len(parents))
        offspring = mutation(offspring, mutation_rate)
        population = parents + offspring
    return max(population, key=lambda x: fitness_function(x, target))



if __name__ == '__main__':
    main()

