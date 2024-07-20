#include <iostream>
#include "DoubleLinkedList.h"

int main() {
    DoublyLinkedList list;

    std::cout << "Inserting elements at the front..." << std::endl;
    list.insertFront(1);
    list.insertFront(2);
    list.insertFront(3);
    list.display(); // Output: 3 2 1

    std::cout << "Inserting elements at the back..." << std::endl;
    list.insertBack(4);
    list.insertBack(5);
    list.insertBack(6);
    list.display(); // Output: 3 2 1 4 5 6

    std::cout << "Removing elements from the front..." << std::endl;
    list.removeFront();
    list.removeFront();
    list.display(); // Output: 1 4 5 6

    std::cout << "Removing elements from the back..." << std::endl;
    list.removeBack();
    list.removeBack();
    list.display(); // Output: 1

    return 0;
}
