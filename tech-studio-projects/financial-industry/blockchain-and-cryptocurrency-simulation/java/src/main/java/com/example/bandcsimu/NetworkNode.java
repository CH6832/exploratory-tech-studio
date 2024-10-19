package com.example.bandcsimu;

import java.util.ArrayList;
import java.util.List;

public class NetworkNode {
    private String address;
    private int port;
    private String nodeName;
    private List<Peer> peers;  // List of peer nodes (address, port)

    // Constructor
    public NetworkNode(String address, int port) {
        this.address = address;
        this.port = port;
        this.nodeName = generateNodeName();  // Generate or assign the node name
        this.peers = new ArrayList<>();
        // Start a new thread to handle incoming connections
        new Thread(this::handleIncomingConnections).start();
    }

    // Connect to a peer node by address and port
    public void connectToPeer(String peerAddress, int peerPort) {
        peers.add(new Peer(peerAddress, peerPort));
        synchronizeBlockchain();
    }

    // Simulate synchronizing the blockchain with peers
    private void synchronizeBlockchain() {
        for (Peer peer : peers) {
            // Fetch peer's blockchain and merge (pseudo-code)
            // Implement actual synchronization logic here
            System.out.println("Synchronizing blockchain with peer: " + peer.getAddress() + ":" + peer.getPort());
        }
    }

    // Broadcast a new block to all peers
    public void broadcastNewBlock(Block block) {
        for (Peer peer : peers) {
            sendBlockToPeer(block, peer.getAddress(), peer.getPort());
        }
    }

    // Simulate handling incoming connections
    private void handleIncomingConnections() {
        // Implement actual network communication here
        System.out.println("Handling incoming connections at: " + address + ":" + port);
        // Simulate waiting for incoming connections...
    }

    // Simulate sending a block to a peer
    private void sendBlockToPeer(Block block, String peerAddress, int peerPort) {
        // Implement actual network communication here
        System.out.println("Sending block to peer: " + peerAddress + ":" + peerPort);
    }

    // Getter for address
    public String getAddress() {
        return address;
    }

    // Getter for node name
    public String getNodeName() {
        return nodeName;
    }

    // Helper method to generate a node name
    private String generateNodeName() {
        // Here, we can generate a unique name for the node, e.g., based on the address or port
        return "Node-" + address + ":" + port;
    }

    // Inner class to represent a Peer (address and port)
    private static class Peer {
        private String address;
        private int port;

        public Peer(String address, int port) {
            this.address = address;
            this.port = port;
        }

        public String getAddress() {
            return address;
        }

        public int getPort() {
            return port;
        }
    }
}
