/**
 * Represents a node in the segment tree.
 */
public class SegmentTreeNode {
    int start;
    int end;
    int total;
    SegmentTreeNode left;
    SegmentTreeNode right;

    public SegmentTreeNode(int start, int end) {
        this.start = start;
        this.end = end;
        this.total = 0;
        this.left = null;
        this.right = null;
    }
}
