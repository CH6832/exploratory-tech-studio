#ifndef GRAPH_H
#define GRAPH_H

#include <vector>
#include <tuple>

class Graph {
public:
    Graph(int vertices);
    void addEdge(int u, int v, int w);
    void KruskalMST();

private:
    int V;
    std::vector<std::tuple<int, int, int>> graph;
    int find(std::vector<int>& parent, int i);
    void unionSets(std::vector<int>& parent, std::vector<int>& rank, int x, int y);
};

#endif // GRAPH_H
