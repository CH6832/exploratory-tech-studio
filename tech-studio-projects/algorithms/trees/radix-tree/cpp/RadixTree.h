// RadixTree.h

#include "RadixNode.h"

/**
 * Represents the radix tree data structure.
 */
class RadixTree {
private:
    RadixNode* root;

public:
    RadixTree() {
        root = new RadixNode('\0');
    }

    void insert(std::string word) {
        RadixNode* node = root;
        for (char c : word) {
            if (node->children.find(c) != node->children.end()) {
                node = node->children[c];
            } else {
                RadixNode* newNode = new RadixNode(c);
                node->children[c] = newNode;
                node = newNode;
            }
        }
    }

    bool search(std::string word) {
        RadixNode* node = root;
        for (char c : word) {
            if (node->children.find(c) != node->children.end()) {
                node = node->children[c];
            } else {
                return false;
            }
        }
        return true;
    }
};
