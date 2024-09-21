#ifndef INSERTIONSORT_H
#define INSERTIONSORT_H

#include <vector>

/**
 * InsertionSort class that implements the insertion sort algorithm.
 */
class InsertionSort {
public:
    /**
     * Sorts a vector using the insertion sort algorithm.
     * @param arr The vector to be sorted.
     */
    void sort(std::vector<int>& arr);

private:
    /**
     * Prints the elements of a vector.
     * @param arr The vector to be printed.
     */
    void printArray(const std::vector<int>& arr);
};

#endif
