
import java.util.ArrayList;
import java.util.List;

public class QUBOFormulation {
    private List<Double> expectedReturns; // List of expected returns
    private List<List<Double>> covarianceMatrix; // Covariance matrix
    private double riskAversion; // Risk aversion factor

    public QUBOFormulation(List<Double> returns, List<List<Double>> covariance, double riskAversion) {
        this.expectedReturns = new ArrayList<>(returns);
        this.covarianceMatrix = new ArrayList<>(covariance);
        this.riskAversion = riskAversion;
    }

    public List<List<Double>> formulateQUBO() {
        int n = expectedReturns.size();
        List<List<Double>> Q = new ArrayList<>();

        // Initialize the QUBO matrix with zeros
        for (int i = 0; i < n; i++) {
            Q.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                Q.get(i).add(0.0); // Fill with zeros
            }
        }

        // Fill the QUBO matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Q.get(i).set(j, calculateQUBOCoefficient(i, j));
            }
        }

        return Q;
    }

    private double calculateQUBOCoefficient(int i, int j) {
        if (i == j) {
            return riskAversion * expectedReturns.get(i) - covarianceMatrix.get(i).get(i); // Diagonal elements
        }
        return -covarianceMatrix.get(i).get(j); // Off-diagonal elements
    }
}
