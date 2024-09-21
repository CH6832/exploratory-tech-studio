#ifndef TREENODE_H
#define TREENODE_H

#include <string>

// TreeNode represents a node in the binary search tree
struct TreeNode {
    int key;
    std::string value;
    TreeNode* left;
    TreeNode* right;

    TreeNode(int k, const std::string& v);
};

#endif // TREENODE_H
