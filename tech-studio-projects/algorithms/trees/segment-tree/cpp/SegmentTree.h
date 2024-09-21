// SegmentTree.h

#include "SegmentTreeNode.h"
#include <vector>

/**
 * Represents the segment tree data structure.
 */
class SegmentTree {
private:
    SegmentTreeNode* root;

    SegmentTreeNode* buildTree(const std::vector<int>& nums, int start, int end) {
        if (start == end) {
            SegmentTreeNode* node = new SegmentTreeNode(start, end);
            node->total = nums[start];
            return node;
        }

        int mid = (start + end) / 2;
        SegmentTreeNode* leftNode = buildTree(nums, start, mid);
        SegmentTreeNode* rightNode = buildTree(nums, mid + 1, end);

        SegmentTreeNode* node = new SegmentTreeNode(start, end);
        node->total = leftNode->total + rightNode->total;
        node->left = leftNode;
        node->right = rightNode;
        return node;
    }

    int query(SegmentTreeNode* node, int start, int end) {
        if (node->start == start && node->end == end) {
            return node->total;
        }

        int mid = (node->start + node->end) / 2;

        if (end <= mid) {
            return query(node->left, start, end);
        } else if (start >= mid + 1) {
            return query(node->right, start, end);
        } else {
            int leftSum = query(node->left, start, mid);
            int rightSum = query(node->right, mid + 1, end);
            return leftSum + rightSum;
        }
    }

    void update(SegmentTreeNode* node, int index, int value) {
        if (node->start == node->end && node->start == index) {
            node->total = value;
            return;
        }

        int mid = (node->start + node->end) / 2;

        if (index <= mid) {
            update(node->left, index, value);
        } else {
            update(node->right, index, value);
        }

        node->total = node->left->total + node->right->total;
    }

public:
    SegmentTree(const std::vector<int>& nums) {
        root = buildTree(nums, 0, nums.size() - 1);
    }

    int query(int start, int end) {
        return query(root, start, end);
    }

    void update(int index, int value) {
        update(root, index, value);
    }
};
