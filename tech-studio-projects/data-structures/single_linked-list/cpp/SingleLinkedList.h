#ifndef LINKED_LIST_H
#define LINKED_LIST_H

#include <iostream>

/**
 * @brief Node class representing a node in the linked list.
 */
class Node {
public:
    int data;
    Node* next;

    Node(int value);
};

/**
 * @brief LinkedList class representing a single linked list.
 */
class LinkedList {
private:
    Node* head;

public:
    LinkedList();
    ~LinkedList();

    void insert(int value); // Insert a value into the linked list
    bool remove(int value); // Remove a value from the linked list
    bool contains(int value) const; // Check if the linked list contains a value
    void display() const; // Display the elements of the linked list
};

#endif // LINKED_LIST_H
