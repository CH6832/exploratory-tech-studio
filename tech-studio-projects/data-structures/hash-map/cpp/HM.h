#ifndef HASH_MAP_LIST_H
#define HASH_MAP_LIST_H

#include <cstddef>
#include <iostream>

class HashMapList {
private:
    struct Node {
        int key;
        int value;
        Node* next;

        Node(int k, int v) : key(k), value(v), next(nullptr) {}
    };

    static const std::size_t TABLE_SIZE = 10;
    Node* table[TABLE_SIZE];

    std::size_t hash(int key) const;

public:
    HashMapList();
    ~HashMapList();

    void insert(int key, int value);
    int get(int key) const;
    bool remove(int key);
    void display() const;
};

#endif // HASH_MAP_LIST_H
