#include <iostream>
#include <vector>

/**
 * LinearRegression.cpp
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
 * Example Usage: In the main function, an example usage of the linearRegression function
 * is provided. We define a matrix of input data X and corresponding output values y,
 * and then call the linearRegression function to find the coefficients and the coefficient
 * of determination. Finally, we print the results.
 */

using namespace std;

// Class to represent a matrix
class Matrix {
public:
    vector<vector<double>> data;

    // Constructor
    Matrix(const vector<vector<double>>& data) : data(data) {}

    // Transpose operation
    Matrix transpose() const {
        vector<vector<double>> result(data[0].size(), vector<double>(data.size()));
        for (size_t i = 0;
