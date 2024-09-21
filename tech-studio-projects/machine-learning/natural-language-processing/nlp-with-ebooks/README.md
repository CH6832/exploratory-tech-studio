# eBook NLP Analysis Tool

This project is a Python script designed to perform Natural Language Processing (NLP) analysis on a collection of eBooks in TXT format. The script processes the eBook content using various NLP techniques to extract meaningful insights and generates a comprehensive analysis report in JSON format.

## Features

1. **Data Retrieval**: 
   - Retrieves and concatenates the text content of all eBooks located in the specified `ebooks` directory.
  
2. **Keyword Extraction**:
   - Extracts paragraphs containing the word "love" and chapter titles from the eBook content using regular expressions.

3. **Word Frequency Analysis**:
   - Counts the occurrences of each word in the eBook content and creates a dictionary mapping each word to its frequency.

4. **Tokenization**:
   - Tokenizes the eBook content into individual words or tokens using the NLTK library.

5. **Stopword Removal**:
   - Removes common English stopwords from the tokenized text to focus on meaningful words.

6. **Lemmatization**:
   - Reduces tokens to their base or dictionary forms using lemmatization to normalize word variations.

7. **Part-of-Speech (POS) Tagging**:
   - Tags each token with its corresponding grammatical category (e.g., noun, verb) using the spaCy library.

8. **Named Entity Recognition (NER)**:
   - Identifies named entities such as persons, organizations, and locations mentioned in the eBook content.

9. **Sentiment Analysis**:
   - Analyzes the sentiment polarity (positive, negative, neutral) of sentences within the eBooks.

10. **TF-IDF Vectorization**:
    - Represents the textual content in numerical form using TF-IDF (Term Frequency-Inverse Document Frequency) vectorization.

11. **Dependency Parsing**:
    - Analyzes the syntactic structure of sentences and extracts grammatical relationships between words using spaCy.

12. **Keyphrase Extraction**:
    - Extracts keyphrases using TF-IDF to identify the most significant terms in the eBook content.

13. **Output Generation**:
    - Structures the analysis results into a dictionary format and serializes them to a JSON file (`output/text_analysis_report.json`).

## Installation

Before running the script, ensure you have Python 3 installed, along with the necessary Python packages.

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/ebook-nlp-analysis.git
   cd ebook-nlp-analysis
   ```

2. **Install dependencies**:
   Install the required Python packages using `pip`:
   ```bash
   pip install nltk spacy numpy scikit-learn reportlab
   ```

3. **Download NLTK Data**:
   The script automatically downloads the necessary NLTK data files (`punkt`, `stopwords`, `wordnet`, `vader_lexicon`) on the first run.

4. **Download spaCy Model**:
   The script uses the `en_core_web_sm` spaCy model. Install it using the following command:
   ```bash
   python -m spacy download en_core_web_sm
   ```

## Usage

To run the script and generate the analysis report:

1. Place your eBook TXT files in the `ebooks` directory.

2. Execute the script:
   ```bash
   python app.py
   ```

3. The analysis results will be saved as a JSON file in the `output` directory:
   - `output/text_analysis_report.json`

## Output

The output JSON file contains the following sections:

- **Filtered Specific Word**: Paragraphs containing the keyword "love."
- **Chapter Titles**: Extracted chapter titles.
- **Word Occurrence**: Dictionary of word frequencies.
- **Filtered Tokens**: Tokens after stopword removal.
- **Lemmatized Tokens**: Lemmatized tokens.
- **POS Tags**: Part-of-Speech tags for each token.
- **Named Entities**: Identified named entities.
- **Sentiments**: Sentiment analysis of sentences.
- **Dependency Parse**: Dependency parsing results.
- **Keyphrases**: Extracted keyphrases.

## Notes

- The script assumes that eBooks are in plain text (TXT) format.
- Ensure that the `ebooks` directory exists and contains the TXT files you wish to analyze.
- The `output` directory will be created automatically if it doesn't exist.
