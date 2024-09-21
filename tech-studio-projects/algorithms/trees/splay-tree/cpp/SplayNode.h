#ifndef SPLAY_NODE_H
#define SPLAY_NODE_H

class SplayNode {
public:
    int key;
    SplayNode* left;
    SplayNode* right;

    SplayNode(int key);
};

#endif // SPLAY_NODE_H
