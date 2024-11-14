import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroverSearch {
    private int n; // Number of bits
    private List<Integer> markedIndices; // Indices of marked states
    private Random random; // Random number generator

    public GroverSearch(int n, List<Integer> marked) {
        this.n = n;
        this.markedIndices = new ArrayList<>(marked);
        this.random = new Random(); // Initialize random seed for the simulation
    }

    public List<Integer> run() {
        int iterations = (int) Math.floor(Math.sqrt(1 << n)); // Number of iterations
        List<Integer> candidates = new ArrayList<>(); // Initialize the candidate states

        // Initialize candidates with indices 0, 1, ..., 2^n - 1
        for (int i = 0; i < (1 << n); i++) {
            candidates.add(i);
        }

        for (int i = 0; i < iterations; i++) {
            candidates = applyOracle(candidates);
            candidates = applyDiffusionOperator(candidates);
        }

        return candidates;
    }

    private List<Integer> applyOracle(List<Integer> candidates) {
        List<Integer> result = new ArrayList<>(candidates);

        // Flip the sign of the marked states
        for (int index : markedIndices) {
            if (index >= 0 && index < candidates.size()) {
                result.set(index, -result.get(index)); // Simulating the oracle
            }
        }
        return result;
    }

    private List<Integer> applyDiffusionOperator(List<Integer> candidates) {
        List<Integer> result = new ArrayList<>(candidates);
        double mean = 0.0;

        // Calculate mean
        for (int candidate : candidates) {
            mean += candidate;
        }
        mean /= candidates.size();

        // Apply the diffusion operator
        for (int i = 0; i < result.size(); i++) {
            result.set(i, (int) (2 * mean - result.get(i)));
        }
        return result;
    }

    public void printResults(List<Integer> results) {
        System.out.println("Possible Solutions:");
        for (int result : results) {
            if (result < 0) {
                System.out.println("Found marked index: " + -result);
            }
        }
    }
}
