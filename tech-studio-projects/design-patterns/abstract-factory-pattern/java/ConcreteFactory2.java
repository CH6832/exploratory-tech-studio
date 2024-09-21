public class ConcreteFactory2 implements AbstractFactory {
    @Override
    public ConcreteProductB createProduct() {
        return new ConcreteProductB();
    }
}
