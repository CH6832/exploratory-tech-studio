using System;
using System.Collections.Generic;
using System.Linq;

class Program
{
    static void Main(string[] args)
    {
        Graph g = new Graph(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        Console.WriteLine("Following is a Topological Sort of the given graph:");
        g.topologicalSort();
    }
}

class Graph
{
    private int V; // Number of vertices
    private List<int>[] adj; // Adjacency list

    public Graph(int v)
    {
        V = v;
        adj = new List<int>[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new List<int>();
    }

    // Function to add an edge to the graph
    public void addEdge(int v, int w)
    {
        adj[v].Add(w);
    }

    // A recursive function used by topologicalSort
    private void topologicalSortUtil(int v, bool[] visited, Stack<int> stack)
    {
        visited[v] = true;

        foreach (int i in adj[v])
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);

        stack.Push(v);
    }

    // The function to do Topological Sort
    public void topologicalSort()
    {
        Stack<int> stack = new Stack<int>();
        bool[] visited = new bool[V];

        for (int i = 0; i < V; i++)
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);

        while (stack.Count != 0)
            Console.Write(stack.Pop() + " ");
    }
}
