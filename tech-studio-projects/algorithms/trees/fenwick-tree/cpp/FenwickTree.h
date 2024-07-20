#ifndef FENWICKTREE_H
#define FENWICKTREE_H

#include <vector>

class FenwickTree {
private:
    int size;
    std::vector<int> tree;

public:
    FenwickTree(int size);
    void update(int index, int delta);
    int query(int index);
    int rangeQuery(int start, int end);
};

#endif
