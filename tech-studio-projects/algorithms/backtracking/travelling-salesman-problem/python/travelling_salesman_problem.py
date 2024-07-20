#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""travelling_salesman_problem.py

The program implements a solution to the Traveling Salesman Problem (TSP), a classic problem
in graph theory and combinatorial optimization. The goal of the TSP is to find the shortest
possible route that visits every city exactly once and returns to the original city. In this
program, cities are represented as vertices of a graph, and the distances between them are
represented by edge weights in the graph.

.. _PEP 0000:
    https://peps.python.org/pep-0000/
"""

from typing import List

V = 4


def main() -> None:
    """Driving code and main function"""
    graph = [
        [0, 10, 15, 20],
        [10, 0, 35, 25],
        [15, 35, 0, 30],
        [20, 25, 30, 0]
    ]
    init_vertex = 0    
    minimum_path = travelling_salesman_problem(graph, init_vertex)
    print(f"Minimum path weight is '{minimum_path}'")
 
    return None


def travelling_salesman_problem(graph: List[List[int]], s: int) -> int:
    """This function takes two arguments: graph, which represents the weighted graph as an adjacency matrix,
    and s, which is the starting vertex for the salesman. It computes the minimum Hamiltonian cycle (a
    cycle that visits each vertex exactly once and returns to the starting vertex) in the given graph. The
    function utilizes backtracking to explore all possible paths, calculating the total path weight for each 
    and pruning paths that exceed the current known minimum. It returns the minimum path weight, which represents 
    the shortest possible route for the salesman.

    Keyword arguments:
    graph (List[List[int]]) -- The adjacency matrix representing the weighted graph.
    s (int) -- The starting vertex for the salesman.
    """
    
    # Initialize visited array
    visited = [False] * V
    visited[s] = True
    
    # Initialize the minimum path as a very large value
    min_path = [float('inf')]

    # Backtracking function to find the minimum path
    def tsp_helper(curr_pos, count, cost):
        if count == V and graph[curr_pos][s]:
            min_path[0] = min(min_path[0], cost + graph[curr_pos][s])
            return

        for i in range(V):
            if not visited[i] and graph[curr_pos][i]:
                visited[i] = True
                tsp_helper(i, count + 1, cost + graph[curr_pos][i])
                visited[i] = False

    # Start the recursion from the initial vertex
    tsp_helper(s, 1, 0)
    
    return min_path[0]


if __name__ == '__main__':
    main()
