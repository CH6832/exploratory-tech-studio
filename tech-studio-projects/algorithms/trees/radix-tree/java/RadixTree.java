// RadixTree.java

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the radix tree data structure.
 */
public class RadixTree {
    private RadixNode root;

    public RadixTree() {
        this.root = new RadixNode(null);
    }

    public void insert(String word) {
        RadixNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children.containsKey(c)) {
                node = node.children.get(c);
            } else {
                RadixNode newNode = new RadixNode(c);
                node.children.put(c, newNode);
                node = newNode;
            }
        }
    }

    public boolean search(String word) {
        RadixNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children.containsKey(c)) {
                node = node.children.get(c);
            } else {
                return false;
            }
        }
        return true;
    }
}
