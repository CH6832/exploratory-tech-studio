#include <iostream>
#include "SuffixArray.h"

int main() {
    std::string text = "banana";
    SuffixArray suffixArray(text);

    // Construct the suffix array
    suffixArray.constructSuffixArray();

    // Display the suffix array
    std::cout << "Suffix Array: ";
    suffixArray.displaySuffixArray();

    return 0;
}
