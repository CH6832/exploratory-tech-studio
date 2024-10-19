# NLP Text Analysis App (Java)

This Java application performs Natural Language Processing (NLP) analysis on a collection of eBooks in `.txt` format. It leverages various NLP techniques and libraries to extract insights from the textual content and generates a comprehensive analysis report in both JSON and PDF formats.

## Key Features

1. **Data Retrieval**: 
   - The script traverses through the specified directory (`ebooks`) to read and concatenate the content of all `.txt` files into a single string for analysis.

2. **Keyword Extraction**:
   - Extracts paragraphs containing the word "love" using regular expressions.

3. **Word Frequency Analysis**:
   - Counts the occurrences of each word in the eBook content and generates a frequency distribution.

4. **Tokenization**:
   - Tokenizes the text into individual words using Apache OpenNLP's Tokenizer model.

5. **Stopword Removal**:
   - Removes common stopwords from the tokenized text to focus on significant content-bearing words.

6. **Lemmatization**:
   - Lemmatizes the remaining tokens to reduce words to their base or dictionary form using OpenNLP's dictionary-based lemmatizer.

7. **Part-of-Speech Tagging and Named Entity Recognition** (NER) (Placeholder):
   - Can be extended to categorize each token into its grammatical category and recognize named entities using libraries like OpenNLP or Stanford NLP.

8. **Sentiment Analysis** (Future Extension):
   - Sentiment analysis would provide sentiment polarity (positive, neutral, negative) for sentences (requires custom or external models).

9. **TF-IDF Vectorization and Keyphrase Extraction**:
   - Extracts important keyphrases using the TF-IDF (Term Frequency-Inverse Document Frequency) method to identify significant terms in the text.

10. **PDF Report Generation**:
    - Generates a PDF summary report using Apache PDFBox to showcase analysis results.

11. **Output Generation**:
    - The analysis results are structured into a JSON report saved to `output/text_analysis_report.json`.

## Prerequisites

- Java 11 or higher
- Apache OpenNLP
- Apache PDFBox
- Gson (for JSON serialization)

### Additional Model Files:
- `en-token.bin` (OpenNLP tokenizer model)
- `en-lemmatizer.dict` (OpenNLP dictionary lemmatizer)

These model files must be downloaded and placed in the project directory.

## Setup Instructions

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/nlp-text-analysis-java.git
   cd nlp-text-analysis-java
   ```

2. **Download Required Libraries**:
   - Download OpenNLP and Apache PDFBox libraries, and add them to your project classpath.
   - Download tokenizer and lemmatizer models (e.g., `en-token.bin`, `en-lemmatizer.dict`) from OpenNLP's model repository.

3. **Install Maven/Gradle Dependencies** (if using Maven or Gradle):
   Add these dependencies to your `pom.xml` or `build.gradle`:

   **Maven (`pom.xml`)**:
   ```xml
   <dependency>
       <groupId>org.apache.opennlp</groupId>
       <artifactId>opennlp-tools</artifactId>
       <version>2.0.0</version>
   </dependency>
   <dependency>
       <groupId>org.apache.pdfbox</groupId>
       <artifactId>pdfbox</artifactId>
       <version>2.0.24</version>
   </dependency>
   <dependency>
       <groupId>com.google.code.gson</groupId>
       <artifactId>gson</artifactId>
       <version>2.8.9</version>
   </dependency>
   ```

   **Gradle (`build.gradle`)**:
   ```groovy
   implementation 'org.apache.opennlp:opennlp-tools:2.0.0'
   implementation 'org.apache.pdfbox:pdfbox:2.0.24'
   implementation 'com.google.code.gson:gson:2.8.9'
   ```

4. **Run the Application**:
   Ensure your `ebooks` directory is populated with `.txt` files. Then, compile and run the application:

   ```bash
   javac NLPApp.java
   java NLPApp
   ```

## Output

- **JSON Report**: A structured JSON report will be saved to `output/text_analysis_report.json` containing the results of the text analysis.
- **PDF Report**: A summary of the analysis will be saved as a PDF in `output/text_analysis_report.pdf`.

## Example Usage

1. **Extract Word Frequencies**: The script counts the frequency of each word across all the eBooks.
2. **Filter Sentences Containing Keywords**: It extracts and lists all sentences containing the word "love".
3. **Tokenization and Lemmatization**: The content is broken down into individual words, and those words are reduced to their base form.

## Future Enhancements

- **Sentiment Analysis**: Add a sentiment analysis model (e.g., VADER or a custom-trained model) to evaluate sentence polarity.
- **POS Tagging and NER**: Integrate POS tagging and NER using models like OpenNLP or Stanford CoreNLP for a more in-depth grammatical analysis.
- **Improved Keyword Extraction**: Use advanced techniques like RAKE or TextRank to improve keyphrase extraction.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
