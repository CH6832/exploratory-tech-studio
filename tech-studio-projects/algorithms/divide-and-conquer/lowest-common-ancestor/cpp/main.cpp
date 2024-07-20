#include <iostream>
#include <vector>

using namespace std;

class Node {
public:
    int val;
    Node* left;
    Node* right;

    Node(int key) {
        val = key;
        left = right = nullptr;
    }
};

/**
 * Find the path from the root node to the given node.
 *
 * @param root The root of the tree.
 * @param path Vector to store the path.
 * @param k The target node value.
 * @return True if the path exists, otherwise false.
 */
bool findPath(Node* root, vector<int>& path, int k) {
    // Base Case
    if (root == nullptr)
        return false;

    // Store the current node in the path
    path.push_back(root->val);

    // Check if the current node is the target node
    if (root->val == k)
        return true;

    // Check if the target node is found in the left or right subtree
    if ((root->left && findPath(root->left, path, k)) ||
        (root->right && findPath(root->right, path, k)))
        return true;

    // If the target node is not found, remove the current node from the path and return false
    path.pop_back();
    return false;
}

/**
 * Find the lowest common ancestor (LCA) of two nodes in a binary tree.
 *
 * @param root The root of the tree.
 * @param n1 The value of the first node.
 * @param n2 The value of the second node.
 * @return The value of the lowest common ancestor, or -1 if either node is not present.
 */
int findLCA(Node* root, int n1, int n2) {
    vector<int> path1, path2;

    // Find paths from root to n1 and root to n2. If either n1 or n2 is not present, return -1
    if (!findPath(root, path1, n1) || !findPath(root, path2, n2))
        return -1;

    // Compare the paths to get the first different value
    int i;
    for (i = 0; i < path1.size() && i < path2.size(); i++) {
        if (path1[i] != path2[i])
            break;
    }
    return path1[i - 1];
}

int main() {
    // Example usage
    Node* root = new Node(1);
    root->left = new Node(2);
    root->right = new Node(3);
    root->left->left = new Node(4);
    root->left->right = new Node(5);
    root->right->left = new Node(6);
    root->right->right = new Node(7);

    // Example call to find the lowest common ancestor of nodes 4 and 6
    int lca = findLCA(root, 4, 6);
    cout << "Lowest Common Ancestor of 4 and 6: " << lca << endl;

    return 0;
}
