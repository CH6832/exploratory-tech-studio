# Blockchain Project

This project is a simplified implementation of a blockchain system with Proof of Stake (PoS), Proof of Work (PoW), and basic network capabilities. The project demonstrates how to create a basic blockchain, handle transactions with digital signatures, and synchronize the blockchain across nodes in a network.

## Features

- **Blockchain Management**: Basic blockchain with block validation and transaction management.
- **Transactions**: Support for transactions with digital signatures.
- **Proof of Work (PoW)**: Mining mechanism to add new blocks.
- **Proof of Stake (PoS)**: Validator selection based on stake.
- **Networking**: Basic network capabilities to connect nodes and broadcast new blocks.

## Technologies Used

This project utilizes various technologies and frameworks to implement its features. Below is a list of the main technologies used along with links to their documentation:

- **Java**: A high-level programming language used for developing the application. [Java Documentation](https://docs.oracle.com/en/java/)
- **Spring Boot**: A framework for building stand-alone, production-grade Spring-based applications. [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- **JUnit 5**: A popular testing framework for Java, used to write unit tests for the application. [JUnit 5 Documentation](https://junit.org/junit5/docs/current/user-guide/)
- **Maven**: A build automation tool used primarily for Java projects. [Maven Documentation](https://maven.apache.org/guides/index.html)

## Prerequisites

- **Java Development Kit (JDK)**: Ensure that JDK is installed on your machine. [JDK Documentation](https://docs.oracle.com/en/java/javase/11/install/installation-jdk-linux-platforms.html)
- **Maven**: Required for managing dependencies and building the project. [Maven Installation Guide](https://maven.apache.org/install.html)

## Installation

1. **Clone the Repository**

   ```bash
   git clone https://github.com/your-username/blockchain-project.git
   cd blockchain-project
   ```

2. **Build the Project**

   ```bash
   mvn clean install
   ```

3. **Run the Application**

   ```bash
   mvn spring-boot:run
   ```

## Usage

After running the application, you can interact with the blockchain through its provided APIs or command-line interface (if implemented). You can create transactions, mine blocks, and manage the blockchain network.

### Example Commands

- Create a transaction:
  ```bash
  curl -X POST http://localhost:8080/transactions --data '{"sender": "Alice", "receiver": "Bob", "amount": 50}'
  ```

- Mine a block:
  ```bash
  curl -X POST http://localhost:8080/mine
  ```

- Print the blockchain:
  ```bash
  curl http://localhost:8080/chain
  ```

## Testing

The project includes a comprehensive test suite that validates the core functionalities of the blockchain system.

### Running Tests

To run the tests, use your IDE's built-in test runner or execute the following command:

```bash
mvn test
```

### Test Coverage

The tests cover the following components:

- **Blockchain**: Validates block creation, transaction management, and chain integrity.
- **Transactions**: Ensures transaction properties and behavior are correct.
- **Blocks**: Checks block initialization and hash generation.
- **Proof of Work**: Tests mining functionality and ensures hash meets difficulty requirements.
- **Proof of Stake**: Validates validator selection based on stake.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more information.
