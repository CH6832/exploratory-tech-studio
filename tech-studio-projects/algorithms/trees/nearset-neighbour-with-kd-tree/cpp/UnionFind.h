#ifndef UNION_FIND_H
#define UNION_FIND_H

#include <vector>

class UnionFind {
private:
    std::vector<int> parent;
    std::vector<int> rank;

public:
    UnionFind(int size);
    int find(int u);
    void unionSets(int u, int v);
};

#endif // UNION_FIND_H
