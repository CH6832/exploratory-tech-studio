import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.ArrayRealVector;

import java.util.ArrayList;
import java.util.List;

public class MarkowitzOptimizer {
    private List<Double> expectedReturns; // List of expected returns
    private List<List<Double>> covarianceMatrix; // Covariance matrix

    public MarkowitzOptimizer(List<Double> returns, List<List<Double>> covariance) {
        this.expectedReturns = new ArrayList<>(returns);
        this.covarianceMatrix = new ArrayList<>(covariance);
    }

    public List<Double> optimizePortfolio(double riskAversion) {
        int n = expectedReturns.size();
        
        // Convert covariance matrix from List to RealMatrix
        double[][] covArray = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                covArray[i][j] = covarianceMatrix.get(i).get(j);
            }
        }
        RealMatrix covMatrix = new Array2DRowRealMatrix(covArray);

        // Convert expected returns to RealVector
        RealVector expectedReturnsVec = new ArrayRealVector(expectedReturns.stream().mapToDouble(Double::doubleValue).toArray());

        // Mean-Variance Optimization
        RealVector weights = covMatrix.inverse().operate(expectedReturnsVec).mapMultiply(1.0 / riskAversion);

        // Normalize weights
        double weightSum = weights.getL1Norm(); // Use L1 norm to get the sum of absolute values
        weights.mapDivide(weightSum); // Normalize

        // Convert RealVector back to List<Double>
        List<Double> resultWeights = new ArrayList<>();
        for (int i = 0; i < weights.getDimension(); i++) {
            resultWeights.add(weights.getEntry(i));
        }
        
        return resultWeights;
    }
}
