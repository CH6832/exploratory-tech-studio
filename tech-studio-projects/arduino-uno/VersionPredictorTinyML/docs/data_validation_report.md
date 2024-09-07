# Data Validation Report

## Overview

This report provides a comprehensive summary of the data validation checks performed on the dataset used for model training. Data validation is crucial to ensure that the dataset is accurate, consistent, and reliable before it is used in the machine learning pipeline. The following sections detail the checks conducted to verify the quality and integrity of the data.

## Validation Checks

### 1. **Consistency Checks**

**Objective:**
- Ensure that the dataset is free from errors such as duplicate entries or missing values.

**Detailed Steps:**

- **Duplicate Data:**
  - **Description:** Check for and remove duplicate rows to avoid redundancy in the dataset.
  - **Method:**
    - Use data manipulation libraries (e.g., Pandas) to identify and remove duplicate entries based on all columns or a subset of columns if applicable.
  - **Code Example:**

    ```python
    # Check for duplicates
    duplicates = df[df.duplicated()]

    # Remove duplicates
    df_cleaned = df.drop_duplicates()

    # Log the number of duplicate rows found and removed
    print(f"Number of duplicate rows removed: {len(duplicates)}")
    ```

- **Missing Values:**
  - **Description:** Identify and handle any missing or null values in the dataset.
  - **Method:**
    - Check for missing values in each column.
    - Decide on an appropriate strategy for handling missing data (e.g., imputation, removal).
  - **Code Example:**

    ```python
    # Check for missing values
    missing_values = df.isnull().sum()

    # Report missing values
    print("Missing values per column:")
    print(missing_values)

    # Optionally, handle missing values
    df_cleaned = df.dropna()  # Example: Drop rows with missing values
    ```

### 2. **Integrity Checks**

**Objective:**
- Verify that the dataset follows the expected order and structure, and that version sequences are logical.

**Detailed Steps:**

- **Sequence Order:**
  - **Description:** Ensure that version numbers are in the correct chronological order.
  - **Method:**
    - Validate that each version's `major` and `minor` numbers follow a logical sequence.
    - For example, minor versions should increment before major versions do.
  - **Code Example:**

    ```python
    # Sort the data by major and minor version numbers
    df_sorted = df.sort_values(by=['major', 'minor'])

    # Check if the sorted data matches the original data order
    if df_sorted.equals(df):
        print("Version numbers are in correct order.")
    else:
        print("Data is not in correct order. Check the sequence.")
    ```

- **Version Increment Validity:**
  - **Description:** Confirm that each version correctly increments from the previous version.
  - **Method:**
    - Verify that for each version (except the last), there is a subsequent version with an incremented minor number or, if needed, an incremented major number.
  - **Code Example:**

    ```python
    # Initialize a flag for validation
    valid_sequence = True

    # Iterate over the sorted dataframe to check version increments
    for i in range(1, len(df_sorted)):
        current_row = df_sorted.iloc[i-1]
        next_row = df_sorted.iloc[i]

        # Check if the next version is valid
        if (current_row['major'] == next_row['major'] and current_row['minor'] >= next_row['minor']) or \
           (current_row['major'] > next_row['major']):
            valid_sequence = False
            break

    if valid_sequence:
        print("Version increments are valid.")
    else:
        print("Version increments are not valid.")
    ```

### 3. **Data Range Checks**

**Objective:**
- Ensure that version numbers fall within reasonable and expected ranges.

**Detailed Steps:**

- **Range Validation:**
  - **Description:** Verify that the `major` and `minor` version numbers are within predefined acceptable ranges.
  - **Method:**
    - Define acceptable ranges based on the expected versioning scheme.
    - Check if all values fall within these ranges.
  - **Code Example:**

    ```python
    # Define acceptable ranges for major and minor versions
    major_range = (0, 10)  # Example range
    minor_range = (0, 99)  # Example range

    # Check if all values are within the acceptable ranges
    major_out_of_range = df[~df['major'].between(*major_range)]
    minor_out_of_range = df[~df['minor'].between(*minor_range)]

    # Report out-of-range values
    print(f"Major versions out of range: {len(major_out_of_range)}")
    print(f"Minor versions out of range: {len(minor_out_of_range)}")
    ```

### 4. **Data Consistency Across Sources**

**Objective:**
- Ensure consistency of version data across different sources if applicable.

**Detailed Steps:**

- **Cross-Source Validation:**
  - **Description:** Verify that version numbers align with data from other sources (e.g., version control systems, software repositories).
  - **Method:**
    - Compare the dataset with version information obtained from other validated sources.
    - Identify and address any discrepancies.
  - **Code Example:**

    ```python
    # Load version data from other sources
    df_other_source = pd.read_csv('other_source_versions.csv')

    # Compare dataframes
    discrepancies = pd.merge(df, df_other_source, on=['major', 'minor'], how='outer', indicator=True)
    discrepancies = discrepancies[discrepancies['_merge'] != 'both']

    # Report discrepancies
    print("Discrepancies found between datasets:")
    print(discrepancies)
    ```

## Summary

This data validation report ensures that the dataset is clean, consistent, and correctly ordered. The checks performed include:

1. **Consistency:** Addressing duplicates and missing values.
2. **Integrity:** Verifying version sequence and increment validity.
3. **Data Range Checks:** Ensuring version numbers fall within expected ranges.
4. **Data Consistency Across Sources:** Comparing with other validated sources to check for discrepancies.
