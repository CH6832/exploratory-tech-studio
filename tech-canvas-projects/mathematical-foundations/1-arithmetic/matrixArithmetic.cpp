#include "matrixArithmetic.h"

#include <iostream>
#include <vector>
#include <stdexcept>
#include <cmath>

std::vector<std::vector<int>> MatrixArithmetic::matrixMultiplication(const std::vector<std::vector<int>>& A, const std::vector<std::vector<int>>& B) {
    if (A[0].size() != B.size()) {
        throw std::invalid_argument("Matrix dimensions are not compatible for multiplication.");
    }

    std::vector<std::vector<int>> result(A.size(), std::vector<int>(B[0].size(), 0));

    for (size_t i = 0; i < A.size(); ++i) {
        for (size_t j = 0; j < B[0].size(); ++j) {
            for (size_t k = 0; k < A[0].size(); ++k) {
                result[i][j] += A[i][k] * B[k][j];
            }
        }
    }

    return result;
}

std::vector<std::vector<int>> MatrixArithmetic::matrixAddition(const std::vector<std::vector<int>>& A, const std::vector<std::vector<int>>& B) {
    if (A.size() != B.size() || A[0].size() != B[0].size()) {
        throw std::invalid_argument("Matrix dimensions are not compatible for addition.");
    }

    std::vector<std::vector<int>> result(A.size(), std::vector<int>(A[0].size(), 0));

    for (size_t i = 0; i < A.size(); ++i) {
        for (size_t j = 0; j < A[0].size(); ++j) {
            result[i][j] = A[i][j] + B[i][j];
        }
    }

    return result;
}

std::vector<std::vector<int>> MatrixArithmetic::scalarMultiplication(const std::vector<std::vector<int>>& A, int scalar) {
    std::vector<std::vector<int>> result(A.size(), std::vector<int>(A[0].size(), 0));

    for (size_t i = 0; i < A.size(); ++i) {
        for (size_t j = 0; j < A[0].size(); ++j) {
            result[i][j] = scalar * A[i][j];
        }
    }

    return result;
}

std::vector<std::vector<int>> MatrixArithmetic::transpose(const std::vector<std::vector<int>>& A) {
    std::vector<std::vector<int>> result(A[0].size(), std::vector<int>(A.size(), 0));

    for (size_t i = 0; i < A.size(); ++i) {
        for (size_t j = 0; j < A[0].size(); ++j) {
            result[j][i] = A[i][j];
        }
    }

    return result;
}

int MatrixArithmetic::determinant(const std::vector<std::vector<int>>& A) {
    if (A.size() != 2 || A[0].size() != 2) {
        throw std::invalid_argument("This function only handles 2x2 matrices.");
    }

    return A[0][0] * A[1][1] - A[0][1] * A[1][0];
}

std::vector<std::vector<float>> MatrixArithmetic::inverse(const std::vector<std::vector<int>>& A) {
    int det = determinant(A);
    if (det == 0) {
        throw std::invalid_argument("Matrix is not invertible.");
    }

    std::vector<std::vector<float>> result(2, std::vector<float>(2, 0));

    result[0][0] = A[1][1] / static_cast<float>(det);
    result[0][1] = -A[0][1] / static_cast<float>(det);
    result[1][0] = -A[1][0] / static_cast<float>(det);
    result[1][1] = A[0][0] / static_cast<float>(det);

    return result;
}

std::pair<float, float> MatrixArithmetic::eigenvalues(const std::vector<std::vector<int>>& A) {
    if (A.size() != 2 || A[0].size() != 2) {
        throw std::invalid_argument("This function only handles 2x2 matrices.");
    }

    float trace = A[0][0] + A[1][1];
    int det = determinant(A);
    float discriminant = std::sqrt(trace * trace - 4 * det);

    if (discriminant < 0) {
        throw std::invalid_argument("The eigenvalues are complex.");
    }

    float lambda1 = (trace + discriminant) / 2;
    float lambda2 = (trace - discriminant) / 2;

    return std::make_pair(lambda1, lambda2);
}


void MatrixArithmetic::printMatrix(const std::vector<std::vector<int>>& matrix) {
    for (const auto& row : matrix) {
        for (int element : row) {
            std::cout << element << " ";
        }
        std::cout << std::endl;
    }
}