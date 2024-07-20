public class Main {
    /**
     * Main function to demonstrate maximum likelihood estimation.
     */
    public static void main(String[] args) {
        // Example: Estimate parameters using maximum likelihood estimation
        double[] observedData = {1.2, 1.5, 2.0, 2.2, 2.8};
        double[] estimatedParams = MaximumLikelihoodEstimation.maximumLikelihoodEstimation(observedData);
        System.out.println("Estimated parameters (mean, standard deviation): " + 
            estimatedParams[0] + ", " + estimatedParams[1]);
    }
}
