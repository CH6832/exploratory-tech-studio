#include <iostream>
#include <vector>
#include <algorithm>
#include <limits>

using namespace std;

const int INF = numeric_limits<int>::max();

vector<int> findMinPath(const vector<vector<int>>& graph) {
    int n = graph.size();
    vector<int> path;
    for (int i = 0; i < n; ++i) {
        path.push_back(i);
    }
    int minPathCost = INF;
    vector<int> minPath;
    do {
        int pathCost = 0;
        for (int i = 0; i < n - 1; ++i) {
            if (graph[path[i]][path[i + 1]] == 0) {
                pathCost = INF;
                break;
            }
            pathCost += graph[path[i]][path[i + 1]];
        }
        if (graph[path[n - 1]][path[0]] == 0) {
            pathCost = INF;
        } else {
            pathCost += graph[path[n - 1]][path[0]];
        }
        if (pathCost < minPathCost) {
            minPathCost = pathCost;
            minPath = path;
        }
    } while (next_permutation(path.begin() + 1, path.end()));
    return minPath;
}

int main() {
    vector<vector<int>> graph = {
        {0, 10, 15, 20},
        {10, 0, 35, 25},
        {15, 35, 0, 30},
        {20, 25, 30, 0}
    };

    vector<int> minPath = findMinPath(graph);
    cout << "Minimum Path: ";
    for (int node : minPath) {
        cout << node << " ";
    }
    cout << endl;

    return 0;
}
