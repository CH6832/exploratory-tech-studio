#include <iostream>
#include "TreeMap.h"

// Main function
int main() {
    // Example: Use TreeMap class by inserting key-value pairs and printing contents using in-order traversal
    TreeMap treeMap;
    treeMap.put(5, "five");
    treeMap.put(3, "three");
    treeMap.put(7, "seven");
    treeMap.put(2, "two");
    treeMap.put(4, "four");
    treeMap.put(6, "six");
    treeMap.put(8, "eight");

    std::vector<std::pair<int, std::string>> contents = treeMap.traverseInOrder();
    std::cout << "TreeMap contents (in-order traversal):" << std::endl;
    for (const auto& pair : contents) {
        std::cout << pair.first << ": " << pair.second << std::endl;
    }

    return 0;
}
