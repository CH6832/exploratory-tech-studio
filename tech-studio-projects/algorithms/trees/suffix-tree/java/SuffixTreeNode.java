import java.util.HashMap;

public class SuffixTreeNode {
    public HashMap<Character, SuffixTreeNode> children;
    public SuffixTreeNode suffixLink;
    public int start;
    public Integer end;

    public SuffixTreeNode(int start, Integer end) {
        this.children = new HashMap<>();
        this.suffixLink = null;
        this.start = start;
        this.end = end;
    }
}
