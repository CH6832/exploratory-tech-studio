# Movie Recommender System

## Overview

The `MovieRecommender` application is a Java-based movie recommendation system built using Apache Spark. It utilizes Spark's Alternating Least Squares (ALS) algorithm to generate personalized movie recommendations for users based on their ratings.

## Features

- **Data Loading:** Loads and parses user ratings and movie titles from text files.
- **Model Training:** Uses ALS to train a recommendation model on user ratings.
- **Recommendation Generation:** Produces top 10 movie recommendations for each user asynchronously.
- **REST API:** Exposes an endpoint to trigger recommendation generation.
- **Output:** Displays the recommended movies along with their predicted ratings for each user in the console.


## Prerequisites

- Java 17 or later
- Apache Spark 3.x or later
- Apache Maven (for building the project)

## Setup

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/yourusername/yourrepository.git
   cd yourrepository
   ```

2. **Build the Project:**

   Use Maven to build the project and resolve dependencies:

   ```bash
   mvn clean package
   ```

3. **Prepare the Data:**

   - **Ratings Data:** Create a `ratings.txt` file with user ratings. The format should be:
     ```
     userId,movieId,rating
     ```
   - **Movies Data:** Create a `movies.txt` file with movie titles. The format should be:
     ```
     movieId,movieTitle
     ```

4. **Run the Application:**

   Execute the application using the built JAR file:

   ```bash
   spark-submit --class com.movierecommender.Main --master local[*] target/yourproject-1.0-SNAPSHOT.jar
   ```

   Ensure that `ratings.txt` and `movies.txt` are in the same directory from where you run the application or provide absolute paths to these files in the code.

## Code Explanation

- **Data Loading:** The ratings and movie titles are loaded from text files and parsed into appropriate formats.
- **Model Training:** The ALS algorithm is used to create a recommendation model. The `rank` and `numIterations` parameters control the complexity of the model.
- **Recommendation Generation:** The trained model generates recommendations, and the results are mapped to movie titles.
- **Output:** Recommendations are printed to the console, showing each user's recommended movies and predicted ratings.

## Configuration

- **Rank:** Number of latent factors in the ALS model (set to 10).
- **Iterations:** Number of iterations to train the ALS model (set to 10).
- **Lambda:** Regularization parameter (set to 0.01).

## Troubleshooting

- **Missing Dependencies:** Ensure that all Maven dependencies are correctly resolved by running `mvn clean package`.
- **File Not Found:** Ensure `ratings.txt` and `movies.txt` are correctly placed and paths are correctly specified.
