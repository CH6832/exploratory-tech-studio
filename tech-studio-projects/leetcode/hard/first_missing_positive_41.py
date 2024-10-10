#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
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
"""


def main():
    sol = Solution()
    print(sol.firstMissingPositive([1,2,0]))
    print(sol.firstMissingPositive([3,4,-1,1]))
    print(sol.firstMissingPositive([7,8,9,11,12]))


class Solution:
    def firstMissingPositive(self, nums: list[int]) -> int:
        n = len(nums)

        new_nums = [0] * n
        for num in nums:
            if 1 <= num <= n:
                new_nums[num - 1] = num

        for i in range(n):
            if new_nums[i] == 0:
                return i + 1

        return n + 1


if __name__ == '__main__':
    main()
