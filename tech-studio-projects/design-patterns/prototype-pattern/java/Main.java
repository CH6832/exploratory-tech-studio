import java.util.HashMap;
import java.util.Map;

// Prototype interface
interface Prototype {
    Prototype clone();
}

// Concrete prototype 1
class ConcretePrototype1 implements Prototype {
    private int attr1;
    private String attr2;

    public ConcretePrototype1(int attr1, String attr2) {
        this.attr1 = attr1;
        this.attr2 = attr2;
    }

    public Prototype clone() {
        return new ConcretePrototype1(attr1, attr2);
    }

    public void print() {
        System.out.println("ConcretePrototype1: { attr1: " + attr1 + ", attr2: " + attr2 + " }");
    }
}

// Concrete prototype 2
class ConcretePrototype2 implements Prototype {
    private float attr3;
    private int[] attr4;

    public ConcretePrototype2(float attr3, int[] attr4) {
        this.attr3 = attr3;
        this.attr4 = attr4.clone();
    }

    public Prototype clone() {
        return new ConcretePrototype2(attr3, attr4);
    }

    public void print() {
        System.out.print("ConcretePrototype2: { attr3: " + attr3 + ", attr4: [");
        for (int i : attr4) {
            System.out.print(i + ", ");
        }
        System.out.println("] }");
    }
}

// Prototype Factory
class PrototypeFactory {
    private static Map<String, Prototype> prototypes = new HashMap<>();

    static void registerPrototype(String name, Prototype prototype) {
        prototypes.put(name, prototype);
    }

    static Prototype createPrototype(String name) {
        Prototype prototype = prototypes.get(name);
        if (prototype != null) {
            return prototype.clone();
        }
        return null;
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        // Register prototypes
        PrototypeFactory.registerPrototype("prototype1", new ConcretePrototype1(10, "foo"));
        PrototypeFactory.registerPrototype("prototype2", new ConcretePrototype2(3.14f, new int[]{1, 2, 3}));

        // Clone prototypes
        Prototype clone1 = PrototypeFactory.createPrototype("prototype1");
        Prototype clone2 = PrototypeFactory.createPrototype("prototype2");

        // Output cloned objects
        if (clone1 instanceof ConcretePrototype1) {
            ((ConcretePrototype1) clone1).print();
        }
        if (clone2 instanceof ConcretePrototype2) {
            ((ConcretePrototype2) clone2).print();
        }
    }
}
