public class Product {
    private String part1;
    private String part2;
    private String part3;

    public Product(String part1, String part2, String part3) {
        this.part1 = part1;
        this.part2 = part2;
        this.part3 = part3;
    }

    @Override
    public String toString() {
        return "Part 1: " + part1 + ", Part 2: " + part2 + ", Part 3: " + part3;
    }
}
