#ifndef HEAP_H
#define HEAP_H

#include <vector>

/**
 * @brief Heap class representing a max-heap data structure.
 */
class Heap {
private:
    std::vector<int> heap; // Internal representation of the heap

    void heapifyUp(); // Restore heap property from bottom to top
    void heapifyDown(); // Restore heap property from top to bottom

public:
    Heap();
    ~Heap();

    void insert(int value); // Insert a value into the heap
    int getMax() const; // Get the maximum value from the heap
    int extractMax(); // Extract and remove the maximum value from the heap
    bool isEmpty() const; // Check if the heap is empty
    void display() const; // Display the heap elements
};

#endif // HEAP_H
