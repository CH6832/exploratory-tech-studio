#include "Graph.h"
#include <algorithm>
#include <iostream>

Graph::Graph(int vertices) : V(vertices) {}

void Graph::addEdge(int u, int v, int w) {
    graph.push_back(std::make_tuple(u, v, w));
}

int Graph::find(std::vector<int>& parent, int i) {
    if (parent[i] != i) {
        parent[i] = find(parent, parent[i]);
    }
    return parent[i];
}

void Graph::unionSets(std::vector<int>& parent, std::vector<int>& rank, int x, int y) {
    int rootX = find(parent, x);
    int rootY = find(parent, y);
    if (rank[rootX] < rank[rootY]) {
        parent[rootX] = rootY;
    } else if (rank[rootX] > rank[rootY]) {
        parent[rootY] = rootX;
    } else {
        parent[rootY] = rootX;
        rank[rootX]++;
    }
}

void Graph::KruskalMST() {
    std::vector<std::tuple<int, int, int>> result;
    int i = 0;
    int e = 0;

    std::sort(graph.begin(), graph.end(), [](const std::tuple<int, int, int>& a, const std::tuple<int, int, int>& b) {
        return std::get<2>(a) < std::get<2>(b);
    });

    std::vector<int> parent(V);
    std::vector<int> rank(V, 0);
    for (int node = 0; node < V; ++node) {
        parent[node] = node;
    }

    while (e < V - 1) {
        int u, v, w;
        std::tie(u, v, w) = graph[i++];
        int x = find(parent, u);
        int y = find(parent, v);
        if (x != y) {
            e++;
            result.push_back(std::make_tuple(u, v, w));
            unionSets(parent, rank, x, y);
        }
    }

    int minimumCost = 0;
    std::cout << "Edges in the constructed MST:" << std::endl;
    for (const auto& edge : result) {
        int u, v, weight;
        std::tie(u, v, weight) = edge;
        minimumCost += weight;
        std::cout << u << " -- " << v << " == " << weight << std::endl;
    }
    std::cout << "Minimum Spanning Tree: " << minimumCost << std::endl;
}
