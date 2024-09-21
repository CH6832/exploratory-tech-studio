#include "Blockchain.h"

Blockchain::Blockchain() {
    chain.push_back(createGenesisBlock());
}

Block Blockchain::createGenesisBlock() {
    return Block(0, "0", {"Genesis Block"});
}

void Blockchain::addBlock(const std::vector<std::string>& transactions) {
    Block newBlock(chain.size(), chain.back().getHash(), transactions);
    chain.push_back(newBlock);
}

bool Blockchain::isChainValid() const {
    for (size_t i = 1; i < chain.size(); ++i) {
        const Block& currentBlock = chain[i];
        const Block& previousBlock = chain[i - 1];

        // Check hash of the current block
        if (currentBlock.getHash() != Block::calculateHash(currentBlock.getIndex(), currentBlock.getPreviousHash(), currentBlock.getTransactions(), currentBlock.getTimestamp())) {
            return false;
        }

        // Check the previous block's hash
        if (currentBlock.getPreviousHash() != previousBlock.getHash()) {
            return false;
        }
    }
    return true;
}

Block Blockchain::getLatestBlock() const {
    return chain.back();
}
