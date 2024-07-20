public class Main {
    public static void main(String[] args) {
        String text = "banana";
        SuffixArray suffixArray = new SuffixArray(text);

        // Construct the suffix array
        suffixArray.constructSuffixArray();

        // Display the suffix array
        System.out.print("Suffix Array: ");
        suffixArray.displaySuffixArray();
    }
}
