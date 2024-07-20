#ifndef TRIE_NODE_H
#define TRIE_NODE_H

#include <unordered_map>

class TrieNode {
public:
    std::unordered_map<char, TrieNode*> children;
    bool is_end_of_word;

    TrieNode();
};

#endif // TRIE_NODE_H
