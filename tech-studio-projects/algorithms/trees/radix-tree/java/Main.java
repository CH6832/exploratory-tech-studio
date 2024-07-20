public class Main {
    public static void main(String[] args) {
        RadixTree radixTree = new RadixTree();
        String[] words = {"apple", "banana", "apricot", "app", "bat", "cat", "car"};
        for (String word : words) {
            radixTree.insert(word);
        }

        System.out.println(radixTree.search("apple"));    // Output: true
        System.out.println(radixTree.search("banana"));   // Output: true
        System.out.println(radixTree.search("apricot"));  // Output: true
        System.out.println(radixTree.search("app"));      // Output: true
        System.out.println(radixTree.search("bat"));      // Output: true
        System.out.println(radixTree.search("cat"));      // Output: true
        System.out.println(radixTree.search("car"));      // Output: true
        System.out.println(radixTree.search("ap"));       // Output: false
        System.out.println(radixTree.search("ba"));       // Output: false
        System.out.println(radixTree.search("ca"));       // Output: false
        System.out.println(radixTree.search("b"));        // Output: false
    }
}
