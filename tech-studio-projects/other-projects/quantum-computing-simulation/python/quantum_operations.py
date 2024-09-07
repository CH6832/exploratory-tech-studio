# -*- coding: utf-8 -*-

"""quantum_operations.py"""

# pylint settings (https://docs.pylint.org/)
# pylint: disable=line-too-long, trailing-whitespace, multiple-statements, fixme, locally-disabled

import logging
from qiskit import QuantumCircuit


def apply_hadamard(circuit: QuantumCircuit, qubit: int) -> None:
    """Applies a Hadamard gate to a specified qubit in the given quantum circuit.

    The Hadamard gate creates an equal superposition of the |0⟩ and |1⟩ states 
    when applied to a qubit. This is a common operation in quantum algorithms 
    like Grover's or the quantum Fourier transform.

    Args:
        circuit (QuantumCircuit): The quantum circuit to which the Hadamard gate will be applied.
        qubit (int): The index of the qubit in the circuit to which the Hadamard gate will be applied.
    """
    logging.info("Applying Hadamard gate to qubit %s", qubit)
    circuit.h(qubit)

    return None


def apply_x_gate(circuit: QuantumCircuit, qubit: int) -> None:
    """Applies a Pauli-X (NOT) gate to a specified qubit in the given quantum circuit.

    The Pauli-X gate (also known as the quantum NOT gate) flips the state of 
    the qubit: it changes |0⟩ to |1⟩ and |1⟩ to |0⟩. This is analogous to 
    the classical NOT gate.

    Args:
        circuit (QuantumCircuit): The quantum circuit to which the X gate will be applied.
        qubit (int): The index of the qubit in the circuit to which the X gate will be applied.
    """
    logging.info("Applying X gate to qubit %s", qubit)
    circuit.x(qubit)

    return None
