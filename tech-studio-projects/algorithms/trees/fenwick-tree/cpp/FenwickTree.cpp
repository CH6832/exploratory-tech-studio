#include "FenwickTree.h"

FenwickTree::FenwickTree(int size) : size(size), tree(size + 1, 0) {}

void FenwickTree::update(int index, int delta) {
    while (index <= size) {
        tree[index] += delta;
        index += index & -index;
    }
}

int FenwickTree::query(int index) {
    int result = 0;
    while (index > 0) {
        result += tree[index];
        index -= index & -index;
    }
    return result;
}

int FenwickTree::rangeQuery(int start, int end) {
    return query(end) - (start > 0 ? query(start - 1) : 0);
}
