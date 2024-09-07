from qiskit.visualization import plot_bloch_multivector
from qiskit.quantum_info import Statevector

def visualize_state(state: Statevector) -> None:
    plot_bloch_multivector(state)
