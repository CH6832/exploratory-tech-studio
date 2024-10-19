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

@SpringBootApplication
public class BlockchainAndCryptoApp implements CommandLineRunner {

    @Autowired
    private Blockchain blockchain;

    @Autowired
    private ProofOfWork proofOfWork;

    public static void main(String[] args) {
        SpringApplication.run(BlockchainAndCryptoApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Create a Blockchain
        blockchain = new Blockchain();
        System.out.println("Blockchain created with Genesis Block.");

        // Simulate Network Nodes
        NetworkNode node1 = new NetworkNode("127.0.0.1", 8080);
        NetworkNode node2 = new NetworkNode("127.0.0.1", 8081);

        // Create Transactions
        Transaction transaction1 = new Transaction("Alice", "Bob", 50);   // Alice sends 50 coins to Bob
        Transaction transaction2 = new Transaction("Bob", "Charlie", 20); // Bob sends 20 coins to Charlie

        System.out.println("Transactions created.");

        // Add Transactions to the Blockchain (Pending until mined)
        blockchain.addTransaction(transaction1);
        blockchain.addTransaction(transaction2);

        // Simulate Consensus Algorithm (Proof of Work)
        proofOfWork = new ProofOfWork(blockchain, 4); // Difficulty set to 4

        // Step 6: Mine a Block and add it to the Blockchain
        Block newBlock = blockchain.createNewBlock(node1);
        proofOfWork.mineBlock(); // If Proof of Work

        blockchain.addBlock(newBlock);
        System.out.println("New block mined and added to the blockchain.");

        // Validate the Blockchain
        if (blockchain.isChainValid()) {
            System.out.println("Blockchain is valid.");
        } else {
            System.out.println("Blockchain is invalid.");
        }

        // Print the Blockchain
        blockchain.printChain();
    }
}