#ifndef LOCK_FREE_LINKED_LIST_H
#define LOCK_FREE_LINKED_LIST_H

#include <atomic>

/**
 * @brief Node class representing a node in the lock-free linked list.
 */
class Node {
public:
    int data;
    std::atomic<Node*> next;

    Node(int value);
};

/**
 * @brief LockFreeLinkedList class representing a lock-free linked list.
 */
class LockFreeLinkedList {
private:
    std::atomic<Node*> head;

public:
    LockFreeLinkedList();
    ~LockFreeLinkedList();

    void insert(int value); // Insert a value into the linked list
    bool remove(int value); // Remove a value from the linked list
    bool contains(int value) const; // Check if the linked list contains a value
    void display() const; // Display the elements of the linked list
};

#endif // LOCK_FREE_LINKED_LIST_H
