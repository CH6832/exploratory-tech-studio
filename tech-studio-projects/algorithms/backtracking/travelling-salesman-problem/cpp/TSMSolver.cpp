#include <iostream>
#include <vector>
#include <cmath>
#include <string>
#include <algorithm>
#include <limits>
#include "TSMSolver.h"

using namespace std;

int TSMSolver::travellingSalesmanProblem(const std::vector<std::vector<int>>& graph, int s) {
    int V = graph.size(); // Number of vertices

    // Store all vertices apart from the source vertex
    std::vector<int> vertex(V);
    for (int i = 0; i < V; i++) {
        if (i != s) {
            vertex.push_back(i);
        }
    }

    // Store minimum weight Hamiltonian Cycle
    int minPath = std::numeric_limits<int>::max();

    // Get all permutations of remaining vertices
    std::vector<std::vector<int>> permutations;
    do {
        // Store current path weight (cost)
        int currentPathWeight = 0;

        // Compute current path weight
        int k = s;
        for (int j : vertex) {
            currentPathWeight += graph[k][j];
            k = j;
        }
        currentPathWeight += graph[k][s];

        // Update minimum path weight
        minPath = std::min(minPath, currentPathWeight);
    } while (std::next_permutation(vertex.begin(), vertex.end()));

    return minPath;
}

