#include <iostream>
#include "mergeSort.h"

void MergeSort::sort(std::vector<int>& arr) {
    if (arr.size() > 1) {
        // Finding the mid of the array
        int mid = arr.size() / 2;

        // Dividing the array elements into 2 halves
        std::vector<int> leftPart(arr.begin(), arr.begin() + mid);
        std::vector<int> rightPart(arr.begin() + mid, arr.end());

        // Sorting both halves
        sort(leftPart);
        sort(rightPart);

        // Merging the sorted halves
        merge(arr, leftPart, rightPart);
    }
}

void MergeSort::merge(std::vector<int>& arr, std::vector<int>& leftPart, std::vector<int>& rightPart) {
    int i = 0, j = 0, k = 0;

    // Copy data to temp arrays leftPart[] and rightPart[]
    while (i < leftPart.size() && j < rightPart.size()) {
        if (leftPart[i] <= rightPart[j]) {
            arr[k] = leftPart[i];
            i++;
        } else {
            arr[k] = rightPart[j];
            j++;
        }
        k++;
    }

    // Checking if any element was left in leftPart[]
    while (i < leftPart.size()) {
        arr[k] = leftPart[i];
        i++;
        k++;
    }

    // Checking if any element was left in rightPart[]
    while (j < rightPart.size()) {
        arr[k] = rightPart[j];
        j++;
        k++;
    }
}

void MergeSort::printArray(const std::vector<int>& arr) {
    for (int num : arr) {
        std::cout << num << " ";
    }
    std::cout << std::endl;
}
