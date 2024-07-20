import java.util.Random;

/**
 * MonteCarlo.java
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
 * Implementation: The monteCarloPi method takes the number of samples numSamples as input and iterates through the
 * algorithm to estimate the value of pi using the Monte Carlo method. It returns the estimated value of pi.
 *
 * Example Usage: In the main method, an example usage of the monteCarloPi function is provided. We specify the
 * number of samples, call the function to estimate pi, and then print the result. Increasing the number of samples
 * generally leads to a more accurate estimate of pi.
 */

public class MonteCarlo {

    /**
     * Main method for driving code and example usage of Monte Carlo Pi Estimation.
     */
    public static void main(String[] args) {
        // Example: Monte Carlo Pi Estimation
        int numSamples = 1000000;
        double piEstimate = monteCarloPi(numSamples);
        System.out.println("Estimated value of pi using " + numSamples + " samples: " + piEstimate);
    }

    /**
     * Estimate the value of pi using the Monte Carlo method.
     *
     * @param numSamples Number of random samples to generate.
     * @return Estimated value of pi.
     */
    public static double monteCarloPi(int numSamples) {
        int numPointsInsideCircle = 0;
        Random random = new Random();

        for (int i = 0; i < numSamples; i++) {
            double x = random.nextDouble() * 2 - 1;
            double y = random.nextDouble() * 2 - 1;
            double distanceSquared = x * x + y * y;

            if (distanceSquared <= 1) {
                numPointsInsideCircle++;
            }
        }

        return 4.0 * numPointsInsideCircle / numSamples;
    }
}
