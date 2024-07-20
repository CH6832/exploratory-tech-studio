/**
 * Represents a node in the splay tree.
 */
public class SplayNode {
    public int key;
    public SplayNode left;
    public SplayNode right;

    /**
     * Constructs a new SplayNode with the given key.
     * @param key The key value of the node.
     */
    public SplayNode(int key) {
        this.key = key;
        this.left = null;
        this.right = null;
    }
}
