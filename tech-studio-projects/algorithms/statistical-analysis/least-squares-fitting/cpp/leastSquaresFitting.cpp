#include "leastSquaresFitting.h"
#include <iostream>
#include <vector>
#include <cmath>

/**
 * Perform least squares fitting to find the coefficients of the polynomial curve.
 *
 * @param xValues The x-coordinates of the data points.
 * @param yValues The y-coordinates of the data points.
 * @param degree The degree of the polynomial curve to fit.
 * @return A vector of coefficients in descending order of powers (highest power first).
 */
std::vector<double> leastSquaresFit(const std::vector<double>& xValues, const std::vector<double>& yValues, int degree) {
    int n = xValues.size();
    std::vector<std::vector<double>> vandermondeMatrix(n, std::vector<double>(degree + 1, 0.0));

    // Create the Vandermonde matrix
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j <= degree; ++j) {
            vandermondeMatrix[i][j] = std::pow(xValues[i], j);
        }
    }

    std::vector<double> coefficients(degree + 1, 0.0);
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
std::vector<double> linearLeastSquares(const std::vector<std::vector<double>>& X, const std::vector<double>& y) {
    int rows = X.size();
    int cols = X[0].size();
    std::vector<std::vector<double>> Xt(cols, std::vector<double>(rows, 0.0));
    std::vector<std::vector<double>> XtX(cols, std::vector<double>(cols, 0.0));
    std::vector<double> Xty(cols, 0.0);

    // Transpose X
    for (int i = 0; i < rows; ++i) {
        for (int j = 0; j < cols; ++j) {
            Xt[j][i] = X[i][j];
        }
    }

    // Compute XtX and Xty
    for (int i = 0; i < cols; ++i) {
        for (int j = 0; j < cols; ++j) {
            for (int k = 0; k < rows; ++k) {
                XtX[i][j] += Xt[i][k] * X[k][j];
            }
        }
        for (int k = 0; k < rows; ++k) {
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
std::vector<double> gaussianElimination(std::vector<std::vector<double>>& A, std::vector<double>& b) {
    int n = b.size();

    for (int p = 0; p < n; ++p) {
        int max = p;
        for (int i = p + 1; i < n; ++i) {
            if (std::abs(A[i][p]) > std::abs(A[max][p])) {
                max = i;
            }
        }

        std::swap(A[p], A[max]);
        std::swap(b[p], b[max]);

        for (int i = p + 1; i < n; ++i) {
            double alpha = A[i][p] / A[p][p];
            b[i] -= alpha * b[p];
            for (int j = p; j < n; ++j) {
                A[i][j] -= alpha * A[p][j];
            }
        }
    }

    std::vector<double> x(n, 0.0);
    for (int i = n - 1; i >= 0; --i) {
        double sum = 0.0;
        for (int j = i + 1; j < n; ++j) {
            sum += A[i][j] * x[j];
        }
        x[i] = (b[i] - sum) / A[i][i];
    }
    return x;
}
