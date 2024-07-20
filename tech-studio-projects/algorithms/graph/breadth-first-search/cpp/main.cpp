#include <iostream>
#include <list>
#include <queue>
#include <unordered_map>

/**
 * BreadthFirstSearch.cpp
 *
 * Breadth First Search (BFS) is a fundamental graph traversal algorithm. It involves visiting
 * all the connected nodes of a graph in a level-by-level manner.
 *
 * Breadth First Search (BFS) is a graph traversal algorithm that explores all the vertices in a
 * graph at the current depth before moving on to the vertices at the next depth level. It starts
 * at a specified vertex and visits all its neighbors before moving on to the next level of neighbors.
 * BFS is commonly used in algorithms for pathfinding, connected components, and shortest path
 * problems in graphs.
 *
 * Let's discuss the algorithm for the BFS:
 *
 * 1. Initialization: Enqueue the starting node into a queue and mark it as visited.
 * 2. Exploration: While the queue is not empty:
 *    - Dequeue a node from the queue and visit it (e.g., print its value).
 *    - For each unvisited neighbor of the dequeued node:
 *        - Enqueue the neighbor into the queue.
 *        - Mark the neighbor as visited.
 * 3. Termination: Repeat step 2 until the queue is empty.
 */

using namespace std;

// Class to represent a graph
class Graph {
    unordered_map<int, list<int>> adjList;

public:
    // Function to add an edge to the graph
    void addEdge(int u, int v) {
        adjList[u].push_back(v);
    }

    // Function to perform BFS traversal
    void BFS(int s) {
        vector<bool> visited(adjList.size(), false);
        queue<int> q;

        // Mark the source node as visited and enqueue it
        q.push(s);
        visited[s] = true;

        while (!q.empty()) {
            // Dequeue a vertex from the queue and print it
            int current = q.front();
            q.pop();
            cout << current << " ";

            // Get all adjacent vertices of the dequeued vertex
            // If an adjacent vertex has not been visited, then mark it visited and enqueue it
            for (int neighbor : adjList[current]) {
                if (!visited[neighbor]) {
                    q.push(neighbor);
                    visited[neighbor] = true;
                }
            }
        }
    }
};

// Driver code
int main() {
    // Create a graph
    Graph graph;
    graph.addEdge(0, 1);
    graph.addEdge(0, 2);
    graph.addEdge(1, 2);
    graph.addEdge(2, 0);
    graph.addEdge(2, 3);
    graph.addEdge(3, 3);

    // Perform BFS traversal starting from vertex 2
    cout << "Following is Breadth First Traversal (starting from vertex 2)" << endl;
    graph.BFS(2);

    return 0;
}
