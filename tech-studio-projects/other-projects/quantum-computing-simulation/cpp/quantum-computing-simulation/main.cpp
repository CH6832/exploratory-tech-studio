#include "Qubit.h"

int main() {
    Qubit q(1, 0); // Initialize qubit |q⟩ = |0⟩
    q.printQubit();

    q.H();
    q.X(); // Apply X gate
    q.H(); // Apply H gate
    q.printQubit();

    Qubit q1(0, 1); // Initialize qubit |q1⟩ = |1⟩
    q1.H();
    q1.X(); // Apply X gate
    q1.printQubit();

    return 0;
}
