#include <iostream>
#include <vector>
#include <cmath>
#include <random>
#include <functional>

using namespace std;

// Define the type of the objective function
using ObjectiveFunction = function<double(const vector<double>&)>;

/**
 * Solves optimization problems using Simulated Annealing.
 *
 * @param objectiveFunction Objective function to minimize.
 * @param initialSolution  Initial solution vector.
 * @param temperature      Initial temperature parameter.
 * @param coolingRate      Cooling rate parameter.
 * @param maxIter          Maximum number of iterations.
 * @return The best solution found and its corresponding objective function value as a vector.
 */
vector<double> simulatedAnnealing(ObjectiveFunction objectiveFunction, const vector<double>& initialSolution, double temperature, double coolingRate, int maxIter) {
    vector<double> currentSolution = initialSolution;
    double currentEnergy = objectiveFunction(currentSolution);
    vector<double> bestSolution = currentSolution;
    double bestEnergy = currentEnergy;

    random_device rd;
    mt19937 gen(rd());
    uniform_real_distribution<double> dis(-0.1, 0.1); // Perturbation range

    for (int iter = 0; iter < maxIter; iter++) {
        vector<double> proposedSolution = currentSolution;
        for (double& value : proposedSolution) {
            value += dis(gen); // Perturb the current solution
        }

        double proposedEnergy = objectiveFunction(proposedSolution);
        double deltaEnergy = proposedEnergy - currentEnergy;

        if (deltaEnergy < 0 || dis(gen) < exp(-deltaEnergy / temperature)) {
            currentSolution = proposedSolution;
            currentEnergy = proposedEnergy;

            if (currentEnergy < bestEnergy) {
                bestSolution = currentSolution;
                bestEnergy = currentEnergy;
            }
        }

        temperature *= coolingRate; // Cool down temperature
    }

    return bestSolution;
}

int main() {
    // Example: Minimize a simple objective function using Simulated Annealing
    ObjectiveFunction objectiveFunction = [](const vector<double>& x) {
        double sum = 0;
        for (double value : x) {
            sum += pow(value, 2);
        }
        return sum;
    };

    vector<double> initialSolution = {2.0, 3.0, 1.5};
    double temperature = 100.0;
    double coolingRate = 0.95;
    int maxIter = 1000;

    vector<double> result = simulatedAnnealing(objectiveFunction, initialSolution, temperature, coolingRate, maxIter);
    cout << "Best solution: ";
    for (double value : result) {
        cout << value << " ";
    }
    cout << endl;

    cout << "Best energy: " << objectiveFunction(result) << endl;

    return 0;
}
