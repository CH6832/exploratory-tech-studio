import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import com.google.gson.Gson;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;
import java.util.Map;

public class KafkaProducerApp {

    private static final String KAFKA_TOPIC = "credit-card-transactions";
    private static final int NUM_TRANSACTIONS = 1000;

    public static void main(String[] args) {
        startProducer();
    }

    public static void startProducer() {
        if (!checkServiceRunning("127.0.0.1", 9092)) {
            System.out.println("Kafka broker is not available, exiting.");
            return;
        }

        // Create Kafka producer properties
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Create Kafka producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        System.out.println("Kafka producer created successfully!");

        // Create a thread pool to simulate multiple transactions being sent concurrently
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < NUM_TRANSACTIONS; i++) {
            executor.submit(() -> {
                Map<String, Object> transaction = generateTransaction();
                String transactionJson = new Gson().toJson(transaction);

                // Send the transaction to Kafka
                producer.send(new ProducerRecord<>(KAFKA_TOPIC, transactionJson));
                System.out.println("Sent transaction: " + transactionJson);
            });

            // Optional: Add a delay between transactions
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Shutdown executor service and close the producer
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        producer.flush();
        producer.close();
        System.out.println("All transactions sent successfully.");
    }

    private static Map<String, Object> generateTransaction() {
        // Simulate a credit card transaction
        Map<String, Object> transaction = new HashMap<>();
        transaction.put("card_number", "xxxx-xxxx-xxxx-" + new Random().nextInt(9000) + 1000);
        transaction.put("amount", round(randomDouble(5.0, 5000.0), 2));
        transaction.put("merchant", randomChoice(new String[]{"Amazon", "Walmart", "BestBuy", "Target", "Starbucks"}));
        transaction.put("timestamp", System.currentTimeMillis());
        transaction.put("location", randomChoice(new String[]{"New York", "California", "Texas", "London", "Paris"}));
        transaction.put("transaction_type", randomChoice(new String[]{"online", "in-store"}));
        transaction.put("transaction_frequency", new Random().nextInt(20) + 1);

        return transaction;
    }

    private static double randomDouble(double min, double max) {
        return min + (max - min) * new Random().nextDouble();
    }

    private static String randomChoice(String[] options) {
        return options[new Random().nextInt(options.length)];
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    private static boolean checkServiceRunning(String host, int port) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), 5000);
            System.out.println("Service running on " + host + ":" + port);
            return true;
        } catch (Exception e) {
            System.out.println("Service NOT running on " + host + ":" + port);
            return false;
        }
    }
}
