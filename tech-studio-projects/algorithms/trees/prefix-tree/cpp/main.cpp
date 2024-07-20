#include <iostream>
#include "Trie.h"

int main() {
    Trie trie;
    std::vector<std::string> words = {"apple", "banana", "apricot", "app", "bat", "cat", "car"};
    for (std::string word : words) {
        trie.insert(word);
    }

    std::cout << trie.search("apple") << std::endl;    // Output: 1
    std::cout << trie.search("banana") << std::endl;   // Output: 1
    std::cout << trie.search("apricot") << std::endl;  // Output: 1
    std::cout << trie.search("app") << std::endl;      // Output: 1
    std::cout << trie.search("bat") << std::endl;      // Output: 1
    std::cout << trie.search("cat") << std::endl;      // Output: 1
    std::cout << trie.search("car") << std::endl;      // Output: 1
    std::cout << trie.search("ap") << std::endl;       // Output: 0
    std::cout << trie.search("ba") << std::endl;       // Output: 0
    std::cout << trie.search("ca") << std::endl;       // Output: 0
    std::cout << trie.search("b") << std::endl;        // Output: 0

    return 0;
}
