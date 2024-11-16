package com.system.simulation.network.packets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PacketHandler {

    private static final Logger logger = LoggerFactory.getLogger(PacketHandler.class);

    /**
     * Simulates the handling of incoming packets.
     *
     * @param packet The packet to be processed.
     */
    public void handlePacket(Object packet) {
        try {
            if (packet instanceof TcpPacket) {
                processTcpPacket((TcpPacket) packet);
            } else if (packet instanceof UdpPacket) {
                processUdpPacket((UdpPacket) packet);
            } else {
                logger.warn("Unknown packet type received.");
            }
        } catch (Exception e) {
            logger.error("Error handling packet: {}", e.getMessage());
        }
    }

    /**
     * Processes a TCP packet.
     *
     * @param tcpPacket The TCP packet to process.
     */
    private void processTcpPacket(TcpPacket tcpPacket) {
        logger.info("Processing TCP packet: {}", tcpPacket);
        // Simulate packet processing (e.g., checksum verification)
    }

    /**
     * Processes a UDP packet.
     *
     * @param udpPacket The UDP packet to process.
     */
    private void processUdpPacket(UdpPacket udpPacket) {
        logger.info("Processing UDP packet: {}", udpPacket);
        // Simulate packet processing (e.g., checksum verification)
    }
}
