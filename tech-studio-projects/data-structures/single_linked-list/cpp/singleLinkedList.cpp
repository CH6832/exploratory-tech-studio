#include "SingleLInkedList.h"

Node::Node(int value) : data(value), next(nullptr) {}

LinkedList::LinkedList() : head(nullptr) {}

LinkedList::~LinkedList() {
    Node* curr = head;
    while (curr != nullptr) {
        Node* next = curr->next;
        delete curr;
        curr = next;
    }
}

void LinkedList::insert(int value) {
    Node* newNode = new Node(value);
    newNode->next = head;
    head = newNode;
}

bool LinkedList::remove(int value) {
    if (head == nullptr) return false; // Empty list
    if (head->data == value) { // If the value to remove is in the head
        Node* temp = head;
        head = head->next;
        delete temp;
        return true;
    }
    Node* curr = head;
    while (curr->next != nullptr && curr->next->data != value) {
        curr = curr->next;
    }
    if (curr->next == nullptr) return false; // Value not found
    Node* temp = curr->next;
    curr->next = curr->next->next;
    delete temp;
    return true;
}

bool LinkedList::contains(int value) const {
    Node* curr = head;
    while (curr != nullptr) {
        if (curr->data == value) return true;
        curr = curr->next;
    }
    return false;
}

void LinkedList::display() const {
    Node* curr = head;
    while (curr != nullptr) {
        std::cout << curr->data << " ";
        curr = curr->next;
    }
    std::cout << std::endl;
}
