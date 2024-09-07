#ifndef SHA_H
#define SHA_H

#include <openssl/sha.h>
#include <string>

class SHA256 {
public:
    SHA256();
    std::string hash(const std::string& data);

private:
    unsigned char digest[SHA256_DIGEST_LENGTH];
};

#endif // SHA_H
