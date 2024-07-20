import java.util.HashMap;
import java.util.Map;

/**
 * Class to perform Bayesian inference.
 */
public class BayesianInference {

    /**
     * Perform Bayesian inference to update priors based on likelihoods.
     *
     * @param priors A map of hypothesis names to their prior probabilities.
     * @param likelihoods A map of hypothesis names to their likelihoods.
     * @return A map of hypothesis names to their updated probabilities.
     */
    public static Map<String, Double> bayesianInference(Map<String, Double> priors, Map<String, Double> likelihoods) {
        Map<String, Double> updatedProbs = new HashMap<>();

        // Compute the denominator (evidence) by summing the products of priors and likelihoods
        double evidence = 0.0;
        for (Map.Entry<String, Double> entry : priors.entrySet()) {
            String hypothesis = entry.getKey();
            evidence += entry.getValue() * likelihoods.get(hypothesis);
        }

        // Update the probabilities of each hypothesis using Bayes' theorem
        for (Map.Entry<String, Double> entry : priors.entrySet()) {
            String hypothesis = entry.getKey();
            double prior = entry.getValue();
            double likelihood = likelihoods.get(hypothesis);
            updatedProbs.put(hypothesis, (prior * likelihood) / evidence);
        }

        return updatedProbs;
    }
}
