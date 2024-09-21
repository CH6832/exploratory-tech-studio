/*
Description:
------------
This program solves the Travelling Salesman Problem (TSP) using brute-force permutation.
Given a weighted graph represented as an adjacency matrix, it finds the minimum Hamiltonian cycle,
i.e., a cycle that visits each vertex exactly once and returns to the starting vertex, with the
minimum total weight.

Compile the program:
--------------------
Step 2: Compile each source file
    g++ -c main.cpp -o main.o
    g++ -c TSMSolver.cpp -o TSMSolver.o
Step 2: Link all object files together to create an executable
    g++ main.o TSMSolver.o -o main
*/

#include <iostream>
#include <vector>
#include <cmath>
#include <string>
#include "TSMSolver.h"

using namespace std;

int main() {
    // Weighted graph represented as an adjacency matrix
    vector<vector<int>> graph = {
        {0, 10, 15, 20},
        {10, 0, 35, 25},
        {15, 35, 0, 30},
        {20, 25, 30, 0}
    };

    int initVertex = 0; // Starting vertex for the salesman

    TSMSolver solver;
    int minimumPath = solver.travellingSalesmanProblem(graph, initVertex);

    std::cout << "Minimum path weight is: " << minimumPath << std::endl;

    return 0;
}