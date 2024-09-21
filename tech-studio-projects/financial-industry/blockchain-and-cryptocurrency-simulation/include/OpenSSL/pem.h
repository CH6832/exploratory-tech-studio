#ifndef PEM_H
#define PEM_H

#include <openssl/pem.h>
#include <string>

// Helper functions for PEM file handling
bool savePemFile(const std::string& filename, EVP_PKEY* key);
EVP_PKEY* loadPemFile(const std::string& filename);

#endif // PEM_H
