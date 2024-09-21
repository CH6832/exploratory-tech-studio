public class Main {
    public static void main(String[] args) {
        // Example usage of DisjointSet
        DisjointSet ds = new DisjointSet(5);

        ds.unionSets(0, 1);
        ds.unionSets(2, 3);
        ds.unionSets(0, 4);

        for (int i = 0; i < 5; ++i) {
            System.out.println("Parent of " + i + ": " + ds.find(i));
        }
    }
}
