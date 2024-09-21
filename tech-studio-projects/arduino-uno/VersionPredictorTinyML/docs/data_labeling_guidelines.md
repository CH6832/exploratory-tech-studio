# Data Labeling Guidelines

## Overview

In this project, data labeling involves predicting the next version number based on the historical version numbers. The labeling is implicit, meaning that each version's label is derived directly from the next sequential version in the dataset. This process is crucial for training a predictive model that can accurately forecast future versions based on past data.

## Data Labeling Process

### 1. **Understanding the Sequence**

- **Definition:**
  - Each version in the dataset has a corresponding label that represents the subsequent version in the sequence. For example, if the current version is `1.0`, the label would be `1.1` if that is the next version in the sequence.

- **Importance:**
  - This approach helps in building a model that can learn the pattern of version increments and predict future versions accurately.

### 2. **Creating Labels**

**Steps to Generate Labels:**

1. **Sort Data by Version:**
   - Ensure that the dataset is sorted in chronological order by version numbers. This is critical as it maintains the correct sequence for labeling.

2. **Assign Labels:**
   - For each version number in the dataset, assign the next version number as its label. If a version does not have a subsequent version (e.g., the latest version), it will not be included in the labeling process.

3. **Handling Edge Cases:**
   - If the dataset ends without a next version, exclude such records from the training set since they do not have a corresponding label.
   - Handle any inconsistencies or gaps in the version sequence carefully to ensure accuracy.

**Example:**

Given the following dataset of versions:

| major | minor |
|-------|-------|
| 1     | 0     |
| 1     | 1     |
| 1     | 2     |
| 2     | 0     |
| 2     | 1     |

The labeling process would result in:

- **Version 1.0:** Label is `1.1`
- **Version 1.1:** Label is `1.2`
- **Version 1.2:** Label is `2.0`
- **Version 2.0:** Label is `2.1`
- **Version 2.1:** No label (if it is the latest version)

### 3. **Data Structure for Labeling**

- **Feature Columns:**
  - `major`: Major version number of the current version.
  - `minor`: Minor version number of the current version.

- **Label Column:**
  - `next_major`: Major version number of the next version.
  - `next_minor`: Minor version number of the next version.

**Example Data Table with Labels:**

| current_major | current_minor | next_major | next_minor |
|---------------|---------------|------------|------------|
| 1             | 0             | 1          | 1          |
| 1             | 1             | 1          | 2          |
| 1             | 2             | 2          | 0          |
| 2             | 0             | 2          | 1          |
| 2             | 1             | -          | -          |

### 4. **Label Validation**

- **Consistency Check:**
  - Ensure that labels correctly correspond to the next version in the sequence.
  - Validate that the version increments are logical and consistent.

- **Data Quality:**
  - Review the dataset for any anomalies or errors in version numbers and labels.
  - Perform sanity checks to verify that each version has a valid next version where applicable.

## Handling Special Cases

- **Multiple Versions per Major Release:**
  - In cases where multiple minor versions exist within the same major version, ensure that labels are assigned correctly based on the immediate next version.

- **Version Gaps:**
  - If there are gaps in the version numbers, handle them by ensuring that the labeling process only uses consecutive versions.

- **Versioning Scheme Differences:**
  - Adapt the labeling process if the versioning scheme deviates from the standard format (e.g., including patch versions or pre-release labels).
