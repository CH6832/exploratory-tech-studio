import java.util.Arrays;

public class SuffixArray {
    private final String text;
    private final int[] suffixArray;

    public SuffixArray(String text) {
        this.text = text;
        this.suffixArray = new int[text.length()];
    }

    public void constructSuffixArray() {
        Suffix[] suffixes = new Suffix[text.length()];
        for (int i = 0; i < text.length(); i++) {
            suffixes[i] = new Suffix(i, text.substring(i));
        }

        Arrays.sort(suffixes);

        for (int i = 0; i < text.length(); i++) {
            suffixArray[i] = suffixes[i].index;
        }
    }

    public void displaySuffixArray() {
        for (int index : suffixArray) {
            System.out.print(index + " ");
        }
        System.out.println();
    }

    private static class Suffix implements Comparable<Suffix> {
        int index;
        String suffix;

        Suffix(int index, String suffix) {
            this.index = index;
            this.suffix = suffix;
        }

        @Override
        public int compareTo(Suffix other) {
            return this.suffix.compareTo(other.suffix);
        }
    }
}
