// vectorArithmetic.cpp

#include "vectorArithmetic.h"

std::vector<int> vector_addition(const std::vector<int>& v1, const std::vector<int>& v2) {
    if (v1.size() != v2.size()) {
        throw std::invalid_argument("Vectors must have the same length.");
    }
    
    std::vector<int> result;
    for (size_t i = 0; i < v1.size(); ++i) {
        result.push_back(v1[i] + v2[i]);
    }
    return result;
}

std::vector<int> vector_subtraction(const std::vector<int>& v1, const std::vector<int>& v2) {
    if (v1.size() != v2.size()) {
        throw std::invalid_argument("Vectors must have the same length.");
    }
    
    std::vector<int> result;
    for (size_t i = 0; i < v1.size(); ++i) {
        result.push_back(v1[i] - v2[i]);
    }
    return result;
}

std::vector<int> scalar_multiplication(const std::vector<int>& vector, int scalar) {
    std::vector<int> result;
    for (size_t i = 0; i < vector.size(); ++i) {
        result.push_back(scalar * vector[i]);
    }
    return result;
}

int dot_product(const std::vector<int>& v1, const std::vector<int>& v2) {
    if (v1.size() != v2.size()) {
        throw std::invalid_argument("Vectors must have the same length.");
    }
    
    int result = 0;
    for (size_t i = 0; i < v1.size(); ++i) {
        result += v1[i] * v2[i];
    }
    return result;
}

std::vector<int> cross_product(const std::vector<int>& v1, const std::vector<int>& v2) {
    if (v1.size() != 3 || v2.size() != 3) {
        throw std::invalid_argument("Cross product is only defined for 3-dimensional vectors.");
    }
    
    std::vector<int> result(3);
    result[0] = v1[1] * v2[2] - v1[2] * v2[1];
    result[1] = v1[2] * v2[0] - v1[0] * v2[2];
    result[2] = v1[0] * v2[1] - v1[1] * v2[0];
    return result;
}
