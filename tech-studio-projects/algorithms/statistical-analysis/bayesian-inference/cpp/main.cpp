#include <iostream>
#include <map>
#include "bayesianInference.h"

/**
 * Main function to demonstrate Bayesian inference.
 */
int main() {
    // Example: Perform Bayesian inference with given priors and likelihoods
    std::map<std::string, double> priors = {{"Hypothesis A", 0.3}, {"Hypothesis B", 0.7}};
    std::map<std::string, double> likelihoods = {{"Hypothesis A", 0.8}, {"Hypothesis B", 0.4}};

    std::map<std::string, double> updatedProbs = bayesianInference(priors, likelihoods);
    std::cout << "Updated probabilities after Bayesian inference:" << std::endl;
    for (const auto &pair : updatedProbs) {
        std::cout << pair.first << ": " << pair.second << std::endl;
    }

    return 0;
}
