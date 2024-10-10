#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
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

"""


def main() -> None:
    """Driving code."""
    sol = Solution()
    print(sol.findMedianSortedArrays([1,3],[2]))
    print(sol.findMedianSortedArrays([1,2],[3,4]))

    return None


class Solution:
    def findMedianSortedArrays(self, nums1: list[int], nums2: list[int]) -> float:

        # Merge the arrays
        a: int = 0
        b: int = 0

        final_array: list = []

        while a < len(nums1) and b < len(nums2):
            if nums1[a] <= nums2[b]:
                final_array.append(nums1[a])
                a += 1
            else:
                final_array.append(nums2[b])
                b += 1
        
        while a < len(nums1):
            final_array.append(nums1[a])
            a += 1

        while b < len(nums2):
            final_array.append(nums2[b])
            b += 1

        # Find the median of it
        arr_len = len(final_array)
        if arr_len % 2 == 1:
            return final_array[arr_len // 2]
        else:
            return ((final_array[arr_len // 2]) + (final_array[arr_len // 2] - 1)) / 2


if __name__ == '__main__':
    main()
