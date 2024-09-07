# Data Collection Plan

## Overview

The goal of this project is to predict the next software version based on historical version data. This data collection plan outlines the methods and sources for gathering version numbers, which will be used to build a predictive model. We aim to collect detailed major and minor version numbers from various sources to ensure a robust dataset for accurate predictions.

## Data Sources

### 1. Version Control Systems (Git, SVN)

**Description:**
- Version control systems store historical version tags and commit histories that include version numbers. Extracting these tags provides insights into how software versions evolve over time.

**Collection Steps:**

- **Git:**
  - **Commands:**
    - `git tag`: List all version tags.
    - `git log --pretty=format:'%d'`: Extract version numbers from commit messages if versioning follows a specific pattern.
  - **Process:**
    - Clone the repository using `git clone <repo_url>`.
    - Run `git tag` to list tags and `git log` to extract versioning information from commit messages.
    - Save the extracted tags in a CSV file format.

- **SVN:**
  - **Commands:**
    - `svn log --stop-on-copy`: Retrieve the version history including tags.
    - `svn info --show-item revision`: Obtain the current revision number.
  - **Process:**
    - Check out the repository using `svn checkout <repo_url>`.
    - Use `svn log` to extract tags or version numbers from the log entries.
    - Save the extracted tags in a CSV file format.

**Example Output:**

```
major,minor
1,0
1,1
1,2
2,0
2,1
```

### 2. Software Repositories

**Description:**
- Software repositories, such as GitHub, GitLab, and Bitbucket, often include version information in release notes or version histories.

**Collection Steps:**

- **GitHub:**
  - **API Access:**
    - Use GitHub API endpoints like `GET /repos/:owner/:repo/tags` to fetch version tags.
    - Use `GET /repos/:owner/:repo/releases` to get detailed release information including version numbers.
  - **Process:**
    - Fetch tags and releases using the GitHub API.
    - Parse the version numbers from the JSON responses.
    - Save the version data to a CSV file.

- **GitLab:**
  - **API Access:**
    - Use GitLab API endpoints like `GET /projects/:id/repository/tags` to retrieve tags.
    - Use `GET /projects/:id/releases` for detailed release information.
  - **Process:**
    - Fetch tags and releases using the GitLab API.
    - Parse and store version numbers in a CSV file.

- **Bitbucket:**
  - **API Access:**
    - Use Bitbucket API endpoints to fetch tags and releases if available.
  - **Process:**
    - Extract version numbers from the fetched data and save them in a CSV file.

**Example Output:**

```
major,minor
1,0
1,1
1,2
2,0
2,1
```

### 3. Manual Data Generation for Simulation

**Description:**
- When real data is insufficient, synthetic data can be generated to simulate versioning patterns. This helps in testing and validating the predictive model.

**Collection Steps:**

- **Data Generation:**
  - **Pattern Creation:**
    - Generate version numbers following typical increment patterns (e.g., `1.0.0`, `1.1.0`, `1.2.0`, `2.0.0`).
  - **Tools:**
    - Use scripts or tools to automate data generation.
  - **Process:**
    - Create a range of versions with incremental patterns.
    - Ensure that the generated data includes diverse scenarios for robust testing.

**Example Output:**

```
major,minor
0,1
0,2
1,0
1,1
1,2
2,0
```

## Data Format

The collected data will be stored in CSV format with the following structure:

- **Filename:** `version_data.csv`
- **Columns:**
  - **`major`:** Integer representing the major version number. This number increments for significant updates or new releases.
  - **`minor`:** Integer representing the minor version number. This number increments for minor updates or patches within the major version.

**Example CSV Content:**

```
major,minor
1,0
1,1
1,2
2,0
2,1
```

**Data Handling:**
- Ensure data consistency by verifying the format before processing.
- Clean and preprocess the data as needed to handle missing or inconsistent entries.
