#include "SplayTree.h"
#include <iostream>

int main() {
    SplayTree splayTree;
    int keys[] = {7, 3, 10, 2, 6, 9, 11, 1, 5, 8, 12};
    int n = sizeof(keys) / sizeof(keys[0]);

    for (int i = 0; i < n; ++i)
        splayTree.insert(keys[i]);

    std::cout << std::boolalpha;
    std::cout << splayTree.search(8) << std::endl;  // Output: true
    std::cout << splayTree.search(4) << std::endl;  // Output: false

    return 0;
}
