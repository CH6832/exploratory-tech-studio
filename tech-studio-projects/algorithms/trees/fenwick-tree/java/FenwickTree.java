public class FenwickTree {
    private int size;
    private int[] tree;

    public FenwickTree(int size) {
        this.size = size;
        this.tree = new int[size + 1];
    }

    public void update(int index, int delta) {
        while (index <= size) {
            tree[index] += delta;
            index += index & -index;
        }
    }

    public int query(int index) {
        int result = 0;
        while (index > 0) {
            result += tree[index];
            index -= index & -index;
        }
        return result;
    }

    public int rangeQuery(int start, int end) {
        return query(end) - (start > 0 ? query(start - 1) : 0);
    }
}
