#include <iostream>
#include "bucketSort.h"

int main() {
    // Driving code
    std::vector<double> arr = {0.78, 0.17, 0.39, 0.26, 0.72, 0.94, 0.21, 0.12, 0.23, 0.68};
    BucketSort::bucketSort(arr);

    // Print the sorted array
    std::cout << "Sorted array: ";
    for (double num : arr) {
        std::cout << num << " ";
    }
    std::cout << std::endl;

    return 0;
}
