#include <iostream>
#include <vector>
#include <algorithm>

class SortingStrategy {
public:
    virtual std::vector<int> sort(const std::vector<int>& data) const = 0;
    virtual ~SortingStrategy() {}
};

class BubbleSortStrategy : public SortingStrategy {
public:
    std::vector<int> sort(const std::vector<int>& data) const override {
        std::cout << "Bubble sorting..." << std::endl;
        std::vector<int> sortedData = data;
        std::sort(sortedData.begin(), sortedData.end());
        return sortedData;
    }
};

class QuickSortStrategy : public SortingStrategy {
public:
    std::vector<int> sort(const std::vector<int>& data) const override {
        std::cout << "Quick sorting..." << std::endl;
        std::vector<int> sortedData = data;
        std::sort(sortedData.begin(), sortedData.end());
        return sortedData;
    }
};

class Sorter {
private:
    SortingStrategy* strategy;

public:
    Sorter(SortingStrategy* strategy) : strategy(strategy) {}

    void setStrategy(SortingStrategy* strategy) {
        this->strategy = strategy;
    }

    std::vector<int> executeSort(const std::vector<int>& data) const {
        return strategy->sort(data);
    }
};

int main() {
    std::vector<int> data = {5, 2, 7, 1, 9};

    BubbleSortStrategy bubbleSortStrategy;
    Sorter sorter(&bubbleSortStrategy);
    auto sortedData = sorter.executeSort(data);
    std::cout << "Sorted data (Bubble Sort): ";
    for (const auto& num : sortedData) {
        std::cout << num << " ";
    }
    std::cout << std::endl;

    QuickSortStrategy quickSortStrategy;
    sorter.setStrategy(&quickSortStrategy);
    sortedData = sorter.executeSort(data);
    std::cout << "Sorted data (Quick Sort): ";
    for (const auto& num : sortedData) {
        std::cout << num << " ";
    }
    std::cout << std::endl;

    return 0;
}
