#include "topologicalSortOfDrag.h"

TopologicalSortOfDrag::TopologicalSortOfDrag(int V) {
    this->V = V;
    adj = new std::list<int>[V];
}

void TopologicalSortOfDrag::addEdge(int v, int w) {
    adj[v].push_back(w);
}

void TopologicalSortOfDrag::topologicalSortUtil(int v, bool visited[], std::stack<int> &Stack) {
    // Mark the current node as visited
    visited[v] = true;

    // Recur for all the vertices adjacent to this vertex
    for (auto i = adj[v].begin(); i != adj[v].end(); ++i)
        if (!visited[*i])
            topologicalSortUtil(*i, visited, Stack);

    // Push current vertex to stack which stores result
    Stack.push(v);
}

void TopologicalSortOfDrag::topologicalSort() {
    std::stack<int> Stack;

    // Mark all the vertices as not visited
    bool *visited = new bool[V];
    for (int i = 0; i < V; i++)
        visited[i] = false;

    // Call the recursive helper function to store Topological Sort starting from all vertices one by one
    for (int i = 0; i < V; i++)
        if (visited[i] == false)
            topologicalSortUtil(i, visited, Stack);

    // Print contents of stack
    while (Stack.empty() == false) {
        std::cout << Stack.top() << " ";
        Stack.pop();
    }
}
