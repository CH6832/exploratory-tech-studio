import java.net.URI;
import java.net.http.WebSocket;
import java.util.concurrent.CompletionStage;

public class WebSocketClient {

    private WebSocket webSocket;
    private String uri;

    // Constructor
    public WebSocketClient(String uri) {
        this.uri = uri;
    }

    // Method to connect to the WebSocket server
    public void connect() {
        WebSocket.Builder builder = WebSocket.newBuilder();
        builder.uri(URI.create(uri));

        // Set up the WebSocket listener for receiving messages
        webSocket = builder.buildAsync().join();

        // Print connection established
        System.out.println("Connected to WebSocket server: " + uri);
    }

    // Method to send a message to the WebSocket server
    public void send(String message) {
        if (webSocket == null) {
            System.err.println("WebSocket is not connected.");
            return;
        }
        webSocket.sendText(message, true);
        System.out.println("Sent message: " + message);
    }

    // Method to receive messages (this is handled by a listener)
    public void receive() {
        // WebSocket already handles receiving messages through the listener set up during the connection.
        // In this example, we'll use a custom listener to print received messages.
        webSocket.request(1);
    }

    // This method will set up a custom listener to handle incoming messages
    public void setMessageHandler() {
        webSocket = WebSocket.newBuilder()
                .uri(URI.create(uri))
                .buildAsync()
                .thenApply(webSocket -> {
                    webSocket.request(1);
                    webSocket.receive().thenAccept(message -> {
                        System.out.println("Received message: " + message.getPayload());
                        receive(); // Request to receive another message
                    });
                    return webSocket;
                }).join();
    }

    // Method to close the WebSocket connection
    public void close() {
        if (webSocket != null) {
            webSocket.sendClose(WebSocket.NORMAL_CLOSURE, "Closing connection").thenRun(() ->
                    System.out.println("WebSocket closed."));
        }
    }
}
