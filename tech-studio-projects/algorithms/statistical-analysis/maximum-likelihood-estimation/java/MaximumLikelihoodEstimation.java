import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.optim.MaxEval;
import org.apache.commons.math3.optim.SimpleValueChecker;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionMappingAdapter;
import org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer;
import org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex;
import org.apache.commons.math3.optim.nonlinear.scalar.noderiv.SimplexOptimizer;
import org.apache.commons.math3.optim.PointValuePair;

public class MaximumLikelihoodEstimation {

    /**
     * Perform maximum likelihood estimation to estimate the parameters of the statistical model.
     *
     * @param data The observed data.
     * @return The estimated parameters of the statistical model (mean and standard deviation).
     */
    public static double[] maximumLikelihoodEstimation(double[] data) {
        MultivariateFunction likelihoodFunction = new LikelihoodFunction(data);
        MultivariateOptimizer optimizer = new SimplexOptimizer(new SimpleValueChecker(1e-10, 1e-30));

        MultivariateFunctionMappingAdapter boundedLikelihoodFunction = 
            new MultivariateFunctionMappingAdapter(likelihoodFunction, new double[] {Double.NEGATIVE_INFINITY, 0}, 
                                                                     new double[] {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY});

        PointValuePair result = optimizer.optimize(new MaxEval(1000),
                                                   new NelderMeadSimplex(2),
                                                   GoalType.MINIMIZE,
                                                   new org.apache.commons.math3.optim.InitialGuess(new double[] {0, 1}),
                                                   boundedLikelihoodFunction);

        return result.getPoint();
    }

    static class LikelihoodFunction implements MultivariateFunction {
        private final double[] data;

        LikelihoodFunction(double[] data) {
            this.data = data;
        }

        /**
         * Compute the likelihood function for the given parameters and data.
         *
         * @param params The parameters of the statistical model (mean and standard deviation).
         * @return The negative likelihood value (to be minimized).
         */
        @Override
        public double value(double[] params) {
            double mu = params[0];
            double sigma = params[1];
            double likelihood = 0.0;
            for (double d : data) {
                likelihood += Math.log(1 / (Math.sqrt(2 * Math.PI) * sigma) * 
                                       Math.exp(-0.5 * Math.pow((d - mu) / sigma, 2)));
            }
            return -likelihood; // Minimize negative likelihood (equivalent to maximizing likelihood)
        }
    }
}
