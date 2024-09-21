#include <iostream>
#include "AVLNode.h"

// Constructor initializes the key and sets height to 1
AVLNode::AVLNode(int key) {
    this->key = key;
    this->height = 1;
    this->left = nullptr;
    this->right = nullptr;
}

// Destructor (if needed)
AVLNode::~AVLNode() {
    delete left;
    delete right;
}

// AVLTree class represents a self-balancing AVL tree
class AVLTree {
public:
    AVLNode* root;

    // Constructor
    AVLTree();

    // Destructor
    ~AVLTree();

    // Function to insert a key into the AVL tree
    void insert(int key);

    // Helper function to perform inorder traversal
    void inorderTraversal(AVLNode* node);

private:
    // Helper functions for AVL tree operations
    AVLNode* insertUtil(AVLNode* node, int key);
    AVLNode* rotateRight(AVLNode* y);
    AVLNode* rotateLeft(AVLNode* x);
    int height(AVLNode* node);
    int balanceFactor(AVLNode* node);
};

// Constructor initializes the root to nullptr
AVLTree::AVLTree() {
    root = nullptr;
}

// Destructor (if needed)
AVLTree::~AVLTree() {
    delete root;
}

// Function to insert a key into the AVL tree
void AVLTree::insert(int key) {
    root = insertUtil(root, key);
}

// Helper function to perform inorder traversal
void AVLTree::inorderTraversal(AVLNode* node) {
    if (node != nullptr) {
        inorderTraversal(node->left);
        std::cout << node->key << " ";
        inorderTraversal(node->right);
    }
}

// Helper function to insert a key into the AVL tree
AVLNode* AVLTree::insertUtil(AVLNode* node, int key) {
    if (node == nullptr) {
        return new AVLNode(key);
    }

    if (key < node->key) {
        node->left = insertUtil(node->left, key);
    } else if (key > node->key) {
        node->right = insertUtil(node->right, key);
    }

    // Update height of the current node
    node->height = 1 + std::max(height(node->left), height(node->right));

    // Perform rotations to balance the tree
    int balance = balanceFactor(node);

    // Left Left Case
    if (balance > 1 && key < node->left->key) {
        return rotateRight(node);
    }

    // Right Right Case
    if (balance < -1 && key > node->right->key) {
        return rotateLeft(node);
    }

    // Left Right Case
    if (balance > 1 && key > node->left->key) {
        node->left = rotateLeft(node->left);
        return rotateRight(node);
    }

    // Right Left Case
    if (balance < -1 && key < node->right->key) {
        node->right = rotateRight(node->right);
        return rotateLeft(node);
    }

    return node;
}

// Helper function to perform right rotation at the given node
AVLNode* AVLTree::rotateRight(AVLNode* y) {
    AVLNode* x = y->left;
    AVLNode* T2 = x->right;

    // Perform rotation
    x->right = y;
    y->left = T2;

    // Update heights
    y->height = 1 + std::max(height(y->left), height(y->right));
    x->height = 1 + std::max(height(x->left), height(x->right));

    return x;
}

// Helper function to perform left rotation at the given node
AVLNode* AVLTree::rotateLeft(AVLNode* x) {
    AVLNode* y = x->right;
    AVLNode* T2 = y->left;

    // Perform rotation
    y->left = x;
    x->right = T2;

    // Update heights
    x->height = 1 + std::max(height(x->left), height(x->right));
    y->height = 1 + std::max(height(y->left), height(y->right));

    return y;
}

// Helper function to calculate height of a node
int AVLTree::height(AVLNode* node) {
    if (node == nullptr) {
        return 0;
    }
    return node->height;
}

// Helper function to calculate balance factor of a node
int AVLTree::balanceFactor(AVLNode* node) {
    if (node == nullptr) {
        return 0;
    }
    return height(node->left) - height(node->right);
}

int main() {
    AVLTree avlTree;
    int keys[] = {9, 5, 10, 0, 6, 11, -1, 1, 2};
    for (int key : keys) {
        avlTree.insert(key);
    }

    std::cout << "Inorder traversal of the AVL tree:\n";
    avlTree.inorderTraversal(avlTree.root);
    std::cout << std::endl;

    return 0;
}
