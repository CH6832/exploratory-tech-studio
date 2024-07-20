/**
 * Main class to demonstrate insertion sort.
 */
public class Main {

    /**
     * The main method that runs the insertion sort demonstration.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6};
        System.out.println("Original array:");
        printArray(arr);

        InsertionSort insertionSort = new InsertionSort();
        insertionSort.sort(arr);

        System.out.println("Sorted array:");
        printArray(arr);
    }

    /**
     * Prints the elements of an array.
     * @param arr The array to be printed.
     */
    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
