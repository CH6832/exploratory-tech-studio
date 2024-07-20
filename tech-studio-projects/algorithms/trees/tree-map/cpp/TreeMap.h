#ifndef TREEMAP_H
#define TREEMAP_H

#include "TreeNode.h"
#include <vector>
#include <utility>

// TreeMap represents a sorted map implemented using a binary search tree
class TreeMap {
private:
    TreeNode* root;

    TreeNode* put(TreeNode* node, int key, const std::string& value);
    std::string get(TreeNode* node, int key);
    TreeNode* remove(TreeNode* node, int key);
    TreeNode* findMin(TreeNode* node);
    void traverseInOrder(TreeNode* node, std::vector<std::pair<int, std::string>>& result);

public:
    // Constructor
    TreeMap();

    // Destructor
    ~TreeMap();

    // Method to insert or update a key-value pair in the tree
    void put(int key, const std::string& value);

    // Method to retrieve the value associated with the given key
    std::string get(int key);

    // Method to remove the key-value pair with the given key from the tree
    void remove(int key);

    // Method to perform an in-order traversal of the tree and return key-value pairs
    std::vector<std::pair<int, std::string>> traverseInOrder();
};

#endif // TREEMAP_H
