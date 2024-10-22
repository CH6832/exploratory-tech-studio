package com.example.bandcsimu;

/**
 * The ProofOfWork class implements the proof-of-work consensus mechanism.
 * This class is responsible for mining a block by finding a hash that meets
 * the required difficulty level.
 */
public class ProofOfWork {
    private Block block;      // The block to be mined
    private int difficulty;   // The difficulty level of mining (number of leading zeros)

    /**
     * Constructor for the ProofOfWork class.
     * Initializes the block to be mined and the difficulty level.
     *
     * @param block The block to be mined.
     * @param difficulty The difficulty level (number of leading zeros required).
     */
    public ProofOfWork(Block block, int difficulty) {
        this.block = block;         // Set the block to be mined
        this.difficulty = difficulty; // Set the mining difficulty
    }

    // Overloaded constructor that initializes the ProofOfWork with a Blockchain.
    public ProofOfWork(Blockchain blockchain, int difficulty) {
        // Placeholder for future implementation.
        // This constructor could initialize a ProofOfWork instance with the latest block from the blockchain.
    }

    /**
     * Mines the block by finding a nonce that produces a hash
     * matching the difficulty target.
     *
     * @return The hash of the mined block.
     */
    public String mineBlock() {
        String target = getLeadingZeros(difficulty); // Generate the target hash prefix
        String hash;           // Hash to be calculated
        int nonce = 0;        // Nonce value to be varied

        // Try different nonces until the hash matches the target
        do {
            // Calculate the hash using the current nonce
            hash = Block.calculateHash(block.getIndex(), block.getPreviousHash(), block.getTransactions(), block.getTimestamp() + nonce);
            nonce++; // Increment the nonce for the next iteration
        } while (!hash.startsWith(target)); // Check if hash starts with the required leading zeros

        System.out.println("Block mined with nonce: " + nonce + ", Hash: " + hash);
        return hash; // Return the successful hash
    }

    /**
     * Helper method to generate a string of leading zeros based on the difficulty level.
     *
     * @param n The number of leading zeros to generate.
     * @return A string containing n leading zeros.
     */
    private String getLeadingZeros(int n) {
        return "0".repeat(n); // Generate a string of 'n' leading zeros
    }
}
