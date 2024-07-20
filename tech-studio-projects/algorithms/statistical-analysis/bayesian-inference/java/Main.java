import java.util.HashMap;
import java.util.Map;

/**
 * Main class to demonstrate Bayesian inference.
 */
public class Main {
    public static void main(String[] args) {
        // Example: Perform Bayesian inference with given priors and likelihoods
        Map<String, Double> priors = new HashMap<>();
        priors.put("Hypothesis A", 0.3);
        priors.put("Hypothesis B", 0.7);

        Map<String, Double> likelihoods = new HashMap<>();
        likelihoods.put("Hypothesis A", 0.8);
        likelihoods.put("Hypothesis B", 0.4);

        Map<String, Double> updatedProbs = BayesianInference.bayesianInference(priors, likelihoods);
        System.out.println("Updated probabilities after Bayesian inference:");
        for (Map.Entry<String, Double> entry : updatedProbs.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
