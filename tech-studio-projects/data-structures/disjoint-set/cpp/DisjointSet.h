#ifndef DISJOINT_SET_H
#define DISJOINT_SET_H

#include <vector>

class DisjointSet {
public:
    DisjointSet(int size);

    void makeSet(int x);
    int find(int x);
    void unionSets(int x, int y);

private:
    std::vector<int> parent;
    std::vector<int> rank;
};

#endif // DISJOINT_SET_H

