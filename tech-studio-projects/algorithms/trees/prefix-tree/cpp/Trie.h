#ifndef TRIE_H
#define TRIE_H

#include "TrieNode.h"
#include <string>

class Trie {
public:
    TrieNode* root;

    Trie();
    void insert(std::string word);
    bool search(std::string word);
    bool starts_with(std::string prefix);
};

#endif // TRIE_H
