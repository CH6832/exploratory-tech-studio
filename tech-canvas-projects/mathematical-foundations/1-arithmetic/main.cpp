// main.cpp

#include <iostream>
#include "matrixArithmetic.h"
#include "vectorArithmetic.h"
#include "floatingPointArithmetic.h"
#include "arithmeticPropertiesAndRules.h"

int main() {
    // Matrix arithmetic

    MatrixArithmetic matrixArithmetic;

    std::vector<std::vector<int>> A = {{2, 3}, {5, 7}};
    std::vector<std::vector<int>> B = {{1, 4}, {2, 6}};
    std::vector<std::vector<int>> C = {{3, 5}, {7, 9}};

    std::cout << "Matrix A:\n" << std::endl;
    matrixArithmetic.printMatrix(A);
    std::cout << "Matrix B:\n" << std::endl;
    matrixArithmetic.printMatrix(B);
    std::cout << "Matrix C:\n" << std::endl;
    matrixArithmetic.printMatrix(C);

    std::vector<std::vector<int>> AB = matrixArithmetic.matrixMultiplication(A, B);
    std::cout << "Matrix AB:\n";
    matrixArithmetic.printMatrix(AB);

    std::vector<std::vector<int>> ABC_add = matrixArithmetic.matrixAddition(AB, C);
    std::cout << "Matrix AB + C:\n";
    matrixArithmetic.printMatrix(ABC_add);

    std::vector<std::vector<int>> scalar_mult_result = matrixArithmetic.scalarMultiplication(A, 3);
    std::cout << "Scalar multiplication of A by 3:\n";
    matrixArithmetic.printMatrix(scalar_mult_result);

    std::vector<std::vector<int>> transpose_A = matrixArithmetic.transpose(A);
    std::cout << "Transpose of A:\n";
    matrixArithmetic.printMatrix(transpose_A);

    int det_A = matrixArithmetic.determinant(A);
    std::cout << "Determinant of A: " << det_A << "\n";

    std::vector<std::vector<float>> inverse_A = matrixArithmetic.inverse(A);
    
    std::vector<std::vector<int>> inverse_A_int;
    for (const auto& row : inverse_A) {
        std::vector<int> int_row;
        for (const auto& elem : row) {
            int_row.push_back(static_cast<int>(elem));
        }
        inverse_A_int.push_back(int_row);
    }
    
    std::cout << "Inverse of A:\n";
    matrixArithmetic.printMatrix(inverse_A_int);

    // Vector arithmetic

    std::vector<int> v1 = {2, 3, 4};
    std::vector<int> v2 = {5, 6, 7};

    std::cout << "Vector v1:\n";
    print_vector(v1);
    std::cout << "Vector v2:\n";
    print_vector(v2);

    std::vector<int> v_add = vector_addition(v1, v2);
    std::cout << "Vector Addition of v1 and v2:\n";
    print_vector(v_add);

    std::vector<int> v_sub = vector_subtraction(v1, v2);
    std::cout << "Vector Subtraction of v1 and v2:\n";
    print_vector(v_sub);

    std::vector<int> scalar_mult = scalar_multiplication(v1, 3);
    std::cout << "Scalar Multiplication of v1 by 3:\n";
    print_vector(scalar_mult);

    int dot_prod = dot_product(v1, v2);
    std::cout << "Dot Product of v1 and v2: " << dot_prod << "\n";

    std::vector<int> cross_prod = cross_product(v1, v2);
    std::cout << "Cross Product of v1 and v2:\n";
    print_vector(cross_prod);

    // Floating-point arithmetic

    float x = 3.14159;
    float y = 2.71828;

    std::cout << "x: " << x << "\n";
    std::cout << "y: " << y << "\n";

    float sum_result = float_addition(x, y);
    std::cout << "Sum: " << sum_result << "\n";

    float difference_result = float_subtraction(x, y);
    std::cout << "Difference: " << difference_result << "\n";

    float product_result = float_multiplication(x, y);
    std::cout << "Product: " << product_result << "\n";

    float division_result = float_division(x, y);
    std::cout << "Division: " << division_result << "\n";

    float rounded_result = round_number(division_result, 3);
    std::cout << "Rounded Division Result: " << rounded_result << "\n";

    // Arithmetic properties and rules

    int x_prop = 3;
    int y_prop = 5;
    int z_prop = 7;

    std::cout << "x: " << x_prop << "\n";
    std::cout << "y: " << y_prop << "\n";
    std::cout << "z: " << z_prop << "\n";

    std::cout << "Commutative Property of Addition: " << commutative_property_addition(x_prop, y_prop) << "\n";
    std::cout << "Associative Property of Addition: " << associative_property_addition(x_prop, y_prop, z_prop) << "\n";
    std::cout << "Distributive Property: " << distributive_property(x_prop, y_prop, z_prop) << "\n";
    std::cout << "Identity Elements of Addition: " << identity_elements_addition(x_prop) << "\n";
    std::cout << "Inverse Elements: " << identity_elements_addition(x_prop) << "\n";
}