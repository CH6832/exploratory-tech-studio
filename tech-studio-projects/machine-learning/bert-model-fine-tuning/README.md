# Fine-Tune BERT on IMDB Dataset

## Overview

`fine_tune.py` is a Python script designed to fine-tune a BERT model on the IMDB dataset for sentiment analysis. The script utilizes the Hugging Face `transformers` and `datasets` libraries to handle model training and evaluation tasks.

## Table of Contents

1. [Features](#features)
2. [Installation](#installation)
3. [Usage](#usage)
4. [Code Explanation](#code-explanation)
5. [Resources](#resources)
6. [Data](#data)
7. [Contributing](#contributing)
8. [License](#license)

## Features

- **Fine-Tunes BERT:** Trains a BERT model for binary sentiment classification on the IMDB dataset.
- **Logging and Evaluation:** Logs training progress and evaluates the model after training.
- **Preprocessing:** Tokenizes text data using the BERT tokenizer.

## Installation

Ensure you have Python 3.6 or higher installed. Follow these steps to set up your environment:

1. **Clone the repository:**

   ```bash
   git clone https://github.com/yourusername/repository.git
   cd repository
   ```

2. **Create a virtual environment (optional but recommended):**

   ```bash
   python -m venv venv
   source venv/bin/activate  # On Windows use `venv\Scripts\activate`
   ```

3. **Install the required dependencies:**

   Create a `requirements.txt` file with the following content:

   ```text
   datasets
   transformers
   torch
   ```

   Install dependencies:

   ```bash
   pip install -r requirements.txt
   ```

## Usage

To fine-tune the BERT model, run the script using the following command:

```bash
python fine_tune.py
```

The script will:

1. Load and preprocess the IMDB dataset.
2. Tokenize the text data.
3. Fine-tune the BERT model.
4. Evaluate the model's performance and print the results.

## Code Explanation

### Overview

The script `fine_tune.py` is structured as follows:

1. **Imports:**
   - The script imports necessary libraries and modules:
     ```python
     from typing import Any
     from datasets import load_dataset
     from transformers import BertTokenizer, BertForSequenceClassification, TrainingArguments, Trainer
     ```

2. **Global Variables:**
   - `TOKENIZER` initializes a BERT tokenizer.
   - `DATASET` loads the IMDB dataset.

3. **`main()` Function:**
   - **Tokenization:** Applies the `tokenize_func` to preprocess the dataset.
   - **Model Setup:** Initializes a BERT model for sequence classification.
   - **TrainingArguments:** Defines training parameters such as the number of epochs and batch size.
   - **Trainer:** Sets up the Trainer API for training and evaluation.
   - **Training and Evaluation:** Starts training and prints evaluation results.

4. **`tokenize_func(examples)` Function:**
   - Tokenizes text data for input to the BERT model, padding and truncating as needed.

### Example

To execute the script, simply run:

```bash
python fine_tune.py
```

## Resources

- **Python 3.12** [python 3.12 Official Website](https://www.python.org/doc/)
   - **typing module** [typing module documentation](https://docs.python.org/3/library/typing.html)
   - **datasets module** [datasets module documentation](https://huggingface.co/docs/datasets/index)
   - **transformers module** [transformers module documentation](https://huggingface.co/transformers/v3.0.2/index.html)
- **Transformers Library:** [Hugging Face Transformers Documentation](https://huggingface.co/docs/transformers/index)
- **Datasets Library:** [Hugging Face Datasets Documentation](https://huggingface.co/docs/datasets/index)
- **IMDB Dataset:** [IMDB Dataset on Hugging Face](https://huggingface.co/datasets/imdb)

## Data

The script uses the IMDB dataset, which contains movie reviews classified as positive or negative. It is loaded using the `datasets` library and is split into training and test sets.

### Dataset Description

- **Source:** IMDB movie reviews dataset.
- **Task:** Sentiment analysis (binary classification).
- **Features:**
  - `text`: The movie review text.
  - `label`: The sentiment label (0 for negative, 1 for positive).
