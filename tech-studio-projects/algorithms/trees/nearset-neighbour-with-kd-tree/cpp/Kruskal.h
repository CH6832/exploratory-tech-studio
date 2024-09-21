#ifndef KRUSKAL_H
#define KRUSKAL_H

#include <vector>
#include <tuple>

class Kruskal {
public:
    static std::vector<std::tuple<int, int, int>> findMinimumSpanningTree(std::vector<std::tuple<int, int, int>>& graph);
};

#endif // KRUSKAL_H
