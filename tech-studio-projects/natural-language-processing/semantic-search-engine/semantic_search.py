#!/usr/bin/env python3
# -*- coding: utf-8 -*-


"""sematnic_search.py

The script demonstrates the creation of a semantic search engine using
Apache Solr and BERT-based embeddings. It accomplishes the following tasks:

1. Indexing Documents: Example documents are indexed into Apache Solr,
allowing efficient searching and retrieval. The indexing process enables
the documents to be searched based on their content.

2. User Query Input: Users input queries to find relevant documents.
The script utilizes BERT to convert the query into semantic embeddings,
enabling more accurate and context-aware search queries.

3. Retrieving Relevant Documents: Based on the semantic similarity between
the query embeddings and document embeddings, the script retrieves the
most relevant documents from the Apache Solr index.

This script serves as a foundation for building advanced search engines
that provide users with precise and contextually relevant search results,
leveraging Apache Solr's powerful search capabilities.
"""

from typing import List
import pysolr
from transformers import BertTokenizer, BertModel
import torch
import numpy as np

# Initialize BERT tokenizer and model
tokenizer = BertTokenizer.from_pretrained('bert-base-uncased')
model = BertModel.from_pretrained('bert-base-uncased')

def index_documents(documents: List[str], solr_client: pysolr.Solr, core_name: str):
    """
    Indexes documents into Apache Solr.

    Args:
        documents (List[str]): List of documents to be indexed.
        solr_client (pysolr.Solr): Apache Solr client instance.
        core_name (str): Name of the Solr core.
    """
    solr_documents = [{'id': str(i), 'text': doc} for i, doc in enumerate(documents)]
    solr_client.add(solr_documents)

def calculate_bert_embeddings(text: str) -> np.ndarray:
    """
    Calculates BERT embeddings for a given text.

    Args:
        text (str): Input text.

    Returns:
        np.ndarray: BERT embeddings.
    """
    input_ids = tokenizer.encode(text, add_special_tokens=True, return_tensors='pt')
    with torch.no_grad():
        outputs = model(input_ids)
    embeddings = outputs[0][:, 0, :].numpy()  # Extract embeddings of [CLS] token
    return embeddings

def calculate_similarity(query_embedding: np.ndarray, document_embeddings: np.ndarray) -> List[float]:
    """
    Calculates cosine similarity between query embedding and document embeddings.

    Args:
        query_embedding (np.ndarray): BERT embedding for query.
        document_embeddings (np.ndarray): List of BERT embeddings for documents.

    Returns:
        List[float]: List of similarity scores.
    """
    document_embeddings = np.array(document_embeddings)
    dot_product = np.dot(query_embedding, document_embeddings.T)
    query_norm = np.linalg.norm(query_embedding)
    document_norms = np.linalg.norm(document_embeddings, axis=1)
    cosine_similarity = dot_product / (query_norm * document_norms)
    return cosine_similarity.tolist()

# Example usage
if __name__ == "__main__":
    # Initialize Solr client
    solr_client = pysolr.Solr('http://localhost:8983/solr', always_commit=True)

    # Index example documents
    documents = ["data/document1.txt",
                 "data/document2.txt",
                 "data/document3.txt"]
    core_name = "semantic_core"
    index_documents(documents, solr_client, core_name)

    # Example query
    query = "Looking for a document about BERT embeddings"

    # Calculate BERT embeddings for the query
    query_embedding = calculate_bert_embeddings(query)

    # Retrieve document embeddings from Solr
    solr_response = solr_client.search('*', rows=len(documents), fl='id,text')
    retrieved_documents = [doc['text'] for doc in solr_response]

    # Calculate similarity scores
    document_embeddings = [calculate_bert_embeddings(doc) for doc in retrieved_documents]
    similarity_scores = calculate_similarity(query_embedding, document_embeddings)

    # Print document texts and similarity scores
    for doc, score in zip(retrieved_documents, similarity_scores):
        print(f"Document: {doc}, Similarity Score: {score}")
