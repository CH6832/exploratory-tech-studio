#ifndef HEAPSORT_H
#define HEAPSORT_H

#include <vector>

class HeapSort {
public:
    /**
     * Sorts an array using the heap sort algorithm.
     * @param arr The vector to be sorted.
     */
    void sort(std::vector<int>& arr);

private:
    /**
     * Heapifies a subtree rooted at index i.
     * @param arr The vector representing the heap.
     * @param n The size of the heap.
     * @param i The index of the root of the subtree.
     */
    void heapify(std::vector<int>& arr, int n, int i);
};

#endif
