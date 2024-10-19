package com.example.bandcsimu;

public class ProofOfWork {
    private Block block;
    private int difficulty;

    // Constructor
    public ProofOfWork(Block block, int difficulty) {
        this.block = block;
        this.difficulty = difficulty;
    }

    public ProofOfWork(Blockchain blockchain, int difficulty2) {
		// TODO Auto-generated constructor stub
	}

	// Method to mine the block
    public String mineBlock() {
        String target = getLeadingZeros(difficulty);
        String hash = "";
        int nonce = 0;

        // Try different nonces until the hash matches the target (starts with a number of leading zeros)
        do {
            hash = Block.calculateHash(block.getIndex(), block.getPreviousHash(), block.getTransactions(), block.getTimestamp() + nonce);
            nonce++;
        } while (!hash.startsWith(target)); // Check if hash starts with the target (leading zeros)

        System.out.println("Block mined with nonce: " + nonce + ", Hash: " + hash);
        return hash;
    }

    // Helper method to generate a string of leading zeros based on the difficulty
    private String getLeadingZeros(int n) {
        return "0".repeat(n);
    }
}
