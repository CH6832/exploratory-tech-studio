// MarketMaker.java
public class MarketMaker {

    private OrderRouter orderRouter;
    private RiskManager riskManager;
    private MarketDataFeed marketDataFeed;

    // Constructor
    public MarketMaker() {
        this.orderRouter = new OrderRouter();
        this.riskManager = new RiskManager();
        this.marketDataFeed = new MarketDataFeed();
    }

    // Method to start the Market Maker
    public void start() {
        System.out.println("Starting Market Maker...");
        marketDataFeed.start();
        orderRouter.start();
        // You can initialize the risk manager logic here if needed
    }

    // Method to stop the Market Maker
    public void stop() {
        System.out.println("Stopping Market Maker...");
        orderRouter.stop();
        marketDataFeed.stop();
    }
}
