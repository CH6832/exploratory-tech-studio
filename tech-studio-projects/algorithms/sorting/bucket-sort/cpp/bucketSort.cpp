#include "bucketSort.h"
#include <iostream>
#include <vector>
#include <algorithm>

void BucketSort::bucketSort(std::vector<double>& arr) {
    int n = arr.size();
    if (n <= 0)
        return;

    // Create empty buckets
    std::vector<std::vector<double>> buckets(n);

    // Initial array
    std::cout << "Initial array: ";
    for (double num : arr) {
        std::cout << num << " ";
    }
    std::cout << std::endl;

    // Distribute array elements into buckets
    for (double num : arr) {
        int bucketIndex = static_cast<int>(num * n);
        buckets[bucketIndex].push_back(num);
        std::cout << "Element " << num << " -> Bucket " << bucketIndex << std::endl;
    }

    // Print buckets after distribution
    std::cout << "\nBuckets after distribution:" << std::endl;
    for (int i = 0; i < n; i++) {
        std::cout << "Bucket " << i << ": ";
        for (double num : buckets[i]) {
            std::cout << num << " ";
        }
        std::cout << std::endl;
    }

    // Sort individual buckets
    for (int i = 0; i < n; i++) {
        std::sort(buckets[i].begin(), buckets[i].end());
    }

    // Print buckets after sorting
    std::cout << "\nBuckets after sorting:" << std::endl;
    for (int i = 0; i < n; i++) {
        std::cout << "Bucket " << i << ": ";
        for (double num : buckets[i]) {
            std::cout << num << " ";
        }
        std::cout << std::endl;
    }

    // Concatenate all buckets into the original array
    int index = 0;
    for (int i = 0; i < n; i++) {
        for (double num : buckets[i]) {
            arr[index++] = num;
        }
    }
}
