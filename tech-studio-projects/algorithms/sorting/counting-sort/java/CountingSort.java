import java.util.Arrays;

/**
 * Class that provides a method to perform Counting Sort on an array of characters.
 */
public class CountingSort {
    /**
     * Sorts an array of characters using the Counting Sort algorithm.
     *
     * @param arr The array of characters to be sorted.
     * @return The sorted array of characters.
     */
    public static char[] countingSort(char[] arr) {
        int n = arr.length;

        // The output character array that will have sorted arr
        char[] output = new char[n];

        // Create a count array to store count of individual characters
        int[] count = new int[256];
        Arrays.fill(count, 0);

        // Store count of each character
        for (int i = 0; i < n; i++) {
            count[arr[i]]++;
        }

        // Change count[i] so that count[i] now contains actual position of this character in output array
        for (int i = 1; i < 256; i++) {
            count[i] += count[i - 1];
        }

        // Build the output character array
        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }

        return output;
    }
}
