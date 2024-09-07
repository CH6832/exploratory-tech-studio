#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""circuit_execution.py"""

# pylint settings (https://docs.pylint.org/)
# pylint: disable=line-too-long, trailing-whitespace, multiple-statements, fixme, locally-disabled

import logging
from qiskit import QuantumCircuit
from qiskit.providers.basic_provider import BasicSimulator
from qiskit.quantum_info import Statevector


def execute_circuit(circuit: QuantumCircuit) -> Statevector:
    """Executes a quantum circuit on a simulator and returns the final statevector.

    This function runs the provided quantum circuit using a quantum simulator, 
    retrieves the resulting statevector, and then returns it. The statevector 
    represents the final quantum state of the circuit after execution.

    Args:
        circuit (QuantumCircuit): The quantum circuit to be executed.
    """
    logging.info("Executing quantum circuit")
    # Initialize the simulator.
    simulator = BasicSimulator()
    # Run the circuit using the simulator.
    result = simulator.run(circuit).result()
    # Obtain the final statevector.
    statevector = result.get_statevector(circuit)
    final_state = Statevector.from_instruction(statevector)

    return final_state
