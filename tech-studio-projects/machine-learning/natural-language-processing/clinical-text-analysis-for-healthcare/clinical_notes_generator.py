#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""clinical_notes_generator.py

This script reads clinical notes data from a CSV file and performs Named Entity
Recognition (NER) using spaCy.
"""

import logging
import pandas as pd
import spacy

# Logging Configurations
logging.basicConfig(
    filename=r'logfiles/clinical_notes.log',
    level=logging.INFO,
    format='%(asctime)s - %(levelname)s - %(message)s')


def main() -> None:
    """Driving code."""
    try:
        # Attempt to read the input CSV file
        data = pd.read_csv(r'input_data/clinical_notes.csv')
        logging.info('Successfully read input CSV file.')

    except FileNotFoundError:
        logging.error('Input CSV file not found.')

        return None

    except pd.errors.EmptyDataError:
        logging.error('Input CSV file is empty.')

        return None

    except Exception as e:
        logging.error('Error reading input CSV file: %s', e)

        return None

    try:
        clinical_notes = data['Note'].tolist()  # Extract all clinical notes
        logging.info('Extracted clinical notes from the CSV file.')

        process_clinical_notes(clinical_notes)  # Perform Named Entity Recognition
        logging.info('Performed Named Entity Recognition on clinical notes.')

        replaced_notes = replace_medical_terms(clinical_notes)  # Perform string replacement
        logging.info('Replaced specific medical terms in clinical notes.')

        data['Note'] = replaced_notes  # Update 'Note' column in DataFrame
    except KeyError:
        logging.error("'Note' column not found in the input CSV file.")

        return None

    except Exception as e:
        logging.error('Error during processing of clinical notes: %e', e)

        return None

    try:
        # Attempt to write the modified data back to a CSV file
        data.to_csv(r'output_data/modified_clinical_notes.csv', index=False)
        logging.info('Successfully wrote the modified clinical notes to output CSV file.')
    except Exception as e:
        logging.error('Error writing to output CSV file: %s', e)

        return None

    logging.info('Completed the clinical notes processing script.')

    return None


def replace_medical_terms(notes: list[str]) -> list[str]:
    """Replace specific medical terms in a list of clinical notes with their abbreviations.

    Args:
        notes (List[str]): A list of clinical notes, where each note is a string.
    """
    replacements = {
        'upper respiratory tract infection': 'URTI',
        'lower back pain': 'LBP',
        'motor vehicle accident': 'MVA'
    }

    replaced_notes = []
    for note in notes:
        try:
            for term, replacement in replacements.items():
                note = note.replace(term, replacement)
            replaced_notes.append(note)
        except Exception as e:
            print("Error processing note: %s", e)
            continue

    return replaced_notes


def process_clinical_notes(notes: list[str]) -> None:
    """Processes a list of clinical notes using spaCy for Named Entity Recognition (NER).

    "Named Entity Recognition (NER) is a technique in natural language processing (NLP) that
    focuses on identifying and classifying entities. The purpose of NER is to automatically
    extract structured information from unstructured text, enabling machines to understand
    and categorize entities in a meaningful manner for various applications like text summarization,
    building knowledge graphs, question answering, and knowledge graph construction. The article
    explores the fundamentals, methods and implementation of the NER model."
    Source: https://www.geeksforgeeks.org/named-entity-recognition/

    Args:
        notes (List[str]): A list of clinical notes, where each note is a string.
    """
    try:
        nlp = spacy.load('en_core_web_sm') # Load spacy model for English
        logging.info('Successfully loaded spaCy model.')
    except OSError as e:
        logging.error("Error loading spaCy model %s: ", e)
        return []

    for note in notes:
        doc = nlp(note)
        for ent in doc.ents:
            print(ent.text, ent.label_)

    return None


if __name__ == "__main__":
    main()
