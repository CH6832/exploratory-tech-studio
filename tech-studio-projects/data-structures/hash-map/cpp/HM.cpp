#include "HM.h"

HashMapList::HashMapList() {
    for (std::size_t i = 0; i < TABLE_SIZE; ++i) {
        table[i] = nullptr;
    }
}

HashMapList::~HashMapList() {
    for (std::size_t i = 0; i < TABLE_SIZE; ++i) {
        Node* current = table[i];
        while (current) {
            Node* next = current->next;
            delete current;
            current = next;
        }
    }
}

std::size_t HashMapList::hash(int key) const {
    return key % TABLE_SIZE;
}

void HashMapList::insert(int key, int value) {
    std::size_t index = hash(key);
    Node* newNode = new Node(key, value);
    newNode->next = table[index];
    table[index] = newNode;
}

int HashMapList::get(int key) const {
    std::size_t index = hash(key);
    Node* current = table[index];
    while (current) {
        if (current->key == key) {
            return current->value;
        }
        current = current->next;
    }
    return -1; // Not found
}

bool HashMapList::remove(int key) {
    std::size_t index = hash(key);
    Node* current = table[index];
    Node* prev = nullptr;
    while (current) {
        if (current->key == key) {
            if (prev) {
                prev->next = current->next;
            }
            else {
                table[index] = current->next;
            }
            delete current;
            return true;
        }
        prev = current;
        current = current->next;
    }
    return false; // Not found
}

void HashMapList::display() const {
    for (std::size_t i = 0; i < TABLE_SIZE; ++i) {
        std::cout << "[" << i << "]: ";
        Node* current = table[i];
        while (current) {
            std::cout << "(" << current->key << ", " << current->value << ") ";
            current = current->next;
        }
        std::cout << std::endl;
    }
}
