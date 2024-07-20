#ifndef AVLNODE_H
#define AVLNODE_H

// AVLNode class represents a node in the AVL tree
class AVLNode {
public:
    int key;
    int height;
    AVLNode* left;
    AVLNode* right;

    // Constructor
    AVLNode(int key);

    // Destructor
    ~AVLNode();
};

#endif
