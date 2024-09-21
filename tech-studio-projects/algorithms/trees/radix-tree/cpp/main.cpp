// Main.cpp

#include "RadixTree.h"
#include <iostream>
#include <vector>

int main() {
    RadixTree radixTree;
    std::vector<std::string> words = {"apple", "banana", "apricot", "app", "bat", "cat", "car"};
    for (const std::string& word : words) {
        radixTree.insert(word);
    }

    std::cout << std::boolalpha;
    std::cout << radixTree.search("apple") << std::endl;    // Output: true
    std::cout << radixTree.search("banana") << std::endl;   // Output: true
    std::cout << radixTree.search("apricot") << std::endl;  // Output: true
    std::cout << radixTree.search("app") << std::endl;      // Output: true
    std::cout << radixTree.search("bat") << std::endl;      // Output: true
    std::cout << radixTree.search("cat") << std::endl;      // Output: true
    std::cout << radixTree.search("car") << std::endl;      // Output: true
    std::cout << radixTree.search("ap") << std::endl;       // Output: false
    std::cout << radixTree.search("ba") << std::endl;       // Output: false
    std::cout << radixTree.search("ca") << std::endl;       // Output: false
    std::cout << radixTree.search("b") << std::endl;        // Output: false

    return 0;
}
