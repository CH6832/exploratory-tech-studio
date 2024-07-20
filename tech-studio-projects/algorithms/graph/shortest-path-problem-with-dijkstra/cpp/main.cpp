#include <iostream>
#include <climits>

/**
 * DijkstraShortestPath.cpp
 *
 * Dijkstra's algorithm is an algorithm for finding the shortest paths
 * between nodes in a graph, which may represent, for example, road networks.
 * It calculates the shortest path from a single source vertex to all other
 * vertices in the graph.
 *
 * Let's discuss the algorithm for Dijkstra's shortest path:
 *
 * 1. Initialization: Initialize the distance from the source vertex to
 *    all other vertices as INFINITY and to itself as 0.
 * 2. Select: Pick the unvisited vertex with the smallest known distance
 *    to the source vertex.
 * 3. Update: Update the distances of the adjacent vertices of the selected
 *    vertex by adding the weight of the edge to the current distance.
 * 4. Repeat: Repeat steps 2 and 3 until all vertices are visited.
 */

using namespace std;

#define V 9 // Number of vertices
#define INF INT_MAX // Represents infinity

// Class to represent a graph
class Graph {
public:
    int graph[V][V]; // Adjacency matrix representation of the graph

    // Function to print the solution (shortest distances)
    void printSolution(int dist[]) {
        cout << "Vertex \t Distance from Source" << endl;
        for (int node = 0; node < V; ++node)
            cout << node << "\t\t" << dist[node] << endl;
    }

    // Utility function to find the vertex with minimum distance value,
   
