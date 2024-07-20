package algorithms.trees.binary-search-tree.java;

public class Main {
    public static void main(String[] args) {
        // Example: Create a binary search tree and perform insertions
        BinarySearchTree bst = new BinarySearchTree();
        int[] keys = { 10, 5, 15, 3, 7, 12, 17 };
        for (int key : keys) {
            bst.insert(key);
        }

        // Perform inorder traversal to print keys in sorted order
        System.out.println("Inorder traversal of the binary search tree:");
        bst.inorderTraversal();

        // Search for a key in the binary search tree
        int searchKey = 7;
        TreeNode result = bst.search(searchKey);
        if (result != null) {
            System.out.println("Key " + searchKey + " found in the binary search tree.");
        } else {
            System.out.println("Key " + searchKey + " not found in the binary search tree.");
        }
    }
}
