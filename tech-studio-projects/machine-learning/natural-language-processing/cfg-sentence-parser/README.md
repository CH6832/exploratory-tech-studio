# Sentence Parser with Noun Phrase Chunking

## Overview

This Python script (`parser.py`) uses the Natural Language Toolkit (NLTK) to parse sentences based on a context-free grammar (CFG) and identify noun phrase chunks. The script is designed to demonstrate basic natural language processing capabilities with NLTK and to showcase the extraction of noun phrases from sentences.

## Features

- **Context-Free Grammar (CFG):** Defines a simple CFG to parse sentences.
- **Noun Phrase Chunking:** Identifies and displays noun phrase chunks within the parsed sentence.
- **File and Console Input:** Allows users to input sentences either through a text file or directly via the console.

## Prerequisites

- Python 3.x
- NLTK (Natural Language Toolkit)

## Installation

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/yourusername/yourrepository.git
   cd yourrepository
   ```

2. **Install Dependencies:**

   Ensure you have NLTK installed. If not, you can install it via pip:

   ```bash
   pip install nltk
   ```

3. **Download NLTK Data:**

   The script requires NLTK data for tokenization. You can download it by running the script or manually using Python:

   ```python
   import nltk
   nltk.download('punkt')
   ```

## Usage

### Running the Script

You can run the script directly from the command line. It accepts an optional argument specifying a filename. If no filename is provided, the script will prompt for sentence input.

**Command-Line Usage:**

- **To read from a file:**

  ```bash
  python parser.py filename.txt
  ```

  Replace `filename.txt` with the path to your text file containing the sentence(s).

- **To input a sentence manually:**

  ```bash
  python parser.py
  ```

  After running the command, you will be prompted to enter a sentence.

### Example

1. **Input from Console:**

   ```bash
   python parser.py
   ```

   ```
   Sentence: The little red door arrived yesterday.
   ```

   **Output:**

   The script will display the parse tree and noun phrase chunks for the sentence.

2. **Input from File:**

   Create a text file `example.txt` with the following content:

   ```
   The armchair was delightful.
   ```

   Run:

   ```bash
   python parser.py example.txt
   ```

   **Output:**

   The script will parse the sentence from the file and display the parse tree along with noun phrase chunks.

## Functions

- `preprocess(sentence)`: Converts a sentence into a list of lowercase words containing at least one alphabetic character.
- `np_chunk(tree)`: Extracts and returns all noun phrase chunks from the parse tree.
