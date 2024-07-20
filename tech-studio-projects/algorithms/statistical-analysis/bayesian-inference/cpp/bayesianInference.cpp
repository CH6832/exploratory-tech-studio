#include "bayesianInference.h"

/**
 * Perform Bayesian inference to update priors based on likelihoods.
 *
 * @param priors A map of hypothesis names to their prior probabilities.
 * @param likelihoods A map of hypothesis names to their likelihoods.
 * @return A map of hypothesis names to their updated probabilities.
 */
std::map<std::string, double> bayesianInference(const std::map<std::string, double> &priors, const std::map<std::string, double> &likelihoods) {
    std::map<std::string, double> updatedProbs;

    // Compute the denominator (evidence) by summing the products of priors and likelihoods
    double evidence = 0.0;
    for (const auto &pair : priors) {
        const std::string &hypothesis = pair.first;
        evidence += pair.second * likelihoods.at(hypothesis);
    }

    // Update the probabilities of each hypothesis using Bayes' theorem
    for (const auto &pair : priors) {
        const std::string &hypothesis = pair.first;
        double prior = pair.second;
        double likelihood = likelihoods.at(hypothesis);
        updatedProbs[hypothesis] = (prior * likelihood) / evidence;
    }

    return updatedProbs;
}
