public class Main {
    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        int[] keys = {9, 5, 10, 0, 6, 11, -1, 1, 2};
        for (int key : keys) {
            avlTree.insert(key);
        }

        System.out.println("Inorder traversal of the AVL tree:");
        avlTree.inorderTraversal();
    }
}
