public class Main {
    public static void main(String[] args) {
        Heap maxHeap = new Heap();

        // Insert elements into the heap
        maxHeap.insert(10);
        maxHeap.insert(20);
        maxHeap.insert(15);
        maxHeap.insert(30);
        maxHeap.insert(5);

        // Display the heap
        System.out.print("Heap after insertion: ");
        maxHeap.display();

        // Get and remove the maximum element from the heap
        int max = maxHeap.extractMax();
        System.out.println("Extracted maximum element: " + max);

        // Display the heap after extraction
        System.out.print("Heap after extraction: ");
        maxHeap.display();
    }
}
