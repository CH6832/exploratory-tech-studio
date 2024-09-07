
### logistic_regression.h

```cpp
#ifndef LOGISTIC_REGRESSION_H
#define LOGISTIC_REGRESSION_H

#include <vector>
#include <string>

class LogisticRegression {
public:
    LogisticRegression(double lr = 0.01, int iterations = 10000);
    void fit(const std::vector<std::vector<double>>& X, const std::vector<int>& y);
    std::vector<int> predict(const std::vector<std::vector<double>>& X);
    
private:
    double sigmoid(double z);
    double learning_rate;
    int num_iterations;
    std::vector<double> weights;
    double bias;
};

#endif // LOGISTIC_REGRESSION_H
