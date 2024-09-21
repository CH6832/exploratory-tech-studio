// RadixNode.h

#pragma once

#include <unordered_map>

/**
 * Represents a node in the radix tree.
 */
class RadixNode {
public:
    char value;
    std::unordered_map<char, RadixNode*> children;

    RadixNode(char value) : value(value) {}
};
