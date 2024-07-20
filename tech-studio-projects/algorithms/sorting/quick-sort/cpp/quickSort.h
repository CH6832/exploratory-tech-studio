#ifndef QUICKSORT_H
#define QUICKSORT_H

#include <vector>

/**
 * QuickSort class that implements the quick sort algorithm.
 */
class QuickSort {
public:
    /**
     * Sorts a vector using the quick sort algorithm.
     * @param arr The vector to be sorted.
     */
    void sort(std::vector<int>& arr);

private:
    /**
     * Recursively sorts a subvector using the quick sort algorithm.
     * @param arr The vector to be sorted.
     * @param low The lowest index of the subvector.
     * @param high The highest index of the subvector.
     */
    void quickSort(std::vector<int>& arr, int low, int high);

    /**
     * Partitions the vector around a pivot element and returns the pivot index.
     * @param arr The vector to be partitioned.
     * @param low The lowest index of the subvector.
     * @param high The highest index of the subvector.
     * @return The pivot index.
     */
    int partition(std::vector<int>& arr, int low, int high);
};

#endif
