import java.util.HashMap;
import java.util.Map;

/**
 * Represents a node in the radix tree.
 */
public class RadixNode {
    Character value;
    Map<Character, RadixNode> children;

    public RadixNode(Character value) {
        this.value = value;
        this.children = new HashMap<>();
    }
}
