// vectorArithmetic.h

#ifndef VECTOR_ARITHMETIC_H
#define VECTOR_ARITHMETIC_H

#include <vector>

std::vector<int> vector_addition(const std::vector<int>& v1, const std::vector<int>& v2);

std::vector<int> vector_subtraction(const std::vector<int>& v1, const std::vector<int>& v2);

std::vector<int> scalar_multiplication(const std::vector<int>& vector, int scalar);

int dot_product(const std::vector<int>& v1, const std::vector<int>& v2);

std::vector<int> cross_product(const std::vector<int>& v1, const std::vector<int>& v2);

#endif // VECTOR_ARITHMETIC_H
