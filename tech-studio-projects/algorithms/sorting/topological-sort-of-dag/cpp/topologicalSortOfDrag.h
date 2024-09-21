#ifndef TOPOLOGICALSORTOFDRAG_H
#define TOPOLOGICALSORTOFDRAG_H

#include <iostream>
#include <list>
#include <stack>

class TopologicalSortOfDrag {
    int V; // No. of vertices
    std::list<int> *adj; // Adjacency list

public:
    TopologicalSortOfDrag(int V); // Constructor
    void addEdge(int v, int w); // Function to add an edge to the graph
    void topologicalSort(); // The function to do Topological Sort
    void topologicalSortUtil(int v, bool visited[], std::stack<int> &Stack); // A recursive function used by topologicalSort
};

#endif
