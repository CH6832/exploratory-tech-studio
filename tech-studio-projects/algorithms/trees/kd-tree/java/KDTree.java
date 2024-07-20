import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class KDTree {
    private Node root;

    public KDTree(List<int[]> points) {
        root = buildTree(points, 0);
    }

    private Node buildTree(List<int[]> points, int axis) {
        if (points.isEmpty()) {
            return null;
        }

        points.sort(Comparator.comparingInt(point -> point[axis]));

        int medianIdx = points.size() / 2;
        int[] median = points.get(medianIdx);

        int nextAxis = (axis + 1) % median.length;
        Node node = new Node(median, axis);
        node.setLeft(buildTree(points.subList(0, medianIdx), nextAxis));
        node.setRight(buildTree(points.subList(medianIdx + 1, points.size()), nextAxis));

        return node;
    }

    public int[] nearestNeighbor(int[] target) {
        int[] best = new int[]{Integer.MAX_VALUE, -1};
        nearestNeighbor(root, target, best);
        return Arrays.copyOf(best, best.length);
    }

    private void nearestNeighbor(Node node, int[] target, int[] best) {
        if (node == null) {
            return;
        }

        int distance = calculateDistance(node.getPoint(), target);
        if (distance < best[0]) {
            best[0] = distance;
            best[1] = node.getPoint()[0];
        }

        int axis = node.getAxis();
        if (target[axis] < node.getPoint()[axis]) {
            nearestNeighbor(node.getLeft(), target, best);
            if ((node.getPoint()[axis] - target[axis]) * (node.getPoint()[axis] - target[axis]) < best[0]) {
                nearestNeighbor(node.getRight(), target, best);
            }
        } else {
            nearestNeighbor(node.getRight(), target, best);
            if ((target[axis] - node.getPoint()[axis]) * (target[axis] - node.getPoint()[axis]) < best[0]) {
                nearestNeighbor(node.getLeft(), target, best);
            }
        }
    }

    private int calculateDistance(int[] point1, int[] point2) {
        int distance = 0;
        for (int i = 0; i < point1.length; i++) {
            distance += (point1[i] - point2[i]) * (point1[i] - point2[i]);
        }
        return distance;
    }
}
