from qiskit import QuantumCircuit, Aer
from qiskit.result import Result
from typing import Dict, Any


"""quantum_computer_simulator.py

Function Parameters: The grovers_algorithm function takes two parameters:
    n_qubits: The number of qubits in the quantum circuit.
    iterations: The number of iterations of Grover's algorithm to perform.
Quantum Circuit Creation: Inside the grovers_algorithm function, a quantum circuit qc is created using QuantumCircuit from Qiskit. The circuit is initialized with n_qubits qubits and classical bits.
Applying Hadamard Gates: Hadamard gates (qc.h) are applied to all qubits, putting them into a superposition of states.
Applying Oracle: An oracle is applied to mark the solution state. In this case, the solution state is |1110>. This is achieved by applying controlled-Z gates (qc.cz) to flip the phase of the target qubit if all control qubits are in the |1> state.
Applying Hadamard Gates Again: Hadamard gates are applied again to prepare for the diffusion operator.
Applying Diffusion Operator: The diffusion operator is applied to amplify the amplitude of the solution state. This involves applying Z gates (qc.z), controlled-Z gates (qc.cz), and Hadamard gates (qc.h).
Measurement: The qubits are measured, collapsing them into classical bits.
Simulating the Quantum Circuit: The quantum circuit is simulated using the Qiskit Aer simulator (AerSimulator) with 1000 shots.
Obtaining Measurement Results: The measurement results are obtained using result.get_counts(qc), which gives the counts of different measurement outcomes.
Return Counts: The counts of measurement outcomes are returned from the function.
"""


def main():
    """Main function."""
    """Main function."""
    n_qubits = 4  # Number of qubits

    counts = grovers_algorithm(n_qubits)
    print("Measurement outcome counts:", counts)


def simulate_circuit(circuit: QuantumCircuit) -> Dict[str, Any]:
    """Simulate the quantum circuit.

    Args:
        circuit (QuantumCircuit): Quantum circuit to simulate.

    Returns:
        Dict[str, Any]: Measurement outcome counts.
    """
    # Simulate the quantum circuit
    simulator = Aer.get_backend('qasm_simulator')
    job = simulator.run(circuit, shots=1000)
    result: Result = job.result()

    # Get counts of measurement outcomes
    counts = result.get_counts(circuit)
    return counts


def grovers_algorithm(n_qubits: int) -> Dict[str, Any]:
    """Simulate Grover's algorithm.

    Args:
        n_qubits (int): Number of qubits.

    Returns:
        Dict[str, Any]: Measurement outcome counts.
    """
    # Create quantum circuit
    circuit = grovers_algorithm_circuit(n_qubits)
    
    # Simulate quantum circuit
    counts = simulate_circuit(circuit)
    return counts


if __name__ == "__main__":
    main()
