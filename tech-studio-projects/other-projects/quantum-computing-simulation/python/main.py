#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""main.py"""

# pylint settings (https://docs.pylint.org/)
# pylint: disable=line-too-long, trailing-whitespace, multiple-statements, fixme, locally-disabled

import logging
import time
import cProfile
import pstats
from io import StringIO
from qiskit import QuantumCircuit
from quantum_operations import apply_hadamard, apply_x_gate
from circuit_execution import execute_circuit
from visualization import plot_bloch_multivector


def main() -> None:
    """Driving code."""
    # Start profiling.
    pr = cProfile.Profile()
    pr.enable()
    
    # Make logging entry
    logging.info("Creating a quantum circuit with 1 qubit")
    # Create a quantum circuit with 1 qubit
    circuit = QuantumCircuit(1)
    print(circuit)

    # Apply quantum gates :
    # ---------------------
    # Apply a Hadamard gate to qubit 0 in the circuit. The
    # Hadamard gate puts the qubit into a superposition state.
    apply_hadamard(circuit, 0)
    print(circuit)
    # The superposition state is unchanged because the X gate flips both
    # terms in the superposition equally.
    apply_x_gate(circuit, 0)
    print(circuit)
    # The qubit is in the state ∣1⟩∣1⟩, which is not a
    # superposition state.
    apply_hadamard(circuit, 0)
    print(circuit)

    # Execute circuit and get final state. All states must be
    # applied when the circuit gets executed
    final_state = execute_circuit(circuit)

    # Print and visualize the final state
    logging.info("Final state %s:", final_state)
    plot_bloch_multivector(final_state)

    # Stop profiling
    pr.disable()

    # Print profiling results
    s = StringIO()
    sortby = pstats.SortKey.CUMULATIVE
    ps = pstats.Stats(pr, stream=s).sort_stats(sortby)
    ps.print_stats()
    logging.info(s.getvalue())

    return None


if __name__ == "__main__":
    start_time = time.time()
    logging.info("Quantum simulation started")
    
    main()
    
    end_time = time.time()
    logging.info("Quantum simulation completed in %s seconds", str(end_time - start_time))
