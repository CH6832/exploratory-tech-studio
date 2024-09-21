public class Main {
    public static void main(String[] args) {
        AbstractFactory factory1 = new ConcreteFactory1();
        clientCode(factory1);

        AbstractFactory factory2 = new ConcreteFactory2();
        clientCode(factory2);
    }

    public static void clientCode(AbstractFactory factory) {
        AbstractProduct product = factory.createProduct();
        System.out.println(product.operation());
    }
}
