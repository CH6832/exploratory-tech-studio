#include <iostream>
#include <vector>
#include <algorithm>
#include <random>

using namespace std;

// Define a chromosome as a vector of integers representing binary digits
using Chromosome = vector<int>;

// Function prototypes
vector<Chromosome> generatePopulation(int populationSize, int chromosomeLength);
int fitnessFunction(const Chromosome& chromosome, const Chromosome& target);
vector<Chromosome> selection(const vector<Chromosome>& population, const Chromosome& target, int numParents);
vector<Chromosome> crossover(const vector<Chromosome>& parents, int numOffspring);
vector<Chromosome> mutation(vector<Chromosome>& offspring, double mutationRate);
Chromosome geneticAlgorithm(const Chromosome& target, int chromosomeLength, int populationSize, int numGenerations, double mutationRate);

int main() {
    // Example: Evolution towards a target binary sequence
    Chromosome targetSequence = {1, 0, 1, 1, 0, 1, 0, 1};  // Define the target sequence
    int numGenerations = 100;  // Number of generations
    int populationSize = 100;  // Size of the population
    int chromosomeLength = targetSequence.size();  // Length of each chromosome
    double mutationRate = 0.01;  // Probability of mutation for each bit

    Chromosome fittestChromosome = geneticAlgorithm(targetSequence, chromosomeLength, populationSize, numGenerations, mutationRate);
    cout << "Fittest chromosome: ";
    for (int bit : fittestChromosome) {
        cout << bit;
    }
    cout << endl;

    return 0;
}

// Generate an initial population of binary chromosomes
vector<Chromosome> generatePopulation(int populationSize, int chromosomeLength) {
    vector<Chromosome> population(populationSize, Chromosome(chromosomeLength));
    random_device rd;
    mt19937 gen(rd());
    uniform_int_distribution<> dis(0, 1);

    for (auto& chromosome : population) {
        for (int& bit : chromosome) {
            bit = dis(gen);
        }
    }

    return population;
}

// Compute the fitness value of a chromosome
int fitnessFunction(const Chromosome& chromosome, const Chromosome& target) {
    int fitness = 0;
    for (size_t i = 0; i < chromosome.size(); i++) {
        if (chromosome[i] == target[i]) {
            fitness++;
        }
    }
    return fitness;
}

// Perform selection of parents based on fitness
vector<Chromosome> selection(const vector<Chromosome>& population, const Chromosome& target, int numParents) {
    vector<Chromosome> selectedParents(population);
    sort(selectedParents.begin(), selectedParents.end(), [&](const Chromosome& a, const Chromosome& b) {
        return fitnessFunction(a, target) > fitnessFunction(b, target);
    });
    selectedParents.resize(numParents);
    return selectedParents;
}

// Perform crossover to produce offspring
vector<Chromosome> crossover(const vector<Chromosome>& parents, int numOffspring) {
    vector<Chromosome> offspring;
    random_device rd;
    mt19937 gen(rd());
    uniform_int_distribution<> dis(0, parents[0].size() - 1);

    for (int i = 0; i < numOffspring; i++) {
        const Chromosome& parent1 = parents[dis(gen)];
        const Chromosome& parent2 = parents[dis(gen)];
        int crossoverPoint = dis(gen);
        Chromosome child(parent1.begin(), parent1.begin() + crossoverPoint);
        child.insert(child.end(), parent2.begin() + crossoverPoint, parent2.end());
        offspring.push_back(child);
    }

    return offspring;
}

// Perform mutation on offspring
vector<Chromosome> mutation(vector<Chromosome>& offspring, double mutationRate) {
    random_device rd;
    mt19937 gen(rd());
    uniform_real_distribution<> dis(0.0, 1.0);

    for (Chromosome& chromosome : offspring) {
        for (int& bit : chromosome) {
            if (dis(gen) < mutationRate) {
                bit = 1 - bit; // Flip the bit
            }
        }
    }

    return offspring;
}

// Perform a classical genetic algorithm to evolve towards a target binary sequence
Chromosome geneticAlgorithm(const Chromosome& target, int chromosomeLength, int populationSize, int numGenerations, double mutationRate) {
    vector<Chromosome> population = generatePopulation(populationSize, chromosomeLength);

    for (int generation = 0; generation < numGenerations; generation++) {
        vector<Chromosome> parents = selection(population, target, populationSize / 2);
        vector<Chromosome> offspring = crossover(parents, populationSize - parents.size());
        offspring = mutation(offspring, mutationRate);
        population =
