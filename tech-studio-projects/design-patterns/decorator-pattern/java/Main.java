// Component interface
interface Coffee {
    double cost();
}

// Concrete component
class SimpleCoffee implements Coffee {
    public double cost() {
        return 1.0;
    }
}

// Decorator class
abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public double cost() {
        return coffee.cost();
    }
}

// Concrete decorators
class Milk extends CoffeeDecorator {
    public Milk(Coffee coffee) {
        super(coffee);
    }

    public double cost() {
        return super.cost() + 0.5;
    }
}

class Sugar extends CoffeeDecorator {
    public Sugar(Coffee coffee) {
        super(coffee);
    }

    public double cost() {
        return super.cost() + 0.2;
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println("Cost of simple coffee: " + coffee.cost());

        coffee = new Milk(coffee);
        System.out.println("Cost of coffee with milk: " + coffee.cost());

        coffee = new Sugar(coffee);
        System.out.println("Cost of coffee with sugar: " + coffee.cost());

        coffee = new Sugar(new Milk(new SimpleCoffee()));
        System.out.println("Cost of coffee with milk and sugar: " + coffee.cost());
    }
}
