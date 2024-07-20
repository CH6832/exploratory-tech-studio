#include "SkipList.h"

template<typename T>
SkipListNode<T>::SkipListNode(const T& value, int level) : value(value), next(level, nullptr) {}

template<typename T>
SkipList<T>::SkipList(int maxLevel) : maxLevel(maxLevel), currentLevel(1) {
    head = new SkipListNode<T>(T(), maxLevel);
}

template<typename T>
SkipList<T>::~SkipList() {
    SkipListNode<T>* curr = head;
    while (curr) {
        SkipListNode<T>* temp = curr->next[0];
        delete curr;
        curr = temp;
    }
}

template<typename T>
void SkipList<T>::insert(const T& value) {
    // Determine the level of the new node
    int level = 1;
    while (std::rand() % 2 == 0 && level < maxLevel) {
        level++;
    }

    // Create a new node
    SkipListNode<T>* newNode = new SkipListNode<T>(value, level);

    // Update the next pointers
    SkipListNode<T>* curr = head;
    for (int i = currentLevel - 1; i >= 0; i--) {
        while (curr->next[i] && curr->next[i]->value < value) {
            curr = curr->next[i];
        }
        if (i < level) {
            newNode->next[i] = curr->next[i];
            curr->next[i] = newNode;
        }
    }

    // Update the current level of the skip list
    if (level > currentLevel) {
        currentLevel = level;
    }
}

template<typename T>
bool SkipList<T>::remove(const T& value) {
    // Find the node to remove
    SkipListNode<T>* curr = head;
    bool found = false;
    for (int i = currentLevel - 1; i >= 0; i--) {
        while (curr->next[i] && curr->next[i]->value < value) {
            curr = curr->next[i];
        }
        if (curr->next[i] && curr->next[i]->value == value) {
            found = true;
            curr->next[i] = curr->next[i]->next[i];
        }
    }

    return found;
}

template<typename T>
bool SkipList<T>::contains(const T& value) const {
    // Search for the value in the skip list
    SkipListNode<T>* curr = head;
    for (int i = currentLevel - 1; i >= 0; i--) {
        while (curr->next[i] && curr->next[i]->value < value) {
            curr = curr->next[i];
        }
        if (curr->next[i] && curr->next[i]->value == value) {
            return true;
        }
    }

    return false;
}

template<typename T>
void SkipList<T>::display() const {
    // Display the elements of the skip list
    SkipListNode<T>* curr = head->next[0];
    while (curr) {
        std::cout << curr->value << " ";
        curr = curr->next[0];
    }
    std::cout << std::endl;
}
