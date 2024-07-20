#include <iostream>
#include <vector>
#include "leastSquaresFitting.h"

/**
 * Main function to demonstrate least squares fitting.
 */
int main() {
    // Example: Fit a polynomial curve to a set of data points
    std::vector<double> xValues = {1, 2, 3, 4, 5};
    std::vector<double> yValues = {2.3, 3.5, 4.2, 5.0, 6.1};
    int degree = 2;
    std::vector<double> coefficients = leastSquaresFit(xValues, yValues, degree);
    std::cout << "Coefficients of the polynomial curve: ";
    for (double coeff : coefficients) {
        std::cout << coeff << " ";
    }
    std::cout << std::endl;
    return 0;
}
