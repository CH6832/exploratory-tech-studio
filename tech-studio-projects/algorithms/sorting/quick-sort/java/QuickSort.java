/**
 * QuickSort class that implements the quick sort algorithm.
 */
public class QuickSort {

    /**
     * Sorts an array using the quick sort algorithm.
     * @param arr The array to be sorted.
     */
    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * Recursively sorts a subarray using the quick sort algorithm.
     * @param arr The array to be sorted.
     * @param low The lowest index of the subarray.
     * @param high The highest index of the subarray.
     */
    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Partition the array and get the pivot index
            int pivotIndex = partition(arr, low, high);

            // Recursively sort the subarrays
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    /**
     * Partitions the array around a pivot element and returns the pivot index.
     * @param arr The array to be partitioned.
     * @param low The lowest index of the subarray.
     * @param high The highest index of the subarray.
     * @return The pivot index.
     */
    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // Choose the last element as the pivot
        int i = low - 1; // Index of smaller element

        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (arr[j] <= pivot) {
                i++;

                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
}
