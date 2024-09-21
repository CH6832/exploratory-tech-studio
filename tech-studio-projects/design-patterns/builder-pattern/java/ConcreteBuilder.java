public class ConcreteBuilder extends Builder {
    private Product product;

    public ConcreteBuilder() {
        this.reset();
    }

    public void reset() {
        this.product = new Product("", "", "");
    }

    @Override
    public void buildPart1(String part1) {
        this.product = new Product(part1, this.product.toString().split(", Part 2: ")[1].split(", Part 3: ")[0], this.product.toString().split(", Part 3: ")[1]);
    }

    @Override
    public void buildPart2(String part2) {
        this.product = new Product(this.product.toString().split("Part 1: ")[1].split(", Part 2: ")[0], part2, this.product.toString().split(", Part 3: ")[1]);
    }

    @Override
    public void buildPart3(String part3) {
        this.product = new Product(this.product.toString().split("Part 1: ")[1].split(", Part 2: ")[0], this.product.toString().split(", Part 2: ")[1].split(", Part 3: ")[0], part3);
    }

    @Override
    public Product getResult() {
        Product product = this.product;
        this.reset();
        return product;
    }
}
