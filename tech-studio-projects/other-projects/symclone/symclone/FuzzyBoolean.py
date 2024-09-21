#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""FuzzyBoolean.py

FuzzyBoolean logic for the SymClone library.
"""

# https://docs.pylint.org/
# pylint: disable=line-too-long, trailing-whitespace, multiple-statements, fixme, locally-disabled

class FuzzyBoolean:
    """
    Class to represent fuzzy boolean values.
    """

    def __init__(self, value: float) -> None:
        """Initialize a FuzzyBoolean instance with the given degree of truth.

        Parameters:
        value (float) -- The degree of truth of the fuzzy boolean value, ranging between 0 and 1.
        """
        self.value = value

        return None

    def __str__(self) -> str:
        """Return the string representation of the fuzzy boolean value."""
        
        return str(self.value)

    def __repr__(self) -> str:
        """Return the formal representation of the fuzzy boolean value."""
        
        return f"FuzzyBoolean({self.value})"

    def __and__(self, other: 'FuzzyBoolean') -> 'FuzzyBoolean':
        """Perform logical AND operation between two fuzzy boolean values.

        Parameters:
        other (FuzzyBoolean) -- The other fuzzy boolean value to perform AND operation with.
        """
        
        return FuzzyBoolean(min(self.value, other.value))

    def __or__(self, other: 'FuzzyBoolean') -> 'FuzzyBoolean':
        """Perform logical OR operation between two fuzzy boolean values.

        Parameters:
        other (FuzzyBoolean) -- The other fuzzy boolean value to perform OR operation with.
        """
        
        return FuzzyBoolean(max(self.value, other.value))

    def __invert__(self) -> 'FuzzyBoolean':
        """Perform logical NOT operation on the fuzzy boolean value."""
        
        return FuzzyBoolean(1 - self.value)

    def __eq__(self, other: 'FuzzyBoolean') -> bool:
        """Check if two fuzzy boolean values are equal.

        Parameters:
        other (FuzzyBoolean) -- The other fuzzy boolean value to compare with.
        """
        
        return self.value == other.value

    def __ne__(self, other: 'FuzzyBoolean') -> bool:
        """Check if two fuzzy boolean values are not equal.

        Parameters:
        other (FuzzyBoolean) -- The other fuzzy boolean value to compare with.
        """
        
        return not self.__eq__(other)

    def __lt__(self, other: 'FuzzyBoolean') -> bool:
        """Check if the fuzzy boolean value is less than the other.

        Parameters:
        other (FuzzyBoolean) -- The other fuzzy boolean value to compare with.
        """
        
        return self.value < other.value

    def __le__(self, other: 'FuzzyBoolean') -> bool:
        """Check if the fuzzy boolean value is less than or equal to the other.

        Parameters:
        other (FuzzyBoolean) -- The other fuzzy boolean value to compare with.
        """
        
        return self.value <= other.value

    def __gt__(self, other: 'FuzzyBoolean') -> bool:
        """Check if the fuzzy boolean value is greater than the other.

        Parameters:
        other (FuzzyBoolean) -- The other fuzzy boolean value to compare with.
        """
        
        return self.value > other.value

    def __ge__(self, other: 'FuzzyBoolean') -> bool:
        """Check if the fuzzy boolean value is greater than or equal to the other.

        Parameters:
        other (FuzzyBoolean): The other fuzzy boolean value to compare with.
        """
        
        return self.value >= other.value
