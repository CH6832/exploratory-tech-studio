Here's a detailed and comprehensive `README.md` file for your movie recommendation project:

---

# Movie Recommendation System

This project is a **Movie Recommendation System** built using **Apache Spark** and the **Alternating Least Squares (ALS)** model. The system generates personalized movie recommendations based on user preferences and ratings. It's designed to be efficient, scalable, and capable of handling large datasets.

## Table of Contents

- [Movie Recommendation System](#movie-recommendation-system)
  - [Table of Contents](#table-of-contents)
  - [Overview](#overview)
    - [Key Features](#key-features)
  - [Project Structure](#project-structure)
  - [Datasets](#datasets)
    - [Example Data](#example-data)
      - [`ratings.txt`](#ratingstxt)
      - [`movies.txt`](#moviestxt)
  - [Dependencies](#dependencies)
  - [Installation and Setup](#installation-and-setup)
    - [Prerequisites](#prerequisites)
    - [Setup Steps](#setup-steps)
  - [Usage](#usage)
    - [Sample Command to Run:](#sample-command-to-run)
  - [How It Works](#how-it-works)
    - [ALS Model Parameters](#als-model-parameters)
    - [Asynchronous Processing](#asynchronous-processing)
  - [Testing](#testing)
  - [License](#license)

## Overview

This project uses collaborative filtering to recommend movies to users based on their past ratings. Built using Spark's **MLlib** library, it employs a matrix factorization model, allowing the recommendation system to predict user ratings on unrated movies, thereby providing recommendations.

### Key Features
- **Data Loading and Parsing**: Loads and parses user ratings and movie metadata.
- **ALS Model Training**: Uses the ALS model to learn from user-movie interactions.
- **Top-N Recommendations**: Generates the top 10 movie recommendations for each user.
- **Asynchronous Processing**: Includes an asynchronous service to manage recommendation generation.

## Project Structure

```
movie-recommendation-system/
├── src/main/scala
│   ├── recommendation_service.scala - Service to generate movie recommendations
│   ├── main.scala - Main application entry point
│   └── data/
│       ├── ratings.txt - Sample user ratings data
│       └── movies.txt - Sample movie metadata
├── README.md - Project documentation
└── LICENSE - MIT License
```

## Datasets

The project uses two datasets:
- **ratings.txt**: Contains user ratings in the format `UserID,MovieID,Rating`.
- **movies.txt**: Contains movie metadata with `MovieID,Title,Genres,Release Date,Director,Rating`.

### Example Data
#### `ratings.txt`
```
1,1,5.0
1,2,4.0
1,3,3.5
...
```

#### `movies.txt`
```
1,Toy Story (1995),Adventure|Animation|Children|Comedy|Fantasy,1995-11-22,John Lasseter,8.3
2,Jumanji (1995),Adventure|Children|Fantasy,1995-12-15,Joe Johnston,6.9
...
```

## Dependencies

- **Scala**: 2.12+
- **Apache Spark**: 3.0+
- **Spark MLlib**: Machine learning library within Spark
- **Spring Framework**: To manage asynchronous operations

These dependencies are managed within the `build.sbt` file.

## Installation and Setup

### Prerequisites

Ensure you have the following installed:
- **Java 8** or higher
- **Scala**
- **Apache Spark**
- **SBT (Scala Build Tool)**

### Setup Steps

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/movie-recommendation-system.git
   cd movie-recommendation-system
   ```

2. **Add Datasets**:
   - Place your `ratings.txt` and `movies.txt` files in the `src/main/resources` directory.

3. **Configure Spark**:
   - Update `SparkConf` in `RecommendationService.scala` if you are using a distributed Spark cluster.

4. **Build the Project**:
   ```bash
   sbt compile
   ```

5. **Run the Project**:
   ```bash
   sbt run
   ```

## Usage

1. The `Main.scala` file is the entry point. It initiates the `RecommendationService` to generate recommendations.
2. The `RecommendationService` loads data, trains the ALS model, and outputs top 10 movie recommendations for each user in `ratings.txt`.

### Sample Command to Run:
```bash
sbt "runMain com.movierecommender.Main"
```

## How It Works

1. **Data Loading**: The project reads and parses data from `ratings.txt` and `movies.txt`.
2. **Model Training**: Using the ALS algorithm, the system factors the user-item interaction matrix to learn user and item features.
3. **Recommendation Generation**:
   - The model generates the top 10 recommended movies for each user.
   - Results are printed in the console, including movie titles and predicted ratings.
   
   The recommendations are printed in the format:
   ```
   User: 1
     Movie: Toy Story, Rating: 4.9
     Movie: Jumanji, Rating: 4.7
     ...
   ```

### ALS Model Parameters
- **Rank**: 10
- **Iterations**: 10
- **Regularization Parameter**: 0.01

### Asynchronous Processing
The `@Async` annotation in `RecommendationService` allows the recommendation process to run asynchronously, making the application responsive to multiple concurrent requests.

## Testing

To test the recommendation system:
1. Adjust the sample data in `ratings.txt` to simulate different user preferences.
2. Run the application, and observe the recommendations for each user.

Automated testing can be added by using libraries such as ScalaTest to validate ALS model output and ensure recommendations are consistent.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

