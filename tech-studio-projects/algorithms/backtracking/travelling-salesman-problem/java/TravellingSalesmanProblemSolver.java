import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TravellingSalesmanProblemSolver {

    public static int travellingSalesmanProblem(int[][] graph, int s) {
        int V = graph.length; // Number of vertices

        // Store all vertices apart from the source vertex
        List<Integer> vertex = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (i != s) {
                vertex.add(i);
            }
        }

        // Store minimum weight Hamiltonian Cycle
        int minPath = Integer.MAX_VALUE;

        // Get all permutations of remaining vertices
        List<List<Integer>> permutations = permute(vertex);

        for (List<Integer> perm : permutations) {
            // Store current path weight (cost)
            int currentPathWeight = 0;

            // Compute current path weight
            int k = s;
            for (int j : perm) {
                currentPathWeight += graph[k][j];
                k = j;
            }
            currentPathWeight += graph[k][s];

            // Update minimum path weight
            minPath = Math.min(minPath, currentPathWeight);
        }
        return minPath;
    }

    // Helper method to generate all permutations of a list of integers
    private static List<List<Integer>> permute(List<Integer> nums) {
        List<List<Integer>> result = new ArrayList<>();
        permuteHelper(nums, 0, result);
        return result;
    }

    private static void permuteHelper(List<Integer> nums, int start, List<List<Integer>> result) {
        if (start == nums.size() - 1) {
            result.add(new ArrayList<>(nums));
            return;
        }
        for (int i = start; i < nums.size(); i++) {
            Collections.swap(nums, i, start);
            permuteHelper(nums, start + 1, result);
            Collections.swap(nums, i, start);
        }
    }
}
