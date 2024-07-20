#include <iostream>
#include "DisjointSet.h"

int main() {
    // Example usage of DisjointSet
    DisjointSet ds(5);
    ds.makeSet(0);
    ds.makeSet(1);
    ds.makeSet(2);
    ds.makeSet(3);
    ds.makeSet(4);

    ds.unionSets(0, 1);
    ds.unionSets(2, 3);
    ds.unionSets(0, 4);

    for (int i = 0; i < 5; ++i) {
        std::cout << "Parent of " << i << ": " << ds.find(i) << std::endl;
    }

    return 0;
}
