# Movie Recommendation System

## Table of Contents

- [Why Such a Project](#why-such-a-project)
- [How It Works](#how-it-works)
  - [Understanding the Business Problem](#understanding-the-business-problem)
  - [Collecting Data](#collecting-data)
  - [Exploring the Dataset (EDA)](#exploring-the-dataset-eda)
  - [Preprocessing and Cleaning](#preprocessing-and-cleaning)
  - [Feature Selection and Engineering](#feature-selection-and-engineering)
  - [Model Training, Evaluation, and Interpretation](#model-training-evaluation-and-interpretation)
  - [Predicting](#predicting)
  - [Reporting](#reporting)
- [Content Overview](#content-overview)
- [Open the Project](#open-the-project)
- [Run the Project](#run-the-project)
- [Debug the Project](#debug-the-project)
- [Resources](#resources)
- [Bug Fixes and Assistance](#bug-fixes-and-assistance)
- [License](#license)
- [Appendices](#appendices)
  - [Data Sources](#data-sources)
  - [Code Documentation](#code-documentation)
  - [References](#references)

## Why Such a Project

This project demonstrates the power of data-driven personalization by creating a movie recommendation system. Personalization plays a critical role in increasing user engagement, loyalty, and satisfaction. This project can have real-world applications in various domains, such as streaming platforms, e-commerce, and marketing.

## How It Works

### Understanding the Business Problem

The goal is to build a recommendation system that suggests movies to users based on their preferences. This system improves user experience, retention, and platform growth by tailoring content to individual tastes.

### Collecting Data

The primary dataset is the [MovieLens 10M Dataset](http://grouplens.org/datasets/movielens/10m/), which provides user ratings for movies. For a broader analysis, the full dataset is accessible at [MovieLens Datasets](https://grouplens.org/datasets/movielens/latest/).

### Exploring the Dataset (EDA)

The dataset contains 10 million ratings from 72,000 users on 10,000 movies. Key files include `ratings.dat`, `movies.dat`, and `tags.dat`. Exploratory data analysis (EDA) helps identify trends and insights, such as rating distributions, popular genres, and user behaviors.

### Preprocessing and Cleaning

Data cleaning includes handling missing values, normalizing ratings, and transforming categorical variables. Unnecessary columns are removed to streamline the dataset for model training.

### Feature Selection and Engineering

Key features such as user IDs, movie IDs, genres, and average ratings are extracted. Additional features, such as the number of ratings per user, are engineered to enhance model accuracy.

### Model Training, Evaluation, and Interpretation

Multiple algorithms were evaluated, including collaborative filtering and content-based approaches. After testing, the Random Forest model was selected due to its superior performance, evaluated using metrics like Root Mean Square Error (RMSE).

### Predicting

The trained model generates personalized movie recommendations based on user preferences and past interactions. These predictions are ranked for relevance.

### Reporting

Final results, including performance metrics and visualizations, are compiled in a report. Insights from the project and potential future improvements are also discussed.

## Content Overview

```
.
├── data/ - Contains datasets.
│   ├── processed/ - Preprocessed data for modeling.
│   └── raw/ - Original, unprocessed datasets.
├── model/ - Trained models and related files.
├── reports/ - Final and intermediate reports.
├── scripts/ - python scripts for the project.
│   ├── data_preprocessing.py - Data cleaning and preprocessing.
│   ├── exploratory_data_analysis.py - EDA.
│   ├── feature_engineering.py - Feature engineering.
│   ├── generate_test_data.py - Test data generation.
│   ├── ml_model_training.py - Model training.
│   └── ml_model_validation.py - Model evaluation.
├── LICENSE - License file.
└── README.md - Project documentation.
```

## Run the Project

**Install Dependencies:**
   Install necessary python packages:
   ```
   scipy, sklearn, numpy, pandas, pydoc
   ```

**Execute Scripts in Order:**
   - **EDA:**
     ```
     scripts/exploratory_data_analysis.py
     ```
   - **Preprocessing:**
     ```
     scripts/data_preprocessing.py
     ```
   - **Feature Engineering:**
     ```
     scripts/feature_engineering.py
     ```
   - **Model Training:**
     ```
     scripts/ml_model_training.py
     ```
   - **Model Validation:**
     ```
     scripts/ml_model_validation.py
     ```

## Bug Fixes and Assistance

This documentation and code have been refined with the help of [ChatGPT](https://openai.com/), ensuring clarity and quality without altering core ideas.

## License

This project is licensed under the [CC BY-NC-ND 4.0 License](https://creativecommons.org/licenses/by-nc-nd/4.0/deed.en). See the [LICENSE](LICENSE) file for details.

## Appendices

### Data Sources
- **MovieLens 10M Dataset:** [https://grouplens.org/datasets/movielens/](https://grouplens.org/datasets/movielens/)

### Code Documentation
The `scripts/` directory contains all code with detailed comments and docstrings.

### References
- Breiman, L. (2001), *Random Forests*, Machine Learning, 45(1), 5-32.
- GroupLens Research (2024), *MovieLens Datasets*, [https://grouplens.org/datasets/movielens/](https://grouplens.org/datasets/movielens/).
