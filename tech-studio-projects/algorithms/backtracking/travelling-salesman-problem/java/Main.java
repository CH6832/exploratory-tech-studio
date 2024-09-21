import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Number of vertices
        int V = 4;

        // Weighted graph represented as an adjacency matrix
        int[][] graph = {
                {0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}
        };

        int initVertex = 0;
        int minimumPath = TravellingSalesmanProblemSolver.travellingSalesmanProblem(graph, initVertex);
        System.out.println("Minimum path weight is: " + minimumPath);
    }
}
