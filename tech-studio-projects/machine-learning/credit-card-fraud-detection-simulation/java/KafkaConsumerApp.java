import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.StringDeserializer;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerApp {

    public static void main(String[] args) {
        // Load Kafka consumer configuration
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "credit-card-fraud-detector");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // Create Kafka consumer and subscribe to the topic
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("credit-card-transactions"));

        // Load the model and scaler (placeholder)
        Object model = loadModel("models/fraud_detection_model"); // Implement this as needed
        Object scaler = loadScaler("models/scaler"); // Implement this as needed

        System.out.println("Listening for transactions...");

        try {
            // Poll for new messages from Kafka
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records) {
                    String transactionJson = record.value();
                    System.out.println("Received transaction: " + transactionJson);

                    // Parse the JSON transaction
                    JsonObject transaction = new Gson().fromJson(transactionJson, JsonObject.class);

                    // Extract features from the transaction (example features)
                    double[] features = extractFeatures(transaction);

                    // Scale features using scaler (placeholder)
                    double[] scaledFeatures = scaleFeatures(features, scaler);

                    // Predict fraud using model (placeholder)
                    boolean isFraud = predictFraud(scaledFeatures, model);

                    if (isFraud) {
                        System.out.println("ALERT: Fraud detected in transaction: " + transactionJson);
                    } else {
                        System.out.println("Transaction is normal: " + transactionJson);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }

    private static double[] extractFeatures(JsonObject transaction) {
        // Extract the relevant features from the transaction JSON
        double amount = transaction.get("amount").getAsDouble();
        double frequency = transaction.get("transaction_frequency").getAsDouble();
        // Add other features as needed
        
        // Return features as a double array
        return new double[]{amount, frequency};
    }

    private static double[] scaleFeatures(double[] features, Object scaler) {
        // Placeholder for scaling logic (you would need a real scaler implementation)
        return features; // Assuming no scaling for now
    }

    private static boolean predictFraud(double[] features, Object model) {
        // Placeholder for prediction logic (replace with actual model inference)
        return Math.random() < 0.5; // Random prediction for demonstration
    }

    private static Object loadModel(String modelPath) {
        // Placeholder for loading the machine learning model (e.g., TensorFlow, DL4J)
        System.out.println("Loaded model from: " + modelPath);
        return new Object(); // Dummy model object
    }

    private static Object loadScaler(String scalerPath) {
        // Placeholder for loading the scaler
        System.out.println("Loaded scaler from: " + scalerPath);
        return new Object(); // Dummy scaler object
    }
}
