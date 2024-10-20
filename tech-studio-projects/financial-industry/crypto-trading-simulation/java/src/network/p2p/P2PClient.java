import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class P2PClient {

    private Socket socket;

    // Constructor
    public P2PClient() {
        socket = null;
    }

    // Method to connect to a server
    public boolean connect(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            return true;
        } catch (IOException e) {
            System.err.println("Connection failed: " + e.getMessage());
            return false;
        }
    }

    // Method to send a message to the server
    public boolean sendMessage(String message) {
        if (socket == null || socket.isClosed()) {
            System.err.println("Socket is not initialized or is closed.");
            return false;
        }

        try {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(message.getBytes());
            outputStream.flush();
            return true;
        } catch (IOException e) {
            System.err.println("Failed to send message: " + e.getMessage());
            return false;
        }
    }

    // Method to receive a message from the server
    public String receiveMessage() {
        if (socket == null || socket.isClosed()) {
            System.err.println("Socket is not initialized or is closed.");
            return "";
        }

        try {
            InputStream inputStream = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);
            return bytesRead > 0 ? new String(buffer, 0, bytesRead) : "";
        } catch (IOException e) {
            System.err.println("Failed to receive message: " + e.getMessage());
            return "";
        }
    }

    // Cleanup method to close the socket
    public void close() {
        if (socket != null && !socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Failed to close socket: " + e.getMessage());
            }
        }
    }
}
