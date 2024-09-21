/**
 * Main class to demonstrate the Counting Sort algorithm.
 */
public class Main {
    /**
     * The main method to run the Counting Sort demonstration.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        char[] inputArr = {'g', 'e', 'e', 'k', 's', 'f', 'o', 'r', 'g', 'e', 'e', 'k', 's'};
        char[] sortedArr = CountingSort.countingSort(inputArr);
        System.out.println("Sorted array: " + new String(sortedArr));
    }
}
