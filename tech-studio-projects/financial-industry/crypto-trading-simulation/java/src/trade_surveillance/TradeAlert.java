// TradeAlert.java
public class TradeAlert {

    // Method to generate an alert for suspicious trades
    public void generateAlert(Order order) {
        Logger.log("Suspicious trade detected: " + order.toString());
        // Additional actions like sending notifications can be added here
    }
}
