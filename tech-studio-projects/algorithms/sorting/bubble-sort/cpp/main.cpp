// main.cpp
// 

#include "bubbleSort.h"
#include <vector>
#include <iostream>

int main() {
    std::vector<int> arr = {64, 34, 25, 12, 22, 11, 90};
    
    std::cout << "Unsorted array: ";
    for (int val : arr) {
        std::cout << val << " ";
    }
    std::cout << std::endl;

    bubble_sort(arr);

    std::cout << "Sorted array: ";
    for (int val : arr) {
        std::cout << val << " ";
    }
    std::cout << std::endl;

    return 0;
}
