#include <iostream>
#include "insertionSort.h"

void InsertionSort::sort(std::vector<int>& arr) {
    std::cout << "Unsorted: ";
    printArray(arr);

    // Iterate over the array to be sorted
    for (size_t i = 1; i < arr.size(); ++i) {
        int x = arr[i];  // Get each element
        int j = i - 1;   // Get one position before x

        // Shift elements until reaching index 0 or getting an element smaller than x
        while (j >= 0 && arr[j] > x) {
            arr[j + 1] = arr[j];
            j--;
        }
        // Place x in its correct position
        arr[j + 1] = x;
    }

    std::cout << "Sorted: ";
    printArray(arr);
}

void InsertionSort::printArray(const std::vector<int>& arr) {
    for (int num : arr) {
        std::cout << num << " ";
    }
    std::cout << std::endl;
}
