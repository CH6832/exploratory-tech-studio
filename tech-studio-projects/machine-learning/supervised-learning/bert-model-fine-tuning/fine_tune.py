#! /usr/bin/env python
# -*- coding: utf-8 -*-

"""fine_tune.py

Fine-tuning a BERT model on the IMDB dataset.
"""

from typing import Any
from datasets import load_dataset
from transformers import BertTokenizer, BertForSequenceClassification, TrainingArguments, Trainer

TOKENIZER: Any = BertTokenizer.from_pretrained('bert-base-uncased')
DATASET = load_dataset('imdb')


def main() -> None:
    """Driving code."""    
    tokenized_datasets = DATASET.map(tokenize_func, batched=True)

    tokenized_datasets.set_format('torch', columns=['input_ids', 'attention_mask', 'label'])

    model = BertForSequenceClassification.from_pretrained('bert-base-uncased', num_labels=2)

    training_settings = TrainingArguments(
        output_dir='./results',
        num_train_epochs=3,
        per_device_train_batch_size=8,
        per_device_eval_batch_size=8,
        warmup_steps=500,
        weight_decay=0.01,
        logging_dir='./logs',
        logging_steps=10,
        evaluation_strategy="epoch",
    )

    trainer = Trainer(
        model=model,
        args=training_settings,
        train_dataset=tokenized_datasets['train'],
        eval_dataset=tokenized_datasets['test'],
    )

    trainer.train()

    results = trainer.evaluate()
    print(results)

    return None


def tokenize_func(examples):
    """Tokenize examples passed to the function."""
    
    return TOKENIZER(examples['text'], padding='max_length', truncation=True)


if __name__ == "__main__":
    main()