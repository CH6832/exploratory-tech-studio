#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the
value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.

Assume the environment does not allow you to store 64-bit integers (signed or unsigned).

Example 1:
Input: x = 123
Output: 321

Example 2:
Input: x = -123
Output: -321

Example 3:
Input: x = 120
Output: 21

Constraints:
    -231 <= x <= 231 - 1
"""


def main() -> None:
    """Driving code."""
    sol = Solution()
    print(sol.reverse(123))
    print(sol.reverse(-123))
    print(sol.reverse(120))

    return None


class Solution:
    def reverse(self, x: int) -> int:
        
        MIN = -2**31
        MAX = 2**31 - 1

        reversed_int: int = 0

        is_negative: bool = False
        if x < 0:
            is_negative = True
        
        x = abs(x)

        while x is not 0:
            digit = x % 10
            x = x // 10
            if reversed_int > (MAX - digit) // 10:
                return 0
            
            reversed_int = reversed_int * 10 + digit

        if is_negative:
            reversed_int = -reversed_int

        if (reversed_int < MIN) or (reversed_int > MAX):
            return 0

        return reversed_int


if __name__ == '__main__':
    main()
