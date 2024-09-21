#include <iostream>
#include "KDTree.h"

int main() {
    std::vector<std::vector<int>> points = {
        {2, 3},
        {5, 4},
        {9, 6},
        {4, 7},
        {8, 1},
        {7, 2}
    };

    KDTree kdTree(points);
    std::vector<int> target = {5, 5};
    std::vector<int> nearestNeighbor = kdTree.nearestNeighbor(target);
    std::cout << "The nearest neighbor of [5, 5] is [" << nearestNeighbor[0] << ", " << nearestNeighbor[1] << "]" << std::endl;

    return 0;
}
