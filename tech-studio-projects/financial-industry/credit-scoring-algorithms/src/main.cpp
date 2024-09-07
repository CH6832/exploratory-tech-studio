#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>
#include <boost/program_options.hpp>
#include <boost/log/trivial.hpp>
#include "logistic_regression.h"

namespace po = boost::program_options;

void read_csv(const std::string& file_path, std::vector<std::vector<double>>& data, std::vector<int>& labels) {
    std::ifstream file(file_path);
    std::string line, cell;
    
    while (std::getline(file, line)) {
        std::stringstream line_stream(line);
        std::vector<double> row;
        while (std::getline(line_stream, cell, ',')) {
            row.push_back(std::stod(cell));
        }
        labels.push_back(static_cast<int>(row.back()));
        row.pop_back();
        data.push_back(row);
    }
}

void read_csv_no_labels(const std::string& file_path, std::vector<std::vector<double>>& data) {
    std::ifstream file(file_path);
    std::string line, cell;
    
    while (std::getline(file, line)) {
        std::stringstream line_stream(line);
        std::vector<double> row;
        while (std::getline(line_stream, cell, ',')) {
            row.push_back(std::stod(cell));
        }
        data.push_back(row);
    }
}

int main(int argc, char* argv[]) {
    std::string train_file;
    std::string test_file;
    double learning_rate;
    int iterations;
    
    po::options_description desc("Allowed options");
    desc.add_options()
        ("help,h", "produce help message")
        ("train,t", po::value<std::string>(&train_file)->required(), "training data file")
        ("test,e", po::value<std::string>(&test_file)->required(), "testing data file")
        ("learning-rate,l", po::value<double>(&learning_rate)->default_value(0.01), "learning rate")
        ("iterations,i", po::value<int>(&iterations)->default_value(10000), "number of iterations");
    
    po::variables_map vm;
    try {
        po::store(po::parse_command_line(argc, argv, desc), vm);
        if (vm.count("help")) {
            std::cout << desc << "\n";
            return 1;
        }
        po::notify(vm);
    } catch (po::error& e) {
        std::cerr << "Error: " << e.what() << "\n";
        std::cerr << desc << "\n";
        return 1;
    }
    
    std::vector<std::vector<double>> train_data;
    std::vector<int> train_labels;
    read_csv(train_file, train_data, train_labels);
    
    LogisticRegression model(learning_rate, iterations);
    model.fit(train_data, train_labels);
    
    std::vector<std::vector<double>> test_data;
    read_csv_no_labels(test_file, test_data);
    
    std::vector<int> predictions = model.predict(test_data);
    
    std::cout << "Predictions:\n";
    for (int prediction : predictions) {
        std::cout << prediction << "\n";
    }
    
    return 0;
}
