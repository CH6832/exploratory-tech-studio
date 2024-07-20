#ifndef BAYESIAN_INFERENCE_H
#define BAYESIAN_INFERENCE_H

#include <map>
#include <string>

/**
 * Perform Bayesian inference to update priors based on likelihoods.
 *
 * @param priors A map of hypothesis names to their prior probabilities.
 * @param likelihoods A map of hypothesis names to their likelihoods.
 * @return A map of hypothesis names to their updated probabilities.
 */
std::map<std::string, double> bayesianInference(const std::map<std::string, double> &priors, const std::map<std::string, double> &likelihoods);

#endif
