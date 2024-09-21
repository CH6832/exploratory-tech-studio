public class Main {
    public static void main(String[] args) {
        // Create a skip list with maximum level 4
        SkipList<Integer> skipList = new SkipList<>(4);

        // Insert elements into the skip list
        skipList.insert(10);
        skipList.insert(20);
        skipList.insert(15);
        skipList.insert(25);

        // Display the skip list
        System.out.print("Skip List after insertion: ");
        skipList.display();

        // Remove an element from the skip list
        skipList.remove(20);

        // Display the skip list after removal
        System.out.print("Skip List after removal: ");
        skipList.display();

        // Check if the skip list contains a value
        System.out.println("Skip List contains 15: " + skipList.contains(15));
        System.out.println("Skip List contains 20: " + skipList.contains(20));
    }
}
