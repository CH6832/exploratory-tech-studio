#include <iostream>
#include <vector>
#include "mergeSort.h"

/**
 * Main function to demonstrate merge sort.
 */
int main() {
    std::vector<int> arr = {38, 27, 43, 3, 9, 82, 10};
    std::cout << "Original array:" << std::endl;

    MergeSort mergeSort;
    // mergeSort.printArray(arr);

    mergeSort.sort(arr);

    std::cout << "Sorted array:" << std::endl;
    // mergeSort.printArray(arr);

    return 0;
}
