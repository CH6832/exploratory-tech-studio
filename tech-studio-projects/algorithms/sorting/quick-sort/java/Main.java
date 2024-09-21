import java.util.Arrays;

/**
 * Main class to demonstrate quick sort.
 */
public class Main {

    /**
     * The main method that runs the quick sort demonstration.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        int[] arr = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("Original array:");
        printArray(arr);

        QuickSort quickSort = new QuickSort();
        quickSort.sort(arr);

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
