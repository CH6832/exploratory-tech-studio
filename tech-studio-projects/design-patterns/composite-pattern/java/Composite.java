import java.util.ArrayList;
import java.util.List;

// Composite class
public class Composite extends Component {
    private String name;
    private List<Component> children = new ArrayList<>();

    public Composite(String name) {
        this.name = name;
    }

    public void add(Component component) {
        children.add(component);
    }

    public void remove(Component component) {
        children.remove(component);
    }

    @Override
    public void operation() {
        System.out.println("Composite " + name + " operation:");
        for (Component child : children) {
            child.operation();
        }
    }
}
