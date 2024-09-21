import java.util.*;

/**
 * DijkstraShortestPath.java
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

public class DijkstraShortestPath {

    static final int INF = Integer.MAX_VALUE; // Represents infinity

    // Class to represent a graph
    static class Graph {
        int V; // Number of vertices
        int[][] graph; // Adjacency matrix representation of the graph

        // Constructor
        Graph(int v) {
            V = v;
            graph = new int[V][V];
        }

        // Function to print the solution (shortest distances)
        void printSolution(int[] dist) {
            System.out.println("Vertex \t Distance from Source");
            for (int node = 0; node < V; ++node)
                System.out.println(node + "\t\t" + dist[node]);
        }

        // Utility function to find the vertex with minimum distance value,
        // from the set of vertices not yet included in the shortest path tree
        int minDistance(int[] dist, boolean[] sptSet) {
            int min = INF, minIndex = -1;
            for (int v = 0; v < V; ++v) {
                if (!sptSet[v] && dist[v] <= min) {
                    min = dist[v];
                    minIndex = v;
                }
            }
            return minIndex;
        }

        // Function that implements Dijkstra's single source shortest path algorithm
        void dijkstra(int src) {
            int[] dist = new int[V]; // The output array to hold shortest distance from src to i
            boolean[] sptSet = new boolean[V]; // sptSet[i] will be true if vertex i is included in shortest path tree

            // Initialize all distances as INFINITE and sptSet[] as false
            for (int i = 0; i < V; ++i) {
                dist[i] = INF;
                sptSet[i] = false;
            }

            // Distance of source vertex from itself is always 0
            dist[src] = 0;

            // Find shortest path for all vertices
            for (int count = 0; count < V - 1; ++count) {
                // Pick the minimum distance vertex from the set of vertices
                // not yet processed. u is always equal to src in the first iteration.
                int u = minDistance(dist, sptSet);

                // Mark the picked vertex as processed
                sptSet[u] = true;

                // Update dist value of the adjacent vertices of the picked vertex
                for (int v = 0; v < V; ++v) {
                    // Update dist[v] only if it is not in sptSet, there is an edge from
                    // u to v, and total weight of path from src to v through u is
                    // smaller than current value of dist[v]
                    if (!sptSet[v] && graph[u][v] != 0 && dist[u] != INF && dist[u] + graph[u][v] < dist[v]) {
                        dist[v] = dist[u] + graph[u][v];
                    }
                }
            }

            // Print the constructed distance array
            printSolution(dist);
        }
    }

    // Driver code
    public static void main(String[] args) {
        // Create a graph
        Graph g = new Graph(9);
        g.graph = new int[][]{
            {0, 4, 0, 0, 0, 0, 0, 8, 0},
            {4, 0, 8, 0, 0, 0, 0, 11, 0},
            {0, 8, 0, 7, 0, 4, 0, 0, 2},
            {0, 0, 7, 0, 9, 14, 0, 0, 0},
            {0, 0, 0, 9, 0, 10, 0, 0, 0},
            {0, 0, 4, 14, 10, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 2, 0, 1, 6},
            {8, 11, 0, 0, 0, 0, 1, 0, 7},
            {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };

        // Perform Dijkstra's algorithm starting from vertex 0
        System.out.println("Following is Dijkstra's shortest path algorithm (starting from vertex 0):");
        g.dijkstra(0);
    }
}
