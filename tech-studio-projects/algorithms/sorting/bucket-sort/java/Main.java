/**
 * Main class to demonstrate bucket sort algorithm.
 */
public class Main {
    /**
     * The main method to execute the program.
     *
     * @param args The command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Driving code
        double[] arr = {0.78, 0.17, 0.39, 0.26, 0.72, 0.94, 0.21, 0.12, 0.23, 0.68};
        BucketSort.bucketSort(arr);

        // Print the sorted array
        System.out.print("Sorted array: ");
        for (double num : arr) {
            System.out.print(num + " ");
        }
    }
}
