import torch
from transformers import BertTokenizer, BertForSequenceClassification
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score
from torch.utils.data import DataLoader, Dataset
from typing import List, Dict, Any

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

By following this workflow, the program enables the fine-tuning of a pre-trained BERT model on a legal document classification dataset, evaluation of the fine-tuned model, and making predictions on new legal document texts.
"""

def main() -> None:
    # Load your legal dataset (replace with your actual dataset loading code)
    texts = [
        "This Agreement ('Agreement') is entered into on [date], by and between [Party A], located at [address], and [Party B], located at [address]. Both parties agree to the terms and conditions outlined herein for the purpose of [purpose of agreement].",
        "By using this website, you agree to be bound by these Terms of Service. Please read these terms carefully before accessing or using the website. If you do not agree with any of these terms, you are prohibited from using or accessing this site.",
        "Our privacy policy outlines how we collect, use, and protect your personal information. By using our services, you consent to the collection and use of your information as described in this policy.",
        "This Lease Agreement ('Lease') is entered into on [date], by and between [Landlord], located at [address], and [Tenant], located at [address]. The Landlord agrees to lease the property located at [address] to the Tenant for the term specified herein.",
        "Plaintiff alleges that Defendant breached the contract by failing to make the required payments as outlined in the agreement dated [date]. Plaintiff seeks damages in the amount of [amount] to compensate for the breach of contract."
    ]
    labels = [0, 1, 2, 3, 4]  # Replace ellipsis with actual label values

    # Split data into train and validation sets
    train_texts, val_texts, train_labels, val_labels = train_test_split(texts, labels, test_size=0.2, random_state=42)

    # Define the number of classes
    num_classes = 2  # Update this according to your dataset

    # Initialize BERT tokenizer and model
    tokenizer = BertTokenizer.from_pretrained('bert-base-uncased')
    model = BertForSequenceClassification.from_pretrained('bert-base-uncased', num_labels=num_classes)

    # Create DataLoaders for training and validation
    train_dataset = LegalDataset(train_texts, train_labels, tokenizer, max_len=512)
    val_dataset = LegalDataset(val_texts, val_labels, tokenizer, max_len=512)

    train_dataloader = DataLoader(train_dataset, batch_size=8, shuffle=True)
    val_dataloader = DataLoader(val_dataset, batch_size=8)

    # Fine-tune the model
    fine_tune_model(model, train_dataloader, val_dataloader, epochs=3)

    # Evaluate the fine-tuned model
    evaluate_model(model, val_dataloader)

    # Deploy the model for inference
    new_texts = [
        "This Agreement ('Agreement') is entered into on [date], by and between [Party A], located at [address], and [Party B], located at [address]. Both parties agree to the terms and conditions outlined herein for the purpose of [purpose of agreement].",
        "By using this website, you agree to be bound by these Terms of Service. Please read these terms carefully before accessing or using the website. If you do not agree with any of these terms, you are prohibited from using or accessing this site.",
        "Our privacy policy outlines how we collect, use, and protect your personal information. By using our services, you consent to the collection and use of your information as described in this policy.",
        "This Lease Agreement ('Lease') is entered into on [date], by and between [Landlord], located at [address], and [Tenant], located at [address]. The Landlord agrees to lease the property located at [address] to the Tenant for the term specified herein.",
        "Plaintiff alleges that Defendant breached the contract by failing to make the required payments as outlined in the agreement dated [date]. Plaintiff seeks damages in the amount of [amount] to compensate for the breach of contract."
    ]
    predicted_labels = predict_texts(new_texts, model, tokenizer)
    print(predicted_labels)

class LegalDataset(Dataset):
    """Dataset class for legal documents."""


    def __init__(self, texts: List[str], labels: List[int], tokenizer: BertTokenizer, max_len: int):
        """
        Initialize LegalDataset.

        Keyword arguments:
        texts (List[str]) -- List of text samples.
        labels (List[int]) -- List of corresponding labels.
        tokenizer (BertTokenizer) -- Tokenizer object.
        max_len (int) -- Maximum sequence length.
        """
        self.texts = texts
        self.labels = labels
        self.tokenizer = tokenizer
        self.max_len = max_len


    def __len__(self) -> int:
        return len(self.texts)


    def __getitem__(self, idx: int) -> Dict[str, Any]:
        text = str(self.texts[idx])
        label = self.labels[idx]

        encoding = self.tokenizer.encode_plus(
            text,
            add_special_tokens=True,
            max_length=self.max_len,
            return_token_type_ids=False,
            pad_to_max_length=True,
            return_attention_mask=True,
            return_tensors='pt',
            truncation=True
        )

        return {
            'text': text,
            'input_ids': encoding['input_ids'].flatten(),
            'attention_mask': encoding['attention_mask'].flatten(),
            'label': torch.tensor(label, dtype=torch.long)
        }


def fine_tune_model(model: BertForSequenceClassification, train_dataloader: DataLoader, val_dataloader: DataLoader, epochs: int = 3) -> None:
    """
    Fine-tunes the BERT model on the legal document classification dataset.

    Keyword arguments:
    model (BertForSequenceClassification) -- Pre-trained BERT model.
    train_dataloader (DataLoader) -- DataLoader for training data.
    val_dataloader (DataLoader) -- DataLoader for validation data.
    epochs (int) -- Number of epochs for training. Default is 3.
    """
    device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
    optimizer = torch.optim.Adam(model.parameters(), lr=2e-5)
    criterion = torch.nn.CrossEntropyLoss()

    for epoch in range(epochs):
        model.train()
        train_loss = 0.0
        for batch in train_dataloader:
            optimizer.zero_grad()
            input_ids = batch['input_ids'].to(device)
            attention_mask = batch['attention_mask'].to(device)
            labels = batch['label'].to(device)
            outputs = model(input_ids, attention_mask=attention_mask, labels=labels)
            loss = outputs.loss
            train_loss += loss.item()
            loss.backward()
            optimizer.step()

        avg_train_loss = train_loss / len(train_dataloader)

        # Validation
        model.eval()
        val_loss = 0.0
        val_preds = []
        val_true = []
        with torch.no_grad():
            for batch in val_dataloader:
                input_ids = batch['input_ids'].to(device)
                attention_mask = batch['attention_mask'].to(device)
                labels = batch['label'].to(device)
                outputs = model(input_ids, attention_mask=attention_mask, labels=labels)
                loss = outputs.loss
                val_loss += loss.item()
                logits = outputs.logits
                preds = torch.argmax(logits, dim=1)
                val_preds.extend(preds.cpu().detach().numpy())
                val_true.extend(labels.cpu().detach().numpy())

        avg_val_loss = val_loss / len(val_dataloader)
        val_accuracy = accuracy_score(val_true, val_preds)

        print(f'Epoch {epoch + 1}/{epochs}, '
              f'Train Loss: {avg_train_loss:.4f}, '
              f'Val Loss: {avg_val_loss:.4f}, '
              f'Val Accuracy: {val_accuracy:.4f}')


def evaluate_model(model: BertForSequenceClassification, dataloader: DataLoader) -> float:
    """
    Evaluates the fine-tuned model on the evaluation dataset.

    Keyword arguments:
    model (BertForSequenceClassification) -- Fine-tuned BERT model.
    dataloader (DataLoader) -- DataLoader for evaluation data.
    """
    device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
    model.eval()
    preds = []
    true_labels = []
    with torch.no_grad():
        for batch in dataloader:
            input_ids = batch['input_ids'].to(device)
            attention_mask = batch['attention_mask'].to(device)
            labels = batch['label'].to(device)
            outputs = model(input_ids, attention_mask=attention_mask)
            logits = outputs.logits
            batch_preds = torch.argmax(logits, dim=1)
            preds.extend(batch_preds.cpu().detach().numpy())
            true_labels.extend(labels.cpu().detach().numpy())
    accuracy = accuracy_score(true_labels, preds)
    print(f'Evaluation Accuracy: {accuracy:.4f}')
    return accuracy


def predict_texts(texts: List[str], model: BertForSequenceClassification, tokenizer: BertTokenizer) -> List[int]:
    """
    Makes predictions on new legal document texts.

    Keyword arguments:
    texts (List[str]) -- List of new legal document texts.
    model (BertForSequenceClassification) -- Fine-tuned BERT model.
    tokenizer (BertTokenizer) -- Tokenizer object.
    """
    device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
    model.eval()
    encoded_texts = tokenizer(texts, padding=True, truncation=True, return_tensors='pt')
    input_ids = encoded_texts['input_ids'].to(device)
    attention_mask = encoded_texts['attention_mask'].to(device)
    with torch.no_grad():
        outputs = model(input_ids, attention_mask=attention_mask)
    logits = outputs.logits
    probabilities = torch.softmax(logits, dim=1)
    predicted_labels = torch.argmax(probabilities, dim=1)
    return predicted_labels.cpu().detach().numpy()


if __name__ == "__main__":
    main()
