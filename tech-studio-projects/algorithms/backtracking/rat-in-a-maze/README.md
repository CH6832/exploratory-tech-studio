# Rat in a Maze Algorithm

This folder contains the code implementation and explanation for the Rat in a Maze problem algorithm.

## Description

The Rat in a Maze problem is a classic backtracking algorithm where you help a rat navigate from a starting point to an exit within a maze represented by a 2D grid. The rat can only move up, down, left, or right, and cannot visit walls or go outside the maze boundaries.

## Algorithm

This implementation utilizes a recursive backtracking approach to explore different paths within the maze. The algorithm checks if the current position is the exit, and if not, it explores valid neighboring cells (not walls or already visited). If a valid path leads to the exit, success is achieved. Otherwise, the algorithm backtracks and tries a different direction.

* **Time Complexity:** O(exp(m*n)) in the worst case, where m and n are the dimensions of the maze grid. This is due to the exponential nature of exploring all possible paths. However, in practice, the complexity can be lower depending on the maze structure.

* **Space Complexity:** O(m*n) due to the recursion stack space used to keep track of the explored path.

## Applications

Combinatorial Optimization, Constraint Satisfaction Problems, Puzzle Solving, Graph Theory, Artificial Intelligence, Bioinformatics, Mathemtatical Problems, Robotics, Cryptography, Software Testing