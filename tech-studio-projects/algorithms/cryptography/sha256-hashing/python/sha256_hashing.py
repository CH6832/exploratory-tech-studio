#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""travelling_salesman_problem.py

The Secure Hash Algorithm (SHA-256) is a cryptographic hash function that produces a 256-bit (32-byte) hash
value. The algorithm operates on messages of any length less than $2^{64}$ bits, and it produces a fixed-size
output regardless of the size of the input message.

The SHA-256 algorithm consists of several logical steps:

    Pre-processing: Padding the message to ensure its length is congruent to 448 modulo 512. This is
    followed by appending the message length in bits as a 64-bit big-endian integer.

    Breaking the message into 512-bit chunks: The message is divided into chunks of 512 bits (64 bytes).

    Processing each chunk: Each chunk is processed in the main loop, where it is divided into 16 32-bit words.
    These words are extended into 64 32-bit words using a specified algorithm. The main loop then operates
    on these words to produce intermediate hash values.

    Updating the hash values: After processing each chunk, the current hash values are updated based on the
    intermediate hash values.

    Producing the final hash value: Once all chunks have been processed, the final hash value is obtained by
    concatenating the current hash values in a specific order and converting them to hexadecimal format.

The sha256 function provided in the code computes the SHA-256 hash of a given message. It takes a bytes
object as input and returns the hash value as a hexadecimal string. The example usage demonstrates how to
hash a message using this function.

.. _PEP 0000:
    https://peps.python.org/pep-0000/
"""


import math
from typing import List, Tuple

# Constants defined by the SHA-256 algorithm
K = [
    0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5, 0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5,
    0xd807aa98, 0x12835b01, 0x243185be, 0x550c7dc3, 0x72be5d74, 0x80deb1fe, 0x9bdc06a7, 0xc19bf174,
    0xe49b69c1, 0xefbe4786, 0x0fc19dc6, 0x240ca1cc, 0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da,
    0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7, 0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967,
    0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13, 0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85,
    0xa2bfe8a1, 0xa81a664b, 0xc24b8b70, 0xc76c51a3, 0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070,
    0x19a4c116, 0x1e376c08, 0x2748774c, 0x34b0bcb5, 0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3,
    0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208, 0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2
]

# Initial hash values (first 32 bits of the fractional parts of the square roots of the first 8 primes 2..19)
H = [
    0x6a09e667, 0xbb67ae85, 0x3c6ef372, 0xa54ff53a, 0x510e527f, 0x9b05688c, 0x1f83d9ab, 0x5be0cd19
]


def main() -> None:
    """Driving code and main function"""
    message = b'Hello, world!'
    hashed_message = sha256(message)
    print(f"Message: {message.decode()}")
    print(f"SHA-256 Hash: {hashed_message}")
 
    return None


def right_rotate(x: int, n: int) -> int:
    """Perform a right rotate operation on an integer.

    Args:
        x (int): The integer to be rotated.
        n (int): The number of positions to rotate by.

    Returns:
        int: The result of the right rotate operation.
    """
    return (x >> n) | (x << (32 - n)) & 0xFFFFFFFF

def sha256(message: bytes) -> str:
    """Compute the SHA-256 hash of a message.

    Args:
        message (bytes): The input message to be hashed.

    Returns:
        str: The SHA-256 hash of the message in hexadecimal format.
    """
    # Pre-processing: Padding the message
    ml = len(message) * 8
    message += b'\x80' # Append a single '1' bit
    while (len(message) * 8) % 512 != 448:
        message += b'\x00' # Append zeros until the length is congruent to 448 mod 512
    message += ml.to_bytes(8, 'big') # Append the message length as a 64-bit big-endian integer

    # Process the message in 512-bit chunks
    chunks = [message[i:i+64] for i in range(0, len(message), 64)]
    for chunk in chunks:
        # Break chunk into 16 32-bit words
        words = [int.from_bytes(chunk[i:i+4], 'big') for i in range(0, 64, 4)]

        # Extend the 16 32-bit words into 64 32-bit words
        for i in range(16, 64):
            s0 = right_rotate(words[i-15], 7) ^ right_rotate(words[i-15], 18) ^ (words[i-15] >> 3)
            s1 = right_rotate(words[i-2], 17) ^ right_rotate(words[i-2], 19) ^ (words[i-2] >> 10)
            words.append((words[i-16] + s0 + words[i-7] + s1) & 0xFFFFFFFF)

        # Initialize hash values for this chunk
        a, b, c, d, e, f, g, h = H

        # Main loop
        for i in range(64):
            S1 = right_rotate(e, 6) ^ right_rotate(e, 11) ^ right_rotate(e, 25)
            ch = (e & f) ^ (~e & g)
            temp1 = (h + S1 + ch + K[i] + words[i]) & 0xFFFFFFFF
            S0 = right_rotate(a, 2) ^ right_rotate(a, 13) ^ right_rotate(a, 22)
            maj = (a & b) ^ (a & c) ^ (b & c)
            temp2 = (S0 + maj) & 0xFFFFFFFF

            h = g
            g = f
            f = e
            e = (d + temp1) & 0xFFFFFFFF
            d = c
            c = b
            b = a
            a = (temp1 + temp2) & 0xFFFFFFFF

        # Add the compressed chunk to the current hash value
        H[0] = (H[0] + a) & 0xFFFFFFFF
        H[1] = (H[1] + b) & 0xFFFFFFFF
        H[2] = (H[2] + c) & 0xFFFFFFFF
        H[3] = (H[3] + d) & 0xFFFFFFFF
        H[4] = (H[4] + e) & 0xFFFFFFFF
        H[5] = (H[5] + f) & 0xFFFFFFFF
        H[6] = (H[6] + g) & 0xFFFFFFFF
        H[7] = (H[7] + h) & 0xFFFFFFFF

    # Produce the final hash value
    return ''.join(format(x, '08x') for x in H)


if __name__ == '__main__':
    main()

