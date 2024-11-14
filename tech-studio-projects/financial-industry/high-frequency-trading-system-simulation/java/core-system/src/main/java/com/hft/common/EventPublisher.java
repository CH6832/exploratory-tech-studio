package com.hft.common;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Publishes trade events using Java NIO, optimized for high-frequency trading environments.
 */
public class EventPublisher {
    private SocketChannel socketChannel;
    private static final String HOST = "localhost";
    private static final int PORT = 9090;

    /**
     * Initializes the EventPublisher with a low-latency, non-blocking socket connection.
     */
    public EventPublisher() {
        try {
            // Setting up non-blocking socket connection
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress(HOST, PORT));
            while (!socketChannel.finishConnect()) {
                // Wait or handle connection delay (non-blocking)
            }
        } catch (IOException e) {
            System.err.println("Error initializing socket connection: " + e.getMessage());
        }
    }

    /**
     * Publishes the order event with low-latency using non-blocking NIO socket.
     * @param message The message to publish.
     */
    public void publishEvent(String message) {
        try {
            ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
            while (buffer.hasRemaining()) {
                socketChannel.write(buffer); // Non-blocking write
            }
        } catch (IOException e) {
            System.err.println("Error publishing event: " + e.getMessage());
        }
    }

    /**
     * Closes the socket connection when done to release resources.
     */
    public void close() {
        try {
            if (socketChannel != null) {
                socketChannel.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing socket channel: " + e.getMessage());
        }
    }
}
