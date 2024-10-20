import java.util.ArrayList;
import java.util.List;

public class TradeSurveillance {
    private List<Order> orderBook; // List to hold orders
    private TradeAlert tradeAlert; // Instance of TradeAlert

    // Constructor
    public TradeSurveillance() {
        this.orderBook = new ArrayList<>();
        this.tradeAlert = new TradeAlert();
    }

    // Method to add an order
    public void addOrder(Order order) {
        orderBook.add(order);
        if (isSuspicious(order)) {
            tradeAlert.generateAlert(order); // Generate alert for suspicious orders
        }
    }

    // Method to determine if an order is suspicious (placeholder logic)
    private boolean isSuspicious(Order order) {
        // Implement your logic here to determine if an order is suspicious
        // Example condition: if the quantity exceeds a certain threshold
        return order.getQuantity() > 100; // Modify this condition as needed
    }
    
    // Additional method to display all orders
    public void displayOrders() {
        for (Order order : orderBook) {
            System.out.println(order);
        }
    }
}
