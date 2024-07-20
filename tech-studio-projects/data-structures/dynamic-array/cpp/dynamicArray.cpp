#include "DynamicArray.h"

DynamicArrayList::DynamicArrayList() : array(nullptr), capacity(0), length(0) {}

DynamicArrayList::~DynamicArrayList() {
    delete[] array;
}

void DynamicArrayList::append(int data) {
    if (length == capacity) {
        resize(capacity == 0 ? 1 : capacity * 2);
    }
    array[length++] = data;
}

void DynamicArrayList::insert(int index, int data) {
    if (index < 0 || index > length) {
        return; // Invalid index
    }
    if (length == capacity) {
        resize(capacity == 0 ? 1 : capacity * 2);
    }
    for (std::size_t i = length; i > index; --i) {
        array[i] = array[i - 1];
    }
    array[index] = data;
    ++length;
}

void DynamicArrayList::remove(int index) {
    if (index < 0 || index >= length) {
        return; // Invalid index
    }
    for (std::size_t i = index; i < length - 1; ++i) {
        array[i] = array[i + 1];
    }
    --length;
}

int DynamicArrayList::get(int index) const {
    if (index < 0 || index >= length) {
        return -1; // Invalid index
    }
    return array[index];
}

std::size_t DynamicArrayList::size() const {
    return length;
}

bool DynamicArrayList::isEmpty() const {
    return length == 0;
}

void DynamicArrayList::resize(std::size_t newCapacity) {
    int* newArray = new int[newCapacity];
    for (std::size_t i = 0; i < length; ++i) {
        newArray[i] = array[i];
    }
    delete[] array;
    array = newArray;
    capacity = newCapacity;
}
