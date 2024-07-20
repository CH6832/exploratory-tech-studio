#include <iostream>
#include <vector>
#include "countingSort.h"

/**
 * Main function to demonstrate the Counting Sort algorithm.
 *
 * @return Exit status.
 */
int main() {
    std::vector<char> inputArr = {'g', 'e', 'e', 'k', 's', 'f', 'o', 'r', 'g', 'e', 'e', 'k', 's'};
    std::vector<char> sortedArr = countingSort(inputArr);

    std::cout << "Sorted array: ";
    for (char ch : sortedArr) {
        std::cout << ch;
    }
    std::cout << std::endl;

    return 0;
}
