#include "quickSort.h"

void QuickSort::sort(std::vector<int>& arr) {
    quickSort(arr, 0, arr.size() - 1);
}

void QuickSort::quickSort(std::vector<int>& arr, int low, int high) {
    if (low < high) {
        // Partition the vector and get the pivot index
        int pivotIndex = partition(arr, low, high);

        // Recursively sort the subvectors
        quickSort(arr, low, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, high);
    }
}

int QuickSort::partition(std::vector<int>& arr, int low, int high) {
    int pivot = arr[high]; // Choose the last element as the pivot
    int i = low - 1; // Index of smaller element

    for (int j = low; j < high; j++) {
        // If current element is smaller than or equal to pivot
        if (arr[j] <= pivot) {
            i++;

            // Swap arr[i] and arr[j]
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    // Swap arr[i+1] and arr[high] (or pivot)
    int temp = arr[i + 1];
    arr[i + 1] = arr[high];
    arr[high] = temp;

    return i + 1;
}
