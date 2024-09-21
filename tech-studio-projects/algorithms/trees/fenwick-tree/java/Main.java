public class Main {
    public static void main(String[] args) {
        // Example usage
        int[] nums = {1, 3, 5, 7, 9, 11, 13, 15};
        FenwickTree fenwickTree = new FenwickTree(nums.length);
        for (int i = 0; i < nums.length; i++) {
            fenwickTree.update(i + 1, nums[i]);
        }

        // Print prefix sums
        for (int i = 0; i <= nums.length; i++) {
            System.out.println("Prefix sum up to index " + i + ": " + fenwickTree.query(i));
        }
    }
}
