#include "Trie.h"

Trie::Trie() {
    root = new TrieNode();
}

void Trie::insert(std::string word) {
    TrieNode* node = root;
    for (char c : word) {
        if (node->children.find(c) == node->children.end()) {
            node->children[c] = new TrieNode();
        }
        node = node->children[c];
    }
    node->is_end_of_word = true;
}

bool Trie::search(std::string word) {
    TrieNode* node = root;
    for (char c : word) {
        if (node->children.find(c) == node->children.end()) {
            return false;
        }
        node = node->children[c];
    }
    return node->is_end_of_word;
}

bool Trie::starts_with(std::string prefix) {
    TrieNode* node = root;
    for (char c : prefix) {
        if (node->children.find(c) == node->children.end()) {
            return false;
        }
        node = node->children[c];
    }
    return true;
}
