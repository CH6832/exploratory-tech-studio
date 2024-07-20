import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents Bucket sort algorithm.
 */
public class BucketSort {
    /**
     * Bucket sort algorithm with visualization.
     *
     * @param arr The array to be sorted
     */
    public static void bucketSort(double[] arr) {
        int n = arr.length;
        if (n <= 0)
            return;

        // Create empty buckets
        ArrayList<Double>[] buckets = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Initial array
        System.out.print("Initial array: ");
        for (double num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Distribute array elements into buckets
        for (double num : arr) {
            int bucketIndex = (int) (num * n);
            buckets[bucketIndex].add(num);
            System.out.println("Element " + num + " -> Bucket " + bucketIndex);
        }

        // Print buckets after distribution
        System.out.println("\nBuckets after distribution:");
        for (int i = 0; i < n; i++) {
            System.out.print("Bucket " + i + ": ");
            for (double num : buckets[i]) {
                System.out.print(num + " ");
            }
            System.out.println();
        }

        // Sort individual buckets
        for (int i = 0; i < n; i++) {
            Collections.sort(buckets[i]);
        }

        // Print buckets after sorting
        System.out.println("\nBuckets after sorting:");
        for (int i = 0; i < n; i++) {
            System.out.print("Bucket " + i + ": ");
            for (double num : buckets[i]) {
                System.out.print(num + " ");
            }
            System.out.println();
        }

        // Concatenate all buckets into the original array
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (double num : buckets[i]) {
                arr[index++] = num;
            }
        }
    }
}
