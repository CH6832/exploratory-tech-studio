#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""legal_doc_classification.py

Description:

Imports: The program starts by importing necessary libraries and modules such as
PyTorch, Hugging Face's Transformers library, scikit-learn, and pandas.
These libraries are used for tasks such as deep learning, data preprocessing,
and evaluation.

Dataset Class: The LegalDataset class is defined to encapsulate the legal document
dataset. It takes a list of texts, corresponding labels, a tokenizer, and a
maximum sequence length as input. It preprocesses the text data using the provided
tokenizer and returns a dictionary containing tokenized input IDs, attention
masks, and labels for each sample.

Fine-tuning Function: The fine_tune_model function is defined to fine-tune the
pre-trained BERT model on the legal document classification dataset. It takes
the model, training dataloader, validation dataloader, and number of epochs as
input. During each epoch, the function iterates through the training dataset,
computes loss, and updates the model parameters using backpropagation. After
each epoch, it evaluates the model on the validation dataset and prints the
training and validation loss along with the validation accuracy.

Evaluation Function: The evaluate_model function evaluates the fine-tuned model
on the evaluation dataset. It takes the fine-tuned model and an evaluation
dataloader as input. It computes predictions on the evaluation dataset, calculates
the accuracy, and prints the evaluation accuracy.

Inference Function: The predict_texts function is defined to make predictions on
new legal document texts using the fine-tuned BERT model. It takes a list of new
texts, the fine-tuned model, and the tokenizer as input. It encodes the new texts
using the tokenizer, passes them through the model to obtain predictions, and
returns the predicted labels.

Main Function: The main function serves as the entry point of the program. It
loads the legal document dataset, splits it into training and validation sets,
initializes the BERT tokenizer and model, creates dataloaders for training and
validation, fine-tunes the model, evaluates the fine-tuned model, and deploys
the model for inference on new texts.

Execution: Finally, the main function is executed if the script is run as the main
program. This triggers the entire workflow, including data loading, model training,
evaluation, and inference.

By following this workflow, the program enables the fine-tuning of a pre-trained BERT model
on a legal document classification dataset, evaluation of the fine-tuned model, and making
predictions on new legal document texts.
"""

# https://docs.pylint.org/
# pylint: disable = line-too-long, trailing-whitespace, multiple-statements, fixme, locally-disabled

from concurrent.futures import ThreadPoolExecutor
from datetime import datetime
import os
import logging
from typing import List, Dict
from docx import Document
from sklearn.model_selection import train_test_split
from torch.utils.data import Dataset, DataLoader
from transformers import BertTokenizer, BertForSequenceClassification, AdamW
import torch
from torch import nn


# Prepare date and time for logging.
now = datetime.now()
formatted_datetime = now.strftime('%Y-%m-%d_%H-%M-%S')

# Set up logging
logging.basicConfig(filename=f'logs\\classification_log_{formatted_datetime}.log', level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

def main() -> None:
    """Main function to execute the script."""
    folder_path = 'legal_docs'  # Replace with your folder path
    
    try:
        logging.info("Starting to read documents from folder.")
        documents_texts = iterate_folder_and_read_docs(folder_path)
        logging.info("Read %s documents.", str(len(documents_texts)))

        # Assume the labels are generated or provided somehow
        labels = [0, 1, 2, 3, 4]  # Replace with actual label values based on your dataset

        if len(documents_texts) != len(labels):
            raise ValueError("The number of documents does not match the number of labels.")

        # Split data into train and validation sets
        texts = list(documents_texts.values())
        train_texts, val_texts, train_labels, val_labels = train_test_split(texts, labels, test_size=0.2, random_state=42)

        # Define the number of classes
        num_classes = len(set(labels))  # Update this according to your dataset

        # Initialize BERT tokenizer and model
        tokenizer = BertTokenizer.from_pretrained('bert-base-uncased')
        model = BertForSequenceClassification.from_pretrained('bert-base-uncased', num_labels=num_classes)

        # Create DataLoaders for training and validation
        train_dataset = LegalDataset(train_texts, train_labels, tokenizer, max_len=512)
        val_dataset = LegalDataset(val_texts, val_labels, tokenizer, max_len=512)

        train_dataloader = DataLoader(train_dataset, batch_size=8, shuffle=True)
        val_dataloader = DataLoader(val_dataset, batch_size=8)

        # Fine-tune the model
        logging.info("Starting model fine-tuning.")
        fine_tune_model(model, train_dataloader, val_dataloader, epochs=3)

        # Evaluate the fine-tuned model
        logging.info("Evaluating model performance.")
        evaluate_model(model, val_dataloader)

        # Deploy the model for inference
        new_texts = [
            "This Agreement ('Agreement') is entered into on [date], by and between [Party A], located at [address], and [Party B], located at [address]. Both parties agree to the terms and conditions outlined herein for the purpose of [purpose of agreement].",
            "By using this website, you agree to be bound by these Terms of Service. Please read these terms carefully before accessing or using the website. If you do not agree with any of these terms, you are prohibited from using or accessing this site.",
            "Our privacy policy outlines how we collect, use, and protect your personal information. By using our services, you consent to the collection and use of your information as described in this policy.",
            "This Lease Agreement ('Lease') is entered into on [date], by and between [Landlord], located at [address], and [Tenant], located at [address]. The Landlord agrees to lease the property located at [address] to the Tenant for the term specified herein.",
            "Plaintiff alleges that Defendant breached the contract by failing to make the required payments as outlined in the agreement dated [date]. Plaintiff seeks damages in the amount of [amount] to compensate for the breach of contract."
        ]
        logging.info("Making predictions on new texts.")
        predicted_labels = predict_texts(new_texts, model, tokenizer)
        logging.info("Predicted labels: %s", predicted_labels)

    except Exception as e:
        logging.error("An error occurred: %s", e)

    return None


def read_word_document(file_path: str) -> str:
    """Reads text from a Word document (.docx) file."""
    try:
        doc = Document(file_path)
        text = [para.text for para in doc.paragraphs]
        return '\n'.join(text)
    except Exception as e:
        logging.error("Failed to read document %s: %s", file_path, e)
        return ""


def iterate_folder_and_read_docs(folder_path: str) -> Dict[str, str]:
    """Iterates over all .docx files in a folder and reads their content using multithreading."""
    texts = {}
    def read_file(filename: str):
        try:
            file_path = os.path.join(folder_path, filename)
            if filename.endswith('.docx'):
                texts[filename] = read_word_document(file_path)
        except Exception as e:
            logging.error("Failed to process file %s: %s", filename, e)

    try:
        with ThreadPoolExecutor() as executor:
            filenames = [f for f in os.listdir(folder_path) if f.endswith('.docx')]
            executor.map(read_file, filenames)
    except Exception as e:
        logging.error("Failed to iterate over folder %s: %s", folder_path, e)

    return texts


class LegalDataset(Dataset):
    """
    Legal Dataset class
    """

    def __init__(self, texts: List[str], labels: List[int], tokenizer: BertTokenizer, max_len: int) -> None:
        """Initialize class."""
        self.texts = texts
        self.labels = labels
        self.tokenizer = tokenizer
        self.max_len = max_len


    def __len__(self) -> int:
        """Return the number of texts contained in the object."""
        
        return len(self.texts)


    def __getitem__(self, idx: int) -> Dict:
        """Retrieve a data sample from the dataset by index."""
        try:
            text = self.texts[idx]
            label = self.labels[idx]
            encoding = self.tokenizer(text, truncation=True, padding='max_length', max_length=self.max_len, return_tensors='pt')

            return {'input_ids': encoding['input_ids'].flatten(),
                    'attention_mask': encoding['attention_mask'].flatten(),
                    'labels': torch.tensor(label, dtype=torch.long)}

        except Exception as e:
            logging.error("Failed to get item at index %s: %s", idx, e)
            raise


def fine_tune_model(model: BertForSequenceClassification, train_dataloader: DataLoader, val_dataloader: DataLoader, epochs: int) -> None:
    """
    Fine-tunes a BERT model for sequence classification.

    This function performs the training and validation of a BERT model using 
    provided dataloaders for training and validation datasets. It updates model 
    weights through backpropagation and reports the loss after each epoch and 
    validation.

    Args:
        model (BertForSequenceClassification): The BERT model to be fine-tuned. 
            This should be an instance of `BertForSequenceClassification` from 
            the `transformers` library.
        train_dataloader (DataLoader): DataLoader instance providing batches of 
            training data. Each batch should contain 'input_ids', 'attention_mask', 
            and 'labels'.
        val_dataloader (DataLoader): DataLoader instance providing batches of 
            validation data. Each batch should contain 'input_ids', 'attention_mask', 
            and 'labels'.
        epochs (int): The number of epochs to train the model.
    """
    device = torch.device('cuda') if torch.cuda.is_available() else torch.device('cpu')
    model.to(device)
    optimizer = AdamW(model.parameters(), lr=2e-5)
    loss_fn = nn.CrossEntropyLoss()

    try:
        for epoch in range(epochs):
            model.train()
            for batch in train_dataloader:
                input_ids = batch['input_ids'].to(device)
                attention_mask = batch['attention_mask'].to(device)
                labels = batch['labels'].to(device)

                optimizer.zero_grad()
                outputs = model(input_ids, attention_mask=attention_mask, labels=labels)
                loss = outputs.loss
                loss.backward()
                optimizer.step()

            logging.info("Epoch %s - Loss: %s", str((epoch+1)/epochs), str(loss.item()))

            # Validate the model
            model.eval()
            val_loss = 0
            with torch.no_grad():
                for batch in val_dataloader:
                    input_ids = batch['input_ids'].to(device)
                    attention_mask = batch['attention_mask'].to(device)
                    labels = batch['labels'].to(device)

                    outputs = model(input_ids, attention_mask=attention_mask, labels=labels)
                    val_loss += outputs.loss.item()

            logging.info("Validation Loss: %s", str(val_loss / len(val_dataloader)))

    except Exception as e:
        logging.error("An error occurred during model fine-tuning: %s", e)

    return None


def evaluate_model(model: BertForSequenceClassification, val_dataloader: DataLoader) -> None:
    """
    Evaluates the performance of a fine-tuned BERT model on the validation dataset.

    This function computes the accuracy of the model on the provided validation 
    dataloader by comparing the model's predictions to the true labels. It calculates
    the number of correct predictions and the total number of predictions, and then 
    prints the validation accuracy as a percentage.

    Args:
        model (BertForSequenceClassification): The fine-tuned BERT model to be evaluated. 
            This should be an instance of `BertForSequenceClassification` from the 
            `transformers` library.
        val_dataloader (DataLoader): DataLoader instance providing batches of validation 
            data. Each batch should contain 'input_ids', 'attention_mask', and 'labels'.
    """     
    device = torch.device('cuda') if torch.cuda.is_available() else torch.device('cpu')
    model.to(device)
    model.eval()
    correct_predictions = 0
    total_predictions = 0

    try:
        with torch.no_grad():
            for batch in val_dataloader:
                input_ids = batch['input_ids'].to(device)
                attention_mask = batch['attention_mask'].to(device)
                labels = batch['labels'].to(device)

                outputs = model(input_ids, attention_mask=attention_mask)
                _, predicted = torch.max(outputs.logits, dim=1)

                total_predictions += labels.size(0)
                correct_predictions += (predicted == labels).sum().item()

        accuracy = correct_predictions / total_predictions
        logging.info("Validation Accuracy: %s", str( accuracy * 100 ))

    except Exception as e:
        logging.error("An error occurred during model evaluation: %s", e)

    return None


def predict_texts(texts: List[str], model: BertForSequenceClassification, tokenizer: BertTokenizer) -> List[int]:
    """Predicts labels for a list of text inputs using a fine-tuned BERT model.

    This function takes a list of text strings and uses a BERT model to predict the
    labels for each text. It tokenizes the texts, performs inference using the model,
    and returns a list of predicted labels. Predictions are made concurrently to 
    improve efficiency.

    Args:
        texts (List[str]): A list of text strings for which predictions are to be made.
        model (BertForSequenceClassification): The BERT model used for prediction. 
            This should be an instance of `BertForSequenceClassification` from the 
            `transformers` library.
        tokenizer (BertTokenizer): The tokenizer used to preprocess the text strings. 
            This should be an instance of `BertTokenizer` from the `transformers` library.
    """
    device = torch.device('cuda') if torch.cuda.is_available() else torch.device('cpu')
    model.to(device)
    model.eval()

    def predict(text: str) -> int:
        try:
            encoding = tokenizer(text, truncation=True, padding='max_length', max_length=512, return_tensors='pt')
            input_ids = encoding['input_ids'].to(device)
            attention_mask = encoding['attention_mask'].to(device)

            with torch.no_grad():
                outputs = model(input_ids, attention_mask=attention_mask)
                _, predicted = torch.max(outputs.logits, dim=1)
                return predicted.item()
        except Exception as e:
            logging.error(f"Failed to predict for text: {text} - Error: {e}")
            return -1  # Return an invalid label in case of error

    try:
        with ThreadPoolExecutor() as executor:
            predictions = list(executor.map(predict, texts))
    except Exception as e:
        logging.error(f"An error occurred during text prediction: {e}")
        predictions = [-1] * len(texts)  # Return invalid labels in case of error

    return predictions


if __name__ == "__main__":
    main()
