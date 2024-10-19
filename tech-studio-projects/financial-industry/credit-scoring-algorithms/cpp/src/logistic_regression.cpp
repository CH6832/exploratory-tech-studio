#include "logistic_regression.h"
#include <cmath>
#include <boost/log/trivial.hpp>

LogisticRegression::LogisticRegression(double lr, int iterations) : learning_rate(lr), num_iterations(iterations) {}

double LogisticRegression::sigmoid(double z) {
    return 1.0 / (1.0 + exp(-z));
}

void LogisticRegression::fit(const std::vector<std::vector<double>>& X, const std::vector<int>& y) {
    int m = X.size();
    int n = X[0].size();
    
    weights.resize(n, 0);
    bias = 0;
    
    for (int i = 0; i < num_iterations; ++i) {
        std::vector<double> dw(n, 0);
        double db = 0;
        double cost = 0;
        
        for (int j = 0; j < m; ++j) {
            double linear_model = bias;
            for (int k = 0; k < n; ++k) {
                linear_model += weights[k] * X[j][k];
            }
            double y_hat = sigmoid(linear_model);
            double error = y_hat - y[j];
            
            for (int k = 0; k < n; ++k) {
                dw[k] += error * X[j][k];
            }
            db += error;
            cost += -y[j] * log(y_hat) - (1 - y[j]) * log(1 - y_hat);
        }
        
        cost /= m;
        
        for (int k = 0; k < n; ++k) {
            weights[k] -= learning_rate * dw[k] / m;
        }
        bias -= learning_rate * db / m;
        
        if (i % 1000 == 0) {
            BOOST_LOG_TRIVIAL(info) << "Iteration " << i << ", Cost: " << cost;
        }
    }
}

std::vector<int> LogisticRegression::predict(const std::vector<std::vector<double>>& X) {
    std::vector<int> predictions;
    for (const auto& sample : X) {
        double linear_model = bias;
        for (size_t i = 0; i < sample.size(); ++i) {
            linear_model += weights[i] * sample[i];
        }
        predictions.push_back(sigmoid(linear_model) >= 0.5 ? 1 : 0);
    }
    return predictions;
}
