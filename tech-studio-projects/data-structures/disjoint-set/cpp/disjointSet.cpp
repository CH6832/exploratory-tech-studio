#include "DisjointSet.h"

DisjointSet::DisjointSet(int size) : parent(size), rank(size, 0) {}

void DisjointSet::makeSet(int x) {
    parent[x] = x;
    rank[x] = 0;
}

int DisjointSet::find(int x) {
    if (x != parent[x]) {
        parent[x] = find(parent[x]);
    }
    return parent[x];
}

void DisjointSet::unionSets(int x, int y) {
    int rootX = find(x);
    int rootY = find(y);

    if (rootX != rootY) {
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        }
        else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        }
        else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }
}
