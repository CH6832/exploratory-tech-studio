package com.example.bandcsimu;

import java.util.ArrayList;
import java.util.List;

/**
 * The Blockchain class represents a blockchain structure that holds a sequence of blocks.
 * It manages the chain of blocks, handles pending transactions, and provides methods to 
 * add new blocks and validate the integrity of the chain.
 * 
 * Key Responsibilities:
 * - Initialize the blockchain with a genesis block.
 * - Add new transactions to a pending list.
 * - Create and add new blocks to the blockchain.
 * - Validate the entire blockchain for integrity and consistency.
 */
public class Blockchain {

    // List to hold the sequence of blocks in the blockchain
    private List<Block> chain;
    
    // List to hold transactions that are yet to be included in a block
    private List<Transaction> pendingTransactions;

    /**
     * Constructor for the Blockchain class.
     * Initializes the blockchain and creates the genesis block, which serves as the starting point of the chain.
     * 
     * Why it's important:
     * - The genesis block is crucial as it provides a reference point for all subsequent blocks.
     * - A new blockchain must always start with a genesis block to maintain integrity.
     */
    public Blockchain() {
        this.chain = new ArrayList<>();
        this.pendingTransactions = new ArrayList<>();
        Block genesisBlock = createGenesisBlock();  // Create the first block in the blockchain
        chain.add(genesisBlock);  // Add the genesis block to the chain
        System.out.println("Genesis Block Created.");
    }

    /**
     * Creates the genesis block for the blockchain.
     * 
     * @return A new Block object representing the genesis block.
     * 
     * Why it's important:
     * - The genesis block has a special index (0) and a predefined previous hash (0).
     * - It initializes the chain and ensures that all other blocks can reference it.
     */
    private Block createGenesisBlock() {
        List<String> genesisTransactions = new ArrayList<>();
        genesisTransactions.add("Genesis Block");  // Add a simple transaction to the genesis block
        return new Block(0, "0", genesisTransactions);  // Return the newly created genesis block
    }

    /**
     * Adds a new block to the blockchain using the provided transactions.
     * 
     * @param block The transactions to be included in the new block.
     * 
     * Why it's important:
     * - New blocks contain transactions that need to be confirmed and stored in the blockchain.
     * - This method links the new block to the previous one using the last block's hash.
     */
    public void addBlock(Object block) {
        Block newBlock = new Block(chain.size(), getLatestBlock().getHash(), block);  // Create a new block
        chain.add(newBlock);  // Add the new block to the blockchain
        System.out.println("New Block added to the blockchain.");
    }

    /**
     * Adds a new transaction to the list of pending transactions.
     * 
     * @param transaction The transaction to be added to the pending list.
     * 
     * Why it's important:
     * - Pending transactions are those that have been created but not yet included in a block.
     * - This method allows for the collection of multiple transactions before creating a new block.
     */
    public void addTransaction(Transaction transaction) {
        pendingTransactions.add(transaction);  // Add the transaction to the pending list
        System.out.println("Transaction added: " + transaction.getSender() + " -> " +
                transaction.getReceiver() + " : " + transaction.getAmount() + " coins");
    }

    /**
     * Validates the blockchain to ensure that all blocks are properly linked and have valid hashes.
     * 
     * @return true if the blockchain is valid, false otherwise.
     * 
     * What it does:
     * - Iterates through the chain to verify that each block's hash is correct and that it points to the previous block.
     * 
     * Why it's important:
     * - Maintaining the integrity of the blockchain is essential for trust and security.
     * - If a block is tampered with, this method will detect the issue.
     */
    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);

            // Check if the current block's hash is valid
            if (!currentBlock.getHash().equals(Block.calculateHash(currentBlock.getIndex(), 
                                                                          currentBlock.getPreviousHash(), 
                                                                          currentBlock.getTransactions(), 
                                                                          currentBlock.getTimestamp()))) {
                return false;  // Current block's hash does not match calculated hash
            }

            // Check if the current block's previous hash matches the hash of the previous block
            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;  // The link between blocks is broken
            }
        }
        return true;  // All blocks are valid
    }

    /**
     * Retrieves the latest block in the blockchain.
     * 
     * @return The most recent Block in the chain.
     * 
     * Why it's important:
     * - The latest block is needed to add new blocks and to verify the chain.
     */
    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);  // Return the last block in the chain
    }

    /**
     * Prints the entire blockchain by invoking the printBlock method on each block.
     * 
     * Why it's important:
     * - Printing the blockchain is useful for debugging, auditing, and understanding the flow of transactions.
     */
    public String printChain() {
        for (Block block : chain) {
            block.printBlock();  // Print the information of each block
        }
		return null;  // The return value is not necessary but could be modified to return a string summary if needed
    }

    /**
     * Creates a new block with the current pending transactions and links it to the latest block in the chain.
     * 
     * @param node The NetworkNode that is creating the block (not used here but could be relevant for future implementation).
     * @return A new Block object containing the pending transactions.
     * 
     * Why it's important:
     * - This method centralizes the logic for creating new blocks and ensures that the pending transactions are 
     *   captured before being cleared from the list.
     */
    public Block createNewBlock(NetworkNode node) {
        Block newBlock = new Block(chain.size(), getLatestBlock().getHash(), getPendingTransactionList());
        pendingTransactions.clear();  // Clear the pending transactions after creating the block
        return newBlock;  // Return the newly created block
    }

    /**
     * Converts pending transactions into a list of transaction strings for inclusion in a new block.
     * 
     * @return A List of Strings representing the pending transactions.
     * 
     * What it does:
     * - Formats each pending transaction into a user-friendly string format for inclusion in a block.
     * 
     * Why it's important:
     * - This helps in capturing the transactions accurately while creating a new block.
     */
    List<String> getPendingTransactionList() {
        List<String> transactionStrings = new ArrayList<>();
        for (Transaction tx : pendingTransactions) {
            transactionStrings.add(tx.getSender() + " -> " + tx.getReceiver() + ": " + tx.getAmount() + " coins");
        }
        return transactionStrings;  // Return the formatted list of transactions
    }
}
