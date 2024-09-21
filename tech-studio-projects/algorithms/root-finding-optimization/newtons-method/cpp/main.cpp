#include <iostream>
#include <functional>

struct Result {
    double root;
    int iterations;
};

Result newtonsMethod(std::function<double(double)> f, std::function<double(double)> df, double x0, double tol, int maxItr) {
    double x = x0;
    double delta_x;
    for (int i = 0; i < maxItr; i++) {
        delta_x = -f(x) / df(x);
        x += delta_x;
        if (std::abs(delta_x) < tol) {
            return {x, i + 1};
        }
    }
    return {x, maxItr};
}

int main() {
    // Example: Find a root of a function using Newton's method
    auto f = [](double x) { return x * x - 4; };
    auto df = [](double x) { return 2 * x; };
    double x0 = 2.0;
    double tol = 1e-6;
    int maxItr = 1000;

    Result result = newtonsMethod(f, df, x0, tol, maxItr);
    std::cout << "Root: " << result.root << std::endl;
    std::cout << "Number of iterations: " << result.iterations << std::endl;

    return 0;
}
