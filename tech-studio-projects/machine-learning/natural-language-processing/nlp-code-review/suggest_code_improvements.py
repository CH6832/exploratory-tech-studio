#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""main.py

We define a function analyze_comment() that takes a comment as input.
We tokenize the comment using nltk.word_tokenize() and then perform part-of-speech tagging using nltk.pos_tag() to identify words and their respective parts of speech.
We analyze the tagged words to find specific patterns indicative of suggestions for code improvement. For example, if the comment contains adjectives like "efficient" or "optimized,"
it suggests optimizing the function for efficiency. Similarly, if the comment contains nouns like "performance" or "speed," it suggests optimizing for better performance.
Finally, we return a list of suggestions based on the analysis.
"""

# https://docs.pylint.org/
# pylint: disable=line-too-long, trailing-whitespace, multiple-statements, fixme, locally-disabled

import sys
import nltk
nltk.download('punkt')
nltk.download('punkt_tab')
nltk.download('averaged_perceptron_tagger_eng')


def main():
    """Driving code."""
    if len(sys.argv) != 2:
        print("Usage: python suggest_code_improvements.py <input_file>")
        sys.exit(1)
    
    filename = sys.argv[1]
    analyze_file(filename)

def suggest_code_improvements(comment) -> list:
    """
    Analyzes a code comment to suggest potential improvements related to performance and efficiency.

    This function tokenizes the given comment, tags each word with its part-of-speech (POS),
    and then provides suggestions based on specific words and their POS tags.

    Args:
        comment (str): A string containing the comment from code which is to be analyzed.

    Returns:
        list: A list of strings containing suggestions for code improvement, specifically targeting
              efficiency and performance aspects.

    Example:
        comment = "This function is optimized for performance and speed."
        suggestions = suggest_code_improvements(comment)
        # suggestions will contain:
        # ["Consider optimizing this function for efficiency.",
        #  "This function could be optimized for better performance."]
    """
    # Tokenize the comment
    tokens = nltk.word_tokenize(comment)
    
    # Part-of-speech tagging
    tags = nltk.pos_tag(tokens)
    
    # Analyze tags for suggestions
    suggestions = []
    for word, tag in tags:
        if tag == 'JJ' and word.lower() in ['efficient', 'optimized']:
            suggestions.append("Consider optimizing this function for efficiency.")
        elif tag == 'NN' and word.lower() in ['performance', 'speed']:
            suggestions.append("This function could be optimized for better performance.")
    
    return suggestions

def analyze_file(filename):
    """
    Analyzes a Python source code file for comments and provides suggestions for improvements.

    This function reads a Python file, extracts comments, and then uses the `suggest_code_improvements` 
    function to generate suggestions aimed at enhancing performance and efficiency.

    Args:
        filename (str): The path to the Python file to be analyzed.

    Returns:
        None: This function prints suggestions directly to the console.

    Example:
        analyze_file('example.py')
        # Output:
        # Code Review Suggestions:
        # Comment: This function is optimized for speed.
        # - Consider optimizing this function for efficiency.
        # - This function could be optimized for better performance.
    """
    with open(filename, 'r', encoding='utf-8') as file:
        code = file.read()
    
    # Tokenize code comments
    comments = []
    lines = code.split('\n')
    for line in lines:
        if '#' in line:
            comments.append(line.split('#', 1)[1].strip())
    
    # Analyze comments and print suggestions
    print("Code Review Suggestions:")
    for comment in comments:
        suggestions = suggest_code_improvements(comment)
        if suggestions:
            print(f"Comment: {comment}")
            for suggestion in suggestions:
                print(f"- {suggestion}")

if __name__ == "__main__":
    main()
