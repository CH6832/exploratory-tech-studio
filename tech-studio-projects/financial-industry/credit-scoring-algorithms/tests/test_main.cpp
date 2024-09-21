#include <iostream>
#include <vector>
#include <cassert>
#include "../src/logistic_regression.h"

void test_sigmoid() {
    LogisticRegression lr;
    assert(lr.sigmoid(0) == 0.5);
    assert(lr.sigmoid(2.197) > 0.9 && lr.sigmoid(2.197) < 1.0);
    assert(lr.sigmoid(-2.197) < 0.1 && lr.sigmoid(-2.197) > 0.0);
}

void test_predict() {
    LogisticRegression lr;
    lr.fit({{1.0, 2.0}, {2.0, 1.0}, {2.5, 1.5}, {3.0, 2.0}, {1.5, 2.5}},
           {0, 0, 1, 1, 0});
    std::vector<int> predictions = lr.predict({{1.0, 2.0}, {2.0, 1.0}, {3.0, 2.0}});
    assert(predictions == std::vector<int>({0, 0, 1}));
}

void test_fit() {
    LogisticRegression lr;
    lr.fit({{1.0, 2.0}, {2.0, 1.0}, {2.5, 1.5}, {3.0, 2.0}, {1.5, 2.5}},
           {0, 0, 1, 1, 0});
    std::vector<int> predictions = lr.predict({{1.0, 2.0}, {2.0, 1.0}, {3.0, 2.0}});
    assert(predictions == std::vector<int>({0, 0, 1}));
}

void test_read_csv() {
    std::vector<std::vector<double>> data;
    std::vector<int> labels;
    read_csv("../tests/test_data/train_data.csv", data, labels);
    assert(data.size() == 50);
    assert(data[0].size() == 2);
    assert(labels.size() == 50);
}

void test_read_csv_no_labels() {
    std::vector<std::vector<double>> data;
    read_csv_no_labels("../tests/test_data/test_data.csv", data);
    assert(data.size() == 5);
    assert(data[0].size() == 2);
}

int main() {
    test_sigmoid();
    test_fit();
    test_predict();
    test_read_csv();
    test_read_csv_no_labels();

    std::cout << "All tests passed!" << std::endl;

    return 0;
}
