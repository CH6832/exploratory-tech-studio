import java.util.Arrays;

public class ConjugateGradient {
    /**
     * Solves a linear system Ax = b using the Conjugate Gradient method.
     *
     * @param A       Coefficient matrix.
     * @param b       Right-hand side vector.
     * @param x0      Initial guess for the solution.
     * @param tol     Tolerance for convergence.
     * @param maxIter Maximum number of iterations.
     * @return The solution vector and number of iterations as a tuple.
     */
    public static Object[] conjugateGradient(double[][] A, double[] b, double[] x0, double tol, int maxIter) {
        double[] x = Arrays.copyOf(x0, x0.length);
        double[] r = Arrays.copyOf(b, b.length);
        double[] p = Arrays.copyOf(r, r.length);

        double rsold = dotProduct(r, r);
        for (int i = 0; i < maxIter; i++) {
            double[] Ap = matrixVectorProduct(A, p);
            double alpha = rsold / dotProduct(p, Ap);
            for (int j = 0; j < x.length; j++) {
                x[j] += alpha * p[j];
                r[j] -= alpha * Ap[j];
            }
            double rsnew = dotProduct(r, r);
            if (Math.sqrt(rsnew) < tol) {
                return new Object[]{x, i + 1};
            }
            double beta = rsnew / rsold;
            for (int j = 0; j < p.length; j++) {
                p[j] = r[j] + beta * p[j];
            }
            rsold = rsnew;
        }
        return new Object[]{x, maxIter};
    }

    /**
     * Computes the dot product of two vectors.
     *
     * @param a Vector a.
     * @param b Vector b.
     * @return The dot product of vectors a and b.
     */
    public static double dotProduct(double[] a, double[] b) {
        double result = 0.0;
        for (int i = 0; i < a.length; i++) {
            result += a[i] * b[i];
        }
        return result;
    }

    /**
     * Computes the matrix-vector product.
     *
     * @param A Matrix A.
     * @param x Vector x.
     * @return The result of the matrix-vector product Ax.
     */
    public static double[] matrixVectorProduct(double[][] A, double[] x) {
        int m = A.length;
        int n = A[0].length;
        double[] result = new double[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i] += A[i][j] * x[j];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // Example: Solve a linear system using Conjugate Gradient method
        double[][] A = {{4, 1}, {1, 3}};
        double[] b = {1, 2};
        double[] x0 = {0, 0};
        double tol = 1e-6;
        int maxIter = 1000;

        Object[] result = conjugateGradient(A, b, x0, tol, maxIter);
        double[] solution = (double[]) result[0];
        int iterations = (int) result[1];

        System.out.println("Solution: " + Arrays.toString(solution));
        System.out.println("Number of iterations: " + iterations);
    }
}
