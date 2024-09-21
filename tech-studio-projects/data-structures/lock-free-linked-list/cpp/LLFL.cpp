#include "LLFL.h"
#include <iostream>

Node::Node(int value) : data(value), next(nullptr) {}

LockFreeLinkedList::LockFreeLinkedList() : head(nullptr) {}

LockFreeLinkedList::~LockFreeLinkedList() {
    Node* curr = head.load();
    while (curr != nullptr) {
        Node* next = curr->next.load();
        delete curr;
        curr = next;
    }
}

void LockFreeLinkedList::insert(int value) {
    Node* newNode = new Node(value);
    newNode->next = head.load();
    while (!head.compare_exchange_weak(newNode->next, newNode));
}

bool LockFreeLinkedList::remove(int value) {
    Node* curr = head.load();
    Node* prev = nullptr;
    while (curr != nullptr && curr->data != value) {
        prev = curr;
        curr = curr->next.load();
    }
    if (curr == nullptr) return false; // Value not found
    if (prev == nullptr) {
        head = curr->next.load();
    }
    else {
        prev->next = curr->next.load();
    }
    delete curr;
    return true;
}

bool LockFreeLinkedList::contains(int value) const {
    Node* curr = head.load();
    while (curr != nullptr) {
        if (curr->data == value) return true;
        curr = curr->next.load();
    }
    return false;
}

void LockFreeLinkedList::display() const {
    Node* curr = head.load();
    while (curr != nullptr) {
        std::cout << curr->data << " ";
        curr = curr->next.load();
    }
    std::cout << std::endl;
}
