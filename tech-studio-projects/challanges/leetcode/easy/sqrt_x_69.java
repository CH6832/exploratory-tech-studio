/**
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
*/

class Solution {
    public int mySqrt(int x) {
        // Handle edge cases for x = 0 or 1
        if (x == 0 || x == 1) {
            return x;
        }

        int l = 1;
        int r = x / 2;
        int res = 0;

        // Binary search loop
        while (l <= r) {
            int m = (l + r) / 2;
            if (m * m == x) {
                return m; // Exact square root found
            } else if (m * m < x) {
                res = m;  // Store m as the closest smaller value
                l = m + 1; // Search in the right half
            } else {
                r = m - 1; // Search in the left half
            }
        }

        return res; // Return the integer part of sqrt(x)
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.mySqrt(4)); // Output: 2
        System.out.println(sol.mySqrt(8)); // Output: 2
    }
}
