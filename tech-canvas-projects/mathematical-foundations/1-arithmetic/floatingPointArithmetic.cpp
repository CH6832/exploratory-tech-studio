#include "floatingPointArithmetic.h"

#include <cmath>
#include <stdexcept>

double FloatingPointArithmetic::floatAddition(double x, double y) {
    return x + y;
}

double FloatingPointArithmetic::floatSubtraction(double x, double y) {
    return x - y;
}

double FloatingPointArithmetic::floatMultiplication(double x, double y) {
    return x * y;
}

double FloatingPointArithmetic::floatDivision(double x, double y) {
    if (y == 0) {
        throw std::invalid_argument("Division by zero is not allowed.");
    }
    return x / y;
}

double FloatingPointArithmetic::roundNumber(double number, int decimalPlaces) {
    return std::round(number * std::pow(10, decimalPlaces)) / std::pow(10, decimalPlaces);
}
