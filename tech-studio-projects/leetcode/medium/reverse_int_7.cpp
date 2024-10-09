#include <iostream>
#include <limits>

class Solution {
public:
    int reverse(int x) {
        const int MIN = std::numeric_limits<int>::min();
        const int MAX = std::numeric_limits<int>::max();

        int reversedInt = 0;
        bool isNegative = false;

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
};

int main() {
    Solution sol;
    std::cout << sol.reverse(123) << std::endl;   // Output: 321
    std::cout << sol.reverse(-123) << std::endl;  // Output: -321
    std::cout << sol.reverse(120) << std::endl;   // Output: 21

    return 0;
}
