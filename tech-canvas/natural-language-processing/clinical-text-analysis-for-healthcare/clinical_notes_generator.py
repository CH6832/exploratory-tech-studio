#!/usr/bin/env python3
# -*- coding: utf-8 -*-


"""clinical_notes_generator.py

This script reads clinical notes data from a CSV file and performs Named Entity
Recognition (NER) using spaCy.
"""


import pandas as pd
import spacy
from spacy.tokens import Doc
from typing import List


def replace_medical_terms(notes: List[str]) -> List[str]:
    """Replace specific medical terms in clinical notes.

    Keyword arguments:
    notes (List[str]) -- List of clinical notes text.
    """
    replacements = {
        'upper respiratory tract infection': 'URTI',
        'lower back pain': 'LBP',
        'motor vehicle accident': 'MVA'
    }

    replaced_notes = []
    for note in notes:
        for term, replacement in replacements.items():
            note = note.replace(term, replacement)
        replaced_notes.append(note)
    
    return replaced_notes


def process_clinical_notes(notes: List[str]) -> None:
    """Process clinical notes data using spaCy for Named Entity Recognition (NER).

    Keyword arguments:
    notes (List[str]): List of clinical notes text.
    """
    nlp = spacy.load('en_core_web_sm')
    for note in notes:
        doc = nlp(note)
        for ent in doc.ents:
            print(ent.text, ent.label_)


def main():
    """main program"""
    # Read data from CSV file
    data = pd.read_csv('clinical_notes.csv')

    # Extract clinical notes
    clinical_notes = data['Note'].tolist()

    # Process clinical notes
    process_clinical_notes(clinical_notes)

    # Perform string replacement
    replaced_notes = replace_medical_terms(clinical_notes)    

    # Update 'Note' column in DataFrame
    data['Note'] = replaced_notes

    # Write modified data back to CSV file
    data.to_csv('modified_clinical_notes.csv', index=False)


if __name__ == "__main__":
    main()
