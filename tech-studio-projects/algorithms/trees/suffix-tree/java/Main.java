package algorithms.trees.suffix-tree.java;

public class Main {

    private void traverse(SuffixTreeNode node, String suffix) {
        if (node.start != -1) {
            System.out.println(suffix + text.substring(node.start, node.end + 1));
        }

        for (SuffixTreeNode child : node.children.values()) {
            traverse(child, suffix + text.substring(node.start, node.end + 1));
        }
    }

    public void printSuffixes() {
        traverse(root, "");
    }
    public static void main(String[] args) {
        String text = "banana";
        SuffixTree suffixTree = new SuffixTree(text);
        suffixTree.printSuffixes();
    }
}
