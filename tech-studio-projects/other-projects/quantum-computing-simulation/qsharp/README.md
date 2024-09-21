# MyQuantumProject

This project demonstrates a simple quantum circuit simulation using Q# and a C# host program. The circuit involves applying a series of quantum gates to a single qubit and measuring the final state.

## Project Structure

- **Operations.qs**: Contains the Q# code defining the quantum operations.
- **Program.cs**: Contains the C# host code that runs the quantum operation and measures its execution time.

## Prerequisites

- [.NET SDK](https://dotnet.microsoft.com/download)
- [QDK (Quantum Development Kit)](https://docs.microsoft.com/en-us/azure/quantum/install-overview-qdk)
- Optionally, a code editor like [Visual Studio Code](https://code.visualstudio.com/) with the Q# extension.

## Installation

1. Clone the repository or download the project files.
2. Navigate to the project directory:
   ```bash
   cd MyQuantumProject
   ```
3. Build the project:
   ```bash
   dotnet build
   ```

## Running the Project

To run the quantum simulation:

1. Execute the project using the following command:
   ```bash
   dotnet run
   ```

2. You should see output like:
   ```
   Applying Hadamard gate to the qubit
   Applying X gate to the qubit
   Applying Hadamard gate again to the qubit
   Measurement result: Zero
   Quantum simulation completed in X ms
   ```

## Explanation

### Q# Operations

The Q# file (`Operations.qs`) defines the quantum operations:

- **Hadamard Gate**: Applied to the qubit to put it into a superposition.
- **X Gate**: A Pauli-X gate, also known as a NOT gate, flips the qubit's state.
- **Measurement**: The final state of the qubit is measured in the Pauli-Z basis.

### C# Host Program

The C# host (`Program.cs`) does the following:

- Initializes a quantum simulator.
- Runs the Q# operation.
- Measures and prints the execution time.

## Additional Notes

- **No External Dependencies**: The project does not require any external dependencies beyond the QDK.
- **Error Handling**: Any syntax or runtime errors will be reported during the build or run phases.

## Troubleshooting

- Ensure all prerequisites are installed correctly.
- If you encounter build errors, make sure that your Q# code is correctly typed and follows Q# conventions.
- If the execution is slow, consider profiling the program using external tools.

## License

This project is open-source and available under the MIT License.
