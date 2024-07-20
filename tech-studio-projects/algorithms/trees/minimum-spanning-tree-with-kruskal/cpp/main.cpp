#include "Graph.h"

int main() {
    // Example usage
    Graph g(4);
    g.addEdge(0, 1, 10);
    g.addEdge(0, 2, 6);
    g.addEdge(0, 3, 5);
    g.addEdge(1, 3, 15);
    g.addEdge(2, 3, 4);
 
    // Function call
    g.KruskalMST();
    
    return 0;
}
