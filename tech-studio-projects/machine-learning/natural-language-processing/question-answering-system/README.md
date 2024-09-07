# Question Answering System

This project demonstrates a minimal working question-answering system using BERT, with critical components written in C++. The system is built using Flask and Docker.

## Project Structure

- `app.py`: Main Python script for the Flask API.
- `preprocessor.cpp`: C++ code for preprocessing text.
- `Dockerfile`: Dockerfile for containerizing the application.
- `requirements.txt`: Python dependencies.
- `preprocessor.so`: Compiled C++ shared library.

## Setup and Installation

1. **Clone the repository**:
    ```sh
    git clone <repository-url>
    cd <repository-directory>
    ```

2. **Build the C++ shared library**:
    ```sh
    g++ -shared -o preprocessor.so -fPIC preprocessor.cpp
    ```

3. **Build the Docker image**:
    ```sh
    docker build -t qa-system .
    ```

4. **Run the Docker container**:
    ```sh
    docker run -p 5000:5000 qa-system
    ```

## API Usage

**Endpoint**: `/answer`

**Method**: POST

**Request**:
```json
{
    "question": "What is the capital of France?",
    "context": "The capital of France is Paris."
}
