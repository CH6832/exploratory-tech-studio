#include <iostream>
#include <random>

/**
 * MonteCarlo.cpp
 *
 * The Monte Carlo method is a statistical technique used to estimate numerical results by random sampling.
 * In this implementation, we use the Monte Carlo method to estimate the value of pi by simulating random points
 * within a unit square and determining the ratio of points that fall within the unit circle.
 *
 * Algorithm: We generate numSamples random points uniformly distributed within the unit square [-1, 1] x [-1, 1].
 * For each point, we calculate its distance from the origin and count how many points fall within the unit circle
 * (i.e., have a distance <= 1 from the origin). The ratio of points inside the circle to the total number of points
 * approximates the ratio of the area of the unit circle to the area of the unit square, which is π/4.
 *
 * Implementation: The monteCarloPi function takes the number of samples numSamples as input and iterates through the
 * algorithm to estimate the value of pi using the Monte Carlo method. It returns the estimated value of pi.
 *
 * Example Usage: In the main function, an example usage of the monteCarloPi function is provided. We specify the
 * number of samples, call the function to estimate pi, and then print the result. Increasing the number of samples
 * generally leads to a more accurate estimate of pi.
 */

/**
 * Estimate the value of pi using the Monte Carlo method.
 *
 * @param numSamples Number of random samples to generate.
 * @return Estimated value of pi.
 */
double monteCarloPi(int numSamples) {
    int numPointsInsideCircle = 0;
    std::random_device rd;
    std::mt19937 gen(rd());
    std::uniform_real_distribution<> dis(-1.0, 1.0);

    for (int i = 0; i < numSamples; i++) {
        double x = dis(gen);
        double y = dis(gen);
        double distanceSquared = x * x + y * y;

        if (distanceSquared <= 1) {
            numPointsInsideCircle++;
        }
    }

    return 4.0 * numPointsInsideCircle / numSamples;
}

int main() {
    // Example: Monte Carlo Pi Estimation
    int numSamples = 1000000;
    double piEstimate = monteCarloPi(numSamples);
    std::cout << "Estimated value of pi using " << numSamples << " samples: " << piEstimate << std::endl;
    return 0;
}
