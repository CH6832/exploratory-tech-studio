/**
 * MergeSort class that implements the merge sort algorithm.
 */
public class MergeSort {

    /**
     * Sorts an array using the merge sort algorithm.
     * @param arr The array to be sorted.
     */
    public void sort(int[] arr) {
        if (arr.length > 1) {
            // Finding the mid of the array
            int mid = arr.length / 2;

            // Dividing the array elements into 2 halves
            int[] leftPart = new int[mid];
            int[] rightPart = new int[arr.length - mid];

            System.arraycopy(arr, 0, leftPart, 0, mid);
            System.arraycopy(arr, mid, rightPart, 0, arr.length - mid);

            // Sorting both halves
            sort(leftPart);
            sort(rightPart);

            // Merging the sorted halves
            merge(arr, leftPart, rightPart);
        }
    }

    /**
     * Merges two subarrays into the original array.
     * @param arr The original array that contains the merged result.
     * @param leftPart The left subarray.
     * @param rightPart The right subarray.
     */
    private void merge(int[] arr, int[] leftPart, int[] rightPart) {
        int i = 0, j = 0, k = 0;

        // Copy data to temp arrays leftPart[] and rightPart[]
        while (i < leftPart.length && j < rightPart.length) {
            if (leftPart[i] <= rightPart[j]) {
                arr[k] = leftPart[i];
                i++;
            } else {
                arr[k] = rightPart[j];
                j++;
            }
            k++;
        }

        // Checking if any element was left in leftPart[]
        while (i < leftPart.length) {
            arr[k] = leftPart[i];
            i++;
            k++;
        }

        // Checking if any element was left in rightPart[]
        while (j < rightPart.length) {
            arr[k] = rightPart[j];
            j++;
            k++;
        }
    }
}
