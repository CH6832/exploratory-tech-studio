#include <iostream>
#include <vector>
#include <algorithm>

class SortingAlgorithm {
public:
    virtual std::vector<int> sort(std::vector<int>& data) = 0;

    std::vector<int> templateMethod(std::vector<int>& data) {
        preprocess(data);
        std::vector<int> sortedData = sort(data);
        postprocess(sortedData);
        return sortedData;
    }

    virtual ~SortingAlgorithm() {}

private:
    void preprocess(std::vector<int>& data) {
        std::cout << "Preprocessing data..." << std::endl;
    }

    void postprocess(std::vector<int>& sortedData) {
        std::cout << "Postprocessing sorted data..." << std::endl;
    }
};

class BubbleSort : public SortingAlgorithm {
public:
    std::vector<int> sort(std::vector<int>& data) override {
        std::cout << "Bubble sorting..." << std::endl;
        std::sort(data.begin(), data.end());
        return data;
    }
};

class QuickSort : public SortingAlgorithm {
public:
    std::vector<int> sort(std::vector<int>& data) override {
        std::cout << "Quick sorting..." << std::endl;
        std::sort(data.begin(), data.end());
        return data;
    }
};

int main() {
    std::vector<int> data = {5, 2, 7, 1, 9};

    BubbleSort bubbleSort;
    std::vector<int> sortedData = bubbleSort.templateMethod(data);
    std::cout << "Sorted data using Bubble Sort:";
    for (int num : sortedData) {
        std::cout << " " << num;
    }
    std::cout << std::endl;

    QuickSort quickSort;
    sortedData = quickSort.templateMethod(data);
    std::cout << "Sorted data using Quick Sort:";
    for (int num : sortedData) {
        std::cout << " " << num;
    }
    std::cout << std::endl;

    return 0;
}
