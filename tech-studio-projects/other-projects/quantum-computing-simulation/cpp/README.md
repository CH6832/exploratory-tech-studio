# Quantum Computer Simulation

This project is a simple simulation of quantum computing concepts using C++. It demonstrates basic quantum gate operations like the X (NOT) gate and the Hadamard (H) gate on qubits (quantum bits). The simulation includes a class-based implementation of qubits and the operations that can be applied to them.

## Project Structure

The project is organized into the following files:

- `main.cpp`: The entry point of the program. This file demonstrates the use of the `Qubit` class by applying quantum gates to qubits and printing their states.
- `Qubit.h`: The header file for the `Qubit` class. It contains the class definition, along with declarations for the constructor and methods.
- `Qubit.cpp`: The implementation file for the `Qubit` class. It defines the constructor and methods declared in `Qubit.h`.

## Files Overview

### 1. `Qubit.h`

This header file defines the `Qubit` class. The `Qubit` class models a quantum bit, which has two amplitude values corresponding to the probability of being in state |0⟩ or |1⟩. The class provides methods to perform operations on the qubit, including:

- **`Qubit(float zero_amplitude, float one_amplitude)`**: Constructor to initialize a qubit with given amplitudes.
- **`void printQubit() const`**: Prints the current state of the qubit as a tuple `(zero_amplitude, one_amplitude)`.
- **`void X()`**: Applies the X (NOT) gate to the qubit, which swaps the amplitudes of the |0⟩ and |1⟩ states.
- **`void H()`**: Applies the Hadamard (H) gate, which puts the qubit into a superposition if it starts in a pure state.

### 2. `Qubit.cpp`

This source file contains the implementation of the `Qubit` class methods. It includes the logic for initializing a qubit and applying quantum gates. The file uses the standard `iostream` library for printing and basic operations, and the `cmath` library for mathematical constants and functions.

### 3. `main.cpp`

This is the main file that runs the quantum simulation. It demonstrates the following:

- Initialization of qubits in specific states.
- Application of quantum gates (H and X).
- Displaying the resulting states of the qubits.

The `main.cpp` file creates two qubits:

1. A qubit initialized in state |0⟩, to which it applies a series of gates (H, X, H).
2. A qubit initialized in state |1⟩, to which it applies an H gate followed by an X gate.

## Compilation and Execution

To compile and run the program, follow these steps:

### Prerequisites

- A C++ compiler such as `g++`.

### Compilation

Use the following command to compile the program:

```bash
g++ main.cpp Qubit.cpp -o quantum_simulation
```

This command will produce an executable named `quantum_simulation`.

### Running the Program

After compiling, you can run the program using the following command:

```bash
./quantum_simulation
```

### Expected Output

The program will output the states of the qubits after each operation. You should see something like:

```
(1.000000, 0.000000)
(1.000000, 0.000000)
(0.000000, 1.000000)
```

This output shows the amplitudes of the qubits after applying the gates.

## How It Works

### Quantum Gates

- **X (NOT) Gate**: The X gate flips the qubit's state. If the qubit is in state |0⟩, it will be flipped to |1⟩ and vice versa.
  
- **Hadamard (H) Gate**: The Hadamard gate puts a qubit into a superposition state. For example, applying the H gate to |0⟩ gives an equal probability of being in |0⟩ and |1⟩.

### Superposition and Measurement

This simulation demonstrates the basic principle of superposition, where a qubit can be in a combination of |0⟩ and |1⟩ states simultaneously. However, this simulation does not include measurement, which would collapse the qubit's state to either |0⟩ or |1⟩ based on the probabilities defined by the amplitudes.

## Further Development

This project can be extended with the following features:

- **Additional Quantum Gates**: Implement other quantum gates like the CNOT, T, and S gates.
- **Entanglement**: Simulate entangled qubits and their behavior.
- **Measurement**: Implement a function to simulate the measurement process, collapsing the qubit's state.

## License

This project is open-source and available for use under the [MIT License](https://opensource.org/licenses/MIT).

## Acknowledgments

This project is a basic introduction to quantum computing concepts in a programming context. For a deeper understanding, consider exploring more advanced topics like quantum algorithms, error correction, and actual quantum hardware.
