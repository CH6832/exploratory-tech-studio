import java.util.HashMap;
import java.util.Map;

// Flyweight interface
interface TreeType {
    void render(int x, int y);
}

// Concrete flyweight
class TreeTypeImpl implements TreeType {
    private String name;
    private String color;
    private String texture;

    public TreeTypeImpl(String name, String color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    public void render(int x, int y) {
        System.out.println("Render " + name + " tree at (" + x + ", " + y + ") with color " + color + " and texture " + texture);
    }
}

// Flyweight factory
class TreeFactory {
    private static Map<String, TreeType> treeTypes = new HashMap<>();

    public static TreeType getTreeType(String name, String color, String texture) {
        String key = name + "_" + color + "_" + texture;
        if (!treeTypes.containsKey(key)) {
            treeTypes.put(key, new TreeTypeImpl(name, color, texture));
        }
        return treeTypes.get(key);
    }
}

// Context
class Tree {
    private int x;
    private int y;
    private TreeType treeType;

    public Tree(int x, int y, TreeType treeType) {
        this.x = x;
        this.y = y;
        this.treeType = treeType;
    }

    public void render() {
        treeType.render(x, y);
    }
}

// Client code
public class Forest {
    public static void main(String[] args) {
        Forest forest = new Forest();
        forest.plantTree(1, 2, "Pine", "Green", "Thick");
        forest.plantTree(3, 4, "Oak", "Brown", "Thin");
        forest.plantTree(5, 6, "Pine", "Green", "Thick");

        forest.draw();
    }

    private final java.util.List<Tree> trees = new java.util.ArrayList<>();

    public void plantTree(int x, int y, String name, String color, String texture) {
        TreeType treeType = TreeFactory.getTreeType(name, color, texture);
        Tree tree = new Tree(x, y, treeType);
        trees.add(tree);
    }

    public void draw() {
        for (Tree tree : trees) {
            tree.render();
        }
    }
}
