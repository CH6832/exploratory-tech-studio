#ifndef SKIPLIST_H
#define SKIPLIST_H

#include <iostream>
#include <vector>
#include <random>

// Forward declaration of SkipListNode
template<typename T>
class SkipListNode;

/**
 * @brief SkipList class representing a skip list data structure.
 */
template<typename T>
class SkipList {
private:
    int maxLevel;
    int currentLevel;
    SkipListNode<T>* head;

public:
    SkipList(int maxLevel);
    ~SkipList();

    void insert(const T& value);
    bool remove(const T& value);
    bool contains(const T& value) const;
    void display() const;
};

/**
 * @brief SkipListNode class representing a node in the skip list.
 */
template<typename T>
class SkipListNode {
public:
    T value;
    std::vector<SkipListNode<T>*> next;

    SkipListNode(const T& value, int level);
};

#endif // SKIPLIST_H
