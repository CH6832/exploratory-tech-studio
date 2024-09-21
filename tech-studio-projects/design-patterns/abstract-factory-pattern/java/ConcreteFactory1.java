public class ConcreteFactory1 implements AbstractFactory {
    @Override
    public ConcreteProductA createProduct() {
        return new ConcreteProductA();
    }
}
