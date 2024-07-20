#include "Node.h"

Node::Node(std::vector<int> point, int axis) : point(point), axis(axis), left(nullptr), right(nullptr) {}

std::vector<int> Node::getPoint() {
    return point;
}

int Node::getAxis() {
    return axis;
}

Node* Node::getLeft() {
    return left;
}

Node* Node::getRight() {
    return right;
}

void Node::setLeft(Node* left) {
    this->left = left;
}

void Node::setRight(Node* right) {
    this->right = right;
}
