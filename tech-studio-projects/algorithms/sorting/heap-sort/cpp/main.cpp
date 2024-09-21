#include <iostream>
#include "heapSort.h"

void printArray(const std::vector<int>& arr) {
    for (int num : arr) {
        std::cout << num << " ";
    }
    std::cout << std::endl;
}

int main() {
    std::vector<int> arr = {12, 11, 13, 5, 6, 7};
    std::cout << "Original array:" << std::endl;
    printArray(arr);

    HeapSort heapSort;
    heapSort.sort(arr);

    std::cout << "Sorted array:" << std::endl;
    printArray(arr);

    return 0;
}
