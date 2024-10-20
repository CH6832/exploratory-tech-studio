// MulticastServer.java
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;



/**
 * public class Main {
    public static void main(String[] args) {
        MulticastServer server = new MulticastServer(12345); // Port number
        new Thread(server::start).start(); // Start the server in a new thread

        // Run for a while, then stop (for demonstration purposes)
        try {
            Thread.sleep(20000); // Let the server run for 20 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        server.stop(); // Stop the server
        System.out.println("Multicast server stopped.");
    }
}
 
*/
public class MulticastServer {

    private MulticastSocket multicastSocket;
    private InetAddress groupAddress;
    private boolean running;

    // Constructor
    public MulticastServer(int port) {
        try {
            multicastSocket = new MulticastSocket(port);
            groupAddress = InetAddress.getByName("239.0.0.1"); // Multicast group
            multicastSocket.joinGroup(groupAddress);
            running = false;
        } catch (IOException e) {
            System.err.println("Failed to create MulticastSocket: " + e.getMessage());
        }
    }

    // Method to start the server
    public void start() {
        running = true;
        System.out.println("Multicast server is listening...");

        // Buffer for receiving messages
        byte[] buffer = new byte[1024];

        while (running) {
            try {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                multicastSocket.receive(packet); // Receive packet from multicast group
                String receivedMessage = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received: " + receivedMessage);
            } catch (IOException e) {
                if (running) {
                    System.err.println("Error receiving packet: " + e.getMessage());
                }
            }
        }
    }

    // Method to stop the server
    public void stop() {
        if (running) {
            running = false;
            try {
                multicastSocket.leaveGroup(groupAddress); // Leave the multicast group
                multicastSocket.close(); // Close the socket
            } catch (IOException e) {
                System.err.println("Failed to leave group or close socket: " + e.getMessage());
            }
        }
    }
}
