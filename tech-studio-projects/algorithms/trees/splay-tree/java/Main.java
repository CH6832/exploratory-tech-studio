/**
 * Main class to demonstrate SplayTree functionality.
 */
public class Main {
    /**
     * Main method to execute the program.
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        SplayTree splayTree = new SplayTree();
        int[] keys = {7, 3, 10, 2, 6, 9, 11, 1, 5, 8, 12};

        for (int key : keys)
            splayTree.insert(key);

        System.out.println(splayTree.search(8)); // Output: true
        System.out.println(splayTree.search(4)); // Output: false
    }
}
