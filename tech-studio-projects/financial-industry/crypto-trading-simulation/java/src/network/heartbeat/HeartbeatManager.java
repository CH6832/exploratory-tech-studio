// HeartbeatManager.java
public class HeartbeatManager {

    private volatile boolean running; // Use volatile for thread safety

    // Constructor
    public HeartbeatManager() {
        this.running = false;
    }

    // Method to start sending heartbeats
    public void start() {
        running = true;
        new Thread(this::sendHeartbeats).start();
    }

    // Method to stop sending heartbeats
    public void stop() {
        running = false;
    }

    // Method that runs in a separate thread to send heartbeats
    private void sendHeartbeats() {
        while (running) {
            try {
                Thread.sleep(5000); // Sleep for 5 seconds
                System.out.println("Sending heartbeat..."); // Placeholder for actual heartbeat logic
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupt status
                System.out.println("Heartbeat thread interrupted");
            }
        }
    }
}
