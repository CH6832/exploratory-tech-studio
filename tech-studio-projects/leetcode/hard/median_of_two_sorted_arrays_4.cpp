/**
Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).

Example 1:
Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.

Example 2:
Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.

Constraints:
    nums1.length == m
    nums2.length == n
    0 <= m <= 1000
    0 <= n <= 1000
    1 <= m + n <= 2000
    -106 <= nums1[i], nums2[i] <= 106
*/

#include <iostream>
#include <vector>

class Solution {
public:
    double findMedianSortedArrays(std::vector<int>& nums1, std::vector<int>& nums2) {
        // Merge the arrays
        int a = 0, b = 0;
        std::vector<int> finalArray;

        while (a < nums1.size() && b < nums2.size()) {
            if (nums1[a] <= nums2[b]) {
                finalArray.push_back(nums1[a++]);
            } else {
                finalArray.push_back(nums2[b++]);
            }
        }

        while (a < nums1.size()) {
            finalArray.push_back(nums1[a++]);
        }

        while (b < nums2.size()) {
            finalArray.push_back(nums2[b++]);
        }

        // Find the median of it
        int arrLen = finalArray.size();
        if (arrLen % 2 == 1) {
            return finalArray[arrLen / 2];
        } else {
            return (finalArray[arrLen / 2] + finalArray[arrLen / 2 - 1]) / 2.0;
        }
    }
};

int main() {
    Solution sol;
    std::vector<int> nums1 = {0, 0};
    std::vector<int> nums2 = {0, 0};
    
    std::cout << sol.findMedianSortedArrays(nums1, nums2) << std::endl;  // Should output 0.00000

    nums1 = {1, 3};
    nums2 = {2};
    std::cout << sol.findMedianSortedArrays(nums1, nums2) << std::endl;  // Should output 2.00000

    nums1 = {1, 2};
    nums2 = {3, 4};
    std::cout << sol.findMedianSortedArrays(nums1, nums2) << std::endl;  // Should output 2.50000

    return 0;
}
