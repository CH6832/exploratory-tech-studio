#ifndef SUFFIXARRAY_H
#define SUFFIXARRAY_H

#include <string>
#include <vector>

/**
 * @brief SuffixArray class representing a suffix array data structure.
 */
class SuffixArray {
private:
    std::string text;
    std::vector<int> suffixArray;

public:
    SuffixArray(const std::string& text);

    void constructSuffixArray();
    void displaySuffixArray() const;
};

#endif // SUFFIXARRAY_H
