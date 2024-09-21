#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""Symbol.py

Symbol class for the SymClone library.
"""

# https://docs.pylint.org/
# pylint: disable=line-too-long, trailing-whitespace, multiple-statements, fixme, locally-disabled

class Symbol:
    """
    Class to represent a symbolic variable.
    """
    
    def __init__(self, name: str) -> None:
        """"""
        self.name = name

        return None

    def __str__(self) -> str:
        """Returns a string representation of the Symbol object."""
        symbol_object: str = self.name
        
        return symbol_object

    def __repr__(self) -> str:
        """Returns a string representation that can be used to recreate the Symbol object."""
        symbol_object_recreated: str = f"Symbol('{self.name}')"
        
        return symbol_object_recreated
