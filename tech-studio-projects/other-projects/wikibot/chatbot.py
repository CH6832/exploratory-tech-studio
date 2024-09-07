#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""chatbot.py"""

# https://docs.pylint.org/
# pylint: disable = line-too-long, trailing-whitespace, multiple-statements, fixme, locally-disabled

from flask import Flask, render_template, request
import re
import json
import http.client as http
import urllib.parse as io


app = Flask(__name__)

# Define the file path for storing conversation history.
FILE_PATH: str = r"data/conversation_history.txt"

# Dictionary to store user input and corresponding bot responses.
conversation_history: dict = {}

# Dictionary to cache Wikipedia article content
article_cache: dict = {}

def get_response(user_input):
    """Function to match user input to patterns and return a response."""
    normalized_input = normalize_input(user_input)

    # Check if the user input is already in the conversation history
    if normalized_input in conversation_history:

        return conversation_history[normalized_input]

    else:
        # If the user input is not in the conversation history, fetch data
        response = fetch_data_from_wikipedia(normalized_input)
        conversation_history[normalized_input] = response

        return response


def normalize_input(input_str):
    """Function to normalize user input."""

    # Convert input to lowercase and remove punctuation
    return re.sub(r'[^\w\s]', '', input_str.lower())


def fetch_data_from_wikipedia(query):
    """Function to fetch data from Wikipedia API with caching."""
    # Check if article content is cached.
    if query in article_cache:
        
        return article_cache[query]
    
    else:
        url = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&exintro=true&titles=" + io.quote(query)
        conn = http.HTTPSConnection("en.wikipedia.org")
        conn.request("GET", url)
        response = conn.getresponse()
        data = json.loads(response.read().decode())
        
        # Extract the introductory text from the response.
        pages = data["query"]["pages"]
        for page_id, page_data in pages.items():
            if "extract" in page_data:
                extract = page_data["extract"]
                article_cache[query] = extract
                return extract
        
        # If no extract is found, return a default response.
        return f"Sorry, I couldn't find any information on '{query}' on Wikipedia."


def save_history_to_file(history) -> None:
    """Function to save conversation history to a file."""
    with open(FILE_PATH, "w", encoding='utf-8') as file:
        for user_input, bot_response in history.items():
            file.write(user_input + "," + bot_response + "\n")

    return None


def load_history_from_file():
    """Function to load conversation history from a file."""
    history = {}
    try:
        with open(FILE_PATH, "r", encoding='utf-8') as file:
            for line in file:
                parts = line.strip().split(",")
                if len(parts) == 2:
                    history[parts[0]] = parts[1]
    except FileNotFoundError:
        pass

    return history


@app.route("/")
def index():
    """Get back the rendered template."""
    
    return render_template("index.html")


@app.route("/chat", methods=["POST"])
def chat():
    """Handle chat with bot"""
    user_input = request.form["user_input"]
    bot_response = get_response(user_input)
    
    return {"bot_response": bot_response}


if __name__ == "__main__":
    # Load conversation history from history file.
    conversation_history = load_history_from_file()
    app.run(debug=True)
