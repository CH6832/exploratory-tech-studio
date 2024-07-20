public class Main {
    public static void main(String[] args) {
        // Create leaf components
        Leaf leaf1 = new Leaf("1");
        Leaf leaf2 = new Leaf("2");

        // Create composite components and add leaf components to them
        Composite composite1 = new Composite("A");
        composite1.add(leaf1);
        composite1.add(leaf2);

        Leaf leaf3 = new Leaf("3");
        Leaf leaf4 = new Leaf("4");

        Composite composite2 = new Composite("B");
        composite2.add(leaf3);
        composite2.add(leaf4);

        // Create another composite and add the first two composites to it
        Composite composite = new Composite("Root");
        composite.add(composite1);
        composite.add(composite2);

        // Operation on the whole composite structure
        composite.operation();
    }
}
