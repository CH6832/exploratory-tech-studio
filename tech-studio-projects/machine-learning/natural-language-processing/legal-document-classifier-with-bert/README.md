# Legal Document Classification with BERT

## Overview

The `legal_doc_classification.py` script leverages the BERT model for classifying legal documents. The script performs several tasks including reading documents from a directory, fine-tuning a BERT model on these documents, evaluating the model's performance, and making predictions on new legal texts.

## Features

- **Document Reading:** Reads and processes legal documents from `.docx` files.
- **Fine-Tuning:** Fine-tunes a pre-trained BERT model on a legal document classification dataset.
- **Evaluation:** Evaluates the fine-tuned model's performance on a validation set.
- **Inference:** Makes predictions on new legal texts using the fine-tuned model.
- **Logging:** Logs detailed information and errors during execution for traceability.

## Prerequisites

- Python 3.x
- PyTorch
- Transformers
- scikit-learn
- pandas
- python-docx

## Installation

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/yourusername/yourrepository.git
   cd yourrepository
   ```

2. **Install Dependencies:**

   Install the required Python packages using pip:

   ```bash
   pip install torch transformers scikit-learn pandas python-docx
   ```

3. **Download BERT Model:**

   The script will automatically download the BERT model when run, but you need to ensure you have an internet connection.

## Usage

1. **Prepare Your Data:**

   Place your `.docx` files containing legal documents in the `legal_docs` directory. Ensure that you have a corresponding list of labels for these documents.

2. **Run the Script:**

   Execute the script from the command line:

   ```bash
   python legal_doc_classification.py
   ```

   The script will read documents from `legal_docs`, process them, fine-tune the BERT model, evaluate the model, and make predictions on predefined new texts.

3. **Check Logs:**

   Logs of the script's execution are saved to a timestamped file in the `logs` directory. This includes information on successful operations and any errors encountered.

## Functions

- `read_word_document(file_path: str) -> str`: Reads text from a Word document (.docx) file.
- `iterate_folder_and_read_docs(folder_path: str) -> Dict[str, str]`: Iterates over `.docx` files in a folder and reads their content using multithreading.
- `LegalDataset`: A custom dataset class for tokenizing legal documents and preparing them for BERT.
- `fine_tune_model(model: BertForSequenceClassification, train_dataloader: DataLoader, val_dataloader: DataLoader, epochs: int) -> None`: Fine-tunes the BERT model on the training dataset.
- `evaluate_model(model: BertForSequenceClassification, val_dataloader: DataLoader) -> None`: Evaluates the BERT model on the validation dataset.
- `predict_texts(texts: List[str], model: BertForSequenceClassification, tokenizer: BertTokenizer) -> List[int]`: Predicts labels for a list of new text inputs.

## Error Handling

- **File Reading Errors:** Logs errors if there are issues reading `.docx` files.
- **Model Training Errors:** Logs errors encountered during model fine-tuning.
- **Model Evaluation Errors:** Logs errors during model evaluation.
- **Prediction Errors:** Logs errors encountered during text prediction.
