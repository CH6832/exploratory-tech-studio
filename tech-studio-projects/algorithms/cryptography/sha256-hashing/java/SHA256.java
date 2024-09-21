import java.math.BigInteger;

public class SHA256 {

    private static final int[] K = {
        0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5, 0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5,
        0xd807aa98, 0x12835b01, 0x243185be, 0x550c7dc3, 0x72be5d74, 0x80deb1fe, 0x9bdc06a7, 0xc19bf174,
        0xe49b69c1, 0xefbe4786, 0x0fc19dc6, 0x240ca1cc, 0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da,
        0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7, 0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967,
        0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13, 0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85,
        0xa2bfe8a1, 0xa81a664b, 0xc24b8b70, 0xc76c51a3, 0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070,
        0x19a4c116, 0x1e376c08, 0x2748774c, 0x34b0bcb5, 0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3,
        0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208, 0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2
    };

    private static final int[] H = {
        0x6a09e667, 0xbb67ae85, 0x3c6ef372, 0xa54ff53a, 0x510e527f, 0x9b05688c, 0x1f83d9ab, 0x5be0cd19
    };

    private static int[] sha256(byte[] message) {
        int[] words = new int[64];
        int ml = message.length * 8;

        // Pre-processing: Padding the message
        int index = 0;
        for (byte b : message) {
            words[index >> 2] |= (b & 0xFF) << (24 - (index % 4) * 8);
            index++;
        }
        words[index >> 2] |= 0x80 << (24 - (index % 4) * 8);
        index++;

        // Append the message length as a 64-bit big-endian integer
        int offset = words.length - 2;
        words[offset] = ml >>> 32;
        words[offset + 1] = ml;

        // Process the message in 512-bit chunks
        for (int chunkStart = 0; chunkStart < words.length; chunkStart += 16) {
            int[] chunk = new int[64];
            System.arraycopy(words, chunkStart, chunk, 0, 16);

            // Extend the 16 32-bit words into 64 32-bit words
            for (int i = 16; i < 64; i++) {
                int s0 = Integer.rotateRight(chunk[i - 15], 7) ^ Integer.rotateRight(chunk[i - 15], 18) ^ (chunk[i - 15] >>> 3);
                int s1 = Integer.rotateRight(chunk[i - 2], 17) ^ Integer.rotateRight(chunk[i - 2], 19) ^ (chunk[i - 2] >>> 10);
                chunk[i] = chunk[i - 16] + s0 + chunk[i - 7] + s1;
            }

            int[] hash = new int[H.length];
            System.arraycopy(H, 0, hash, 0, H.length);

            // Main loop
            for (int i = 0; i < 64; i++) {
                int S1 = Integer.rotateRight(hash[4], 6) ^ Integer.rotateRight(hash[4], 11) ^ Integer.rotateRight(hash[4], 25);
                int ch = (hash[4] & hash[5]) ^ (~hash[4] & hash[6]);
                int temp1 = hash[7] + S1 + ch + K[i] + chunk[i];
                int S0 = Integer.rotateRight(hash[0], 2) ^ Integer.rotateRight(hash[0], 13) ^ Integer.rotateRight(hash[0], 22);
                int maj = (hash[0] & hash[1]) ^ (hash[0] & hash[2]) ^ (hash[1] & hash[2]);
                int temp2 = S0 + maj;

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
            for (int i = 0; i < H.length; i++) {
                H[i] += hash[i];
            }
        }

        return H;
    }

    private static String toHexString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int value : array) {
            sb.append(String.format("%08x", value));
        }
        return sb.toString();
    }

    public static String sha256(byte[] message) {
        return toHexString(sha256(message));
    }

    public static void main(String[] args) {
        byte[] message = "Hello, world!".getBytes();
        System.out.println("Message: " + new String(message));
        System.out.println("SHA-256 Hash: " + sha256(message));
    }
}
