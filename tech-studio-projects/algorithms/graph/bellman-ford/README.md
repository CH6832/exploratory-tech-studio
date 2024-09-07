# Bellman-Ford Algorithm

## Overview

The Bellman-Ford algorithm is a shortest-path algorithm used for finding the shortest paths from a single source vertex to all other vertices in a weighted graph. Unlike Dijkstra's algorithm, the Bellman-Ford algorithm can handle graphs with negative weight edges. It is particularly useful for detecting negative weight cycles in the graph.

## Theoretical Background

### Definition

Given a weighted graph with \(V\) vertices and \(E\) edges, the Bellman-Ford algorithm computes the shortest path from a source vertex \(s\) to all other vertices. It works even when edge weights are negative, but it cannot handle negative weight cycles that are reachable from the source.

### Algorithm Steps

1. **Initialization**:
   - Set the distance to the source vertex \(s\) to 0 and all other vertices to infinity.

2. **Relaxation**:
   - For each vertex, iterate through all edges and update the distance if a shorter path is found. This step is repeated \(V-1\) times, where \(V\) is the number of vertices.

3. **Negative Weight Cycle Detection**:
   - Perform one additional iteration through all edges. If any distance can still be updated, then a negative weight cycle exists in the graph.

### Complexity

- **Time Complexity**: \(O(V \times E)\), where \(V\) is the number of vertices and \(E\) is the number of edges. This is because each of the \(V-1\) iterations involves checking all \(E\) edges.
- **Space Complexity**: \(O(V)\), due to the storage of distance values for each vertex.
