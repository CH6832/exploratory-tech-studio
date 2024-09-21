#include "KDTree.h"
#include <algorithm>
#include <limits>

KDTree::KDTree(std::vector<std::vector<int>>& points) {
    root = buildTree(points, 0);
}

Node* KDTree::buildTree(std::vector<std::vector<int>>& points, int axis) {
    if (points.empty()) {
        return nullptr;
    }

    std::sort(points.begin(), points.end(), [axis](const auto& a, const auto& b) {
        return a[axis] < b[axis];
    });

    int medianIdx = points.size() / 2;
    std::vector<int> median = points[medianIdx];

    int nextAxis = (axis + 1) % median.size();
    Node* node = new Node(median, axis);
    node->setLeft(buildTree(std::vector<std::vector<int>>(points.begin(), points.begin() + medianIdx), nextAxis));
    node->setRight(buildTree(std::vector<std::vector<int>>(points.begin() + medianIdx + 1, points.end()), nextAxis));

    return node;
}

std::vector<int> KDTree::nearestNeighbor(std::vector<int>& target) {
    std::vector<int> best = {std::numeric_limits<int>::max(), -1};
    nearestNeighbor(root, target, best);
    return best;
}

void KDTree::nearestNeighbor(Node* node, std::vector<int>& target, std::vector<int>& best) {
    if (node == nullptr) {
        return;
    }

    int distance = calculateDistance(node->getPoint(), target);
    if (distance < best[0]) {
        best[0] = distance;
        best[1] = node->getPoint()[0];
    }

    int axis = node->getAxis();
    if (target[axis] < node->getPoint()[axis]) {
        nearestNeighbor(node->getLeft(), target, best);
        if ((node->getPoint()[axis] - target[axis]) * (node->getPoint()[axis] - target[axis]) < best[0]) {
            nearestNeighbor(node->getRight(), target, best);
        }
    } else {
        nearestNeighbor(node->getRight(), target, best);
        if ((target[axis] - node->getPoint()[axis]) * (target[axis] - node->getPoint()[axis]) < best[0]) {
            nearestNeighbor(node->getLeft(), target, best);
        }
    }
}

int KDTree::calculateDistance(std::vector<int>& point1, std::vector<int>& point2) {
    int distance = 0;
    for (size_t i = 0; i < point1.size(); i++) {
        distance += (point1[i] - point2[i]) * (point1[i] - point2[i]);
    }
    return distance;
}
