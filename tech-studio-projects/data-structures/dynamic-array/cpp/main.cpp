#include <iostream>
#include "DynamicArray.h"

int main() {
    DynamicArrayList list;

    // Append elements
    list.append(1);
    list.append(2);
    list.append(3);
    list.append(4);

    // Insert element at index 2
    list.insert(2, 5);

    // Remove element at index 1
    list.remove(1);

    // Display list contents
    std::cout << "List contents:" << std::endl;
    for (std::size_t i = 0; i < list.size(); ++i) {
        std::cout << list.get(i) << " ";
    }
    std::cout << std::endl;

    return 0;
}
