#include <iostream>
#include "FenwickTree.h"

int main() {
    // Example usage
    std::vector<int> nums = {1, 3, 5, 7, 9, 11, 13, 15};
    FenwickTree fenwickTree(nums.size());
    for (int i = 0; i < nums.size(); ++i) {
        fenwickTree.update(i + 1, nums[i]);
    }

    // Print prefix sums
    for (int i = 0; i <= nums.size(); ++i) {
        std::cout << "Prefix sum up to index " << i << ": " << fenwickTree.query(i) << std::endl;
    }

    return 0;
}
