// OrderRouter.java
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Map;
import java.time.Instant;

public class OrderRouter {

    // Method to place an order
    public void placeOrder(String symbol, String side, double quantity, double price) {
        try {
            // Load API keys from Config class (assuming a similar utility class exists in Java)
            String apiKey = Config.get("BINANCE_API_KEY");
            String apiSecret = Config.get("BINANCE_API_SECRET");

            if (apiKey == null || apiSecret == null || apiKey.isEmpty() || apiSecret.isEmpty()) {
                Logger.logError("API keys are missing in config");
                return;
            }

            // Construct the order payload
            String query = "symbol=" + symbol + "&side=" + side + "&type=LIMIT&timeInForce=GTC" +
                           "&quantity=" + quantity + "&price=" + price +
                           "&timestamp=" + Instant.now().toEpochMilli();

            // Sign the request
            String signature = hmacSha256(apiSecret, query);
            query += "&signature=" + signature;

            // Set the API endpoint and headers
            String url = "https://api.binance.com/api/v3/order";
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("X-MBX-APIKEY", apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(query))
                .build();

            // Send the request and handle the response
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                Logger.logError("Failed to place order: " + response.body());
            } else {
                Logger.logInfo("Order placed successfully.");
            }
        } catch (Exception e) {
            Logger.logError("An error occurred: " + e.getMessage());
        }
    }

    // HMAC-SHA256 signing function
    private String hmacSha256(String key, String data) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        mac.init(secretKeySpec);
        byte[] hash = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
