#ifndef LEAST_SQUARES_FITTING_H
#define LEAST_SQUARES_FITTING_H

#include <vector>

/**
 * Perform least squares fitting to find the coefficients of the polynomial curve.
 *
 * @param xValues The x-coordinates of the data points.
 * @param yValues The y-coordinates of the data points.
 * @param degree The degree of the polynomial curve to fit.
 * @return A vector of coefficients in descending order of powers (highest power first).
 */
std::vector<double> leastSquaresFit(const std::vector<double>& xValues, const std::vector<double>& yValues, int degree);

#endif
