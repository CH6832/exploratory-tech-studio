#include "countingSort.h"
#include <vector>
#include <cstring>

/**
 * Sorts an array of characters using the Counting Sort algorithm.
 *
 * @param arr The vector of characters to be sorted.
 * @return The sorted vector of characters.
 */
std::vector<char> countingSort(const std::vector<char>& arr) {
    int n = arr.size();
    std::vector<char> output(n);
    int count[256];
    std::memset(count, 0, sizeof(count));

    // Store count of each character
    for (char ch : arr) {
        count[static_cast<unsigned char>(ch)]++;
    }

    // Change count[i] so that count[i] now contains actual position of this character in output array
    for (int i = 1; i < 256; ++i) {
        count[i] += count[i - 1];
    }

    // Build the output character array
    for (int i = n - 1; i >= 0; --i) {
        output[count[static_cast<unsigned char>(arr[i])] - 1] = arr[i];
        count[static_cast<unsigned char>(arr[i])]--;
    }

    return output;
}
