#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
Given a non-negative integer x, return the square root of x rounded down to the nearest integer. The returned integer should be non-negative as well.

You must not use any built-in exponent function or operator.
For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.

Example 1:
Input: x = 4
Output: 2
Explanation: The square root of 4 is 2, so we return 2.

Example 2:
Input: x = 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since we round it down to the nearest integer, 2 is returned.

Constraints:
0 <= x <= 231 - 1

Time Complexity:
O(LogN)
Space Complexity:
O(1)
"""

def main() -> None:
    """Driving code"""
    sol = Solution()
    print(sol.mySqrt(4))
    print(sol.mySqrt(8))

    return None


class Solution:
    def mySqrt(self, x: int) -> int:
        if x == 0 or x == 1:
            return x

        l = 1
        r = x // 2

        res = 0
        # The while loop sets the search range here.
        # If it is l becomes greater then r, then there
        # is no search range.
        while l <= r:
            # m is the middle point for the search.
            m = (l + r) // 2
            if m * m == x:
                # Exact square root is found.
                return m
            elif m * m < x:
                # m is to small to be apotential square root
                res = m
                # Focus on larger numbers.
                l = m + 1
            else: # (m * m > x)
                # Focus on smaller numbers.
                r = m - 1

        return res


if __name__ == '__main__':
    main()
