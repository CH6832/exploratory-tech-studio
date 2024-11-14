public class Main {
    public static void main(String[] args) {
        int port = 12345; // Replace with your desired port
        P2PServer server = new P2PServer(port);

        // Start the server in a new thread to avoid blocking the main thread
        Thread serverThread = new Thread(server::start);
        serverThread.start();

        // Allow the server to run for some time before stopping
        try {
            Thread.sleep(60000); // Run for 60 seconds
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted: " + e.getMessage());
        }

        // Stop the server
        server.stop();
        System.out.println("Server stopped.");
    }
}
