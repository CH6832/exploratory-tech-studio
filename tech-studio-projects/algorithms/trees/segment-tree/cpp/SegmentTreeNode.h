// SegmentTreeNode.h

#pragma once

/**
 * Represents a node in the segment tree.
 */
class SegmentTreeNode {
public:
    int start;
    int end;
    int total;
    SegmentTreeNode* left;
    SegmentTreeNode* right;

    SegmentTreeNode(int start, int end) : start(start), end(end), total(0), left(nullptr), right(nullptr) {}
};
