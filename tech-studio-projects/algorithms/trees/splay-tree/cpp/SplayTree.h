// SplayTree.h

#ifndef SPLAY_TREE_H
#define SPLAY_TREE_H

#include "SplayNode.h"

class SplayTree {
private:
    SplayNode* root;

    SplayNode* insert(SplayNode* node, int key);
    SplayNode* splay(SplayNode* node, int key);
    SplayNode* rotateLeft(SplayNode* node);
    SplayNode* rotateRight(SplayNode* node);

public:
    SplayTree();
    void insert(int key);
    bool search(int key);
};

#endif // SPLAY_TREE_H
