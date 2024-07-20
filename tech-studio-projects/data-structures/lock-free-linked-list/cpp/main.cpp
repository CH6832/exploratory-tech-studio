#include <iostream>
#include "LLFL.h"

int main() {
    LockFreeLinkedList list;

    // Insert elements into the linked list
    list.insert(10);
    list.insert(20);
    list.insert(15);

    // Display the linked list
    std::cout << "Linked List after insertion: ";
    list.display();

    // Remove an element from the linked list
    list.remove(20);

    // Display the linked list after removal
    std::cout << "Linked List after removal: ";
    list.display();

    // Check if the linked list contains a value
    std::cout << "Linked List contains 15: " << std::boolalpha << list.contains(15) << std::endl;
    std::cout << "Linked List contains 25: " << std::boolalpha << list.contains(25) << std::endl;

    return 0;
}
