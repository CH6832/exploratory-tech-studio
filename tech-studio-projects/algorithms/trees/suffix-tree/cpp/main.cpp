#include "SuffixTree.h"

int main() {
    std::string text = "banana";
    SuffixTree suffixTree(text);
    suffixTree.printSuffixes();
    return 0;
}
