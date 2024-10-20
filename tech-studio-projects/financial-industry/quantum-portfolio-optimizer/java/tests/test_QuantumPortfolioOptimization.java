
import java.util.List;

public class test_QuantumPortfolioOptimization {
    public static void main(String[] args) {
        String returnsFile = "data/returns.csv";  // Load your returns data
        String covFile = "data/covariance.csv";   // Load your covariance matrix

        // Load returns and covariance matrix using DataLoader
        List<Double> returns = DataLoader.loadReturns(returnsFile);
        List<List<Double>> covariance = DataLoader.loadCovariance(covFile);

        // Create an instance of MarkowitzOptimizer
        MarkowitzOptimizer optimizer = new MarkowitzOptimizer(returns, covariance);
        double riskAversion = 2.0; // Example risk aversion parameter

        // Optimize portfolio
        List<Double> optimalWeights = optimizer.optimizePortfolio(riskAversion);

        // Print the optimal portfolio weights
        System.out.println("Optimal Portfolio Weights:");
        for (double weight : optimalWeights) {
            System.out.print(weight + " ");
        }
        System.out.println();
    }
}
