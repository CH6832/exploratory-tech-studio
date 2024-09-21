#ifndef KDTREE_H
#define KDTREE_H

#include "Node.h"
#include <vector>

class KDTree {
private:
    Node* root;

    Node* buildTree(std::vector<std::vector<int>>& points, int axis);
    void nearestNeighbor(Node* node, std::vector<int>& target, std::vector<int>& best);
    int calculateDistance(std::vector<int>& point1, std::vector<int>& point2);

public:
    KDTree(std::vector<std::vector<int>>& points);
    std::vector<int> nearestNeighbor(std::vector<int>& target);
};

#endif
