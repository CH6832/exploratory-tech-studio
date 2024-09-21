#include "DoubleLinkedList.h"
#include <iostream>

DoublyLinkedList::DoublyLinkedList() : head(nullptr), tail(nullptr) {}

DoublyLinkedList::~DoublyLinkedList() {
    while (!isEmpty()) {
        removeFront();
    }
}

void DoublyLinkedList::insertFront(int data) {
    Node* newNode = new Node(data);
    if (isEmpty()) {
        head = tail = newNode;
    }
    else {
        newNode->next = head;
        head->prev = newNode;
        head = newNode;
    }
}

void DoublyLinkedList::insertBack(int data) {
    Node* newNode = new Node(data);
    if (isEmpty()) {
        head = tail = newNode;
    }
    else {
        tail->next = newNode;
        newNode->prev = tail;
        tail = newNode;
    }
}

void DoublyLinkedList::removeFront() {
    if (isEmpty()) {
        return;
    }
    Node* temp = head;
    if (head == tail) {
        head = tail = nullptr;
    }
    else {
        head = head->next;
        head->prev = nullptr;
    }
    delete temp;
}

void DoublyLinkedList::removeBack() {
    if (isEmpty()) {
        return;
    }
    Node* temp = tail;
    if (head == tail) {
        head = tail = nullptr;
    }
    else {
        tail = tail->prev;
        tail->next = nullptr;
    }
    delete temp;
}

bool DoublyLinkedList::isEmpty() const {
    return head == nullptr;
}

void DoublyLinkedList::display() const {
    Node* current = head;
    while (current != nullptr) {
        std::cout << current->data << " ";
        current = current->next;
    }
    std::cout << std::endl;
}
