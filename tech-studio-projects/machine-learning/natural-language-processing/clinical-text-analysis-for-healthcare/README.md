# Clinical Notes Generator with Named Entity Recognition (NER)

## Overview

The `clinical_notes_generator.py` script reads clinical notes from a CSV file, performs Named Entity Recognition (NER) using spaCy, and replaces specific medical terms with their abbreviations. The script outputs the processed data to a new CSV file and logs its activities for traceability.

## Features

- **Named Entity Recognition (NER):** Utilizes spaCy to identify and classify named entities in clinical notes.
- **Medical Term Replacement:** Replaces specific medical terms with their abbreviations.
- **Logging:** Logs operations and errors to a file for auditing and debugging.
- **CSV Input/Output:** Reads from and writes to CSV files for easy integration with other data processing pipelines.

## Prerequisites

- Python 3.x
- spaCy
- pandas
- spaCy English model (`en_core_web_sm`)

## Installation

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/yourusername/yourrepository.git
   cd yourrepository
   ```

2. **Install Dependencies:**

   Install the required Python packages using pip:

   ```bash
   pip install pandas spacy
   ```

3. **Download the spaCy English Model:**

   Download the spaCy model for English:

   ```bash
   python -m spacy download en_core_web_sm
   ```

## Usage

1. **Prepare Input Data:**

   Ensure you have a CSV file named `clinical_notes.csv` in the `input_data` directory. The CSV file should have a column named `Note` containing the clinical notes.

2. **Run the Script:**

   Execute the script from the command line:

   ```bash
   python clinical_notes_generator.py
   ```

   The script will read from `input_data/clinical_notes.csv`, process the notes, and write the modified data to `output_data/modified_clinical_notes.csv`.

3. **Check Logs:**

   Logs of the script's execution will be saved to `logfiles/clinical_notes.log`. This includes information on successful operations and any errors encountered.

## Functions

- `replace_medical_terms(notes: list[str]) -> list[str]`: Replaces specific medical terms with their abbreviations.
- `process_clinical_notes(notes: list[str]) -> None`: Processes clinical notes using spaCy for Named Entity Recognition.

## Error Handling

- **FileNotFoundError:** If the input CSV file is not found.
- **EmptyDataError:** If the input CSV file is empty.
- **KeyError:** If the 'Note' column is missing in the CSV file.
- **OSError:** If there's an issue loading the spaCy model.
