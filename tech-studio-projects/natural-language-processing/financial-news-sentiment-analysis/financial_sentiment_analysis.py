#!/usr/bin/env python3
# -*- coding: utf-8 -*-


"""financial_sentiment_analysis.py

Financial News Sentiment Analysis:
----------------------------------
This script scrapes financial news articles from the Bloomberg Markets section,
preprocesses the text data, and performs sentiment analysis using TextBlob.
"""


import requests
from bs4 import BeautifulSoup
from textblob import TextBlob
import re
from typing import List, Tuple


def main() -> None:
    """main function to scrape news articles, preprocess text, and perform sentiment analysis."""
    # URL of Bloomberg Markets section
    url = 'https://www.bloomberg.com/markets'
    # Scrape Bloomberg news articles
    articles = scrape_bloomberg_news(url)
    # Analyze sentiment for each article
    for headline, content in articles:
        # Preprocess article content
        preprocessed_content = preprocess_text(content)
        # Perform sentiment analysis
        sentiment = analyze_sentiment(preprocessed_content)
        print("Headline:", headline)
        print("Sentiment:", sentiment)
        print()


def scrape_bloomberg_news(url: str) -> List[Tuple[str, str]]:
    """Scrape headlines and content of news articles from Bloomberg Markets section.

    Keyword arguments:
    url (str): The URL of the Bloomberg Markets section.

    Returns:
    List[Tuple[str, str]]: A list of tuples containing headline and content of each article.
    """
    # Send a GET request to the specified URL
    response = requests.get(url)
    # Parse the HTML content of the page
    soup = BeautifulSoup(response.text, 'html.parser')
    # Extract article headlines and content
    articles = []
    for article in soup.find_all('article'):
        headline = article.find('h1').get_text()
        content = article.find('div', class_='abstract').get_text()
        articles.append((headline, content))
    return articles


def preprocess_text(text: str) -> str:
    """Preprocess text data by removing non-alphanumeric characters and converting to lowercase.

    Keyword arguments:
    text (str): The text to be preprocessed.
    """
    # Remove non-alphanumeric characters
    text = re.sub(r'[^a-zA-Z0-9\s]', '', text)
    # Convert text to lowercase
    text = text.lower()
    return text


def analyze_sentiment(text: str) -> str:
    """Perform sentiment analysis on the given text using TextBlob.

    Keyword arguments:
    text (str): The text for sentiment analysis.
    """
    # Create a TextBlob object
    blob = TextBlob(text)
    # Perform sentiment analysis
    sentiment_score = blob.sentiment.polarity
    if sentiment_score > 0:
        sentiment = "Positive"
    elif sentiment_score < 0:
        sentiment = "Negative"
    else:
        sentiment = "Neutral"
    return sentiment


if __name__ == "__main__":
    main()
