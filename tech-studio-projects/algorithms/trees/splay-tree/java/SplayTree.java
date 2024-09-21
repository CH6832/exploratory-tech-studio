/**
 * Represents the splay tree data structure.
 */
public class SplayTree {
    private SplayNode root;

    /**
     * Constructs an empty SplayTree.
     */
    public SplayTree() {
        this.root = null;
    }

    /**
     * Inserts a key into the splay tree.
     * @param key The key to insert.
     */
    public void insert(int key) {
        this.root = insert(this.root, key);
    }

    /**
     * Helper method to insert a key into the splay tree.
     * @param node The root node of the current subtree.
     * @param key The key to insert.
     * @return The updated root node of the subtree.
     */
    private SplayNode insert(SplayNode node, int key) {
        if (node == null)
            return new SplayNode(key);

        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        }
        return node;
    }

    /**
     * Searches for a key in the splay tree.
     * @param key The key to search for.
     * @return True if the key is found, otherwise false.
     */
    public boolean search(int key) {
        this.root = splay(this.root, key);
        return this.root != null && this.root.key == key;
    }

    /**
     * Performs the splay operation on the tree.
     * @param node The root node of the current subtree.
     * @param key The key to splay.
     * @return The updated root node of the subtree.
     */
    private SplayNode splay(SplayNode node, int key) {
        if (node == null || node.key == key)
            return node;

        if (key < node.key) {
            if (node.left == null)
                return node;
            if (key < node.left.key) {
                node.left.left = splay(node.left.left, key);
                node = rotateRight(node);
            } else if (key > node.left.key) {
                node.left.right = splay(node.left.right, key);
                if (node.left.right != null)
                    node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        } else {
            if (node.right == null)
                return node;
            if (key > node.right.key) {
                node.right.right = splay(node.right.right, key);
                node = rotateLeft(node);
            } else if (key < node.right.key) {
                node.right.left = splay(node.right.left, key);
                if (node.right.left != null)
                    node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
    }

    /**
     * Performs a left rotation on the given node.
     * @param node The node to rotate.
     * @return The new root node after rotation.
     */
    private SplayNode rotateLeft(SplayNode node) {
        SplayNode newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        return newRoot;
    }

    /**
     * Performs a right rotation on the given node.
     * @param node The node to rotate.
     * @return The new root node after rotation.
     */
    private SplayNode rotateRight(SplayNode node) {
        SplayNode newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        return newRoot;
    }
}
