// TcpClient.java
public class TcpClient {
    private String ip;
    private int port;

    // Constructor
    public TcpClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
        System.out.println("TcpClient created for IP: " + ip + " Port: " + port);
    }

    // Method to connect to the TCP server
    public void connect() {
        System.out.println("Connecting to TCP server...");
        // Implement connection logic here
    }

    // Method to send a message
    public void send(String message) {
        System.out.println("Sending message: " + message);
        // Implement sending logic here
    }

    // Method to close the TCP connection
    public void close() {
        System.out.println("Closing TCP connection...");
        // Implement close logic here
    }
}
