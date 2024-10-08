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
