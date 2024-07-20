// bubbleSort.cpp
// 

#include "bubbleSort.h"
#include <iostream>
#include <vector>
#include <algorithm>
#include <chrono>
#include <thread>

void bubble_sort(std::vector<int>& arr) {
    int n = arr.size();
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n - i - 1; ++j) {
            if (arr[j] > arr[j + 1]) {
                std::swap(arr[j], arr[j + 1]);
                // Visualization (pause for a short duration)
                std::this_thread::sleep_for(std::chrono::milliseconds(100));
                // Output the current state of the array
                for (int k = 0; k < n; ++k) {
                    std::cout << arr[k] << " ";
                }
                std::cout << std::endl;
            }
        }
    }
}
