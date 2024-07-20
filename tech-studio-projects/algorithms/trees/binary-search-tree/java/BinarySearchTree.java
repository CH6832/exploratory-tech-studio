public class BinarySearchTree {
    private TreeNode root;

    /**
     * TreeNode: Represents a node in the binary search tree.
     */
    private static class TreeNode {
        int key;
        TreeNode left;
        TreeNode right;

        TreeNode(int key) {
            this.key = key;
            left = null;
            right = null;
        }
    }

    /**
     * Constructor: Initializes an empty binary search tree.
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Insert a key into the binary search tree.
     * 
     * @param key The key to be inserted.
     */
    public void insert(int key) {
        root = insertRec(root, key);
    }

    /**
     * Recursively insert a key into the binary search tree.
     * 
     * @param root The root of the current subtree.
     * @param key  The key to be inserted.
     * @return The root of the updated subtree.
     */
    private TreeNode insertRec(TreeNode root, int key) {
        if (root == null) {
            return new TreeNode(key);
        }

        if (key < root.key) {
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key);
        }

        return root;
    }

    /**
     * Search for a key in the binary search tree.
     * 
     * @param key The key to search for.
     * @return The TreeNode containing the key, or null if key is not found.
     */
    public TreeNode search(int key) {
        return searchRec(root, key);
    }

    /**
     * Recursively search for a key in the binary search tree.
     * 
     * @param root The root of the current subtree.
     * @param key  The key to search for.
     * @return The TreeNode containing the key, or null if key is not found.
     */
    private TreeNode searchRec(TreeNode root, int key) {
        if (root == null || root.key == key) {
            return root;
        }

        if (key < root.key) {
            return searchRec(root.left, key);
        } else {
            return searchRec(root.right, key);
        }
    }

    /**
     * Perform an inorder traversal of the binary search tree.
     */
    public void inorderTraversal() {
        inorderTraversalRec(root);
        System.out.println();
    }

    /**
     * Recursively perform an inorder traversal of the binary search tree.
     * 
     * @param root The root of the current subtree.
     */
    private void inorderTraversalRec(TreeNode root) {
        if (root != null) {
            inorderTraversalRec(root.left);
            System.out.print(root.key + " ");
            inorderTraversalRec(root.right);
        }
    }
}
