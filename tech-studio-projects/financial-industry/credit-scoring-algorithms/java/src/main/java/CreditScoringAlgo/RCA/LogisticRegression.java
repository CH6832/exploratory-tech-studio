package CreditScoringAlgo.RCA;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LogisticRegression {
    private static final Logger logger = LoggerFactory.getLogger(LogisticRegression.class);
    private double learningRate;
    private int numIterations;
    private List<Double> weights;
    private double bias;

    public LogisticRegression(double learningRate, int numIterations) {
        this.learningRate = learningRate;
        this.numIterations = numIterations;
        this.weights = new ArrayList<>();
        this.bias = 0.0;
    }

    private double sigmoid(double z) {
        return 1.0 / (1.0 + Math.exp(-z));
    }

    public void fit(List<List<Double>> X, List<Integer> y) {
        int m = X.size();
        int n = X.get(0).size();

        // Initialize weights to zero
        for (int i = 0; i < n; i++) {
            weights.add(0.0);
        }

        for (int i = 0; i < numIterations; i++) {
            List<Double> dw = new ArrayList<>();
            double db = 0.0;
            double cost = 0.0;

            // Initialize dw to zero
            for (int j = 0; j < n; j++) {
                dw.add(0.0);
            }

            for (int j = 0; j < m; j++) {
                double linearModel = bias;
                for (int k = 0; k < n; k++) {
                    linearModel += weights.get(k) * X.get(j).get(k);
                }
                double yHat = sigmoid(linearModel);
                double error = yHat - y.get(j);

                // Update gradients
                for (int k = 0; k < n; k++) {
                    dw.set(k, dw.get(k) + error * X.get(j).get(k));
                }
                db += error;
                cost += -y.get(j) * Math.log(yHat) - (1 - y.get(j)) * Math.log(1 - yHat);
            }

            cost /= m;

            // Update weights and bias
            for (int k = 0; k < n; k++) {
                weights.set(k, weights.get(k) - (learningRate * dw.get(k) / m));
            }
            bias -= learningRate * db / m;

            // Logging
            if (i % 1000 == 0) {
                logger.info("Iteration {}, Cost: {}", i, cost);
            }
        }
    }

    public List<Integer> predict(List<List<Double>> X) {
        List<Integer> predictions = new ArrayList<>();
        for (List<Double> sample : X) {
            double linearModel = bias;
            for (int i = 0; i < sample.size(); i++) {
                linearModel += weights.get(i) * sample.get(i);
            }
            predictions.add(sigmoid(linearModel) >= 0.5 ? 1 : 0);
        }
        return predictions;
    }
}