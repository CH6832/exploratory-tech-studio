package com.example.bandcsimu;

import java.util.ArrayList;
import java.util.List;

/**
 * The NetworkNode class represents a node in the blockchain network.
 * Each node maintains a list of peer nodes it is connected to and is capable
 * of synchronizing its blockchain with these peers, as well as broadcasting
 * new blocks to them.
 */
public class NetworkNode {
    private String address;  // The IP address of the node
    private int port;        // The port number for communication
    private String nodeName; // The unique name of the node
    private List<Peer> peers;  // List of connected peer nodes (address, port)

    /**
     * Constructor for the NetworkNode class.
     *
     * @param address The IP address of the node.
     * @param port The port number for the node.
     */
    public NetworkNode(String address, int port) {
        this.address = address;
        this.port = port;
        this.nodeName = generateNodeName();  // Generate or assign the node name
        this.peers = new ArrayList<>();       // Initialize the list of peers

        // Start a new thread to handle incoming connections
        new Thread(this::handleIncomingConnections).start();
    }

    /**
     * Connects to a peer node specified by its address and port.
     * This method adds the peer to the list of peers and initiates synchronization.
     *
     * @param peerAddress The address of the peer node to connect to.
     * @param peerPort The port of the peer node to connect to.
     */
    public void connectToPeer(String peerAddress, int peerPort) {
        peers.add(new Peer(peerAddress, peerPort)); // Add peer to the list
        synchronizeBlockchain(); // Synchronize blockchain with the newly connected peer
    }

    /**
     * Simulates the synchronization of the blockchain with connected peers.
     * This method fetches the peer's blockchain and merges it with the local blockchain.
     * Note: Actual synchronization logic should be implemented here.
     */
    private void synchronizeBlockchain() {
        for (Peer peer : peers) {
            // Fetch peer's blockchain and merge (pseudo-code)
            // Implement actual synchronization logic here
            System.out.println("Synchronizing blockchain with peer: " + peer.getAddress() + ":" + peer.getPort());
        }
    }

    /**
     * Broadcasts a newly mined block to all connected peers.
     * Each peer receives a copy of the new block to update their local chains.
     *
     * @param block The block to be sent to the peers.
     */
    public void broadcastNewBlock(Block block) {
        for (Peer peer : peers) {
            sendBlockToPeer(block, peer.getAddress(), peer.getPort()); // Send block to each peer
        }
    }

    /**
     * Simulates the handling of incoming connections to the node.
     * This would typically involve listening for and managing network connections.
     * Note: Actual network communication logic should be implemented here.
     */
    private void handleIncomingConnections() {
        // Implement actual network communication here
        System.out.println("Handling incoming connections at: " + address + ":" + port);
        // Simulate waiting for incoming connections...
    }

    /**
     * Simulates sending a block to a specific peer node.
     * This method would normally include logic for actual network communication.
     *
     * @param block The block to be sent.
     * @param peerAddress The address of the peer receiving the block.
     * @param peerPort The port of the peer receiving the block.
     */
    private void sendBlockToPeer(Block block, String peerAddress, int peerPort) {
        // Implement actual network communication here
        System.out.println("Sending block to peer: " + peerAddress + ":" + peerPort);
    }

    // Getter for the node's address
    public String getAddress() {
        return address; // Return the address of this node
    }

    // Getter for the node's name
    public String getNodeName() {
        return nodeName; // Return the name of this node
    }

    /**
     * Generates a unique name for the node based on its address and port.
     * This can be useful for identifying nodes in the network.
     *
     * @return A string representing the node's unique name.
     */
    private String generateNodeName() {
        // Generate a unique name for the node
        return "Node-" + address + ":" + port; // Format: "Node-IP:Port"
    }

    /**
     * Inner class representing a peer node in the network.
     * Each peer has an address and a port for communication.
     */
    private static class Peer {
        private String address; // The address of the peer
        private int port;       // The port of the peer

        /**
         * Constructor for the Peer class.
         *
         * @param address The address of the peer.
         * @param port The port of the peer.
         */
        public Peer(String address, int port) {
            this.address = address; // Set the peer's address
            this.port = port;       // Set the peer's port
        }

        // Getter for the peer's address
        public String getAddress() {
            return address; // Return the address of the peer
        }

        // Getter for the peer's port
        public int getPort() {
            return port; // Return the port of the peer
        }
    }
}
