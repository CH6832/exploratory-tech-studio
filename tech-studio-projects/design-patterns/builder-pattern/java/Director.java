public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void construct() {
        this.builder.buildPart1("Part 1 built");
        this.builder.buildPart2("Part 2 built");
        this.builder.buildPart3("Part 3 built");
    }
}
