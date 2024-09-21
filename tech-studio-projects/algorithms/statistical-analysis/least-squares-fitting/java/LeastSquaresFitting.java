import java.util.Arrays;

/**
 * Class to perform least squares fitting.
 */
public class LeastSquaresFitting {

    /**
     * Perform least squares fitting to find the coefficients of the polynomial curve.
     *
     * @param xValues The x-coordinates of the data points.
     * @param yValues The y-coordinates of the data points.
     * @param degree The degree of the polynomial curve to fit.
     * @return An array of coefficients in descending order of powers (highest power first).
     */
    public static double[] leastSquaresFit(double[] xValues, double[] yValues, int degree) {
        int n = xValues.length;
        double[][] vandermondeMatrix = new double[n][degree + 1];
        
        // Create the Vandermonde matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= degree; j++) {
                vandermondeMatrix[i][j] = Math.pow(xValues[i], j);
            }
        }

        double[] coefficients = new double[degree + 1];
        double[] residuals = new double[n];
        
        // Perform least squares fitting
        coefficients = linearLeastSquares(vandermondeMatrix, yValues);

        return coefficients;
    }

    /**
     * Solve the linear least squares problem using normal equations.
     *
     * @param X The Vandermonde matrix.
     * @param y The y-coordinates of the data points.
     * @return The coefficients of the polynomial curve.
     */
    private static double[] linearLeastSquares(double[][] X, double[] y) {
        int rows = X.length;
        int cols = X[0].length;
        double[][] Xt = new double[cols][rows];
        double[][] XtX = new double[cols][cols];
        double[] Xty = new double[cols];

        // Transpose X
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Xt[j][i] = X[i][j];
            }
        }

        // Compute XtX and Xty
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < rows; k++) {
                    XtX[i][j] += Xt[i][k] * X[k][j];
                }
            }
            for (int k = 0; k < rows; k++) {
                Xty[i] += Xt[i][k] * y[k];
            }
        }

        // Solve XtX * coefficients = Xty using Gaussian elimination
        return gaussianElimination(XtX, Xty);
    }

    /**
     * Solve a system of linear equations using Gaussian elimination.
     *
     * @param A The matrix of coefficients.
     * @param b The right-hand side vector.
     * @return The solution vector.
     */
    private static double[] gaussianElimination(double[][] A, double[] b) {
        int n = b.length;

        for (int p = 0; p < n; p++) {
            int max = p;
            for (int i = p + 1; i < n; i++) {
                if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                    max = i;
                }
            }

            double[] temp = A[p];
            A[p] = A[max];
            A[max] = temp;

            double t = b[p];
            b[p] = b[max];
            b[max] = t;

            for (int i = p + 1; i < n; i++) {
                double alpha = A[i][p] / A[p][p];
                b[i] -= alpha * b[p];
                for (int j = p; j < n; j++) {
                    A[i][j] -= alpha * A[p][j];
                }
            }
        }

        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
        }
        return x;
    }
}
