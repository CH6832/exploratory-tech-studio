#ifndef RSA_H
#define RSA_H

#include <openssl/rsa.h>
#include <openssl/pem.h>
#include <openssl/err.h>
#include <string>

class RSAKey {
public:
    RSAKey();
    ~RSAKey();

    // Generate a new RSA key pair
    bool generateKeyPair(int bits = 2048);

    // Save key pair to files
    bool savePublicKey(const std::string& filename) const;
    bool savePrivateKey(const std::string& filename) const;

    // Load key pair from files
    bool loadPublicKey(const std::string& filename);
    bool loadPrivateKey(const std::string& filename);

    // Encrypt and decrypt data
    std::string encrypt(const std::string& data) const;
    std::string decrypt(const std::string& data) const;

private:
    RSA* rsa;
    EVP_PKEY* evp_key;

    bool initialize();
    void cleanup();
};

#endif // RSA_H
