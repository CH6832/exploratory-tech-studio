import java.util.Random;

public class SkipList<T extends Comparable<T>> {
    private static final double PROBABILITY = 0.5;

    private class Node {
        T value;
        Node[] next;

        Node(T value, int level) {
            this.value = value;
            next = new Node[level + 1];
        }
    }

    private final int MAX_LEVEL;
    private int level;
    private Node head;
    private Random random;

    public SkipList(int maxLevel) {
        MAX_LEVEL = maxLevel;
        level = 0;
        head = new Node(null, MAX_LEVEL);
        random = new Random();
    }

    public void insert(T value) {
        Node[] update = new Node[MAX_LEVEL + 1];
        Node current = head;

        for (int i = level; i >= 0; i--) {
            while (current.next[i] != null && current.next[i].value.compareTo(value) < 0) {
                current = current.next[i];
            }
            update[i] = current;
        }

        current = current.next[0];

        if (current == null || !current.value.equals(value)) {
            int newLevel = randomLevel();
            if (newLevel > level) {
                for (int i = level + 1; i <= newLevel; i++) {
                    update[i] = head;
                }
                level = newLevel;
            }

            Node newNode = new Node(value, newLevel);
            for (int i = 0; i <= newLevel; i++) {
                newNode.next[i] = update[i].next[i];
                update[i].next[i] = newNode;
            }
        }
    }

    public boolean contains(T value) {
        Node current = head;
        for (int i = level; i >= 0; i--) {
            while (current.next[i] != null && current.next[i].value.compareTo(value) < 0) {
                current = current.next[i];
            }
        }
        current = current.next[0];
        return current != null && current.value.equals(value);
    }

    public void remove(T value) {
        Node[] update = new Node[MAX_LEVEL + 1];
        Node current = head;

        for (int i = level; i >= 0; i--) {
            while (current.next[i] != null && current.next[i].value.compareTo(value) < 0) {
                current = current.next[i];
            }
            update[i] = current;
        }

        current = current.next[0];

        if (current != null && current.value.equals(value)) {
            for (int i = 0; i <= level; i++) {
                if (update[i].next[i] != current) {
                    break;
                }
                update[i].next[i] = current.next[i];
            }

            while (level > 0 && head.next[level] == null) {
                level--;
            }
        }
    }

    private int randomLevel() {
        int level = 0;
        while (Math.random() < PROBABILITY && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    public void display() {
        for (int i = level; i >= 0; i--) {
            Node current = head.next[i];
            System.out.print("Level " + i + ": ");
            while (current != null) {
                System.out.print(current.value + " ");
                current = current.next[i];
            }
            System.out.println();
        }
    }
}
