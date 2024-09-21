// Product interface
interface Pizza {
    void prepare();
    void bake();
    void cut();
    void box();
}

// Concrete products
class CheesePizza implements Pizza {
    public void prepare() {
        System.out.println("Preparing Cheese Pizza...");
    }
    public void bake() {
        System.out.println("Baking Cheese Pizza...");
    }
    public void cut() {
        System.out.println("Cutting Cheese Pizza...");
    }
    public void box() {
        System.out.println("Boxing Cheese Pizza...");
    }
}

class PepperoniPizza implements Pizza {
    public void prepare() {
        System.out.println("Preparing Pepperoni Pizza...");
    }
    public void bake() {
        System.out.println("Baking Pepperoni Pizza...");
    }
    public void cut() {
        System.out.println("Cutting Pepperoni Pizza...");
    }
    public void box() {
        System.out.println("Boxing Pepperoni Pizza...");
    }
}

// Creator interface
interface PizzaStore {
    Pizza createPizza();
    default void orderPizza() {
        Pizza pizza = createPizza();
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
    }
}

// Concrete creators
class NYStylePizzaStore implements PizzaStore {
    public Pizza createPizza() {
        return new CheesePizza();
    }
}

class ChicagoStylePizzaStore implements PizzaStore {
    public Pizza createPizza() {
        return new PepperoniPizza();
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        PizzaStore nyStore = new NYStylePizzaStore();
        nyStore.orderPizza();

        PizzaStore chicagoStore = new ChicagoStylePizzaStore();
        chicagoStore.orderPizza();
    }
}
