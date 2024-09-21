import java.util.function.DoubleUnaryOperator;

public class NewtonsMethod {

    /**
     * Find a root of a function using Newton's method.
     *
     * @param f      The function for which to find the root.
     * @param df     The derivative of the function.
     * @param x0     Initial guess for the root.
     * @param tol    Tolerance for convergence.
     * @param maxItr Maximum number of iterations.
     * @return The root and the number of iterations.
     */
    public static Result newtonsMethod(DoubleUnaryOperator f, DoubleUnaryOperator df, double x0, double tol, int maxItr) {
        double x = x0;
        double delta_x;
        for (int i = 0; i < maxItr; i++) {
            delta_x = -f.applyAsDouble(x) / df.applyAsDouble(x);
            x += delta_x;
            if (Math.abs(delta_x) < tol) {
                return new Result(x, i + 1);
            }
        }
        return new Result(x, maxItr);
    }

    public static void main(String[] args) {
        // Example: Find a root of a function using Newton's method
        DoubleUnaryOperator f = x -> x * x - 4;
        DoubleUnaryOperator df = x -> 2 * x;
        double x0 = 2.0;
        double tol = 1e-6;
        int maxItr = 1000;

        Result result = newtonsMethod(f, df, x0, tol, maxItr);
        System.out.println("Root: " + result.root);
        System.out.println("Number of iterations: " + result.iterations);
    }

    static class Result {
        double root;
        int iterations;

        Result(double root, int iterations) {
            this.root = root;
            this.iterations = iterations;
        }
    }
}
