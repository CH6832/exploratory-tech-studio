public class Node {
    private int[] point;
    private int axis;
    private Node left;
    private Node right;

    public Node(int[] point, int axis) {
        this.point = point;
        this.axis = axis;
        this.left = null;
        this.right = null;
    }

    public int[] getPoint() {
        return point;
    }

    public int getAxis() {
        return axis;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
