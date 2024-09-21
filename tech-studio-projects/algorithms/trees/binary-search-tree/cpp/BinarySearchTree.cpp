#include <iostream>

class BinarySearchTree {
private:
    struct TreeNode {
        int key;
        TreeNode* left;
        TreeNode* right;

        TreeNode(int key) : key(key), left(nullptr), right(nullptr) {}
    };

    TreeNode* root;

    /**
     * Recursively insert a key into the binary search tree.
     * 
     * @param root The root of the current subtree.
     * @param key  The key to be inserted.
     * @return The root of the updated subtree.
     */
    TreeNode* insertRec(TreeNode* root, int key) {
        if (root == nullptr) {
            return new TreeNode(key);
        }

        if (key < root->key) {
            root->left = insertRec(root->left, key);
        } else if (key > root->key) {
            root->right = insertRec(root->right, key);
        }

        return root;
    }

    /**
     * Recursively search for a key in the binary search tree.
     * 
     * @param root The root of the current subtree.
     * @param key  The key to search for.
     * @return The TreeNode containing the key, or nullptr if key is not found.
     */
    TreeNode* searchRec(TreeNode* root, int key) {
        if (root == nullptr || root->key == key) {
            return root;
        }

        if (key < root->key) {
            return searchRec(root->left, key);
        } else {
            return searchRec(root->right, key);
        }
    }

    /**
     * Recursively perform an inorder traversal of the binary search tree.
     * 
     * @param root The root of the current subtree.
     */
    void inorderTraversalRec(TreeNode* root) {
        if (root != nullptr) {
            inorderTraversalRec(root->left);
            std::cout << root->key << " ";
            inorderTraversalRec(root->right);
        }
    }

public:
    /**
     * Constructor: Initializes an empty binary search tree.
     */
    BinarySearchTree() : root(nullptr) {}

    /**
     * Insert a key into the binary search tree.
     * 
     * @param key The key to be inserted.
     */
    void insert(int key) {
        root = insertRec(root, key);
    }

    /**
     * Search for a key in the binary search tree.
     * 
     * @param key The key to search for.
     * @return The TreeNode containing the key, or nullptr if key is not found.
     */
    TreeNode* search(int key) {
        return searchRec(root, key);
    }

    /**
     * Perform an inorder traversal of the binary search tree.
     */
    void inorderTraversal() {
        inorderTraversalRec(root);
        std::cout << std::endl;
    }
};
