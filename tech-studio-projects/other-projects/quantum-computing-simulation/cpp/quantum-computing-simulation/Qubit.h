#ifndef QUBIT_H
#define QUBIT_H

class Qubit {
public:
    float zero; ///< Amplitude for state |0⟩
    float one;  ///< Amplitude for state |1⟩

    /**
     * @brief Constructor for Qubit, initializes the amplitudes.
     * @param zero_amplitude Amplitude for the |0⟩ state.
     * @param one_amplitude Amplitude for the |1⟩ state.
     */
    Qubit(float zero_amplitude, float one_amplitude);

    /**
     * @brief Prints the state of the qubit.
     */
    void printQubit() const;

    /**
     * @brief Applies the X (NOT) gate to the qubit.
     * This gate swaps the amplitudes of the |0⟩ and |1⟩ states.
     */
    void X();

    /**
     * @brief Applies the Hadamard (H) gate to the qubit.
     * This gate puts the qubit into an equal superposition of |0⟩ and |1⟩ if it starts in |0⟩ or |1⟩.
     */
    void H();
};

#endif // QUBIT_H
