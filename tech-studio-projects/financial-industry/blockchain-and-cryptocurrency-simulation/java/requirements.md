# Requirements Document for Blockchain and Cryptocurrency Simulation

## Overview

This project is a simplified implementation of a blockchain system that incorporates various blockchain concepts, including **Proof of Work (PoW)**, **Proof of Stake (PoS)**, and basic **network capabilities**. The system is built using **Java** and the **Spring Boot** framework, allowing for easy management of RESTful APIs and background tasks.

## Architecture

The architecture of the system is modular, consisting of several components that work together to achieve the overall functionality of a blockchain. The key components include:

1. **Blockchain**: Manages the chain of blocks, transactions, and validation.
2. **Block**: Represents a single block in the blockchain containing transactions, a reference to the previous block, and other metadata.
3. **Transaction**: Represents a transaction between users in the blockchain system, including sender, receiver, amount, and signature.
4. **Proof of Work**: Implements the mining algorithm, which ensures that new blocks are added to the chain after solving computational problems.
5. **Proof of Stake**: Implements the algorithm for selecting validators based on their stake in the network.
6. **Network Node**: Represents a node in the blockchain network that can communicate with other nodes, handle transactions, and synchronize the blockchain.

## Component Overview

### 1. Blockchain

- **Responsibilities**: 
  - Maintains the list of blocks in the chain.
  - Manages transactions (adding, validating).
  - Ensures the integrity and validity of the blockchain.

- **Key Methods**: 
  - `addTransaction(Transaction transaction)`: Adds a transaction to the pending list.
  - `createNewBlock(NetworkNode node)`: Creates a new block for mining.
  - `isChainValid()`: Validates the entire chain for integrity.

### 2. Block

- **Responsibilities**: 
  - Represents a single block in the blockchain.
  - Contains metadata like index, previous hash, timestamp, and transactions.

- **Key Methods**: 
  - `calculateHash()`: Generates a hash for the block based on its contents.

### 3. Transaction

- **Responsibilities**: 
  - Represents a transaction between two parties (sender and receiver).
  - Contains transaction details, including amount and signature.

- **Key Methods**: 
  - `getTransactionData()`: Generates a string representation of the transaction for signing or hashing.

### 4. Proof of Work

- **Responsibilities**: 
  - Implements the mining algorithm.
  - Ensures the new block meets the difficulty criteria by requiring a specific hash format.

- **Key Methods**: 
  - `mineBlock()`: Attempts to find a valid nonce for the block’s hash.

### 5. Proof of Stake

- **Responsibilities**: 
  - Implements a mechanism to select a validator based on their stake in the network.

- **Key Methods**: 
  - `addStake(String validator, double amount)`: Adds stake for a validator.
  - `selectValidator()`: Randomly selects a validator based on their stake.

### 6. Network Node

- **Responsibilities**: 
  - Represents a participant in the blockchain network.
  - Connects to other nodes, broadcasts new blocks, and synchronizes the blockchain.

- **Key Methods**: 
  - `connectToPeer(String peerAddress, int peerPort)`: Connects to another node in the network.
  - `broadcastNewBlock(Block block)`: Sends newly mined blocks to connected peers.

## Interaction Between Components

1. **Creating Transactions**: 
   - Users initiate transactions by creating `Transaction` objects. These transactions are added to the `Blockchain`.

2. **Mining New Blocks**: 
   - When enough transactions are gathered, a new `Block` is created. The `Proof of Work` component attempts to mine this block by finding a valid hash.
   - The mined block is then added to the `Blockchain`.

3. **Maintaining Consensus**:
   - The `Proof of Stake` component can be used to select validators based on their stakes, which can help in scenarios where consensus is required for transaction validation.

4. **Node Communication**:
   - Each `NetworkNode` can connect to peers and share the latest blocks. This synchronization helps maintain a consistent state across the network.

5. **Validation**: 
   - The `Blockchain` component periodically checks the validity of the entire chain to ensure no invalid transactions or blocks have been introduced.

## Conclusion

This modular architecture allows for flexibility and scalability. Each component can be developed, tested, and modified independently while maintaining cohesive functionality within the system. The use of **Java** and **Spring Boot** enhances the maintainability and extendibility of the application, paving the way for further enhancements and features in future iterations of the project.
