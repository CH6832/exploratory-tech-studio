#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""travelling_salesman_problem.py

The RSA encryption algorithm relies on the mathematical properties of modular arithmetic and prime
factorization. Here's a brief explanation of how the program works:

generate_keypair: This function generates a public-private key pair for RSA encryption. It takes
two prime numbers p and q as input and returns the public key (e, n) and the private key (d, n).
The public key consists of e (the encryption exponent) and n (the modulus), while the private key
consists of d (the decryption exponent) and n.

 encrypt: This function encrypts a message using RSA encryption. It takes the message as input and
 uses the public key (e, n) to encrypt the message.

decrypt: This function decrypts a ciphertext using RSA decryption. It takes the ciphertext as input
and uses the private key (d, n) to decrypt the ciphertext.

 gcd, egcd, and modinv: These are helper functions used to compute the greatest common divisor,
 extended Euclidean algorithm, and modular multiplicative inverse, respectively. They are used
 internally by the RSA encryption and decryption functions.

 Example Usage: The example usage section demonstrates how to generate a key pair, encrypt a message,
 and decrypt the ciphertext.

.. _PEP 0000:
    https://peps.python.org/pep-0000/
"""


from sys import maxsize
from itertools import permutations
from typing import List, Tuple

def main() -> None:
    """Driving code and main function"""
    # Example usage
    p = 61
    q = 53
    public_key, private_key = generate_keypair(p, q)
    message = 42
    print(f"Original Message: {message}")
    ciphertext = encrypt(message, public_key)
    print(f"Encrypted Message: {ciphertext}")
    decrypted_message = decrypt(ciphertext, private_key)
    print(f"Decrypted Message: {decrypted_message}")
 
    return None


def gcd(a: int, b: int) -> int:
    """Compute the greatest common divisor of two integers.

    Args:
        a (int): The first integer.
        b (int): The second integer.

    Returns:
        int: The greatest common divisor of a and b.
    """
    while b != 0:
        a, b = b, a % b
    return a

def egcd(a: int, b: int) -> Tuple[int, int, int]:
    """Extended Euclidean Algorithm.

    Args:
        a (int): The first integer.
        b (int): The second integer.

    Returns:
        Tuple[int, int, int]: A tuple (gcd, x, y) such that gcd(a, b) = ax + by.
    """
    if a == 0:
        return b, 0, 1
    else:
        gcd, x, y = egcd(b % a, a)
        return gcd, y - (b // a) * x, x

def modinv(a: int, m: int) -> int:
    """Compute the modular multiplicative inverse of a modulo m.

    Args:
        a (int): The integer.
        m (int): The modulus.

    Returns:
        int: The modular multiplicative inverse of a modulo m.
    """
    gcd, x, y = egcd(a, m)
    if gcd != 1:
        raise Exception("Modular inverse does not exist")
    else:
        return x % m

def generate_keypair(p: int, q: int) -> Tuple[Tuple[int, int], Tuple[int, int]]:
    """Generate a public-private key pair for RSA encryption.

    Args:
        p (int): A prime number.
        q (int): Another prime number.

    Returns:
        Tuple[Tuple[int, int], Tuple[int, int]]: A tuple containing the public key (e, n)
        and the private key (d, n).
    """
    n = p * q
    phi = (p - 1) * (q - 1)
    
    # Choose an integer e such that e and phi are coprime
    e = 2
    while gcd(e, phi) != 1:
        e += 1
    
    # Compute the modular multiplicative inverse of e modulo phi
    d = modinv(e, phi)
    
    return (e, n), (d, n)

def encrypt(message: int, public_key: Tuple[int, int]) -> int:
    """Encrypt a message using RSA encryption.

    Args:
        message (int): The message to be encrypted.
        public_key (Tuple[int, int]): The public key (e, n).

    Returns:
        int: The encrypted message.
    """
    return pow(message, public_key[0], public_key[1])

def decrypt(ciphertext: int, private_key: Tuple[int, int]) -> int:
    """Decrypt a ciphertext using RSA decryption.

    Args:
        ciphertext (int): The ciphertext to be decrypted.
        private_key (Tuple[int, int]): The private key (d, n).

    Returns:
        int: The decrypted message.
    """
    return pow(ciphertext, private_key[0], private_key[1])


if __name__ == '__main__':
    main()

