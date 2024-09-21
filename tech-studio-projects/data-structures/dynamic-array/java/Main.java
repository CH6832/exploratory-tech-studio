public class Main {
    public static void main(String[] args) {
        DynamicArrayList list = new DynamicArrayList();

        // Append elements
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);

        // Insert element at index 2
        list.insert(2, 5);

        // Remove element at index 1
        list.remove(1);

        // Display list contents
        System.out.println("List contents:");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }
}
