#!/usr/bin/python3
# -*- coding: utf-8 -*-

"""app.py

The app.py script is a Python program designed to perform Natural Language Processing
(NLP) analysis on a collection of eBooks in TXT file format. It leverages various
NLP techniques and libraries to extract insights from the textual content of the eBooks
and generates a comprehensive analysis report.

Key Features:
-------------
1. Data Retrieval: The script retrieves the textual content of all eBooks located within the specified directory (ebooks). It traverses the directory structure recursively, reading each TXT file and concatenating their contents into a single string.
2. Keyword Extraction: It extracts paragraphs containing the word "love" and chapter titles from the eBook content using regular expressions.
3. Word Frequency Analysis: The script performs word frequency analysis to count the occurrences of each word in the eBook content. It constructs a dictionary mapping each word to its frequency of occurrence.
4. Tokenization: Utilizing the NLTK library, the script tokenizes the eBook content into individual words or tokens.
5. Stopword Removal: Stopwords, common words that do not carry significant meaning, are removed from the tokenized text to focus on content-bearing words.
6. Lemmatization: The script lemmatizes the remaining tokens, reducing them to their base or dictionary form to normalize variations of words.
7. POS Tagging: Using the spaCy library, it performs Part-of-Speech (POS) tagging to categorize each token into its corresponding grammatical category (e.g., noun, verb, adjective).
8. Named Entity Recognition (NER): NER is applied to identify named entities such as persons, organizations, and locations mentioned in the eBook content.
9. Sentiment Analysis: Sentiment analysis is conducted on sentences within the eBooks to determine the sentiment polarity (positive, negative, neutral) of each sentence.
10. TF-IDF Vectorization: The script utilizes TF-IDF (Term Frequency-Inverse Document Frequency) vectorization to represent the textual content in a numerical form suitable for machine learning tasks.
11. Dependency Parsing: Dependency parsing using spaCy is performed to analyze the syntactic structure of sentences and extract grammatical relationships between words.
12. Keyphrase Extraction: Keyphrases are extracted using TF-IDF to identify the most significant terms in the eBook content.
13. Output Generation: The results of the analysis are structured into a dictionary format and serialized to a JSON file (output/text_analysis_report.json). This JSON file serves as the comprehensive analysis report containing various insights extracted from the eBooks.
"""

from io import TextIOWrapper
import json
from pydoc import doc
from typing import List
import nltk
import os
import re
# python -m spacy download en_core_web_sm
import en_core_web_sm
from tkinter import Canvas
from nltk.tokenize import word_tokenize
from nltk.corpus import stopwords
from nltk.stem import WordNetLemmatizer
from numpy import ndarray
import spacy
from sklearn.feature_extraction.text import TfidfVectorizer
from nltk.sentiment import SentimentIntensityAnalyzer
from nltk.tokenize import sent_tokenize
from reportlab.lib.pagesizes import letter
from reportlab.pdfgen import canvas


ROOT_DIR: str = r"ebooks"

# read in content of all ebooks
ebooks: str = ""
subdir: str
dirs: List[dir]
files: List[dir]
for subdir, dirs, files in os.walk(ROOT_DIR):
    for file in files:
        rel_path_to_ebook: str = os.path.join(subdir, file)
        ebook_file: TextIOWrapper
        with open(rel_path_to_ebook, "r", encoding="utf-8") as ebook_file:
            r_ebook: str = ebook_file.read()
            ebooks += r_ebook

# extract paragraphs with the word "love" contained 
pattern: re.Pattern = re.compile("[^\n]+love[^\n]+")
findings_love: list = re.findall(pattern, ebooks)
findings_love[:2]
paragraph_with_keyword = findings_love[:2]

# extract chapter titles
findings_chapter_0: list = re.findall(pattern, ebooks)
findings_chapter_1: list = [item.strip("\n\n") for item in findings_chapter_0]

# find occurence of any word
pattern: re.Pattern = re.compile("[a-zA-Z]+")
findings: list = re.findall(pattern, ebooks.lower())
d: dict = {}
for word in findings:
    if word in d.keys():
        d[word] = d[word] + 1
    else:
        d[word] = 1

# tokenization
nltk.download("punkt")
tokens: List[str] = word_tokenize(ebooks)

# stopword removal
nltk.download("stopwords")
stop_words: set = set(stopwords.words('english'))
filtered_tokens: list = [word for word in tokens if word.lower() not in stop_words]

# lemmatization
nltk.download("wordnet")
lemmatizer: WordNetLemmatizer = WordNetLemmatizer()
lemmatized_tokens: list = [lemmatizer.lemmatize(word) for word in filtered_tokens]

# POS tagging (Using spaCy)
nlp = en_core_web_sm.load()
nlp = spacy.load("en_core_web_sm")
doc = nlp(ebooks)
pos_tags = [(token.text, token.pos_) for token in doc]

# Named Entity Recognition (NER) (Using spaCy)
ner_entities: List[tuple] = [(ent.text, ent.label_) for ent in doc.ents]

# sentiment analysis
nltk.download("vader_lexicon")
sentiment_analyzer = SentimentIntensityAnalyzer()
sentences = sent_tokenize(ebooks)
sentiments: list = [sentiment_analyzer.polarity_scores(sentence) for sentence in sentences]

# TF-IDF Vectorization
tfidf_vectorizer: TfidfVectorizer = TfidfVectorizer()
tfidf_matrix = tfidf_vectorizer.fit_transform([ebooks])

# dependency Parsing (Using spaCy)
dependency_parse = [(token.text, token.dep_) for token in doc]

# keyphrase Extraction (Using TF-IDF)
n: int = 5
tfidf_vectorizer: TfidfVectorizer = TfidfVectorizer(max_features=1000)
tfidf_matrix = tfidf_vectorizer.fit_transform([ebooks])
feature_names: ndarray = tfidf_vectorizer.get_feature_names_out()
sorted_indices = tfidf_matrix.toarray().argsort()[:, ::-1]
keyphrases = [feature_names[i] for i in sorted_indices[0][:n]]

# prepare results for the pdf file
results: dict = {
    "Filtered Specific Word": paragraph_with_keyword,
    "Chapter Titles": findings_chapter_1,
    "Word Occurence": d,
    "Filtered Tokens": filtered_tokens,
    "Lemmatized Tokens": lemmatized_tokens,
    "POS Tags": pos_tags,
    "Named Entities": ner_entities,
    "Sentiments": sentiments,
    "Dependency Parse": dependency_parse,
    "Keyphrases": keyphrases
}

# json format string
ordered_dict_str: str = json.dumps(results, indent=4)

# Write the JSON string to a TXT file
out_file: TextIOWrapper
with open(r"output/text_analysis_report.json", "w") as out_file:
    out_file.write(ordered_dict_str)
