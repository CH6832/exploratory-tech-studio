import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TravellingSalesmanProblem {

    private static final int INF = Integer.MAX_VALUE;

    public static List<Integer> findMinPath(int[][] graph) {
        int n = graph.length;
        List<Integer> path = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            path.add(i);
        }
        int minPathCost = INF;
        List<Integer> minPath = new ArrayList<>();
        do {
            int pathCost = 0;
            for (int i = 0; i < n - 1; ++i) {
                if (graph[path.get(i)][path.get(i + 1)] == 0) {
                    pathCost = INF;
                    break;
                }
                pathCost += graph[path.get(i)][path.get(i + 1)];
            }
            if (graph[path.get(n - 1)][path.get(0)] == 0) {
                pathCost = INF;
            } else {
                pathCost += graph[path.get(n - 1)][path.get(0)];
            }
            if (pathCost < minPathCost) {
                minPathCost = pathCost;
                minPath = new ArrayList<>(path);
            }
        } while (nextPermutation(path));
        return minPath;
    }

    private static boolean nextPermutation(List<Integer> list) {
        int n = list.size();
        int i = n - 2;
        while (i >= 0 && list.get(i) >= list.get(i + 1)) {
            i--;
        }
        if (i < 0) {
            return false;
        }
        int j = n - 1;
        while (list.get(i) >= list.get(j)) {
            j--;
        }
        Collections.swap(list, i, j);
        Collections.reverse(list.subList(i + 1, n));
        return true;
    }

    public static void main(String[] args) {
        int[][] graph = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };

        List<Integer> minPath = findMinPath(graph);
        System.out.print("Minimum Path: ");
        for (int node : minPath) {
            System.out.print(node + " ");
        }
        System.out.println();
    }
}
