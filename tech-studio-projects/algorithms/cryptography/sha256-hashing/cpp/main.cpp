#include <iostream>
#include <cstdint>
#include <cstring>
#include <vector>

class SHA256 {
private:
    static const uint32_t K[64];
    static const uint32_t H[8];

    static uint32_t rightRotate(uint32_t x, int n) {
        return (x >> n) | (x << (32 - n));
    }

    static std::vector<uint32_t> sha256(const uint8_t* message, size_t length) {
        std::vector<uint32_t> words(64, 0);
        size_t ml = length * 8;

        // Pre-processing: Padding the message
        size_t index = 0;
        for (size_t i = 0; i < length; ++i) {
            words[index >> 2] |= static_cast<uint32_t>(message[i]) << (24 - (index % 4) * 8);
            index++;
        }
        words[index >> 2] |= 0x80 << (24 - (index % 4) * 8);
        index++;

        // Append the message length as a 64-bit big-endian integer
        size_t offset = words.size() - 2;
        words[offset] = ml >> 32;
        words[offset + 1] = ml;

        // Process the message in 512-bit chunks
        for (size_t chunkStart = 0; chunkStart < words.size(); chunkStart += 16) {
            std::vector<uint32_t> chunk(words.begin() + chunkStart, words.begin() + chunkStart + 16);

            // Extend the 16 32-bit words into 64 32-bit words
            for (size_t i = 16; i < 64; i++) {
                uint32_t s0 = rightRotate(chunk[i - 15], 7) ^ rightRotate(chunk[i - 15], 18) ^ (chunk[i - 15] >> 3);
                uint32_t s1 = rightRotate(chunk[i - 2], 17) ^ rightRotate(chunk[i - 2], 19) ^ (chunk[i - 2] >> 10);
                chunk.push_back(chunk[i - 16] + s0 + chunk[i - 7] + s1);
            }

            std::vector<uint32_t> hash(H, H + 8);

            // Main loop
            for (size_t i = 0; i < 64; i++) {
                uint32_t S1 = rightRotate(hash[4], 6) ^ rightRotate(hash[4], 11) ^ rightRotate(hash[4], 25);
                uint32_t ch = (hash[4] & hash[5]) ^ (~hash[4] & hash[6]);
                uint32_t temp1 = hash[7] + S1 + ch + K[i] + chunk[i];
                uint32_t S0 = rightRotate(hash[0], 2) ^ rightRotate(hash[0], 13) ^ rightRotate(hash[0], 22);
                uint32_t maj = (hash[0] & hash[1]) ^ (hash[0] & hash[2]) ^ (hash[1] & hash[2]);
                uint32_t temp2 = S0 + maj;

                hash[7] = hash[6];
                hash[6] = hash[5];
                hash[5] = hash[4];
                hash[4] = hash[3] + temp1;
                hash[3] = hash[2];
                hash[2] = hash[1];
                hash[1] = hash[0];
                hash[0] = temp1 + temp2;
            }

            // Add the compressed chunk to the current hash value
            for (size_t i = 0; i < 8; i++) {
                H[i] += hash[i];
            }
        }

        return H;
    }

public:
    static std::string sha256(const uint8_t* message, size_t length) {
        std::vector<uint32_t> result = sha256(message, length);
        std::string hash;
        for (uint32_t val : result) {
            char buffer[9];
            snprintf(buffer, sizeof(buffer), "%08x", val);
            hash += buffer;
        }
        return hash;
    }
};

const uint32_t SHA256::K[64] = {
    0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5, 0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5,
    0xd807aa98, 0x12835b01, 0x243185be, 0x550c7
