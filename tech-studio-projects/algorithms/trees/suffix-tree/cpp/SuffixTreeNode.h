#include <unordered_map>

class SuffixTreeNode {
public:
    std::unordered_map<char, SuffixTreeNode*> children;
    SuffixTreeNode* suffixLink;
    int start;
    int* end;

    SuffixTreeNode(int start, int* end) {
        this->suffixLink = nullptr;
        this->start = start;
        this->end = end;
    }
};
