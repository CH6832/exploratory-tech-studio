package model;

import java.util.HashMap;
import java.util.Map;

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
        for (Map.Entry<Integer, Integer> entry : items.entrySet()) {
            int productId = entry.getKey();
            int quantity = entry.getValue();
            Double price = productPrices.get(productId);
            if (price != null) {
                total += price * quantity;
            }
        }
        return total;
    }
}
