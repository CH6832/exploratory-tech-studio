#include "SuffixArray.h"
#include <algorithm>

SuffixArray::SuffixArray(const std::string& text) : text(text) {}

void SuffixArray::constructSuffixArray() {
    // Generate suffixes of the text
    int n = text.length();
    std::vector<std::pair<std::string, int>> suffixes;
    for (int i = 0; i < n; i++) {
        suffixes.emplace_back(text.substr(i), i);
    }

    // Sort the suffixes lexicographically
    std::sort(suffixes.begin(), suffixes.end());

    // Populate the suffix array with the sorted suffix indices
    suffixArray.resize(n);
    for (int i = 0; i < n; i++) {
        suffixArray[i] = suffixes[i].second;
    }
}

void SuffixArray::displaySuffixArray() const {
    // Display the suffix array
    for (int i : suffixArray) {
        std::cout << i << " ";
    }
    std::cout << std::endl;
}
