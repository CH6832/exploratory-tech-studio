#include <iostream>
#include <vector>
#include "maximum_likelihood_estimation.h"

/**
 * Main function to demonstrate maximum likelihood estimation.
 */
int main() {
    // Example: Estimate parameters using maximum likelihood estimation
    std::vector<double> observed_data = {1.2, 1.5, 2.0, 2.2, 2.8};
    auto estimated_params = maximum_likelihood_estimation(observed_data);
    std::cout << "Estimated parameters (mean, standard deviation): " << estimated_params.first << ", " << estimated_params.second << std::endl;
    return 0;
}
