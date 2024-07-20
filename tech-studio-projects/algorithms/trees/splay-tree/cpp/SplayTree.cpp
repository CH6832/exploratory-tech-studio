// SplayTree.cpp

#include "SplayTree.h"

SplayTree::SplayTree() {
    this->root = nullptr;
}

void SplayTree::insert(int key) {
    this->root = insert(this->root, key);
}

bool SplayTree::search(int key) {
    this->root = splay(this->root, key);
    return this->root && this->root->key == key;
}

SplayNode* SplayTree::insert(SplayNode* node, int key) {
    if (!node)
        return new SplayNode(key);

    if (key < node->key) {
        node->left = insert(node->left, key);
    } else if (key > node->key) {
        node->right = insert(node->right, key);
    }
    return node;
}

SplayNode* SplayTree::splay(SplayNode* node, int key) {
    if (!node || node->key == key)
        return node;

    if (key < node->key) {
        if (!node->left)
            return node;
        if (key < node->left->key) {
            node->left->left = splay(node->left->left, key);
            node = rotateRight(node);
        } else if (key > node->left->key) {
            node->left->right = splay(node->left->right, key);
            if (node->left->right)
                node->left = rotateLeft(node->left);
        }
        return rotateRight(node);
    } else {
        if (!node->right)
            return node;
        if (key > node->right->key) {
            node->right->right = splay(node->right->right, key);
            node = rotateLeft(node);
        } else if (key < node->right->key) {
            node->right->left = splay(node->right->left, key);
            if (node->right->left)
                node->right = rotateRight(node->right);
        }
        return rotateLeft(node);
    }
}

SplayNode* SplayTree::rotateLeft(SplayNode* node) {
    SplayNode* newRoot = node->right;
    node->right = newRoot->left;
    newRoot->left = node;
    return newRoot;
}

SplayNode* SplayTree::rotateRight(SplayNode* node) {
    SplayNode* newRoot = node->left;
    node->left = newRoot->right;
    newRoot->right = node;
    return newRoot;
}
