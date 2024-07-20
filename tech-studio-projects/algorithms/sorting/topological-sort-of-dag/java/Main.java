/**
 * Main class to demonstrate topological sort.
 */
public class Main {
    /**
     * The main method that runs the topological sort demonstration.
     * @param args Command-line arguments (not used).
     */
    public static void main(String args[]) {
        // Create a graph given in the diagram
        TopologicalSortOfDrag g = new TopologicalSortOfDrag(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        System.out.println("Following is a Topological " + "sort of the given graph");
        g.topologicalSort();
    }
}
