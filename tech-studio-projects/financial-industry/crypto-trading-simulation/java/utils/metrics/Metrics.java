import utils.Metrics;
import utils.OrderMetrics;
import utils.TradeMetrics;

public class Main {
    public static void main(String[] args) {
        // Initialize metrics
        Metrics metrics = new Metrics();
        OrderMetrics orderMetrics = new OrderMetrics(metrics);
        TradeMetrics tradeMetrics = new TradeMetrics(metrics);

        // Simulate recording some metrics
        long startTime = System.currentTimeMillis();
        // Simulate order routing logic here
        long endTime = System.currentTimeMillis();
        orderMetrics.recordOrderRoutingTime(endTime - startTime);

        startTime = System.currentTimeMillis();
        // Simulate trade execution logic here
        endTime = System.currentTimeMillis();
        tradeMetrics.recordTradeExecutionTime(endTime - startTime);

        // Report metrics
        metrics.reportMetrics();
    }
}
