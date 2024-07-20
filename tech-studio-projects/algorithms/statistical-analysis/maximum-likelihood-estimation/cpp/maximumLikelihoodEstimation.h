#ifndef MAXIMUM_LIKELIHOOD_ESTIMATION_H
#define MAXIMUM_LIKELIHOOD_ESTIMATION_H

#include <vector>
#include <utility>

/**
 * Compute the likelihood function for the given parameters and data.
 *
 * @param params The parameters of the statistical model (mean and standard deviation).
 * @param data The observed data.
 * @return The negative likelihood value (to be minimized).
 */
double likelihood_function(const std::pair<double, double>& params, const std::vector<double>& data);

/**
 * Perform maximum likelihood estimation to estimate the parameters of the statistical model.
 *
 * @param data The observed data.
 * @return The estimated parameters of the statistical model (mean and standard deviation).
 */
std::pair<double, double> maximum_likelihood_estimation(const std::vector<double>& data);

#endif // MAXIMUM_LIKELIHOOD_ESTIMATION_H
