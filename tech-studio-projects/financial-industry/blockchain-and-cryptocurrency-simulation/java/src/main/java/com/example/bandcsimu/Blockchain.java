package com.example.bandcsimu;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private List<Block> chain;
    private List<Transaction> pendingTransactions;

    // Constructor: Initialize the blockchain with the genesis block
    public Blockchain() {
        this.chain = new ArrayList<>();
        this.pendingTransactions = new ArrayList<>();
        Block genesisBlock = createGenesisBlock();
        chain.add(genesisBlock);
        System.out.println("Genesis Block Created.");
    }

    // Create the Genesis Block
    private Block createGenesisBlock() {
        List<String> genesisTransactions = new ArrayList<>();
        genesisTransactions.add("Genesis Block");
        return new Block(0, "0", genesisTransactions);
    }

    // Add a new block to the blockchain with given transactions
    public void addBlock(Object block) {
        Block newBlock = new Block(chain.size(), getLatestBlock().getHash(), block);
        chain.add(newBlock);
        System.out.println("New Block added to the blockchain.");
    }

    // Add a new transaction to the pending transactions list
    public void addTransaction(Transaction transaction) {
        pendingTransactions.add(transaction);
        System.out.println("Transaction added: " + transaction.getSender() + " -> " +
                transaction.getReceiver() + " : " + transaction.getAmount() + " coins");
    }

    // Validate the blockchain to check if all blocks are properly linked
    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);

            // Check if the current block's hash is valid
            if (!currentBlock.getHash().equals(Block.calculateHash(currentBlock.getIndex(), 
                                                                          currentBlock.getPreviousHash(), 
                                                                          currentBlock.getTransactions(), 
                                                                          currentBlock.getTimestamp()))) {
                return false;
            }

            // Check if the current block's previous hash matches the hash of the previous block
            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;
            }
        }
        return true;
    }

    // Get the latest block in the chain
    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    // Print the entire blockchain
    public String printChain() {
        for (Block block : chain) {
            block.printBlock();
        }
		return null;
    }

    // Create a new block with the pending transactions and link it to the previous block
    public Block createNewBlock(NetworkNode node) {
        Block newBlock = new Block(chain.size(), getLatestBlock().getHash(), getPendingTransactionList());
        pendingTransactions.clear(); // Clear pending transactions after creating the block
        return newBlock;
    }

    // Helper function to convert pending transactions to a list of transaction strings
    List<String> getPendingTransactionList() {
        List<String> transactionStrings = new ArrayList<>();
        for (Transaction tx : pendingTransactions) {
            transactionStrings.add(tx.getSender() + " -> " + tx.getReceiver() + ": " + tx.getAmount() + " coins");
        }
        return transactionStrings;
    }
}
