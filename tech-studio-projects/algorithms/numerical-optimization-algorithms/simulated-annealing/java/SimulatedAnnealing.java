import java.util.Random;
import java.util.function.Function;

public class SimulatedAnnealing {

    /**
     * Solves optimization problems using Simulated Annealing.
     *
     * @param objectiveFunction Objective function to minimize.
     * @param initialSolution  Initial solution vector.
     * @param temperature      Initial temperature parameter.
     * @param coolingRate      Cooling rate parameter.
     * @param maxIter          Maximum number of iterations.
     * @return The best solution found and its corresponding objective function value as an array.
     */
    public static double[] simulatedAnnealing(Function<double[], Double> objectiveFunction, double[] initialSolution, double temperature, double coolingRate, int maxIter) {
        double[] currentSolution = initialSolution.clone();
        double currentEnergy = objectiveFunction.apply(currentSolution);
        double[] bestSolution = currentSolution.clone();
        double bestEnergy = currentEnergy;

        Random random = new Random();
        for (int iter = 0; iter < maxIter; iter++) {
            double[] proposedSolution = new double[currentSolution.length];
            for (int i = 0; i < currentSolution.length; i++) {
                proposedSolution[i] = currentSolution[i] + random.nextGaussian() * 0.1; // Perturb the current solution
            }

            double proposedEnergy = objectiveFunction.apply(proposedSolution);
            double deltaEnergy = proposedEnergy - currentEnergy;

            if (deltaEnergy < 0 || Math.random() < Math.exp(-deltaEnergy / temperature)) {
                currentSolution = proposedSolution;
                currentEnergy = proposedEnergy;

                if (currentEnergy < bestEnergy) {
                    bestSolution = currentSolution.clone();
                    bestEnergy = currentEnergy;
                }
            }

            temperature *= coolingRate; // Cool down temperature
        }

        return new double[]{bestEnergy};
    }

    public static void main(String[] args) {
        // Example: Minimize a simple objective function using Simulated Annealing
        Function<double[], Double> objectiveFunction = x -> {
            double sum = 0;
            for (double value : x) {
                sum += Math.pow(value, 2);
            }
            return sum;
        };

        double[] initialSolution = new double[]{2.0, 3.0, 1.5};
        double temperature = 100.0;
        double coolingRate = 0.95;
        int maxIter = 1000;

        double[] result = simulatedAnnealing(objectiveFunction, initialSolution, temperature, coolingRate, maxIter);
        System.out.println("Best energy: " + result[0]);
    }
}
