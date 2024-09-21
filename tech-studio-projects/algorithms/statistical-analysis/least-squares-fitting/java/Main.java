import java.util.Arrays;

/**
 * Main class to demonstrate least squares fitting.
 */
public class Main {
    public static void main(String[] args) {
        // Example: Fit a polynomial curve to a set of data points
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {2.3, 3.5, 4.2, 5.0, 6.1};
        int degree = 2;
        double[] coefficients = LeastSquaresFitting.leastSquaresFit(xValues, yValues, degree);
        System.out.println("Coefficients of the polynomial curve: " + Arrays.toString(coefficients));
    }
}
