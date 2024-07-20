#include <iostream>
#include "SkipList.h"

int main() {
    // Create a skip list with maximum level 4
    SkipList<int> skipList(4);

    // Insert elements into the skip list
    skipList.insert(10);
    skipList.insert(20);
    skipList.insert(15);
    skipList.insert(25);

    // Display the skip list
    std::cout << "Skip List after insertion: ";
    skipList.display();

    // Remove an element from the skip list
    skipList.remove(20);

    // Display the skip list after removal
    std::cout << "Skip List after removal: ";
    skipList.display();

    // Check if the skip list contains a value
    std::cout << "Skip List contains 15: " << std::boolalpha << skipList.contains(15) << std::endl;
    std::cout << "Skip List contains 20: " << std::boolalpha << skipList.contains(20) << std::endl;

    return 0;
}
