import java.util.ArrayList;
import java.util.List;

class Node {
    int val;
    Node left, right;

    Node(int key) {
        val = key;
        left = right = null;
    }
}

public class LowestCommonAncestor {

    /**
     * Find the path from the root node to the given node.
     *
     * @param root The root of the tree.
     * @param path List to store the path.
     * @param k The target node value.
     * @return True if the path exists, otherwise false.
     */
    private static boolean findPath(Node root, List<Integer> path, int k) {
        // Base Case
        if (root == null)
            return false;

        // Store the current node in the path
        path.add(root.val);

        // Check if the current node is the target node
        if (root.val == k)
            return true;

        // Check if the target node is found in the left or right subtree
        if ((root.left != null && findPath(root.left, path, k)) ||
            (root.right != null && findPath(root.right, path, k)))
            return true;

        // If the target node is not found, remove the current node from the path and return false
        path.remove(path.size() - 1);
        return false;
    }

    /**
     * Find the lowest common ancestor (LCA) of two nodes in a binary tree.
     *
     * @param root The root of the tree.
     * @param n1 The value of the first node.
     * @param n2 The value of the second node.
     * @return The value of the lowest common ancestor, or -1 if either node is not present.
     */
    public static int findLCA(Node root, int n1, int n2) {
        List<Integer> path1 = new ArrayList<>();
        List<Integer> path2 = new ArrayList<>();

        // Find paths from root to n1 and root to n2. If either n1 or n2 is not present, return -1
        if (!findPath(root, path1, n1) || !findPath(root, path2, n2))
            return -1;

        // Compare the paths to get the first different value
        int i;
        for (i = 0; i < path1.size() && i < path2.size(); i++) {
            if (!path1.get(i).equals(path2.get(i)))
                break;
        }
        return path1.get(i - 1);
    }

    public static void main(String[] args) {
        // Example usage
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        // Example call to find the lowest common ancestor of nodes 4 and 6
        int lca = findLCA(root, 4, 6);
        System.out.println("Lowest Common Ancestor of 4 and 6: " + lca);
    }
}
