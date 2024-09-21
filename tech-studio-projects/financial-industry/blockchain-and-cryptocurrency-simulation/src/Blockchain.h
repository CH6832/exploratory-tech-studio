#ifndef BLOCKCHAIN_H
#define BLOCKCHAIN_H

#include "Block.h"
#include <vector>
#include <string>

class Blockchain {
public:
    Blockchain();
    void addBlock(const std::vector<std::string>& transactions);
    bool isChainValid() const;
    Block getLatestBlock() const;

private:
    std::vector<Block> chain;
    Block createGenesisBlock();
};

#endif // BLOCKCHAIN_H