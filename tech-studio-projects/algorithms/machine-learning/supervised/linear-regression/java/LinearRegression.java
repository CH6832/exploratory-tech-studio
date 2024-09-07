import java.util.Arrays;

/**
 * LinearRegression.java
 *
 * Linear regression is a statistical method used to model the relationship between
 * a dependent variable and one or more independent variables. The goal is to find
 * the coefficients (parameters) of the linear equation that best fits the observed data.
 *
 * Matrix Formulation: The linear regression problem can be represented in matrix form
 * as X * coefficients = y, where X is the matrix of independent variables (features),
 * coefficients is the vector of unknown parameters, and y is the vector of observed
 * output values.
 *
 * Least Squares Method: The coefficients are computed using the least squares method,
 * which minimizes the sum of squared differences between the observed and predicted
 * output values.
 *
 * Coefficient of Determination (R^2): The coefficient of determination, often denoted
 * as R^2, measures the proportion of the variance in the dependent variable that is
 * predictable from the independent variables. It ranges from 0 to 1, with higher values
 * indicating a better fit of the model to the data.
 *
 * Example Usage: In the main method, an example usage of the linearRegression function
 * is provided. We define a matrix of input data X and corresponding output values y,
 * and then call the linearRegression function to find the coefficients and the coefficient
 * of determination. Finally, we print the results.
 */

public class LinearRegression {

    /**
     * Main method for driving code and example usage of linear regression.
     */
    public static void main(String[] args) {
        // Example: Linear regression
        double[][] X = {{1, 2}, {1, 3}, {1, 4}, {1, 5}};
        double[] y = {2, 3, 4, 5};

        double[] coefficients = linearRegression(X, y);

        System.out.println("Coefficients: " + Arrays.toString(coefficients));
        System.out.println("Coefficient of Determination (R^2): " + calculateRSquared(X, y, coefficients));
    }

    /**
     * Perform linear regression to find the coefficients.
     *
     * @param X Matrix of independent variables (features).
     * @param y Vector of dependent variable (target).
     * @return Coefficients (parameters) of the linear regression.
     */
    public static double[] linearRegression(double[][] X, double[] y) {
        int n = X[0].length;
        int m = X.length;

        // Add a column of ones for the intercept term
        double[][] X_with_intercept = new double[m][n + 1];
        for (int i = 0; i < m; i++) {
            X_with_intercept[i][0] = 1;
            System.arraycopy(X[i], 0, X_with_intercept[i], 1, n);
        }

        // Compute the coefficients using the least squares method
        Matrix X_matrix = new Matrix(X_with_intercept);
        Matrix y_matrix = new Matrix(y, m);
        Matrix coefficients_matrix = X_matrix.transpose().times(X_matrix).inverse().times(X_matrix.transpose()).times(y_matrix);

        return coefficients_matrix.getColumnPackedCopy();
    }

    /**
     * Calculate the coefficient of determination (R^2).
     *
     * @param X Matrix of independent variables (features).
     * @param y Vector of dependent variable (target).
     * @param coefficients Coefficients of the linear regression.
     * @return Coefficient of determination (R^2).
     */
    public static double calculateRSquared(double[][] X, double[] y, double[] coefficients) {
        int n = X[0].length;
        int m = X.length;

        // Add a column of ones for the intercept term
        double[][] X_with_intercept = new double[m][n + 1];
        for (int i = 0; i < m; i++) {
            X_with_intercept[i][0] = 1;
            System.arraycopy(X[i], 0, X_with_intercept[i], 1, n);
        }

        // Compute the predicted values
        double[] y_pred = new double[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= n; j++) {
                y_pred[i] += X_with_intercept[i][j] * coefficients[j];
            }
        }

        // Compute the total sum of squares
        double ss_total = 0;
        for (double value : y) {
            ss_total += Math.pow(value - mean(y), 2);
        }

        // Compute the residual sum of squares
        double ss_residual = 0;
        for (int i = 0; i < m; i++) {
            ss_residual += Math.pow(y[i] - y_pred[i], 2);
        }

        // Compute the coefficient of determination (R^2)
        return 1 - (ss_residual / ss_total);
    }

    /**
     * Calculate the mean of an array of values.
     *
     * @param arr Input array.
     * @return Mean of the array.
     */
    public static double mean(double[] arr) {
        double sum = 0;
        for (double value : arr) {
            sum += value;
        }
        return sum / arr.length;
    }
}
