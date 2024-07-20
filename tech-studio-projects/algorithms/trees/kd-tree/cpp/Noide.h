#ifndef NODE_H
#define NODE_H

#include <vector>

class Node {
private:
    std::vector<int> point;
    int axis;
    Node* left;
    Node* right;

public:
    Node(std::vector<int> point, int axis);
    std::vector<int> getPoint();
    int getAxis();
    Node* getLeft();
    Node* getRight();
    void setLeft(Node* left);
    void setRight(Node* right);
};

#endif
