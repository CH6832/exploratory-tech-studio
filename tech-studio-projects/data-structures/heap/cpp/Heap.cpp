#include "Heap.h"
#include <iostream>

Heap::Heap() {}

Heap::~Heap() {}

void Heap::insert(int value) {
    heap.push_back(value);
    heapifyUp();
}

int Heap::getMax() const {
    if (!isEmpty()) {
        return heap[0];
    }
    return -1; // Heap is empty
}

int Heap::extractMax() {
    if (!isEmpty()) {
        int max = heap[0];
        heap[0] = heap.back();
        heap.pop_back();
        heapifyDown();
        return max;
    }
    return -1; // Heap is empty
}

bool Heap::isEmpty() const {
    return heap.empty();
}

void Heap::display() const {
    for (int value : heap) {
        std::cout << value << " ";
    }
    std::cout << std::endl;
}

void Heap::heapifyUp() {
    int index = heap.size() - 1;
    while (index > 0 && heap[index] > heap[(index - 1) / 2]) {
        std::swap(heap[index], heap[(index - 1) / 2]);
        index = (index - 1) / 2;
    }
}

void Heap::heapifyDown() {
    int index = 0;
    while (2 * index + 1 < heap.size()) {
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
        int largerChild = leftChild;

        if (rightChild < heap.size() && heap[rightChild] > heap[leftChild]) {
            largerChild = rightChild;
        }

        if (heap[index] < heap[largerChild]) {
            std::swap(heap[index], heap[largerChild]);
            index = largerChild;
        }
        else {
            break;
        }
    }
}
