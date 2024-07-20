/**
 * InsertionSort class that implements the insertion sort algorithm.
 */
public class InsertionSort {

    /**
     * Sorts an array using the insertion sort algorithm.
     * @param arr The array to be sorted.
     */
    public void sort(int[] arr) {
        System.out.println("Unsorted: ");
        printArray(arr);
        
        // Iterate over the array to be sorted
        for (int i = 1; i < arr.length; i++) {
            int x = arr[i];  // Get each element
            int j = i - 1;   // Get one position before x

            // Shift elements until reaching index 0 or getting an element smaller than x
            while (j >= 0 && arr[j] > x) {
                arr[j + 1] = arr[j];
                j--;
            }
            // Place x in its correct position
            arr[j + 1] = x;
        }
        
        System.out.println("Sorted: ");
        printArray(arr);
    }

    /**
     * Prints the elements of an array.
     * @param arr The array to be printed.
     */
    private void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
