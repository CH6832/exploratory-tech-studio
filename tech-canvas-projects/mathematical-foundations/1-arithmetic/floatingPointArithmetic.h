#ifndef FLOATING_POINT_ARITHMETIC_H
#define FLOATING_POINT_ARITHMETIC_H

class FloatingPointArithmetic {
public:
    static double floatAddition(double x, double y);
    static double floatSubtraction(double x, double y);
    static double floatMultiplication(double x, double y);
    static double floatDivision(double x, double y);
    static double roundNumber(double number, int decimalPlaces = 2);
};

#endif // FLOATING_POINT_ARITHMETIC_H
