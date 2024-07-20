#include <iostream>
#include "Kruskal.h"

int main() {
    // Example usage
    std::vector<std::tuple<int, int, int>> graph = {
        {0, 1, 4}, {0, 7, 8}, {1, 2, 8}, {1, 7, 11},
        {2, 3, 7}, {2, 8, 2}, {2, 5, 4}, {3, 4, 9},
        {3, 5, 14}, {4, 5, 10}, {5, 6, 2}, {6, 7, 1}, {6, 8, 6}, {7, 8, 7}
    };

    std::vector<std::tuple<int, int, int>> minimumSpanningTree = Kruskal::findMinimumSpanningTree(graph);

    std::cout << "Minimum Spanning Tree:" << std::endl;
    for (const auto& edge : minimumSpanningTree) {
        std::cout << std::get<0>(edge) << " " << std::get<1>(edge) << " " << std::get<2>(edge) << std::endl;
    }

    return 0;
}
