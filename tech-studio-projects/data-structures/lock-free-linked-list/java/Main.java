public class Main {
    public static void main(String[] args) {
        LockFreeLinkedList list = new LockFreeLinkedList();

        // Insert elements into the linked list
        list.insert(10);
        list.insert(20);
        list.insert(15);

        // Display the linked list
        System.out.print("Linked List after insertion: ");
        list.display();

        // Remove an element from the linked list
        list.remove(20);

        // Display the linked list after removal
        System.out.print("Linked List after removal: ");
        list.display();

        // Check if the linked list contains a value
        System.out.println("Linked List contains 15: " + list.contains(15));
        System.out.println("Linked List contains 25: " + list.contains(25));
    }
}
