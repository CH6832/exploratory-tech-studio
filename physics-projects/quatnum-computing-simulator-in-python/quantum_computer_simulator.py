# Quantum Computing Simulator with Python:

# Repository Link: QuantumComputingSimulator-Python
# Description: This repository demonstrates your proficiency in Python and your understanding of quantum computing concepts. Showcase examples of quantum computing simulations implemented in Python using libraries such as Qiskit, PyQuil, or QuTiP. Include modules for simulating quantum circuits, quantum gates, and quantum algorithms. Highlight your ability to develop software for quantum computing applications and your expertise in Python programming. Please provide an python example

from qiskit import QuantumCircuit, Aer, execute

# Define the number of qubits and iterations for Grover's algorithm
n = 4  # Number of qubits
iterations = 3  # Number of Grover iterations

# Create a quantum circuit with n qubits and n classical bits
qc = QuantumCircuit(n, n)

# Apply Hadamard gates to all qubits
qc.h(range(n))

# Apply oracle: mark the solution state (in this case, |1110>)
qc.x(0)
qc.x(1)
qc.x(2)

qc.cz(0, 3)

qc.x(0)
qc.x(1)
qc.x(2)

# Apply Hadamard gates again
qc.h(range(n))

# Apply diffusion operator
qc.z(range(n))
qc.cz(0, 1)
qc.h(2)
qc.cz(2, 3)

# Measure qubits
qc.measure(range(n), range(n))

# Simulate the quantum circuit
simulator = Aer.get_backend('qasm_simulator')
job = execute(qc, simulator, shots=1000)
result = job.result()

# Get counts of measurement outcomes
counts = result.get_counts(qc)
print("Measurement outcome counts:", counts)