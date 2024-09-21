public class SuffixTree {
    private SuffixTreeNode root;
    private String text;

    public SuffixTree(String text) {
        this.root = new SuffixTreeNode(-1, null);
        this.text = text;
        buildSuffixTree();
    }

    private void buildSuffixTree() {
        int n = text.length();
        SuffixTreeNode activeNode = root;
        int activeEdge = 0;
        int activeLength = 0;
        int remainingSuffixCount = 0;
        SuffixTreeNode lastNewNode = null;

        for (int i = 0; i < n; i++) {
            lastNewNode = null;
            remainingSuffixCount++;

            while (remainingSuffixCount > 0) {
                if (activeLength == 0) {
                    activeEdge = i;
                }

                if (!activeNode.children.containsKey(text.charAt(i))) {
                    activeNode.children.put(text.charAt(i), new SuffixTreeNode(i, n - 1));

                    if (lastNewNode != null) {
                        lastNewNode.suffixLink = activeNode;
                        lastNewNode = null;
                    }
                } else {
                    SuffixTreeNode nextNode = activeNode.children.get(text.charAt(activeEdge));

                    if (activeLength >= nextNode.end - nextNode.start + 1) {
                        activeEdge += nextNode.end - nextNode.start + 1;
                        activeLength -= nextNode.end - nextNode.start + 1;
                        activeNode = nextNode;
                        continue;
                    }

                    if (text.charAt(nextNode.start + activeLength) == text.charAt(i)) {
                        if (lastNewNode != null && activeNode != root) {
                            lastNewNode.suffixLink = activeNode;
                            lastNewNode = null;
                        }

                        activeLength++;
                        break;
                    }

                    SuffixTreeNode newNode = new SuffixTreeNode(nextNode.start, nextNode.start + activeLength - 1);
                    activeNode.children.put(text.charAt(activeEdge), newNode);
                    newNode.children.put(text.charAt(i), new SuffixTreeNode(i, n - 1));
                    nextNode.start += activeLength;
                    newNode.children.put(text.charAt(nextNode.start), nextNode);

                    if (lastNewNode != null) {
                        lastNewNode.suffixLink = newNode;
                    }

                    lastNewNode = newNode;
                }

                remainingSuffixCount--;

                if (activeNode == root && activeLength > 0) {
                    activeLength--;
                    activeEdge = i - remainingSuffixCount + 1;
                } else if (activeNode != root) {
                    activeNode = activeNode.suffixLink;
                }
            }
        }
    }
}
