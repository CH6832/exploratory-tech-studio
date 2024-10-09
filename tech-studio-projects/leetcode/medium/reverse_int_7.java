public class Solution {
    public int reverse(int x) {
        int MIN = Integer.MIN_VALUE;
        int MAX = Integer.MAX_VALUE;

        int reversedInt = 0;
        boolean isNegative = false;

        if (x < 0) {
            isNegative = true;
            x = -x;
        }

        while (x != 0) {
            int digit = x % 10;
            x /= 10;

            // Check for overflow
            if (reversedInt > (MAX - digit) / 10) {
                return 0;
            }

            reversedInt = reversedInt * 10 + digit;
        }

        if (isNegative) {
            reversedInt = -reversedInt;
        }

        return (reversedInt < MIN || reversedInt > MAX) ? 0 : reversedInt;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.reverse(123));   // Output: 321
        System.out.println(sol.reverse(-123));  // Output: -321
        System.out.println(sol.reverse(120));   // Output: 21
    }
}
