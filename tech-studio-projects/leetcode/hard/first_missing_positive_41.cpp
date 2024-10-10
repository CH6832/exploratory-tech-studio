/**
Given an unsorted integer array nums. Return the smallest positive integer that is not present in nums.

You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.

Example 1:
Input: nums = [1,2,0]
Output: 3
Explanation: The numbers in the range [1,2] are all in the array.

Example 2:
Input: nums = [3,4,-1,1]
Output: 2
Explanation: 1 is in the array but 2 is missing.

Example 3:
Input: nums = [7,8,9,11,12]
Output: 1
Explanation: The smallest positive integer 1 is missing.

Constraints:
    1 <= nums.length <= 105
    -231 <= nums[i] <= 231 - 1
*/

#include <iostream>
#include <vector>

class Solution {
public:
    int firstMissingPositive(std::vector<int>& nums) {
        int n = nums.size();
        std::vector<int> new_nums(n, 0);
        
        // Fill the new_nums with valid positive integers
        for (int num : nums) {
            if (num > 0 && num <= n) {
                new_nums[num - 1] = num; // Place num in the correct index (num - 1)
            }
        }

        // Find the first missing positive integer
        for (int i = 0; i < n; ++i) {
            if (new_nums[i] == 0) {
                return i + 1; // Return the first missing positive integer
            }
        }

        return n + 1; // If all numbers from 1 to n are present, return n + 1
    }
};

int main() {
    Solution sol;
    std::cout << sol.firstMissingPositive(std::vector<int>{1, 2, 0}) << std::endl;      // Output: 3
    std::cout << sol.firstMissingPositive(std::vector<int>{3, 4, -1, 1}) << std::endl; // Output: 2
    std::cout << sol.firstMissingPositive(std::vector<int>{7, 8, 9, 11, 12}) << std::endl; // Output: 1

    return 0;
}
