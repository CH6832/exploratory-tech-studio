#include <iostream>
#include <vector>
#include "quickSort.h"

/**
 * Main function to demonstrate quick sort.
 */
int main() {
    std::vector<int> arr = {38, 27, 43, 3, 9, 82, 10};
    std::cout << "Original array:" << std::endl;
    for (int num : arr) {
        std::cout << num << " ";
    }
    std::cout << std::endl;

    QuickSort quickSort;
    quickSort.sort(arr);

    std::cout << "Sorted array:" << std::endl;
    for (int num : arr) {
        std::cout << num << " ";
    }
    std::cout << std::endl;

    return 0;
}
