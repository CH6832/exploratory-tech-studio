import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClassicalGeneticAlgorithm {

    /**
     * Main method to drive the genetic algorithm.
     */
    public static void main(String[] args) {
        // Example: Evolution towards a target binary sequence
        List<Integer> targetSequence = List.of(1, 0, 1, 1, 0, 1, 0, 1);  // Define the target sequence
        int numGenerations = 100;  // Number of generations
        int populationSize = 100;  // Size of the population
        int chromosomeLength = targetSequence.size();  // Length of each chromosome
        double mutationRate = 0.01;  // Probability of mutation for each bit

        List<Integer> fittestChromosome = geneticAlgorithm(targetSequence, chromosomeLength, populationSize, numGenerations, mutationRate);
        System.out.println("Fittest chromosome: " + fittestChromosome);
    }

    /**
     * Generate an initial population of binary chromosomes.
     * 
     * @param populationSize    Number of individuals in the population.
     * @param chromosomeLength Length of each chromosome.
     * @return Initial population represented as a list of binary chromosomes.
     */
    public static List<List<Integer>> generatePopulation(int populationSize, int chromosomeLength) {
        List<List<Integer>> population = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < populationSize; i++) {
            List<Integer> chromosome = new ArrayList<>();
            for (int j = 0; j < chromosomeLength; j++) {
                chromosome.add(random.nextInt(2));
            }
            population.add(chromosome);
        }

        return population;
    }

    /**
     * Compute the fitness value of a chromosome.
     * 
     * @param chromosome Binary chromosome to evaluate.
     * @param target     Target binary sequence.
     * @return Fitness value of the chromosome (number of matching bits with the target).
     */
    public static int fitnessFunction(List<Integer> chromosome, List<Integer> target) {
        int fitness = 0;
        for (int i = 0; i < chromosome.size(); i++) {
            if (chromosome.get(i).equals(target.get(i))) {
                fitness++;
            }
        }
        return fitness;
    }

    /**
     * Perform selection of parents based on fitness.
     * 
     * @param population Current population.
     * @param target     Target binary sequence.
     * @param numParents Number of parents to select.
     * @return Selected parent chromosomes.
     */
    public static List<List<Integer>> selection(List<List<Integer>> population, List<Integer> target, int numParents) {
        population.sort((a, b) -> fitnessFunction(b, target) - fitnessFunction(a, target));
        return population.subList(0, numParents);
    }

    /**
     * Perform crossover to produce offspring.
     * 
     * @param parents      Selected parent chromosomes.
     * @param numOffspring Number of offspring to generate.
     * @return Offspring chromosomes resulting from crossover.
     */
    public static List<List<Integer>> crossover(List<List<Integer>> parents, int numOffspring) {
        List<List<Integer>> offspring = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numOffspring; i++) {
            List<Integer> parent1 = parents.get(random.nextInt(parents.size()));
            List<Integer> parent2 = parents.get(random.nextInt(parents.size()));
            int crossoverPoint = random.nextInt(parent1.size());
            List<Integer> child = new ArrayList<>(parent1.subList(0, crossoverPoint));
            child.addAll(parent2.subList(crossoverPoint, parent2.size()));
            offspring.add(child);
        }

        return offspring;
    }

    /**
     * Perform mutation on offspring.
     * 
     * @param offspring    Offspring chromosomes.
     * @param mutationRate Probability of mutation for each bit.
     * @return Mutated offspring chromosomes.
     */
    public static List<List<Integer>> mutation(List<List<Integer>> offspring, double mutationRate) {
        Random random = new Random();

        for (List<Integer> chromosome : offspring) {
            for (int i = 0; i < chromosome.size(); i++) {
                if (random.nextDouble() < mutationRate) {
                    chromosome.set(i, 1 - chromosome.get(i)); // Flip the bit
                }
            }
        }

        return offspring;
    }

    /**
     * Perform a classical genetic algorithm to evolve towards a target binary sequence.
     * 
     * @param target           Target binary sequence to evolve towards.
     * @param chromosomeLength Length of each chromosome.
     * @param populationSize   Number of individuals in the population.
     * @param numGenerations   Number of generations to run the algorithm.
     * @param mutationRate     Probability of mutation for each bit.
     * @return The fittest chromosome found after the specified number of generations.
     */
    public static List<Integer> geneticAlgorithm(List<Integer> target, int chromosomeLength, int populationSize, int numGenerations, double mutationRate) {
        List<List<Integer>> population = generatePopulation(populationSize, chromosomeLength);

        for (int generation = 0; generation < numGenerations; generation++) {
            List<List<Integer>> parents = selection(population, target, populationSize / 2);
            List<List<Integer>> offspring = crossover(parents, populationSize - parents.size());
            offspring = mutation(offspring, mutationRate);
            population = new ArrayList<>(parents);
            population.addAll(offspring);
        }

        List<Integer> fittestChromosome = null;
        int maxFitness = Integer.MIN_VALUE;
        for (List<Integer> chromosome : population) {
            int fitness = fitnessFunction(chromosome, target);
            if (fitness > maxFitness) {
                maxFitness = fitness;
                fittestChromosome = chromosome;
            }
        }

        return fittestChromosome;
    }
}
