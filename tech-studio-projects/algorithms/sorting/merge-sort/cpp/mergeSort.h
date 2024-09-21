#ifndef MERGESORT_H
#define MERGESORT_H

#include <vector>

/**
 * MergeSort class that implements the merge sort algorithm.
 */
class MergeSort {
public:
    /**
     * Sorts a vector using the merge sort algorithm.
     * @param arr The vector to be sorted.
     */
    void sort(std::vector<int>& arr);

private:
    /**
     * Merges two subarrays into the original array.
     * @param arr The original array that contains the merged result.
     * @param leftPart The left subarray.
     * @param rightPart The right subarray.
     */
    void merge(std::vector<int>& arr, std::vector<int>& leftPart, std::vector<int>& rightPart);

    /**
     * Prints the elements of a vector.
     * @param arr The vector to be printed.
     */
    void printArray(const std::vector<int>& arr);
};

#endif
