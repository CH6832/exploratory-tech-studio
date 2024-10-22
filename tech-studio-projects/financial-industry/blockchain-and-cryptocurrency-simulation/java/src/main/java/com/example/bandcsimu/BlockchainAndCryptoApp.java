package com.example.bandcsimu;

import com.example.bandcsimu.Blockchain;
import com.example.bandcsimu.NetworkNode;
import com.example.bandcsimu.Transaction;
import com.example.bandcsimu.ProofOfWork;
import com.example.bandcsimu.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The BlockchainAndCryptoApp class serves as the entry point for the blockchain application.
 * It initializes the blockchain, creates transactions, simulates network nodes,
 * and integrates a proof-of-work consensus mechanism.
 * 
 * Implements CommandLineRunner to execute code after the Spring application has started.
 */
@SpringBootApplication
public class BlockchainAndCryptoApp implements CommandLineRunner {

    // Autowired instances of Blockchain and ProofOfWork
    @Autowired
    private Blockchain blockchain;

    @Autowired
    private ProofOfWork proofOfWork;

    /**
     * The main method serves as the entry point for the Spring Boot application.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(BlockchainAndCryptoApp.class, args);  // Launch the Spring application
    }

    /**
     * The run method executes after the Spring application context is loaded.
     * It initializes the blockchain, creates transactions, and simulates mining.
     * 
     * @param args Command line arguments passed to the application
     * @throws Exception if any error occurs during execution
     */
    @Override
    public void run(String... args) throws Exception {
        // Create a Blockchain instance
        blockchain = new Blockchain();  // Initialize the blockchain
        System.out.println("Blockchain created with Genesis Block.");  // Confirm genesis block creation

        // Simulate Network Nodes
        NetworkNode node1 = new NetworkNode("127.0.0.1", 8080);  // Create a network node at localhost:8080
        NetworkNode node2 = new NetworkNode("127.0.0.1", 8081);  // Create a second network node at localhost:8081

        // Create Transactions
        Transaction transaction1 = new Transaction("Alice", "Bob", 50);   // Alice sends 50 coins to Bob
        Transaction transaction2 = new Transaction("Bob", "Charlie", 20); // Bob sends 20 coins to Charlie

        System.out.println("Transactions created.");  // Confirm transaction creation

        // Add Transactions to the Blockchain (Pending until mined)
        blockchain.addTransaction(transaction1);  // Add transaction1 to pending transactions
        blockchain.addTransaction(transaction2);  // Add transaction2 to pending transactions

        // Simulate Consensus Algorithm (Proof of Work)
        proofOfWork = new ProofOfWork(blockchain, 4); // Initialize Proof of Work with a difficulty of 4

        // Step 6: Mine a Block and add it to the Blockchain
        Block newBlock = blockchain.createNewBlock(node1);  // Create a new block with pending transactions
        proofOfWork.mineBlock(); // Execute the Proof of Work algorithm to mine the block

        blockchain.addBlock(newBlock);  // Add the mined block to the blockchain
        System.out.println("New block mined and added to the blockchain.");  // Confirm block addition

        // Validate the Blockchain
        if (blockchain.isChainValid()) {
            System.out.println("Blockchain is valid.");  // Confirm that the blockchain is valid
        } else {
            System.out.println("Blockchain is invalid.");  // Indicate a problem with the blockchain
        }

        // Print the entire Blockchain
        blockchain.printChain();  // Display the complete blockchain information
    }
}
