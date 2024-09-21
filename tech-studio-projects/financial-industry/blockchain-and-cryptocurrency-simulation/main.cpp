#include "Blockchain.h"
#include "ProofOfWork.h"
#include "ProofOfStake.h"
#include "src/NetworkNode.h"
#include "src/Transaction.h"
#include <iostream>
#include <openssl/rsa.h>
#include <openssl/pem.h>

int main() {
    // Initialize RSA keys
    RSA* keyPair = RSA_generate_key(2048, RSA_F4, nullptr, nullptr);
    BIO* pri = BIO_new(BIO_s_mem());
    BIO* pub = BIO_new(BIO_s_mem());
    PEM_write_bio_RSAPrivateKey(pri, keyPair, nullptr, nullptr, 0, nullptr, nullptr);
    PEM_write_bio_RSAPublicKey(pub, keyPair);

    char* priKey = nullptr;
    char* pubKey = nullptr;
    long priLen = BIO_get_mem_data(pri, &priKey);
    long pubLen = BIO_get_mem_data(pub, &pubKey);

    std::string privateKey(priKey, priLen);
    std::string publicKey(pubKey, pubLen);

    BIO_free_all(pri);
    BIO_free_all(pub);

    Blockchain blockchain;
    ProofOfStake pos;

    // Add some stake
    pos.addStake(publicKey, 100);

    // Create a transaction
    Transaction tx(publicKey, "ReceiverPublicKey", 10, keyPair);
    if (tx.verifySignature()) {
        std::cout << "Transaction verified and added to blockchain\n";
    }

    blockchain.addBlock({tx.getTransactionData()});
    ProofOfWork pow(blockchain.getLatestBlock(), 4);
    std::string hash = pow.mineBlock();
    std::cout << "Block mined: " << hash << "\n";

    std::cout << "Blockchain validity: " << (blockchain.isChainValid() ? "Valid" : "Invalid") << "\n";

    // Initialize network node
    NetworkNode node("127.0.0.1", 8080);
    node.connectToPeer("127.0.0.1", 8081);
    node.broadcastNewBlock(blockchain.getLatestBlock());

    RSA_free(keyPair);

    return 0;
}
