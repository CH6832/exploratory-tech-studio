package model;

import java.util.HashMap;
import java.util.Map;
import java.text.DecimalFormat;

public class ShoppingCart {
    private Map<Integer, Integer> items;

    public ShoppingCart() {
        items = new HashMap<>();
    }

    public void addItem(int productId, int quantity) {
        items.put(productId, items.getOrDefault(productId, 0) + quantity);
    }

    public void removeItem(int productId) {
        items.remove(productId);
    }

    public Map<Integer, Integer> getItems() {
        return items;
    }

    public double getTotalPrice(Map<Integer, Double> productPrices) {
        double total = 0.0;

        // Loop through each item in the cart
        for (Map.Entry<Integer, Integer> entry : items.entrySet()) {
            int productId = entry.getKey();
            int quantity = entry.getValue();

            // Get price for the productId from the productPrices map
            Double price = productPrices.get(productId);

            // Handle case where the price is not found
            if (price != null) {
                total += price * quantity; // Add price * quantity to the total
            } else {
                // Use default price if price not found
                price = 9.99; // Default price
                total += price * quantity;
            }
        }

        // Round total to 2 decimal places using DecimalFormat
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.parseDouble(df.format(total));
    }
}
