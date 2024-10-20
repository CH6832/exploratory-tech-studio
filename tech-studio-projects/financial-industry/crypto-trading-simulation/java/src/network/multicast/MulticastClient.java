// MulticastClient.java
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastClient {

    private MulticastSocket multicastSocket;
    private InetAddress groupAddress;
    private int port;

    // Constructor
    public MulticastClient() {
        multicastSocket = null;
    }

    // Method to join a multicast group
    public boolean joinGroup(String group, int port) {
        this.port = port;
        try {
            // Create a MulticastSocket
            multicastSocket = new MulticastSocket(port);
            groupAddress = InetAddress.getByName(group);
            multicastSocket.joinGroup(groupAddress);
            return true;
        } catch (IOException e) {
            System.err.println("Failed to join multicast group: " + e.getMessage());
            return false;
        }
    }

    // Method to send a message to the multicast group
    public boolean sendMessage(String message) {
        if (multicastSocket == null) {
            System.err.println("Multicast socket is not initialized.");
            return false;
        }
        try {
            byte[] buffer = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, groupAddress, port);
            multicastSocket.send(packet);
            return true;
        } catch (IOException e) {
            System.err.println("Failed to send message: " + e.getMessage());
            return false;
        }
    }

    // Cleanup method to leave the group and close the socket
    public void close() {
        if (multicastSocket != null) {
            try {
                multicastSocket.leaveGroup(groupAddress);
                multicastSocket.close();
            } catch (IOException e) {
                System.err.println("Failed to leave group or close socket: " + e.getMessage());
            }
        }
    }
}
