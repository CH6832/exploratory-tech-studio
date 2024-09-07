#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""chatbot_test.py

Test case for the chatbot.
"""

# https://docs.pylint.org/
# pylint: disable = line-too-long, trailing-whitespace, multiple-statements, fixme, locally-disabled

import os
import sys
import unittest
from chatbot import get_response, load_history_from_file, normalize_input, fetch_data_from_wikipedia, save_history_to_file
project_root = os.path.abspath(os.path.join(os.path.dirname(__file__), '..'))
sys.path.append(project_root)


class TestChatbot(unittest.TestCase):
    """
    Test class for the chatbot.
    """

    def test_input_normalization(self) -> None:
        """Test input normalization."""
        input_str = "Hello, World!"
        normalized_input = normalize_input(input_str)
        self.assertEqual(normalized_input, "hello world", "Input normalization failed")

        return None


    def test_fetch_data_from_wikipedia(self) -> None:
        """Test fetching data from Wikipedia."""
        query = "Albert Einstein"
        response = fetch_data_from_wikipedia(query)
        self.assertNotEqual(response, "", "Failed to fetch data from Wikipedia")

        return None


    def test_get_response_known_input(self) -> None:
        """Test generating response for known user input."""
        user_input = "Who is Isaac Newton?"
        response = get_response(user_input)
        self.assertNotEqual(response, "", "Failed to generate response for known input")

        return None


    def test_get_response_unknown_input(self) -> None:
        """Test generating response for unknown user input."""
        unknown_input = "Random gibberish"
        unknown_response = get_response(unknown_input)
        self.assertNotEqual(unknown_response, "", "Failed to generate response for unknown input")

        return None


    def test_save_and_load_history(self) -> None:
        """Test conversation history management - saving and loading from file."""
        test_history = {"test input 1": "test response 1", "test input 2": "test response 2"}
        save_history_to_file(test_history)
        loaded_history = load_history_from_file()
        self.assertEqual(loaded_history, test_history, "Failed to save and load conversation history")

        return None


if __name__ == '__main__':
    unittest.main()
