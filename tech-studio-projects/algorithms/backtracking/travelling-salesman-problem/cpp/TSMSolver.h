#ifndef MAZESOLVER_H
#define MAZESOLVER_H

#include <iostream>
#include <vector>
#include <cmath>
#include <string>

using namespace std;

class TSMSolver {
public:
    /**
     * @brief Solves the Travelling Salesman Problem (TSP) using brute-force permutation.
     *
     * Given a weighted graph represented as an adjacency matrix, this method finds the minimum Hamiltonian cycle,
     * i.e., a cycle that visits each vertex exactly once and returns to the starting vertex, with the
     * minimum total weight.
     *
     * @param graph The weighted graph represented as an adjacency matrix.
     * @param s The starting vertex for the salesman.
     * @return The minimum path weight.
     */
    int travellingSalesmanProblem(const std::vector<std::vector<int>>& graph, int s);
};

#endif // TSMSOLVER_H

