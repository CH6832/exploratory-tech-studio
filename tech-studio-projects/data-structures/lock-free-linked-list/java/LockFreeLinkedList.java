import java.util.concurrent.atomic.AtomicMarkableReference;

public class LockFreeLinkedList {
    private class Node {
        int value;
        AtomicMarkableReference<Node> next;

        Node(int value, Node next) {
            this.value = value;
            this.next = new AtomicMarkableReference<>(next, false);
        }
    }

    private final Node head;

    public LockFreeLinkedList() {
        head = new Node(Integer.MIN_VALUE, null);
        head.next.set(new Node(Integer.MAX_VALUE, null), false);
    }

    public boolean insert(int value) {
        Node pred, curr;
        while (true) {
            Node[] nodes = find(value);
            pred = nodes[0];
            curr = nodes[1];

            if (curr.value == value) {
                return false; // Value already present
            } else {
                Node newNode = new Node(value, curr);
                if (pred.next.compareAndSet(curr, newNode, false, false)) {
                    return true;
                }
            }
        }
    }

    public boolean remove(int value) {
        Node pred, curr;
        while (true) {
            Node[] nodes = find(value);
            pred = nodes[0];
            curr = nodes[1];

            if (curr.value != value) {
                return false; // Value not present
            } else {
                Node succ = curr.next.getReference();
                if (!curr.next.attemptMark(succ, true)) {
                    continue; // Retry
                }
                pred.next.compareAndSet(curr, succ, false, false);
                return true;
            }
        }
    }

    public boolean contains(int value) {
        Node curr = head;
        while (curr.value < value) {
            curr = curr.next.getReference();
        }
        return curr.value == value && !curr.next.isMarked();
    }

    public void display() {
        Node curr = head.next.getReference();
        while (curr.value < Integer.MAX_VALUE) {
            System.out.print(curr.value + " ");
            curr = curr.next.getReference();
        }
        System.out.println();
    }

    private Node[] find(int value) {
        Node pred = null, curr = null, succ = null;
        boolean[] marked = {false};
        boolean snip;

        retry: while (true) {
            pred = head;
            curr = pred.next.getReference();

            while (true) {
                succ = curr.next.get(marked);
                while (marked[0]) {
                    snip = pred.next.compareAndSet(curr, succ, false, false);
                    if (!snip) continue retry;
                    curr = succ;
                    succ = curr.next.get(marked);
                }
                if (curr.value >= value) {
                    return new Node[]{pred, curr};
                }
                pred = curr;
                curr = succ;
            }
        }
    }
}
