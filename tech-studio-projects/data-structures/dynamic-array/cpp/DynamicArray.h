#ifndef DYNAMIC_ARRAY_LIST_H
#define DYNAMIC_ARRAY_LIST_H

#include <cstddef>

class DynamicArrayList {
public:
    DynamicArrayList();
    ~DynamicArrayList();

    void append(int data);
    void insert(int index, int data);
    void remove(int index);
    int get(int index) const;
    std::size_t size() const;
    bool isEmpty() const;

private:
    int* array;
    std::size_t capacity;
    std::size_t length;

    void resize(std::size_t newCapacity);
};

#endif // DYNAMIC_ARRAY_LIST_H
