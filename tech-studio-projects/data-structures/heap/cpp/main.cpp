#include <iostream>
#include "Heap.h"

int main() {
    Heap maxHeap;

    // Insert elements into the heap
    maxHeap.insert(10);
    maxHeap.insert(20);
    maxHeap.insert(15);
    maxHeap.insert(30);
    maxHeap.insert(5);

    // Display the heap
    std::cout << "Heap after insertion: ";
    maxHeap.display();

    // Get and remove the maximum element from the heap
    int max = maxHeap.extractMax();
    std::cout << "Extracted maximum element: " << max << std::endl;

    // Display the heap after extraction
    std::cout << "Heap after extraction: ";
    maxHeap.display();

    return 0;
}
