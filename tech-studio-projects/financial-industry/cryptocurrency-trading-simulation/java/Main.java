import utils.Config;
import core.market_maker.MarketMaker;

public class Main {
    public static void main(String[] args) {
        // Load the configuration from a JSON file
        Config.loadConfig("config.json");

        // Start the MarketMaker strategy
        MarketMaker marketMaker = new MarketMaker();
        marketMaker.start();
    }
}
