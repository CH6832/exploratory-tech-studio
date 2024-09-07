#include "Qubit.h"
#include <iostream>
#include <cmath>

const float R2 = 0.7071067812; // it is 1 / sqrt(2)

Qubit::Qubit(float zero_amplitude, float one_amplitude)
    : zero(zero_amplitude), one(one_amplitude) {}

void Qubit::printQubit() const {
    std::cout << "(" << zero << ", " << one << ")\n";
}

void Qubit::X() {
    float tmp_zero = 0 * zero + 1 * one;
    float tmp_one = 1 * zero + 0 * one;
    zero = tmp_zero;
    one = tmp_one;
}

void Qubit::H() {
    float tmp_zero = R2 * zero + R2 * one;
    float tmp_one = R2 * zero - R2 * one;
    zero = tmp_zero;
    one = tmp_one;
}
