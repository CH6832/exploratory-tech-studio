#!/usr/bin/env python3
# -*- coding: utf-8 -*-


"""sentiment_analysis_financial_data.py

Description: A tool for analyzing market sentiment from news articles, social media, and other sources to predict market trends and sentiment shifts.
Technologies: Python for text processing and sentiment analysis using spaCy and VADER, Java for web scraping using Selenium, and C++ for sentiment analysis algorithms.
Folder Structure: projects/market_sentiment_analysis_tool/" -> provide afully working and functional example with fully working and docuemnted code
"""


import requests
from bs4 import BeautifulSoup
import nltk
from nltk.sentiment.vader import SentimentIntensityAnalyzer


# initialize VADER sentiment analyzer
nltk.download('vader_lexicon')
sia = SentimentIntensityAnalyzer()


def main():
    """Driving code."""
    # scraping BBC News
    bbc_articles = scrape_news_website("https://www.bbc.com/news", "h3", "p")
    print("BBC News Articles:")
    for i, article in enumerate(bbc_articles, start=1):
        print(f"Article {i}: {article['headline']}")
        print(f"Summary: {article['summary']}")
        sentiment_scores = analyze_sentiment(article['summary'])
        print("Sentiment Scores:", sentiment_scores)
        print()

    # scraping CNN
    cnn_articles = scrape_news_website("https://www.cnn.com/", "h3", "p")
    print("CNN Articles:")
    for i, article in enumerate(cnn_articles, start=1):
        print(f"Article {i}: {article['headline']}")
        print(f"Summary: {article['summary']}")
        sentiment_scores = analyze_sentiment(article['summary'])
        print("Sentiment Scores:", sentiment_scores)
        print()

    # scraping Reuters
    reuters_articles = scrape_news_website("https://www.reuters.com/", "h3", "p")
    print("Reuters Articles:")
    for i, article in enumerate(reuters_articles, start=1):
        print(f"Article {i}: {article['headline']}")
        print(f"Summary: {article['summary']}")
        sentiment_scores = analyze_sentiment(article['summary'])
        print("Sentiment Scores:", sentiment_scores)
        print()

    # scraping The New York Times
    nytimes_articles = scrape_news_website("https://www.nytimes.com/", "h2", "p")
    print("The New York Times Articles:")
    for i, article in enumerate(nytimes_articles, start=1):
        print(f"Article {i}: {article['headline']}")
        print(f"Summary: {article['summary']}")
        sentiment_scores = analyze_sentiment(article['summary'])
        print("Sentiment Scores:", sentiment_scores)
        print()


def scrape_news_website(url, headline_tag, summary_tag) -> list:
    """Scrapper to scrape latest news from websites."""
    response = requests.get(url, timeout=10)
    soup = BeautifulSoup(response.text, "html.parser")

    articles = []
    for article in soup.find_all("article"):
        headline_element = article.find(headline_tag)
        summary_element = article.find(summary_tag)
        print("headline_element:", headline_element)
        if headline_element and summary_element:
            headline = headline_element.text.strip()
            summary = summary_element.text.strip()
            articles.append({"headline": headline, "summary": summary})
        else:
            # Skip this article if either headline or summary is not found
            continue
    
    return articles

def analyze_sentiment(text):
    """Perform sentiment analysis using VADER."""
    sentiment_scores = sia.polarity_scores(text)
    
    return sentiment_scores

if __name__ == "__main__":
    main()
