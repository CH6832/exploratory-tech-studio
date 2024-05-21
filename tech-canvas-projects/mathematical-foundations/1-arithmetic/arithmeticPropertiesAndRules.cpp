#include "arithmeticPropertiesAndRules.h"

bool ArithmeticPropertiesAndRules::commutativePropertyAddition(double x, double y) {
    return x + y == y + x;
}

bool ArithmeticPropertiesAndRules::associativePropertyAddition(double x, double y, double z) {
    return (x + y) + z == x + (y + z);
}

bool ArithmeticPropertiesAndRules::distributiveProperty(double x, double y, double z) {
    return x * (y + z) == x * y + x * z;
}

bool ArithmeticPropertiesAndRules::identityElementsAddition(double x) {
    return x + 0 == x && 0 + x == x;
}

bool ArithmeticPropertiesAndRules::inverseElementsAddition(double x) {
    return x + (-x) == 0 && (-x) + x == 0;
}
