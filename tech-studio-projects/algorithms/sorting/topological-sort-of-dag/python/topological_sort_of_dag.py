
# recursive topological sorting
# ----------------------------
#Python program to print topological sorting of a DAG
from collections import defaultdict

def main() -> None:
    """Driving code"""
    g= Graph(6)
    g.addEdge(5, 2)
    g.addEdge(5, 0)
    g.addEdge(4, 0)
    g.addEdge(4, 1)
    g.addEdge(2, 3)
    g.addEdge(3, 1)
    
    print ("Following is a Topological Sort of the given graph")
    g.topologicalSort()

    return None

#Class to represent a graph
class Graph:
    """Class to represent a graph."""

    def __init__(self,vertices) -> None:
        self.graph = defaultdict(list) # dictionary containing adjacency List
        self.V = vertices # number of vertices

        return None
 
    # function to add an edge to graph
    def addEdge(self,u,v) -> None:
        """Add an edge to the paragraph."""
        self.graph[u].append(v)

        return None
 
    # A recursive function used by topologicalSort
    def topologicalSortUtil(self,v,visited,stack) -> None:
        """Recursive function sued by topological sort."""
        # Mark the current node as visited.
        visited[v] = True
 
        # Recur for all the vertices adjacent to this vertex
        for i in self.graph[v]:
            if visited[i] == False:
                self.topologicalSortUtil(i,visited,stack)
 
        # Push current vertex to stack which stores result
        stack.insert(0,v)

        return None

    # 
    def topologicalSort(self) -> None:
        """The function to do Topological Sort. It uses
        recursive topologicalSortUtil()."""
        # Mark all the vertices as not visited
        visited = [False]*self.V
        stack =[]
 
        # Call the recursive helper function to store Topological
        # Sort starting from all vertices one by one
        for i in range(self.V):
            if visited[i] == False:
                self.topologicalSortUtil(i,visited,stack)
 
        # Print contents of stack
        print (stack)

        return None

if __name__ == "__main__":
    main()