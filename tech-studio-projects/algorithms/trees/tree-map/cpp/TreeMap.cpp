#include "TreeMap.h"

// Constructor
TreeMap::TreeMap() : root(nullptr) {}

// Destructor
TreeMap::~TreeMap() {
    // Implement destructor to free memory allocated for tree nodes
    // This will be called automatically when TreeMap object goes out of scope
}

// Method to insert or update a key-value pair in the tree
void TreeMap::put(int key, const std::string& value) {
    root = put(root, key, value);
}

// Method to retrieve the value associated with the given key
std::string TreeMap::get(int key) {
    return get(root, key);
}

// Method to remove the key-value pair with the given key from the tree
void TreeMap::remove(int key) {
    root = remove(root, key);
}

// Method to perform an in-order traversal of the tree and return key-value pairs
std::vector<std::pair<int, std::string>> TreeMap::traverseInOrder() {
    std::vector<std::pair<int, std::string>> result;
    traverseInOrder(root, result);
    return result;
}

// Private helper functions
TreeNode* TreeMap::put(TreeNode* node, int key, const std::string& value) {
    // Implementation
}

std::string TreeMap::get(TreeNode* node, int key) {
    // Implementation
}

TreeNode* TreeMap::remove(TreeNode* node, int key) {
    // Implementation
}

TreeNode* TreeMap::findMin(TreeNode* node) {
    // Implementation
}

void TreeMap::traverseInOrder(TreeNode* node, std::vector<std::pair<int, std::string>>& result) {
    // Implementation
}
