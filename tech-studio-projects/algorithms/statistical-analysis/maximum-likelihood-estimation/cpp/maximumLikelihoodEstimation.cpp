#include "maximum_likelihood_estimation.h"
#include <cmath>
#include <algorithm>
#include <limits>

/**
 * Compute the likelihood function for the given parameters and data.
 *
 * @param params The parameters of the statistical model (mean and standard deviation).
 * @param data The observed data.
 * @return The negative likelihood value (to be minimized).
 */
double likelihood_function(const std::pair<double, double>& params, const std::vector<double>& data) {
    double mu = params.first;
    double sigma = params.second;
    double likelihood = 0.0;
    for (double d : data) {
        likelihood += std::log(1 / (std::sqrt(2 * M_PI) * sigma) * std::exp(-0.5 * std::pow((d - mu) / sigma, 2)));
    }
    return -likelihood; // Minimize negative likelihood (equivalent to maximizing likelihood)
}

/**
 * Perform maximum likelihood estimation to estimate the parameters of the statistical model.
 *
 * @param data The observed data.
 * @return The estimated parameters of the statistical model (mean and standard deviation).
 */
std::pair<double, double> maximum_likelihood_estimation(const std::vector<double>& data) {
    std::pair<double, double> initial_guess = {0.0, 1.0};
    std::pair<double, double> best_params = initial_guess;
    double best_likelihood = std::numeric_limits<double>::infinity();
    double step = 0.1;
    for (double mu = -10.0; mu <= 10.0; mu += step) {
        for (double sigma = 0.1; sigma <= 10.0; sigma += step) {
            std::pair<double, double> params = {mu, sigma};
            double likelihood = likelihood_function(params, data);
            if (likelihood < best_likelihood) {
                best_likelihood = likelihood;
                best_params = params;
            }
        }
    }
    return best_params;
}
