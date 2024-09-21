#include <iostream>
#include <vector>
#include "insertionSort.h"

int main() {
    std::vector<int> arr = {12, 11, 13, 5, 6};
    std::cout << "Original array:" << std::endl;

    InsertionSort insertionSort;
    insertionSort.sort(arr);

    std::cout << "Sorted array:" << std::endl;
    // insertionSort.printArray(arr);

    return 0;
}
