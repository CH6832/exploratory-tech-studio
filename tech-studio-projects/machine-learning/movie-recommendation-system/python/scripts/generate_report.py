#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Title:        generate_report_and_convert_to_pdf.py
Description:  This script generates a Markdown report and converts it to a PDF using Pandoc.
Author:       [Your Name]
Date:         2024-11-07
Version:      1.0

This script generates a sample Markdown report based on dynamically collected data and then 
converts it into a PDF using Pandoc.
"""

import os
import subprocess
import sys
from datetime import datetime


def generate_markdown_report(report_file_path):
    """
    Generate a dynamic report in Markdown format and save it to the specified file.
    
    :param report_file_path: str, path to the report file to save the Markdown report.
    :raises IOError: If the report file cannot be written.
    """
    try:
        # Open the report file to write Markdown content
        with open(report_file_path, 'w') as file:
            # Title and header
            file.write(f"# Dynamic Report\n")
            file.write(f"Generated on: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}\n\n")
            
            # Section 1: Introduction
            file.write(f"## Introduction\n")
            file.write("This report provides an overview of the data generated dynamically in Markdown format.\n\n")
            
            # Section 2: Data Summary
            file.write(f"## Data Summary\n")
            file.write("- Data Point 1: Value A\n")
            file.write("- Data Point 2: Value B\n")
            file.write("- Data Point 3: Value C\n\n")
            
            # Section 3: Conclusion
            file.write(f"## Conclusion\n")
            file.write("The data presented above shows the dynamic nature of the generated content.\n")
            
            print(f"Markdown report generated and saved to '{report_file_path}'")
    
    except IOError as e:
        print(f"Error writing the Markdown report: {e}")
        raise

# pandoc misisng and moveilens data sa well
def check_pandoc_installed():
    """
    Check if Pandoc is installed on the system.
    If not, provide an error message and exit.
    """
    try:
        # Try running 'pandoc --version' to check if Pandoc is installed
        result = subprocess.run(['pandoc', '--version'], check=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
        print("Pandoc is installed.")
        return True
    except subprocess.CalledProcessError:
        print("Error: Pandoc is not installed. Please install Pandoc before running this script.")
        return False


def check_latex_installed():
    """
    Check if LaTeX (TeX Live or MiKTeX) is installed.
    If not, provide an error message and exit.
    """
    try:
        # Try running 'pdflatex --version' to check if LaTeX is installed
        result = subprocess.run(['pdflatex', '--version'], check=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
        print("LaTeX is installed.")
        return True
    except subprocess.CalledProcessError:
        print("Error: LaTeX is not installed. Please install a LaTeX distribution (e.g., TeX Live, MiKTeX) before running this script.")
        return False


def convert_md_to_pdf(input_md_file, output_pdf_file):
    """
    Converts a given Markdown file to PDF using Pandoc.

    :param input_md_file: str, path to the input Markdown file
    :param output_pdf_file: str, path to the output PDF file
    :raises ValueError: If the input file is not found or is not a valid Markdown file.
    """
    # Check if the input Markdown file exists and is a valid Markdown file
    if not os.path.exists(input_md_file):
        raise FileNotFoundError(f"Input file '{input_md_file}' does not exist.")
    if not input_md_file.endswith(".md"):
        raise ValueError(f"Input file '{input_md_file}' is not a Markdown file. Please provide a .md file.")

    # Try converting the Markdown file to PDF using Pandoc
    try:
        # Build the Pandoc command
        command = ['pandoc', input_md_file, '-o', output_pdf_file]
        
        # Run the command
        result = subprocess.run(command, check=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
        
        print(f"Conversion successful! The PDF is saved as '{output_pdf_file}'")
        return True
    except subprocess.CalledProcessError as e:
        print(f"Error during Pandoc execution: {e.stderr.decode()}")
        raise RuntimeError(f"Pandoc conversion failed: {e.stderr.decode()}")
    except Exception as e:
        print(f"Unexpected error during conversion: {str(e)}")
        raise


def main():
    """
    Main function to execute the Markdown report generation and PDF conversion.
    Handles all errors and provides helpful messages to the user.
    """
    # Set paths
    report_file_path = '../reports/report.md'
    output_pdf_file = '../reports/report.pdf'

    # Generate the report
    try:
        generate_markdown_report(report_file_path)
    except IOError as e:
        print(f"Error: {e}")
        sys.exit(2)

    # Ensure Pandoc and LaTeX are installed
    if not check_pandoc_installed() or not check_latex_installed():
        print("Required software is missing. Exiting.")
        sys.exit(1)

    try:
        # Convert the Markdown to PDF
        convert_md_to_pdf(report_file_path, output_pdf_file)
    except (FileNotFoundError, ValueError) as e:
        print(f"Error: {e}")
        sys.exit(3)
    except RuntimeError as e:
        print(f"Error: {e}")
        sys.exit(4)
    except Exception as e:
        print(f"An unexpected error occurred: {e}")
        sys.exit(5)


if __name__ == '__main__':
    main()
