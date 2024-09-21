#include "Kruskal.h"
#include "UnionFind.h"
#include <algorithm>

std::vector<std::tuple<int, int, int>> Kruskal::findMinimumSpanningTree(std::vector<std::tuple<int, int, int>>& graph) {
    std::sort(graph.begin(), graph.end(), [](const std::tuple<int, int, int>& a, const std::tuple<int, int, int>& b) {
        return std::get<2>(a) < std::get<2>(b);
    });

    int n = graph.size();
    UnionFind uf(n);
    std::vector<std::tuple<int, int, int>> mst;

    for (const auto& edge : graph) {
        int u = std::get<0>(edge);
        int v = std::get<1>(edge);
        if (uf.find(u) != uf.find(v)) {
            mst.push_back(edge);
            uf.unionSets(u, v);
        }
    }

    return mst;
}
