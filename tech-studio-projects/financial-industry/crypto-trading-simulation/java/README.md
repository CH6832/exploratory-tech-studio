# Market Maker Project

This project implements a market maker strategy using a modular architecture in Java. It includes components for configuration management, market data feeds, order routing, and trade surveillance. The project is designed to be extensible and maintainable, making it suitable for real-world trading applications.

## Table of Contents

- [Market Maker Project](#market-maker-project)
  - [Table of Contents](#table-of-contents)
  - [Features](#features)
  - [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Usage](#usage)
    - [Running Tests](#running-tests)
    - [Running with Docker](#running-with-docker)
  - [License](#license)
  - [Acknowledgments](#acknowledgments)

## Features

- **Configuration Management**: Load configurations from JSON files.
- **Market Maker Strategy**: Implements a simple market making logic.
- **Order Routing**: Routes orders based on market conditions.
- **Trade Surveillance**: Monitors trades for suspicious activity.
- **WebSocket and TCP Client**: Connects to market data feeds and order execution services.

## Getting Started

These instructions will help you set up the project on your local machine for development and testing purposes.

### Prerequisites

- **Java 17**: Make sure you have Java 17 installed on your system.
- **Maven**: This project uses Maven for dependency management and building the project.
- **Docker** (optional): If you want to run the application in a Docker container.

### Installation

1. **Clone the repository**:

   ```bash
   git clone https://github.com/yourusername/market-maker-project.git
   cd market-maker-project
   ```

2. **Build the project using Maven**:

   ```bash
   mvn clean package
   ```

3. **Install any required dependencies** if you are using Maven.

### Usage

To run the market maker application:

```bash
java -cp target/market-maker-project-1.0.jar Main
```

Replace `market-maker-project-1.0.jar` with the actual jar file name generated after building the project.

### Running Tests

This project includes unit tests using Google Test for C++ components and JUnit for Java components.

1. **C++ Tests**: 
   Make sure you have Google Test installed, then run the tests using:
   ```bash
   cd build
   ./run_tests
   ```

2. **Java Tests**:
   You can run tests with Maven:
   ```bash
   mvn test
   ```

### Running with Docker

If you wish to run the project in a Docker container, ensure that Docker is installed and then follow these steps:

1. **Build the Docker image**:
   ```bash
   docker build -t market-maker .
   ```

2. **Run the Docker container**:
   ```bash
   docker run -it market-maker
   ```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- [nlohmann/json](https://github.com/nlohmann/json) for JSON parsing in C++
- [JUnit](https://junit.org/junit5/) for testing in Java
- [Google Test](https://github.com/google/googletest) for C++ unit testing
