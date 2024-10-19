# Blockchain Project

This project is a simplified implementation of a blockchain system with Proof of Stake (PoS), Proof of Work (PoW), and basic network capabilities. The project demonstrates how to create a basic blockchain, handle transactions with digital signatures, and synchronize the blockchain across nodes in a network.

## Features

- **Blockchain Management**: Basic blockchain with block validation and transaction management.
- **Transactions**: Support for transactions with digital signatures using RSA encryption.
- **Proof of Work (PoW)**: Mining mechanism to add new blocks.
- **Proof of Stake (PoS)**: Validator selection based on stake.
- **Networking**: Basic network capabilities to connect nodes and broadcast new blocks.

## Prerequisites

- **OpenSSL**: For cryptographic functions like RSA encryption and SHA-256 hashing.
- **CMake**: For building the project.
- **Google Test**: For running unit tests.

## Installation

1. **Clone the Repository**

   ```bash
   git clone https://github.com/your-username/blockchain-project.git
   cd blockchain-project
   ```

2. **Build the Project**

   ```bash
   mkdir build
   cd build
   cmake ..
   make
   ```

3. **Run the Application**

   ```bash
   ./your_application_name
   ```

## Usage

After running the application, you can interact with the blockchain through its provided APIs or command-line interface (if implemented). You can create transactions, mine blocks, and manage the blockchain network.

### Example Commands

- Create a transaction:
  ```bash
  ./your_application_name create_transaction --from Alice --to Bob --amount 50
  ```

- Mine a block:
  ```bash
  ./your_application_name mine_block
  ```

- Print the blockchain:
  ```bash
  ./your_application_name print_chain
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

## Contributing

Contributions are welcome! If you would like to contribute to this project, please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes and commit them (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Create a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more information.

## Acknowledgments

- Thanks to the open-source community for providing libraries and frameworks that make building blockchain applications easier.
- Inspired by various blockchain technologies and concepts.

