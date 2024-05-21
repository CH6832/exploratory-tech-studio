#ifndef MATRIX_ARITHMETIC_H
#define MATRIX_ARITHMETIC_H

#include <vector>

class MatrixArithmetic {
public:
    static std::vector<std::vector<int>> matrixMultiplication(const std::vector<std::vector<int>>& A, const std::vector<std::vector<int>>& B);
    static std::vector<std::vector<int>> matrixAddition(const std::vector<std::vector<int>>& A, const std::vector<std::vector<int>>& B);
    static std::vector<std::vector<int>> scalarMultiplication(const std::vector<std::vector<int>>& A, int scalar);
    static std::vector<std::vector<int>> transpose(const std::vector<std::vector<int>>& A);
    static int determinant(const std::vector<std::vector<int>>& A);
    static std::vector<std::vector<float>> inverse(const std::vector<std::vector<int>>& A);
    static std::pair<float, float> eigenvalues(const std::vector<std::vector<int>>& A);
    void printMatrix(const std::vector<std::vector<int>>& matrix);
};

#endif // MATRIX_ARITHMETIC_H
