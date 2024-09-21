import java.util.*;

/**
 * BreadthFirstSearch.java
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
public class BreadthFirstSearch {

    // Class to represent a graph
    static class Graph {
        private final Map<Integer, List<Integer>> adjList;

        // Constructor
        Graph() {
            adjList = new HashMap<>();
        }

        // Function to add an edge to the graph
        void addEdge(int u, int v) {
            adjList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        }

        // Function to perform BFS traversal
        void BFS(int s) {
            // Mark all the vertices as not visited
            Map<Integer, Boolean> visited = new HashMap<>();
            for (int key : adjList.keySet()) {
                visited.put(key, false);
            }

            // Create a queue for BFS
            Queue<Integer> queue = new LinkedList<>();

            // Mark the source node as visited and enqueue it
            queue.offer(s);
            visited.put(s, true);

            while (!queue.isEmpty()) {
                // Dequeue a vertex from the queue and print it
                int current = queue.poll();
                System.out.print(current + " ");

                // Get all adjacent vertices of the dequeued vertex
                // If an adjacent vertex has not been visited, then mark it visited and enqueue it
                for (int neighbor : adjList.getOrDefault(current, Collections.emptyList())) {
                    if (!visited.get(neighbor)) {
                        queue.offer(neighbor);
                        visited.put(neighbor, true);
                    }
                }
            }
        }
    }

    // Driver code
    public static void main(String[] args) {
        // Create a graph
        Graph graph = new Graph();
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        // Perform BFS traversal starting from vertex 2
        System.out.println("Following is Breadth First Traversal (starting from vertex 2)");
        graph.BFS(2);
    }
}
