import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<int[]> points = Arrays.asList(
            new int[]{2, 3},
            new int[]{5, 4},
            new int[]{9, 6},
            new int[]{4, 7},
            new int[]{8, 1},
            new int[]{7, 2}
        );

        KDTree kdTree = new KDTree(points);
        int[] target = new int[]{5, 5};
        int[] nearestNeighbor = kdTree.nearestNeighbor(target);
        System.out.println("The nearest neighbor of " + Arrays.toString(target) + " is " + Arrays.toString(nearestNeighbor));
    }
}
