#ifndef DOUBLY_LINKED_LIST_H
#define DOUBLY_LINKED_LIST_H

#include <cstddef>

class DoublyLinkedList {
public:
    DoublyLinkedList();
    ~DoublyLinkedList();

    void insertFront(int data);
    void insertBack(int data);
    void removeFront();
    void removeBack();
    bool isEmpty() const;
    void display() const;

private:
    struct Node {
        int data;
        Node* prev;
        Node* next;
        Node(int val) : data(val), prev(nullptr), next(nullptr) {}
    };

    Node* head;
    Node* tail;
};

#endif // DOUBLY_LINKED_LIST_H
