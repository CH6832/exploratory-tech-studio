namespace MyQuantumProject {
    open Microsoft.Quantum.Intrinsic;
    open Microsoft.Quantum.Canon;

    operation ApplyQuantumGates() : Unit {
        using (qubits = Qubit[1]) {  // Allocate an array with one qubit
            let qubit = qubits[0];    // Access the first qubit
            H(qubit);                 // Apply a Hadamard gate
            X(qubit);                 // Apply a Pauli-X gate
            Reset(qubit);             // Reset the qubit to |0⟩
        }
    }
}
